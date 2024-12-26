package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "LBAMOERP_TXNSTCKPRDS")
public class LbamoerpTxnstckprds {

    @Id
    @Column(name = "VSTCPRDID")
    private String vStcprdid;

    @Column(name = "VWRHSID")
    private String vWrhsid;

    @Column(name = "NQTY")
    private Integer nQty;

    @Column(name = "NTPYMNTS")
    private Integer nTpyments;

    @Column(name = "DPYMNTDT")
    private LocalDate dPaymentDate;

    @Column(name = "VPYMNTST")
    private String vPaymentStatus;

    @Column(name = "VCREA")
    private String vCrea;

    @Column(name = "DCREA")
    private LocalDateTime dCrea;

    @Column(name = "VMODI")
    private String vModi;

    @Column(name = "DMODI")
    private LocalDateTime dModi;

    // Getter dan Setter
    public String getvStcprdid() {
        return vStcprdid;
    }

    public void setvStcprdid(String vStcprdid) {
        this.vStcprdid = vStcprdid;
    }

    public String getvWrhsid() {
        return vWrhsid;
    }

    public void setvWrhsid(String vWrhsid) {
        this.vWrhsid = vWrhsid;
    }

    public Integer getnQty() {
        return nQty;
    }

    public void setnQty(Integer nQty) {
        this.nQty = nQty;
    }

    public Integer getnTpyments() {
        return nTpyments;
    }

    public void setnTpyments(Integer nTpyments) {
        this.nTpyments = nTpyments;
    }

    public LocalDate getdPaymentDate() {
        return dPaymentDate;
    }

    public void setdPaymentDate(LocalDate dPaymentDate) {
        this.dPaymentDate = dPaymentDate;
    }

    public String getvPaymentStatus() {
        return vPaymentStatus;
    }

    public void setvPaymentStatus(String vPaymentStatus) {
        this.vPaymentStatus = vPaymentStatus;
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