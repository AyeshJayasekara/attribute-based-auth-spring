package com.ayeshj.allion.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.persistence.*;
import java.util.Objects;

/**
 * Policy Entity
 *
 * @author Ayesh Jayasekara
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "policy", schema = "allion")
public class PolicyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "target")
    private String target;
    @Basic
    @Column(name = "condition")
    private String condition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PolicyEntity that = (PolicyEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Expression resolveTarget() {
        ExpressionParser elParser = new SpelExpressionParser();
        return elParser.parseExpression(target);
    }

    public Expression resolveCondition() {
        ExpressionParser elParser = new SpelExpressionParser();
        return elParser.parseExpression(condition);
    }
}
