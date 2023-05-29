//package com.petrov.shop.security;
//
//import com.petrov.shop.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class SecurityConfiguration {

//    @Autowired
//    private UserAuthService userAuthService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userAuthService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//    @Configuration
//    public static class UiSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity
//                    .authorizeRequests()
//                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
//                    .antMatchers("/models/**").permitAll()
//                    .antMatchers("/users/**").hasRole("ADMIN")
//                    .antMatchers("/new_user").permitAll()
//                    .antMatchers("/access_denied").authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/sign_in")
//                    .defaultSuccessUrl("/users")
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedPage("/access_denied");
//        }
//    }

//    @Autowired
//    public void authConfig(AuthenticationManagerBuilder authBuilder, UserAuthService userService) throws Exception {
//        authBuilder.userDetailsService(userService);
//    }
//
//    @Configuration
//    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity
//                    .authorizeRequests()
//                    .anyRequest().permitAll()
//                    .and()
//                    .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/")
//                    .and()
//                    .httpBasic()
//                    .authenticationEntryPoint((req, resp, exception) -> {
//                        resp.setContentType("application/json");
//                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        resp.setCharacterEncoding("UTF-8");
//                        resp.getWriter().println("{ \"error\": \"" + exception.getMessage() + "\" }");
//                    })
//                    .and()
//                    .csrf();
//        }
//    }
//}
