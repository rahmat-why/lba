package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_MSTWRHSES")
public class LbamoerpMstwrhses {

    @Id
    @Column(name = "VWRHSID")
    private String vWrhsid;

    @Column(name = "VNAME")
    private String vName;

    @Column(name = "VADDRESS")
    private String vAddress;

    @Column(name = "NCAPACITY")
    private Integer nCapacity;

    @Column(name = "VCREA")
    private String vCrea;

    @Column(name = "DCREA")
    private LocalDateTime dCrea;

    @Column(name = "VMODI")
    private String vModi;

    @Column(name = "DMODI")
    private LocalDateTime dModi;

    @Column(name = "VSTATUS")
    private String vStatus;

    public String getvWrhsid() {
        return vWrhsid;
    }

    public void setvWrhsid(String vWrhsid) {
        this.vWrhsid = vWrhsid;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress;
    }

    public Integer getnCapacity() {
        return nCapacity;
    }

    public void setnCapacity(Integer nCapacity) {
        this.nCapacity = nCapacity;
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

    public String getvStatus() {
        return vStatus;
    }

    public void setvStatus(String vStatus) {
        this.vStatus = vStatus;
    }
}