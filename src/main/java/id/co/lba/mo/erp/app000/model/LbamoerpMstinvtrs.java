package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LBAMOERP_MSTINVTRS")
public class LbamoerpMstinvtrs {

    @Id
    @Column(name = "VINVTRID")
    private String vInvtrId;

    @Column(name = "VPRDID")
    private String vPrdId;

    @Column(name = "VWRHID")
    private String vWrhsId;

    @Column(name = "NTSTCK")
    private Integer nStck;

    @Column(name = "VCREA")
    private String vCrea;

    @Column(name = "DCREA")
    private LocalDateTime dCrea;

    @Column(name = "VMODI")
    private String vModi;

    @Column(name = "DMODI")
    private LocalDateTime dModi;

    public String getvInvtrId() {
        return vInvtrId;
    }

    public void setvInvtrId(String vInvtrId) {
        this.vInvtrId = vInvtrId;
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

    public Integer getnStck() {
        return nStck;
    }

    public void setnStck(Integer nStck) {
        this.nStck = nStck;
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