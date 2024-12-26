package id.co.lba.mo.erp.app001.vo;

public class Erp001VoCustomer {
    private String customerId;
    private String name;
    private String email;
    private String website;
    private String sector;
    private String beginEffective;
    private String endEffective;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getBeginEffective() {
        return beginEffective;
    }

    public void setBeginEffective(String beginEffective) {
        this.beginEffective = beginEffective;
    }

    public String getEndEffective() {
        return endEffective;
    }

    public void setEndEffective(String endEffective) {
        this.endEffective = endEffective;
    }
}
