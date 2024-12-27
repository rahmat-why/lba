package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_TXNTRFSTCKS")
public class LbamoerpTxntrfstcks {

    @Id
    @Column(name = "VTRFSTCID")
    private String vTrfstcId;

    @Column(name = "VPRDID")
    private String vPrdid;

    @Column(name = "VWRHSID")
    private String vWrhsId;

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

    public String getvTrfstcId() {
        return vTrfstcId;
    }

    public void setvTrfstcId(String vTrfstcId) {
        this.vTrfstcId = vTrfstcId;
    }

    public String getvPrdid() {
        return vPrdid;
    }

    public void setvPrdid(String vPrdid) {
        this.vPrdid = vPrdid;
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
}