package com.ayeshj.allion.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Spring Data REST global configuration class
 *
 * @author Ayesh Jayasekara
 */
@Configuration
public class SpringRestConfiguration implements RepositoryRestConfigurer {

    /**
     * Method to configure Spring DATA REST
     *
     * @param config Repo config object
     * @param cors   CORS registry
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.setReturnBodyOnCreate(true);
        config.setReturnBodyOnUpdate(true);
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
    }
}
