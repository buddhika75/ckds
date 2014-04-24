package gov.sp.health.bean;

import gov.sp.health.entity.Area;
import gov.sp.health.entity.CkdRecord;
import gov.sp.health.entity.Diagnosis;
import gov.sp.health.entity.Institution;
import gov.sp.health.entity.Occupation;
import gov.sp.health.entity.Person;
import gov.sp.health.facade.CkdRecordFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CkdRecordController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    SessionController sessionController;
    @EJB
    private CkdRecordFacade ejbFacade;
    List<CkdRecord> selectedItems;
    private CkdRecord current;
    private List<CkdRecord> items = null;
    String selectText = "";
    Institution institution;
    Date recordDate;
    Area rdhsArea;
    Area mohArea;
    Area phiArea;
    Occupation occupation;
    Diagnosis diagnosis;
    Diagnosis coormobidity;

    public void addOccupation() {
        if (current == null) {
            return;
        }
        if (occupation == null) {
            return;
        }
        if (current.getOccupations() == null) {
            current.setOccupations(new ArrayList<Occupation>());
        }
        current.getOccupations().add(occupation);
        occupation = null;
        UtilityController.addSuccessMessage("Occupation Added");
    }

    public void addComorbidities() {
        if (current == null) {
            return;
        }
        if (coormobidity == null) {
            return;
        }
        if (current.getComorbidities() == null) {
            current.setComorbidities(new ArrayList<Diagnosis>());
        }
        current.getComorbidities().add(coormobidity);
        coormobidity = null;
        UtilityController.addSuccessMessage("Coormorbidity Added");
    }

                                                         

    
    public List<CkdRecord> getSelectedItems() {
        selectedItems = getFacade().findBySQL("select c from CkdRecord c where c.retired=false and upper(c.name) like '%" + getSelectText().toUpperCase() + "%' order by c.name");
        return selectedItems;
    }

    public List<CkdRecord> completeCkdRecord(String qry) {
        List<CkdRecord> a = null;
        if (qry != null) {
            a = getFacade().findBySQL("select c from CkdRecord c where c.retired=false and upper(c.name) like '%" + qry.toUpperCase() + "%' order by c.name");
        }
        if (a == null) {
            a = new ArrayList<CkdRecord>();
        }
        return a;
    }

    public void prepareAdd() {
        current = new CkdRecord();
    }

    public void setSelectedItems(List<CkdRecord> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getSelectText() {
        return selectText;
    }

    private void recreateModel() {
        items = null;
    }

    public void saveSelected() {

        if (getCurrent().getId() != null && getCurrent().getId() > 0) {
            getFacade().edit(current);
            UtilityController.addSuccessMessage("updated Successfully");
        } else {
            current.setCreatedAt(Calendar.getInstance(TimeZone.getTimeZone("IST")).getTime());
            current.setCreater(sessionController.getLoggedUser());
            getFacade().create(current);
            UtilityController.addSuccessMessage("saved Successfully");
        }
        recreateModel();
        getItems();
    }
    
    public String saveAndAddGis() {
        if (getCurrent().getId() != null && getCurrent().getId() > 0) {
            getFacade().edit(current);
            UtilityController.addSuccessMessage("updated Successfully");
        } else {
            current.setCreatedAt(Calendar.getInstance(TimeZone.getTimeZone("IST")).getTime());
            current.setCreater(sessionController.getLoggedUser());
            getFacade().create(current);
            UtilityController.addSuccessMessage("saved Successfully");
        }
        return "add_case_gis";
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public CkdRecordFacade getFacade() {
        return ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public CkdRecordController() {
    }

    public CkdRecord getCurrent() {
        if (current == null) {
            current = new CkdRecord();
            Person person = new Person();
            current.setPerson(person);
        }
        return current;
    }

    public void setCurrent(CkdRecord current) {
        this.current = current;
    }

    public void delete() {

        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(Calendar.getInstance(TimeZone.getTimeZone("IST")).getTime());
            current.setRetirer(sessionController.getLoggedUser());
            getFacade().edit(current);
            UtilityController.addSuccessMessage("DeleteSuccessfull");
        } else {
            UtilityController.addSuccessMessage("NothingToDelete");
        }
        recreateModel();
        getItems();
        current = null;
        getCurrent();
    }

    public Boolean checkCurrent() {
        if (current == null) {
            UtilityController.addSuccessMessage("Nothing To Delete");
            return false;
        } else {
            return true;
        }

    }

    public List<CkdRecord> getItems() {
        items = getFacade().findAll("name", true);
        return items;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Area getRdhsArea() {
        return rdhsArea;
    }

    public void setRdhsArea(Area rdhsArea) {
        this.rdhsArea = rdhsArea;
    }

    public CkdRecordFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CkdRecordFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Area getMohArea() {
        return mohArea;
    }

    public void setMohArea(Area mohArea) {
        this.mohArea = mohArea;
    }

    public Area getPhiArea() {
        return phiArea;
    }

    public void setPhiArea(Area phiArea) {
        this.phiArea = phiArea;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Diagnosis getCoormobidity() {
        return coormobidity;
    }

    public void setCoormobidity(Diagnosis coormobidity) {
        this.coormobidity = coormobidity;
    }

    /**
     *
     */
    @FacesConverter(forClass = CkdRecord.class)
    public static class CkdRecordControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CkdRecordController controller = (CkdRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ckdRecordController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            java.lang.Long key;

            try {
                key = Long.valueOf(value);
            } catch (Exception ee) {
                key = 0l;
            }

            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CkdRecord) {
                CkdRecord o = (CkdRecord) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + CkdRecordController.class.getName());
            }
        }
    }

    @FacesConverter("ckdRecordCon")
    public static class CkdRecordConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CkdRecordController controller = (CkdRecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ckdRecordController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            java.lang.Long key;

            try {
                key = Long.valueOf(value);
            } catch (Exception ee) {
                key = 0l;
            }

            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CkdRecord) {
                CkdRecord o = (CkdRecord) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + CkdRecordController.class.getName());
            }
        }
    }
}
