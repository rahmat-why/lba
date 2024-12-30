package id.co.lba.mo.erp.app000.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LBAMOERP_DTLORDERS")
public class LbamoerpDtlorders implements Serializable {

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "VBJNUMBER")
        private String vBjNumber;

        @Column(name = "VPRDID")
        private String vPrdId;

        public String getvBjNumber() {
            return vBjNumber;
        }

        public void setvBjNumber(String vBjNumber) {
            this.vBjNumber = vBjNumber;
        }

        public String getvPrdId() {
            return vPrdId;
        }

        public void setvPrdId(String vPrdId) {
            this.vPrdId = vPrdId;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Id id = (Id) obj;
            return vBjNumber.equals(id.vBjNumber) && vPrdId.equals(id.vPrdId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(vBjNumber, vPrdId);
        }
    }

    @EmbeddedId
    private Id id;

    @Column(name = "VWRHSID")
    private String vWrhsId;

    @Column(name = "NQTY")
    private Integer nQty;

    @Column(name = "NPRICE")
    private Integer nPrice;

    @Column(name = "NCOST")
    private Integer nCost;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
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

    public Integer getnPrice() {
        return nPrice;
    }

    public void setnPrice(Integer nPrice) {
        this.nPrice = nPrice;
    }

    public Integer getnCost() {
        return nCost;
    }

    public void setnCost(Integer nCost) {
        this.nCost = nCost;
    }
}