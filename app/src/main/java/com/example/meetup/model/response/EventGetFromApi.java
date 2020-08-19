package com.example.meetup.model.response;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Venue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

public class EventGetFromApi {


    @Expose
    private int id;
    @Expose
    private String photo;
    @Expose
    private String name;
    @Expose
    private String link;
    @SerializedName("going_count")
    @Expose
    private int goingCount;
    @SerializedName("went_count")
    @Expose
    private int wentCount;
    @SerializedName("description_raw")
    @Expose
    private String descriptionRaw;
    @SerializedName("description_html")
    @Expose
    private  String descriptionHtml;
    @SerializedName("schedule_permanent")
    @Expose
    private String schedulePermanent;
    @SerializedName("schedule_date_warning")
    @Expose
    private String scheduleDateWarning;
    @SerializedName("schedule_time_alert")
    @Expose
    private String scheduleTimeAlert;
    @SerializedName("schedule_start_date")
    @Expose
    private String scheduleStartDate;
    @SerializedName("schedule_start_time")
    @Expose
    private String scheduleStartTime;
    @SerializedName("schedule_end_date")
    @Expose
    private String scheduleEndDate;
    @SerializedName("schedule_end_time")
    @Expose
    private String scheduleEndTime;
    @SerializedName("schedule_one_day_event")
    @Expose
    private String scheduleOneDayEvent;
    @SerializedName("schedule_extra")
    @Expose
    private String scheduleExtra;
    @Expose
    private Venue venue;
    @Expose
    private Category category;
    @SerializedName("distance")
    @Expose
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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

    public String getDescriptionRaw() {
        return descriptionRaw;
    }

    public void setDescriptionRaw(String descriptionRaw) {
        this.descriptionRaw = descriptionRaw;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getSchedulePermanent() {
        return schedulePermanent;
    }

    public void setSchedulePermanent(String schedulePermanent) {
        this.schedulePermanent = schedulePermanent;
    }

    public String getScheduleDateWarning() {
        return scheduleDateWarning;
    }

    public void setScheduleDateWarning(String scheduleDateWarning) {
        this.scheduleDateWarning = scheduleDateWarning;
    }

    public String getScheduleTimeAlert() {
        return scheduleTimeAlert;
    }

    public void setScheduleTimeAlert(String scheduleTimeAlert) {
        this.scheduleTimeAlert = scheduleTimeAlert;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(String scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getScheduleOneDayEvent() {
        return scheduleOneDayEvent;
    }

    public void setScheduleOneDayEvent(String scheduleOneDayEvent) {
        this.scheduleOneDayEvent = scheduleOneDayEvent;
    }

    public String getScheduleExtra() {
        return scheduleExtra;
    }

    public void setScheduleExtra(String scheduleExtra) {
        this.scheduleExtra = scheduleExtra;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "EventGetFromApi{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", goingCount=" + goingCount +
                ", wentCount=" + wentCount +
                ", descriptionRaw='" + descriptionRaw + '\'' +
                ", descriptionHtml='" + descriptionHtml + '\'' +
                ", schedulePermanent='" + schedulePermanent + '\'' +
                ", scheduleDateWarning='" + scheduleDateWarning + '\'' +
                ", scheduleTimeAlert='" + scheduleTimeAlert + '\'' +
                ", scheduleStartDate='" + scheduleStartDate + '\'' +
                ", scheduleStartTime='" + scheduleStartTime + '\'' +
                ", scheduleEndDate='" + scheduleEndDate + '\'' +
                ", scheduleEndTime='" + scheduleEndTime + '\'' +
                ", scheduleOneDayEvent='" + scheduleOneDayEvent + '\'' +
                ", scheduleExtra='" + scheduleExtra + '\'' +
                ", venue=" + venue +
                ", category=" + category +
                '}';
    }
}
