/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.sp.health.facade;

import gov.sp.health.entity.CkdRecord;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pdhs
 */
@Stateless
public class CkdRecordFacade extends AbstractFacade<CkdRecord> {
    @PersistenceContext(unitName = "phgisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CkdRecordFacade() {
        super(CkdRecord.class);
    }
    
}
