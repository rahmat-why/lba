package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_TXNORDERS")
public class LbamoerpTxnorders {

    @Id
    @Column(name = "VBJNUMBER")
    private String vBjNumber;

    @Column(name = "VCSTID")
    private String vCstId;

    @Column(name = "VORDST")
    private String vOrdSt;

    @Column(name = "VPAYSYT")
    private String vPaysyt;

    @Column(name = "VDELSYT")
    private String vDelsyt;

    @Column(name = "NSUBTOTAL")
    private Integer nSubtotal;

    @Column(name = "NTPAYMENT")
    private Integer nTpayment;

    @Column(name = "NTCOST")
    private Integer nTcost;

    @Column(name = "NFEE")
    private Integer nFee;

    @Column(name = "DORDERDT")
    private LocalDate dOrderDt;

    @Column(name = "VCREA")
    private String vCrea;

    @Column(name = "DCREA")
    private LocalDateTime dCrea;

    @Column(name = "VMODI")
    private String vModi;

    @Column(name = "DMODI")
    private LocalDateTime dModi;

    @Column(name = "VDESC")
    private String vDesc;

    public String getvBjNumber() {
        return vBjNumber;
    }

    public void setvBjNumber(String vBjNumber) {
        this.vBjNumber = vBjNumber;
    }

    public String getvCstId() {
        return vCstId;
    }

    public void setvCstId(String vCstId) {
        this.vCstId = vCstId;
    }

    public String getvOrdSt() {
        return vOrdSt;
    }

    public void setvOrdSt(String vOrdSt) {
        this.vOrdSt = vOrdSt;
    }

    public String getvPaysyt() {
        return vPaysyt;
    }

    public void setvPaysyt(String vPaysyt) {
        this.vPaysyt = vPaysyt;
    }

    public String getvDelsyt() {
        return vDelsyt;
    }

    public void setvDelsyt(String vDelsyt) {
        this.vDelsyt = vDelsyt;
    }

    public Integer getnSubtotal() {
        return nSubtotal;
    }

    public void setnSubtotal(Integer nSubtotal) {
        this.nSubtotal = nSubtotal;
    }

    public Integer getnTpayment() {
        return nTpayment;
    }

    public void setnTpayment(Integer nTpayment) {
        this.nTpayment = nTpayment;
    }

    public Integer getnTcost() {
        return nTcost;
    }

    public void setnTcost(Integer nTcost) {
        this.nTcost = nTcost;
    }

    public Integer getnFee() {
        return nFee;
    }

    public void setnFee(Integer nFee) {
        this.nFee = nFee;
    }

    public LocalDate getdOrderDt() {
        return dOrderDt;
    }

    public void setdOrderDt(LocalDate dOrderDt) {
        this.dOrderDt = dOrderDt;
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

    public String getvDesc() {
        return vDesc;
    }

    public void setvDesc(String vDesc) {
        this.vDesc = vDesc;
    }
}
