package com.ztgm.base.pojo;

public class LockResidents {
    private String id;

    private String name;

    private String telephone;

    private String gender;

    private String nationality;

    private String birth;

    private String idCrad;

    private String permanentAddress;

    private String idCradAddress;

    private String idCradStarttime;

    private String idCradEndtime;

    private String ctime22;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    public String getIdCrad() {
        return idCrad;
    }

    public void setIdCrad(String idCrad) {
        this.idCrad = idCrad == null ? null : idCrad.trim();
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress == null ? null : permanentAddress.trim();
    }

    public String getIdCradAddress() {
        return idCradAddress;
    }

    public void setIdCradAddress(String idCradAddress) {
        this.idCradAddress = idCradAddress == null ? null : idCradAddress.trim();
    }

    public String getIdCradStarttime() {
        return idCradStarttime;
    }

    public void setIdCradStarttime(String idCradStarttime) {
        this.idCradStarttime = idCradStarttime == null ? null : idCradStarttime.trim();
    }

    public String getIdCradEndtime() {
        return idCradEndtime;
    }

    public void setIdCradEndtime(String idCradEndtime) {
        this.idCradEndtime = idCradEndtime == null ? null : idCradEndtime.trim();
    }

    public String getCtime22() {
        return ctime22;
    }

    public void setCtime22(String ctime22) {
        this.ctime22 = ctime22 == null ? null : ctime22.trim();
    }
}