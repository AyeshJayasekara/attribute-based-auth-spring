package com.ayeshj.allion.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Consultation Entity
 *
 * @author Ayesh Jayasekara
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "consultation", schema = "allion")
public class ConsultationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "diagnosis")
    private String diagnosis;
    @Basic
    @Column(name = "blood_pressure")
    private String bloodPressure;
    @Basic
    @Column(name = "doctor_id")
    private int doctorId;
    @Basic
    @Column(name = "patient_id")
    private Integer patientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConsultationEntity that = (ConsultationEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
