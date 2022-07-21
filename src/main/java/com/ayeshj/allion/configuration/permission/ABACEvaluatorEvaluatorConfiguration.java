package com.ayeshj.allion.configuration.permission;

import com.ayeshj.allion.policy.PolicyEnforcementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Permission Evaluator Class for custom permission evaluation
 *
 * @author Ayesh Jayasekara
 */
@Configuration
public class ABACEvaluatorEvaluatorConfiguration implements PermissionEvaluator {

    private final PolicyEnforcementService enforcementService;

    /**
     * Constructor for Dependency Injection
     *
     * @param enforcementService Enforcement Service DI
     */
    @Autowired
    public ABACEvaluatorEvaluatorConfiguration(PolicyEnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    /**
     * Evaluate Permission
     *
     * @param authentication     Authenticated subject
     * @param targetDomainObject Object being accessed in the request
     * @param permission         Permission in action
     * @return True if has access false otherwise
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return enforcementService.check(authentication.getPrincipal(), targetDomainObject, permission, new HashMap<>());
    }


    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException(); // Method not supported
    }
}
