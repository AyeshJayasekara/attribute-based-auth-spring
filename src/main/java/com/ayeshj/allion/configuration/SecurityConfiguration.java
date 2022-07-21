package com.ayeshj.allion.configuration;

import com.ayeshj.allion.configuration.permission.ABACEvaluatorEvaluatorConfiguration;
import com.ayeshj.allion.model.AppUserEntity;
import com.ayeshj.allion.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Main security configuration class
 *
 * @author Ayesh Jayasekara
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ayeshj.allion"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    private final UserRepository userRepository;
    private final ABACEvaluatorEvaluatorConfiguration evaluatorConfiguration;

    /**
     * Constructor autowiring
     *
     * @param userRepository         User repository DI
     * @param evaluatorConfiguration Permission evaluator config DI
     */
    @Autowired
    public SecurityConfiguration(UserRepository userRepository, ABACEvaluatorEvaluatorConfiguration evaluatorConfiguration) {
        this.userRepository = userRepository;
        this.evaluatorConfiguration = evaluatorConfiguration;
    }

    /**
     * Password encoding bean
     *
     * @return BCrypt encoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info("Use Temp Password : {} - {}", "admin", passwordEncoder.encode("admin"));
        return passwordEncoder;
    }

    /**
     * Filter chain for new security implementation
     *
     * @param http HTTP Context being configured
     * @return Filter chain configured
     * @throws Exception for any config conflicts
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Custom user detail service
     *
     * @return User Details Service
     */
    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            Optional<AppUserEntity> optionalAppUser = userRepository.findFirstByUsername(username);

            if (optionalAppUser.isPresent()) {
                AppUserEntity appUserEntity = optionalAppUser.get();
                return new User(appUserEntity.getUsername(), appUserEntity.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException(username);
            }

        };
    }

    /**
     * Method security configurer method
     *
     * @return Expression handler for pre post authorization annotation
     */
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler result = new DefaultMethodSecurityExpressionHandler();
        result.setPermissionEvaluator(evaluatorConfiguration);
        return result;
    }
}
