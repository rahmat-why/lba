package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_MSTCUSTS")
public class LbamoerpMstcusts {
    @Id
    private String vCstid;
    private String vName;
    private String vEmail;
    private String vWebsite;
    private String vSector;
    private LocalDate dBegineff;
    private LocalDate dEndeff;
    private String vCrea;
    private LocalDateTime dCrea;
    private String vModi;
    private LocalDateTime dModi;

    public String getvCstid() {
        return vCstid;
    }

    public void setvCstid(String vCstid) {
        this.vCstid = vCstid;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvEmail() {
        return vEmail;
    }

    public void setvEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getvWebsite() {
        return vWebsite;
    }

    public void setvWebsite(String vWebsite) {
        this.vWebsite = vWebsite;
    }

    public String getvSector() {
        return vSector;
    }

    public void setvSector(String vSector) {
        this.vSector = vSector;
    }

    public LocalDate getdBegineff() {
        return dBegineff;
    }

    public void setdBegineff(LocalDate dBegineff) {
        this.dBegineff = dBegineff;
    }

    public LocalDate getdEndeff() {
        return dEndeff;
    }

    public void setdEndeff(LocalDate dEndeff) {
        this.dEndeff = dEndeff;
    }

    public String getvCrea() {
        return vCrea;
    }

    public void setvCrea(String vCrea) {
        this.vCrea = vCrea;
    }

    public LocalDateTime getdCrea() {
        return dCrea;
    }

    public void setdCrea(LocalDateTime dCrea) {
        this.dCrea = dCrea;
    }

    public String getvModi() {
        return vModi;
    }

    public void setvModi(String vModi) {
        this.vModi = vModi;
    }

    public LocalDateTime getdModi() {
        return dModi;
    }

    public void setdModi(LocalDateTime dModi) {
        this.dModi = dModi;
    }
}
