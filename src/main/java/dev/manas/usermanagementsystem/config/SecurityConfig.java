package dev.manas.usermanagementsystem.config;

import dev.manas.usermanagementsystem.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpBasicDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
            return http
                .csrf(customizer->customizer.disable())
                .authorizeHttpRequests(request->request.requestMatchers("signup","login").permitAll()
                        .anyRequest().authenticated()).
                  httpBasic(Customizer.withDefaults()).
                  sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build(); //create a new session object everytime we send the request from client to server , no need to worry about session id.
        //To access apis from postman without login form
        //http.formLogin(Customizer.withDefaults());//creating customized login form to access apis using browser
        //httpBasic(Customizer.withDefaults());//To access apis from postman without login form


    }
    @Bean
    public UserDetailsService userDetailsService()throws Exception
    {

        UserDetails user1 = User.withDefaultPasswordEncoder().username("manas").password("password").build();

        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }



}
