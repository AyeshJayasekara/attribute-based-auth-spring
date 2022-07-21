package com.ayeshj.allion.policy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Access Control Context Model for unification of attribute names
 *
 * @author Ayesh Jayasekara
 */
@Data
@AllArgsConstructor
public class SecurityAccessContextModel {
    private Object subject;
    private Object resource;
    private Object action;
    private Object environment;
}
