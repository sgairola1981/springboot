package com.gairola.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gairola.jwt.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public JwtRequestFilter jwtAuthenticationFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/authenticate").permitAll()
                        // .antMatchers("/api/v1/auth/**").permitAll()
                        // .antMatchers("/v2/api-docs/**").permitAll()
                        // .antMatchers("/swagger-ui/**").permitAll()
                        // .antMatchers("/swagger-resources/**").permitAll()
                        // .antMatchers("/swagger-ui.html").permitAll()
                        // .antMatchers("/webjars/**").permitAll()
                        .anyRequest()
                        .authenticated());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http
    // .csrf().disable()
    // .exceptionHandling()
    // .authenticationEntryPoint(authenticationEntryPoint)
    // .and()
    // .sessionManagement()
    // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // .and()
    // .authorizeRequests()
    // .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
    // .antMatchers("/api/v1/auth/**").permitAll()
    // .antMatchers("/v2/api-docs/**").permitAll()
    // .antMatchers("/swagger-ui/**").permitAll()
    // .antMatchers("/swagger-resources/**").permitAll()
    // .antMatchers("/swagger-ui.html").permitAll()
    // .antMatchers("/webjars/**").permitAll()
    // .anyRequest()
    // .authenticated();
    // http.addFilterBefore(jwtAuthenticationFilter(),
    // UsernamePasswordAuthenticationFilter.class);
    // }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Override
    // @Bean
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    // return super.authenticationManagerBean();
    // }

    // @Override
    // @Bean
    // protected UserDetailsService userDetailsService() {
    // UserDetails ramesh =
    // User.builder().username("ramesh").password(passwordEncoder()
    // .encode("password")).roles("USER").build();
    // UserDetails admin =
    // User.builder().username("admin").password(passwordEncoder()
    // .encode("admin")).roles("ADMIN").build();
    // return new InMemoryUserDetailsManager(ramesh, admin);
    // }
}