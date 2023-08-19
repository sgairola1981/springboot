package com.gairola.gairolaapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurity {

        @Autowired
        private AccessDeniedHandler accessDeniedHandler;
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public static PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return (web) -> web.ignoring()
                                .requestMatchers("/resources/**");
        }

        @Bean
        public nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect layoutDialect() {
                return new nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable()
                                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/main/register/**")
                                                .permitAll()
                                                .requestMatchers("/main/index").permitAll()
                                                .requestMatchers("/main/service").permitAll()
                                                .requestMatchers("/main/home").permitAll()
                                                .requestMatchers("/data/**").permitAll()
                                                // .requestMatchers("/data/**").hasRole("USER")
                                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                                .requestMatchers("/main/users").hasRole("ADMIN")
                                                .requestMatchers("/", "/js/**", "/css/**", "/img/**", "/webjars/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(
                                                form -> form
                                                                .loginPage("/main/login")
                                                                .loginProcessingUrl("/main/login")
                                                                .defaultSuccessUrl("/main/users")
                                                                .permitAll())
                                .logout(
                                                logout -> logout
                                                                .logoutRequestMatcher(
                                                                                new AntPathRequestMatcher(
                                                                                                "/main/logout"))
                                                                .permitAll()
                                // .and()
                                // .exceptionHandling()
                                // .accessDeniedHandler(accessDeniedHandler)
                                );

                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth
                                .userDetailsService(userDetailsService)
                                .passwordEncoder(passwordEncoder());
        }

}
