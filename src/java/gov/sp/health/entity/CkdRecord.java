/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author pdhs
 */
@Entity
public class CkdRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String clinicRegistrationNumber;
    boolean newCase;
    String nic;
    @ManyToOne(cascade = CascadeType.ALL)
    Person person;
    @OneToMany
    List<Occupation> occupations;
    @ManyToOne
    Area rdhsArea;
    int caseYear;
    int caseMonth;
    @ManyToOne
    Area mohArea;
    @ManyToOne
    Area phiArea;
    @OneToMany
    List<Diagnosis> comorbidities;
    @ManyToOne
    Diagnosis diagnosis;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date recordDate;
    
    @ManyToOne
    WebUser creater;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date createdAt;
    @Lob
    String createdComments;

    boolean retired;
    @ManyToOne
    WebUser retirer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date retiredAt;
    @Lob
    String retiredComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClinicRegistrationNumber() {
        return clinicRegistrationNumber;
    }

    public void setClinicRegistrationNumber(String clinicRegistrationNumber) {
        this.clinicRegistrationNumber = clinicRegistrationNumber;
    }

    public boolean isNewCase() {
        return newCase;
    }

    public void setNewCase(boolean newCase) {
        this.newCase = newCase;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Occupation> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<Occupation> occupations) {
        this.occupations = occupations;
    }

    public Area getRdhsArea() {
        return rdhsArea;
    }

    public void setRdhsArea(Area rdhsArea) {
        this.rdhsArea = rdhsArea;
    }

    public int getCaseYear() {
        return caseYear;
    }

    public void setCaseYear(int caseYear) {
        this.caseYear = caseYear;
    }

    public int getCaseMonth() {
        return caseMonth;
    }

    public void setCaseMonth(int caseMonth) {
        this.caseMonth = caseMonth;
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

    public List<Diagnosis> getComorbidities() {
        return comorbidities;
    }

    public void setComorbidities(List<Diagnosis> comorbidities) {
        this.comorbidities = comorbidities;
    }

    public WebUser getCreater() {
        return creater;
    }

    public void setCreater(WebUser creater) {
        this.creater = creater;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedComments() {
        return createdComments;
    }

    public void setCreatedComments(String createdComments) {
        this.createdComments = createdComments;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public WebUser getRetirer() {
        return retirer;
    }

    public void setRetirer(WebUser retirer) {
        this.retirer = retirer;
    }

    public Date getRetiredAt() {
        return retiredAt;
    }

    public void setRetiredAt(Date retiredAt) {
        this.retiredAt = retiredAt;
    }


    
    
    public String getRetiredComments() {
        return retiredComments;
    }

    public void setRetiredComments(String retiredComments) {
        this.retiredComments = retiredComments;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
    

    @ManyToOne(cascade = CascadeType.ALL)
    GisCoordinate gisCoordinate;

    public GisCoordinate getGisCoordinate() {
        return gisCoordinate;
    }

    public void setGisCoordinate(GisCoordinate gisCoordinate) {
        this.gisCoordinate = gisCoordinate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CkdRecord)) {
            return false;
        }
        CkdRecord other = (CkdRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.sp.health.entity.CkdRecord[ id=" + id + " ]";
    }

}
