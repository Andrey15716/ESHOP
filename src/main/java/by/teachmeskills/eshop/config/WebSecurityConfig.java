package by.teachmeskills.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static by.teachmeskills.eshop.utils.EshopConstants.ROLE_ADMIN;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = "by")
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz

                                        .antMatchers("/webapp/WEB-INF/**", "/")
                                        .permitAll()
                                        .antMatchers("/login/profile")
                                        .authenticated()
                                        .antMatchers("/cart/buy")
                                        .authenticated()
                                        .antMatchers("/home/admin")
                                        .hasRole(ROLE_ADMIN)
                                        .and()
                                        .formLogin()
                                        .loginPage("/login")
                                        .usernameParameter("name")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/home")
                                        .permitAll()
                                        .and()
                                        .rememberMe()
                                        .alwaysRemember(true)
                                        .and()
                                        .logout()
                                        .invalidateHttpSession(true)
                                        .clearAuthentication(true)
                                        .permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}