package com.example.shopjava6.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.shopjava6.entity.Account;
import com.example.shopjava6.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder pe;

    @Autowired
    AccountService accountService;

    //Encode password
    @Bean
    public BCryptPasswordEncoder getPassEncoder() {

        return new BCryptPasswordEncoder();
    }

    //Authentication Manager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception   {
        auth.userDetailsService( username -> {
            try {
                Account acc = accountService.findById(username);

                //create user detail
                String password = acc.getPassword();
                String[] roles = acc.getAuthorities().stream()
                        .map(er -> er.getRole().getId())
                        .collect(Collectors.toList()).toArray(new String[0]);

                return User.withUsername(username)
                        .password(pe.encode(password))
                        .roles(roles).build();
            } catch (UsernameNotFoundException e) {
                throw new UsernameNotFoundException(username+" not found");
            }
        });
    }


    //Authorization and login method interface
    @Override
    protected void configure(HttpSecurity http) throws Exception  {
        //CSRF,CORS
        http.csrf().disable().cors().disable();

        //authorization
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
                .antMatchers("/admins/**").hasAnyRole("STAF","DIRE")
                .antMatchers("/rest/authorities").hasRole("DIRE")
                .anyRequest().permitAll();

        //login method interface
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success",false)
                .failureUrl("/security/login/error")
                .usernameParameter("username");

        http.rememberMe()
                .tokenValiditySeconds(86400);

        //access dinied router
        http.exceptionHandling()
                .accessDeniedPage("/auth/access/denied");
        //logout
        http.logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success");
    }

    //Cho phép truy xuất REST API từ bên ngoài(domain khác)
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }


}
