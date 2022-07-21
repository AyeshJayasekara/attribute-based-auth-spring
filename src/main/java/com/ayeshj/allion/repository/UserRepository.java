package com.ayeshj.allion.repository;

import com.ayeshj.allion.model.AppUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

/**
 * User CRUD Repository
 *
 * @author Ayesh Jayasekara
 */
@SuppressWarnings({"RedundantSuppression", "unused"})
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<AppUserEntity, Integer> {

    @RestResource(exported = false)
    Optional<AppUserEntity> findFirstByUsername(String username);

    @PostAuthorize("hasPermission(returnObject,'PROFILE_VIEW')")
    Optional<AppUserEntity> findTopByUsername(String username);
}
