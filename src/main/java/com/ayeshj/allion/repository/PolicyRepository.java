package com.ayeshj.allion.repository;

import com.ayeshj.allion.model.PolicyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Policy CRUD Repository
 *
 * @author Ayesh Jayasekara
 */
@Repository
public interface PolicyRepository extends CrudRepository<PolicyEntity, Integer> {

    @Override
    List<PolicyEntity> findAll();


}
