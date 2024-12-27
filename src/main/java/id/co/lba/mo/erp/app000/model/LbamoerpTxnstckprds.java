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
    private String vStcprdId;

    @Column(name = "VPRDID")
    private String vPrdId;

    @Column(name = "VWRHSID")
    private String vWrhsId;

    @Column(name = "NQTY")
    private Integer nQty;

    @Column(name = "NTPYMNTS")
    private Integer nTpyments;

    @Column(name = "NPRICE")
    private Integer nPrice;

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

    public String getvStcprdId() {
        return vStcprdId;
    }

    public void setvStcprdId(String vStcprdId) {
        this.vStcprdId = vStcprdId;
    }

    public String getvPrdId() {
        return vPrdId;
    }

    public void setvPrdId(String vPrdId) {
        this.vPrdId = vPrdId;
    }

    public String getvWrhsId() {
        return vWrhsId;
    }

    public void setvWrhsId(String vWrhsId) {
        this.vWrhsId = vWrhsId;
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

    public Integer getnPrice() {
        return nPrice;
    }

    public void setnPrice(Integer nPrice) {
        this.nPrice = nPrice;
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