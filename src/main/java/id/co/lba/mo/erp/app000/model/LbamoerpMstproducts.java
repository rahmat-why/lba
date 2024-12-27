package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_MSTPRODUCTS")
public class LbamoerpMstproducts {

    @Id
    @Column(name = "VPRDID")
    private String vPrdId;

    @Column(name = "VNAME")
    private String vName;

    @Column(name = "XIMG")
    private String xImg;

    @Column(name = "NWEIGHT")
    private Integer nWeight;

    @Column(name = "VUNIT")
    private String vUnit;

    @Column(name = "NPRBUY")
    private Integer nPrbuy;

    @Column(name = "NPRUNTDEL")
    private Integer nPruntdel;

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

    @Column(name = "NPRUNTPIC")
    private Integer nPruntpic;

    public String getvPrdId() {
        return vPrdId;
    }

    public void setvPrdId(String vPrdId) {
        this.vPrdId = vPrdId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getxImg() {
        return xImg;
    }

    public void setxImg(String xImg) {
        this.xImg = xImg;
    }

    public Integer getnWeight() {
        return nWeight;
    }

    public void setnWeight(Integer nWeight) {
        this.nWeight = nWeight;
    }

    public String getvUnit() {
        return vUnit;
    }

    public void setvUnit(String vUnit) {
        this.vUnit = vUnit;
    }

    public Integer getnPrbuy() {
        return nPrbuy;
    }

    public void setnPrbuy(Integer nPrbuy) {
        this.nPrbuy = nPrbuy;
    }

    public Integer getnPruntdel() {
        return nPruntdel;
    }

    public void setnPruntdel(Integer nPruntdel) {
        this.nPruntdel = nPruntdel;
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

    public Integer getnPruntpic() {
        return nPruntpic;
    }

    public void setnPruntpic(Integer nPruntpic) {
        this.nPruntpic = nPruntpic;
    }
}