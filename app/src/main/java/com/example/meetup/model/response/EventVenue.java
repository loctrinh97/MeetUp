package com.example.meetup.model.response;

public class EventVenue {
    private int id;
    private String photo;
    private String name;
    private String link;
    private int goingCount;
    private int wentCount;
    private String scheduleStartDate;
    private int venueId;
    private double distance;
    private String contactAddress;
    private String geoArea;
    private String geoLong;
    private String geoLat;

    public EventVenue(int id, String photo, String name, String link, int goingCount, int wentCount, String scheduleStartDate, int venueId, double distance, String contactAddress, String geoArea, String geoLong, String geoLat) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.link = link;
        this.goingCount = goingCount;
        this.wentCount = wentCount;
        this.scheduleStartDate = scheduleStartDate;
        this.venueId = venueId;
        this.distance = distance;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public int getGoingCount() {
        return goingCount;
    }

    public void setGoingCount(int goingCount) {
        this.goingCount = goingCount;
    }

    public int getWentCount() {
        return wentCount;
    }

    public void setWentCount(int wentCount) {
        this.wentCount = wentCount;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
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
}
