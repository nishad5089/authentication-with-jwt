package com.commonauthmodule.commonauthmodule.config;

import com.commonauthmodule.commonauthmodule.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    public SpringSecurityConfiguration(UserDetailsServiceImpl userDetailsService, CustomJwtAuthenticationFilter customJwtAuthenticationFilter, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.userDetailsService = userDetailsService;
        this.customJwtAuthenticationFilter = customJwtAuthenticationFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public CustomJwtAuthenticationFilter authenticationJwtTokenFilter() {
        return new CustomJwtAuthenticationFilter();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/helloadmin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("/authenticate").permitAll().anyRequest().authenticated()
                //if any exception occurs call this
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
        http.addFilterBefore(customJwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

    }


}
