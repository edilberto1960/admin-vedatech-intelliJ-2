package com.vedatech.admin.model;

public class ResultData3 {

    private int id;
    private String nameDepartment;
    private String typeName;
    private Double total;

    public ResultData3() {

    }

    public ResultData3(int id, String nameDepartment, String typeName, Double total) {
        this.id = id;
        this.nameDepartment = nameDepartment;
        this.typeName = typeName;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
