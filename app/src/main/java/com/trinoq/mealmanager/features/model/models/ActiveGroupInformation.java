package com.trinoq.mealmanager.features.model.models;

public class ActiveGroupInformation {

    Integer id;
    String groupName;

    public ActiveGroupInformation(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
