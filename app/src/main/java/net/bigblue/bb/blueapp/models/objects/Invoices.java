package net.bigblue.bb.blueapp.models.objects;


public class Invoices {

    private String custId;
    private String invNum;
    private String invPo;
    private String invDate;
    private String status;
    private String total;
    private String payments;
    private String awb;
    private String country;
    private String fob;
    private String totalLbs;


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getInvNum() {
        return invNum;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    public String getInvPo() {
        return invPo;
    }

    public void setInvPo(String invPo) {
        this.invPo = invPo;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFob() {
        return fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    public String getTotalLbs() {
        return totalLbs;
    }

    public void setTotalLbs(String totalLbs) {
        this.totalLbs = totalLbs;
    }
}
