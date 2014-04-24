package gov.sp.health.bean;

import gov.sp.health.facade.OccupationFacade;
import gov.sp.health.entity.Occupation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named
@SessionScoped
public class OccupationController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    SessionController sessionController;
    @EJB
    private OccupationFacade ejbFacade;
    List<Occupation> selectedItems;
    private Occupation current;
    private List<Occupation> items = null;
    String selectText = "";

    public List<Occupation> getSelectedItems() {
        selectedItems = getFacade().findBySQL("select c from Occupation c where c.retired=false and upper(c.name) like '%" + getSelectText().toUpperCase() + "%' order by c.name");
        return selectedItems;
    }

    public List<Occupation> completeOccupation(String qry) {
        List<Occupation> a = null;
        if (qry != null) {
            a = getFacade().findBySQL("select c from Occupation c where c.retired=false and upper(c.name) like '%" + qry.toUpperCase() + "%' order by c.name");
        }
        if (a == null) {
            a = new ArrayList<Occupation>();
        }
        return a;
    }


    public void prepareAdd() {
        current = new Occupation();
    }

    public void setSelectedItems(List<Occupation> selectedItems) {
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

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public OccupationFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(OccupationFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public OccupationController() {
    }

    public Occupation getCurrent() {
        return current;
    }

    public void setCurrent(Occupation current) {
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

    private OccupationFacade getFacade() {
        return ejbFacade;
    }

    public List<Occupation> getItems() {
        items = getFacade().findAll("name", true);
        return items;
    }

    /**
     *
     */
    @FacesConverter("occupationCon")
    public static class OccupationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OccupationController controller = (OccupationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "occupationController");
            return controller.getEjbFacade().find(getKey(value));
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
            if (object instanceof Occupation) {
                Occupation o = (Occupation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + OccupationController.class.getName());
            }
        }
    }
}
