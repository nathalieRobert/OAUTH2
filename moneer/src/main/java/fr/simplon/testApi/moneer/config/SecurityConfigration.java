package fr.simplon.testApi.moneer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
*/import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter {
	
	@Bean
    @Override
    public UserDetailsService userDetailsService () {
    UserDetails user = User.builder (). username ("user").password("secret")
    .roles ("USER"). build ();
    UserDetails userAdmin = User.builder (). username ("admin").password("secret")
    .roles ("ADMIN"). build ();
        return new InMemoryUserDetailsManager (user, userAdmin);
    }
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
