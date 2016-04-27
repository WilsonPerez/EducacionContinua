/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idi.ucuenca.controller.util;

import javax.persistence.Persistence;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 */
public final class EntityManagerUtil {
    private static final javax.persistence.EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("edu.ucuenca_mavenproject2_war_1.0-SNAPSHOTPU");

    private EntityManagerUtil() {}

    public static javax.persistence.EntityManagerFactory get() {
        return emfInstance;
    }
}