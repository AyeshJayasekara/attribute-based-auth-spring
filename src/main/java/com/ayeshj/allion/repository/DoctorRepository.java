package com.ayeshj.allion.repository;

import com.ayeshj.allion.model.DoctorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Doctor CRUD Repository
 *
 * @author Ayesh Jayasekara
 */
@RepositoryRestResource(collectionResourceRel = "doctor", path = "doctor")
public interface DoctorRepository extends CrudRepository<DoctorEntity, Integer> {
}
