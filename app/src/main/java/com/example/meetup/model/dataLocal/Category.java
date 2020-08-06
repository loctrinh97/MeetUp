package com.example.meetup.model.dataLocal;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "categories")
public class Category {
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    @Expose
    @ColumnInfo(name = "slug")
    private String slug;
    @SerializedName("parent_id")
    @Expose
    @ColumnInfo(name = "parent_id")
    private String parentID;
    public Category(){

    }
    @Ignore
    public Category(String name, String slug, String parentID) {
        this.name = name;
        this.slug = slug;
        this.parentID = parentID;
    }
    @Ignore
    public Category(int id, String name, String slug, String parentID){
        this.id = id;
        this.name  = name;
        this.slug = slug;
        this.parentID = parentID;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentID() {
        return parentID;
    }

    public String getSlug() {
        return slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
