/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.sp.health.facade;

import gov.sp.health.entity.RecordOccupation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pdhs
 */
@Stateless
public class RecordOccupationFacade extends AbstractFacade<RecordOccupation> {
    @PersistenceContext(unitName = "phgisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecordOccupationFacade() {
        super(RecordOccupation.class);
    }
    
}
