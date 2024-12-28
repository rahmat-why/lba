package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_TXNRSTCKS")
public class LbamoerpTxnrstcks {

    @Id
    @Column(name = "VRSTCKID")
    private String vRstckId;

    @Column(name = "VPRDID")
    private String vPrdId;

    @Column(name = "VWRHSID")
    private String vWrhsId;

    @Column(name = "NQTY")
    private Integer nQty;

    @Column(name = "NTPYMNT")
    private Integer nTpyment;

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

    @Column(name = "DRSTCKDT")
    private LocalDate dRestockDate;

    public String getvRstckId() {
        return vRstckId;
    }

    public void setvRstckId(String vRstckId) {
        this.vRstckId = vRstckId;
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

    public Integer getnTpyment() {
        return nTpyment;
    }

    public void setnTpyment(Integer nTpyment) {
        this.nTpyment = nTpyment;
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

    public LocalDate getdRestockDate() {
        return dRestockDate;
    }

    public void setdRestockDate(LocalDate dRestockDate) {
        this.dRestockDate = dRestockDate;
    }
}