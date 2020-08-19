package com.example.meetup.model.dataLocal;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "venues")
public class Venue {
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private  int id;
    @Expose
    @ColumnInfo(name = "name")
    private String name;
    @Expose
    @ColumnInfo(name = "type")
    private int type;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "permanent")
    private int permanent;
    @ColumnInfo(name = "contact_phone")
    private String contactPhone;
    @ColumnInfo(name = "contact_address")
    private String contactAddress;
    @SerializedName("geo_area")
    @Expose
    @ColumnInfo(name = "geo_area")
    private String geoArea;
    @SerializedName("geo_long")
    @Expose
    @ColumnInfo(name = "geo_long")
    private String geoLong;
    @SerializedName("geo_lat")
    @Expose
    @ColumnInfo(name = "geo_lat")
    private String geoLat;
    public Venue(){

    }
    @Ignore
    public Venue(int id, String name, int type, String description, int permanent, String contactPhone, String contactAddress, String geoArea, String geoLong, String geoLat) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.permanent = permanent;
        this.contactPhone = contactPhone;
        this.contactAddress = contactAddress;
        this.geoArea = geoArea;
        this.geoLong = geoLong;
        this.geoLat = geoLat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeoArea() {
        return geoArea;
    }

    public void setGeoArea(String geoArea) {
        this.geoArea = geoArea;
    }

    public String getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(String geoLong) {
        this.geoLong = geoLong;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public int getPermanent() {
        return permanent;
    }

    public void setPermanent(int permanent) {
        this.permanent = permanent;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
}
