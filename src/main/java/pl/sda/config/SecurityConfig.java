//package pl.sda.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//}


package pl.sda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("{noop}dba").roles("DBA");

        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.username, u.password_hash,1 FROM user u WHERE u.username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.role_name, 1 " +
                        "FROM user u " +
                        "INNER JOIN user_role ur ON ur.user_id = u.user_id " +
                        "INNER JOIN role r ON r.role_id = ur.role_id " +
                        "WHERE u.username=?")
//                .passwordEncoder(passwordEncoder()) //////// dodane przeze mnie
                .dataSource(dataSource);
    }


//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin@admin.pl")
//                .password(bCryptPasswordEncoder.encode("admin12345"))
//                .roles("ADMIN");

        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.username, u.password_hash,1 FROM user u WHERE u.username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.role_name, 1 " +
                        "FROM user u " +
                        "INNER JOIN user_role ur ON ur.user_id = u.user_id " +
                        "INNER JOIN role r ON r.role_id = ur.role_id " +
                        "WHERE u.username=?")
                .dataSource(dataSource);
//                .passwordEncoder(bCryptPasswordEncoder);
    }
*/


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")  //pierwotna wersja
                .antMatchers("/admin/**").access("hasRole('ADMIN')")  //zmienione przeze mnie
                .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .antMatchers("/login*").permitAll()
                .antMatchers("/header*").permitAll()
                .antMatchers("/register*").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("username")
                .passwordParameter("pass")
                .defaultSuccessUrl("/index", true)
//                .failureUrl("/error");
        ;

        // .failureHandler(authenticationFailureHandler())

        http.csrf().disable()
                .headers().frameOptions().disable();




        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
        //  .logoutSuccessHandler(logoutSuccessHandler())
        ;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


