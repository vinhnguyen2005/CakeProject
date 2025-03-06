package com.example.cakeprj.dto.request;

public class SubCategoryCreationRequest {
    private String id;
    private String name;
    private int cakeCount;

    public SubCategoryCreationRequest(String id, String name, int cakeCount) {
        this.id = id;
        this.name = name;
        this.cakeCount = cakeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCakeCount() {
        return cakeCount;
    }

    public void setCakeCount(int cakeCount) {
        this.cakeCount = cakeCount;
    }
}
