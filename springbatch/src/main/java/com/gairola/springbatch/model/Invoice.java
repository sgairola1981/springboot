package com.gairola.springbatch.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor   
@AllArgsConstructor
public class Invoice {

   @Id
   @GeneratedValue
   private Long id;
   private String name;
   private String number;
   private Double amount;
   private Double discount;
   private Double finalAmount;
   private String location;
}