/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.sp.health.bean;

import gov.sp.health.data.AreaType;
import gov.sp.health.data.InstitutionType;
import gov.sp.health.data.Sex;
import gov.sp.health.data.StaffRole;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author pdhs
 */
@Named(value = "enumController")
@ApplicationScoped
public class EnumController {
    
    
    public EnumController() {
    
    }
    
    public Sex[] getSexValues(){
        return Sex.values();
    }
    
    public InstitutionType[] getInstitutionTypes(){
        return InstitutionType.values();
    }
    
    public AreaType[] getAreaTypes(){
        return AreaType.values();
    }
    
    
    public StaffRole[] getStaffRoles(){
        return StaffRole.values();
    }
}
