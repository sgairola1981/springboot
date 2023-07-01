package com.gairola.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.gairola.springbatch.model.Invoice;
import com.gairola.springbatch.repository.InvoiceRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Invoice, Invoice>chunk(5)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FlatFileItemReader<Invoice> reader() {
        // Create reader instance
        FlatFileItemReader<Invoice> reader = new FlatFileItemReader<Invoice>();

        // Set input file location
        reader.setResource(new FileSystemResource("csv/invoice.csv"));

        // Set number of lines to skips. Use it if file has header rows.
        reader.setLinesToSkip(1);

        // Configure how each line will be parsed and mapped to different values
        reader.setLineMapper(new DefaultLineMapper() {
            {
                // 3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {

                        setNames(new String[] { "name", "number", "amount", "discount", "location" });
                    }
                });
                // Set values in Employee class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Invoice>() {
                    {
                        setTargetType(Invoice.class);
                    }
                });
            }
        });
        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());
        return reader;
    }

    /*
     * @Bean
     * public ConsoleItemWriter<Invoice> writer() {
     * return new ConsoleItemWriter<Invoice>();
     * }
     */
    // Autowire InvoiceRepository
    @Autowired
    InvoiceRepository repository;

    // Writer class Object
    @Bean
    public ItemWriter<Invoice> writer() {
        // return new InvoiceItemWriter(); // Using lambda expression code instead of a
        // separate implementation
        return invoices -> {
            System.out.println("Saving Invoice Records: " + invoices);
            repository.saveAll(invoices);
        };
    }

    // Processor class Object
    @Bean
    public ItemProcessor<Invoice, Invoice> processor() {
        // return new InvoiceProcessor(); // Using lambda expression code instead of a
        // separate implementation
        return invoice -> {
            Double discount = invoice.getAmount() * (invoice.getDiscount() / 100.0);
            Double finalAmount = invoice.getAmount() - discount;
            invoice.setFinalAmount(finalAmount);
            return invoice;
        };
    }

    // Listener class Object
    @Bean
    public JobExecutionListener listener() {
        return new InvoiceListener();
    }

    // Autowire StepBuilderFactory
    @Autowired
    private StepBuilderFactory sbf;

    // Step Object
    @Bean
    public Step stepA() {
        return sbf.get("stepA")
                .<Invoice, Invoice>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    // Autowire JobBuilderFactory
    @Autowired
    private JobBuilderFactory jbf;

    // Job Object
    @Bean
    public Job jobA() {
        return jbf.get("jobA")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(stepA())
                // .next(stepB())
                // .next(stepC())
                .build();
    }

}
