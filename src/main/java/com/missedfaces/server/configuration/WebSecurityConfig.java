package com.missedfaces.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  private DataSource dataSource;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select username, password, enabled from users where username=?")
        .authoritiesByUsernameQuery(
            "select username, authority from authorities where username=?");
  }

  @Configuration
  public static class ApiSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .csrf().disable()
          .headers().frameOptions().disable()
          .and()

          .formLogin()
          .loginPage("/entrar")
          .defaultSuccessUrl("/home")
          .permitAll()
          .and()

          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/entrar")
          .invalidateHttpSession(true)
          .permitAll()
          .and()

          .authorizeRequests()
          .antMatchers("/api/public/**", "/static/**", "/", "/cadastrarse").permitAll()
          .and()

          .authorizeRequests()
          .anyRequest().authenticated();
    }
  }

//  @Configuration
//  @Order(1)
//  public static class ApiSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http
//          .antMatcher("/api/**")
//          .authorizeRequests()
//          .anyRequest()
//          .hasRole("DETECTOR")
//          .and()
//          .httpBasic();
//    }
//  }
//
//  @Configuration
//  @Order(2)
//  public static class H2ConsoleSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http
//          .antMatcher("/h2-console/**")
//          .csrf().disable()
//          .headers().frameOptions().disable()
//          .and()
//          .authorizeRequests()
//          .anyRequest()
//          .permitAll()
//          .and();
//    }
//  }
//
//  @Configuration
//  public static class WebSiteSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http
//          .authorizeRequests()
//          .anyRequest().authenticated()
//          .and()
//
//          .formLogin()
//          .loginPage("/login")
//          .permitAll()
//          .and()
//
//          .logout()
//          .logoutUrl("/logout")
//          .logoutSuccessUrl("/login")
//          .invalidateHttpSession(true)
//          .permitAll();
//    }
//  }
}
