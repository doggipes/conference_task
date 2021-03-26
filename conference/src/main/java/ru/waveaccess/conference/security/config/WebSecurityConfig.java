package ru.waveaccess.conference.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.waveaccess.conference.model.enums.Role;
import ru.waveaccess.conference.security.details.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    @Qualifier(value = "customUserDetailsService")
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/signUp", "/login", "/h2-console/**").permitAll()
                .antMatchers("/presentation", "/schedule").hasAnyAuthority(Role.PRESENTER.name(), Role.ADMIN.name())
                .antMatchers("/", "/join/**").authenticated();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .permitAll();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID", "remember-me", "SESSION")
                .invalidateHttpSession(true);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

    private final DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
