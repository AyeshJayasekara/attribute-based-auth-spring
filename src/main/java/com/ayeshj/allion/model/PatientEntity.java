package com.ayeshj.allion.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Patient Entity
 *
 * @author Ayesh Jayasekara
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "patient", schema = "allion")
public class PatientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Basic
    @Column(name = "contact")
    private String contact;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PatientEntity that = (PatientEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
