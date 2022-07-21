package com.ayeshj.allion.repository;

import com.ayeshj.allion.model.ConsultationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.List;

/**
 * Consultation CRUD Repository
 *
 * @author Ayesh Jayasekara
 */
@SuppressWarnings({"RedundantSuppression", "unused"})
@RepositoryRestResource(collectionResourceRel = "consultation", path = "consultation")
// Expose as REST Endpoint for brevity
public interface ConsultationRepository extends CrudRepository<ConsultationEntity, Integer> {

    @PostAuthorize("hasPermission(returnObject,'CONSULTATION_VIEW')")
    List<ConsultationEntity> findAllByDoctorId(int doctorID);
}
