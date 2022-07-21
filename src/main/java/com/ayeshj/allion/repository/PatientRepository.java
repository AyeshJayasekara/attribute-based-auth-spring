package com.ayeshj.allion.repository;

import com.ayeshj.allion.model.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Patient CRUD Repository
 *
 * @author Ayesh Jayasekara
 */
@RepositoryRestResource(collectionResourceRel = "patient", path = "patient")
public interface PatientRepository extends CrudRepository<PatientEntity, Integer> {

}
