package com.ayeshj.allion.policy;

import com.ayeshj.allion.model.AppUserEntity;
import com.ayeshj.allion.model.PolicyEntity;
import com.ayeshj.allion.policy.model.SecurityAccessContextModel;
import com.ayeshj.allion.repository.PolicyRepository;
import com.ayeshj.allion.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Policy Enforcement Service
 *
 * @author Ayesh Jayasekara
 */
@Service
@Slf4j
public class PolicyEnforcementService {

    private final List<PolicyEntity> policyModelList;
    private final UserRepository userRepository;

    /**
     * Constructor for DI
     *
     * @param policyRepository Policy Repo DI
     * @param userRepository   User Repo DI
     */
    @Autowired
    public PolicyEnforcementService(PolicyRepository policyRepository, UserRepository userRepository) {
        this.policyModelList = policyRepository.findAll();
        this.userRepository = userRepository;
    }

    /**
     * Evaluates the attribute and the permissions
     *
     * @param subject     Authorized user
     * @param resource    Object being accessed
     * @param action      Performing action (VIEW / UPDATE / SAVE / DELETE)
     * @param environment Additional env param (not utilized)
     * @return True if has access false otherwise
     */
    public boolean check(Object subject, Object resource, Object action, Object environment) {

        int userIdOfSubject = findUserIdOfSubject((User) subject); // Resolve user ID from username

        Optional<PolicyEntity> hasPermission = policyModelList.stream()
                .filter(policy -> policy.resolveTarget() // Filter the applicable targets
                        .getValue(new SecurityAccessContextModel(userIdOfSubject, resource, action, environment),
                                Boolean.class))
                .filter(policy -> policy.resolveCondition() // Filter matching attribute conditions
                        .getValue(new SecurityAccessContextModel(userIdOfSubject, resource, action, environment),
                                Boolean.class))
                .findFirst(); // Stop evaluating matching conditions as soon as one attribute permission is found to be value; user has access no need to evaluate further


        return hasPermission.isPresent();
    }

    /**
     * Fetch user id for username
     *
     * @param userPrincipal authenticated user principal
     * @return User ID if the authenticated user, -1 if there is a problem
     */
    private int findUserIdOfSubject(User userPrincipal) {
        Optional<AppUserEntity> user = userRepository.findFirstByUsername(userPrincipal.getUsername());
        return user.map(AppUserEntity::getId).orElse(-1);
    }

}
