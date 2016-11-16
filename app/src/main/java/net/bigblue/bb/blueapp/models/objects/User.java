package net.bigblue.bb.blueapp.models.objects;


public class User {

    private String custId;
    private String name;
    private String email;
    private String company;
    private String phone;
    private String salesRep;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;
    private String token;

    @Override
    public String toString() {
        return "User{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", phone='" + phone + '\'' +
                ", salesRep='" + salesRep + '\'' +
                ", unique_id='" + unique_id + '\'' +
                ", password='" + password + '\'' +
                ", old_password='" + old_password + '\'' +
                ", new_password='" + new_password + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
