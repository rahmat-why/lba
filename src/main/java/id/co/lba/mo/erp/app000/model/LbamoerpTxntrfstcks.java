package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_TXNTRFSTCKS")
public class LbamoerpTxntrfstcks {

    @Id
    @Column(name = "VTRFSTCID")
    private String vTrfstcId;

    @Column(name = "VPRDID")
    private String vPrdId;

    @Column(name = "VWRHSFROM")
    private String vWrhsFrom;

    @Column(name = "NQTY")
    private Integer nQty;

    @Column(name = "VDESC")
    private String vDesc;

    @Column(name = "VCREA")
    private String vCrea;

    @Column(name = "DCREA")
    private LocalDateTime dCrea;

    @Column(name = "VMODI")
    private String vModi;

    @Column(name = "DMODI")
    private LocalDateTime dModi;

    @Column(name = "NTCOST")
    private Integer nTcost;

    @Column(name = "VWRHSTO")
    private String vWrhsTo;

    @Column(name = "DTRFDT")
    private LocalDate dTrfdt;

    public String getvTrfstcId() {
        return vTrfstcId;
    }

    public void setvTrfstcId(String vTrfstcId) {
        this.vTrfstcId = vTrfstcId;
    }

    public String getvPrdId() {
        return vPrdId;
    }

    public void setvPrdId(String vPrdId) {
        this.vPrdId = vPrdId;
    }

    public String getvWrhsFrom() {
        return vWrhsFrom;
    }

    public void setvWrhsFrom(String vWrhsFrom) {
        this.vWrhsFrom = vWrhsFrom;
    }

    public Integer getnQty() {
        return nQty;
    }

    public void setnQty(Integer nQty) {
        this.nQty = nQty;
    }

    public String getvDesc() {
        return vDesc;
    }

    public void setvDesc(String vDesc) {
        this.vDesc = vDesc;
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

    public Integer getnTcost() {
        return nTcost;
    }

    public void setnTcost(Integer nTcost) {
        this.nTcost = nTcost;
    }

    public String getvWrhsTo() {
        return vWrhsTo;
    }

    public void setvWrhsTo(String vWrhsTo) {
        this.vWrhsTo = vWrhsTo;
    }

    public LocalDate getdTrfdt() {
        return dTrfdt;
    }

    public void setdTrfdt(LocalDate dTrfdt) {
        this.dTrfdt = dTrfdt;
    }
}