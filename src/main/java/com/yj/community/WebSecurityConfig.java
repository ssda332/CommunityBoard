package com.yj.community;

import com.yj.community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // 6
                .antMatchers("/members/login", "/members/add", "/", "/board/list", "/board/search").permitAll() // ????????? ?????? ??????
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN??? ?????? ?????? USER??? USER,ADMIN??? ?????? ??????
                .anyRequest().authenticated() // ????????? ???????????? ????????? ????????? ?????? ?????? ????????? ????????? ?????? ??????
                .and()

                .formLogin() // 7
                .loginPage("/members/login") // ????????? ????????? ??????
                .loginProcessingUrl("/members/login")
                .defaultSuccessUrl("/") // ????????? ?????? ??? ??????????????? ??????
                .failureUrl("/members/login?error=true")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()

                .logout() // 8
                .logoutUrl("/members/logout")
                .logoutSuccessUrl("/") // ???????????? ????????? ??????????????? ??????
                .invalidateHttpSession(true) // ?????? ?????????
                .and()
                .csrf().disable()
        ;
        http.authenticationProvider(authenticationProvider);
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

}
