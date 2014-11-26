/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.edcontinua.farcade;

import edu.ucuenca.edcontinua.entities.DirigidoA;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilson
 */
@Stateless
public class DirigidoAFacade extends AbstractFacade<DirigidoA> {
    @PersistenceContext(unitName = "edu.ucuenca_mavenproject2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DirigidoAFacade() {
        super(DirigidoA.class);
    }
    
}
