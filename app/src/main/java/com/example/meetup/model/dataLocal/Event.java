package com.example.meetup.model.dataLocal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "events",indices =@Index("venue_id") ,foreignKeys = @ForeignKey(entity = Venue.class,parentColumns = "id",
                                                                            childColumns = "venue_id",
                                                                            onDelete = ForeignKey.CASCADE) )
public class Event {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "photo")
    private String photo;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "link")
    private String link;
    @ColumnInfo(name = "going_count")
    private int goingCount;
    @ColumnInfo(name = "went_count")
    private int wentCount;
    @ColumnInfo(name = "description_raw")
    private String descriptionRaw;
    @ColumnInfo(name = "description_html")
    private  String descriptionHtml;
    @ColumnInfo(name = "schedule_permanent")
    private String schedulePermanent;
    @ColumnInfo(name = "schedule_date_warning")
    private String scheduleDateWarning;
    @ColumnInfo(name = "schedule_time_alert")
    private String scheduleTimeAlert;
    @ColumnInfo(name = "schedule_start_date")
    private String scheduleStartDate;
    @ColumnInfo(name = "schedule_start_time")
    private String scheduleStartTime;
    @ColumnInfo(name = "schedule_end_date")
    private String scheduleEndDate;
    @ColumnInfo(name = "schedule_end_time")
    private String scheduleEndTime;
    @ColumnInfo(name = "schedule_one_day_event")
    private String scheduleOneDayEvent;
    @ColumnInfo(name = "schedule_extra")
    private String scheduleExtra;

    @ColumnInfo(name = "venue_id")
    private int venueId;
    @Ignore
    public Event(int id, String photo, String name, String link, int goingCount, int wentCount, String descriptionRaw, String descriptionHtml, String schedulePermanent, String scheduleDateWarning, String scheduleTimeAlert, String scheduleStartDate, String scheduleStartTime, String scheduleEndDate, String scheduleEndTime, String scheduleOneDayEvent, String scheduleExtra, int venueId) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.link = link;
        this.goingCount = goingCount;
        this.wentCount = wentCount;
        this.descriptionRaw = descriptionRaw;
        this.descriptionHtml = descriptionHtml;
        this.schedulePermanent = schedulePermanent;
        this.scheduleDateWarning = scheduleDateWarning;
        this.scheduleTimeAlert = scheduleTimeAlert;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndDate = scheduleEndDate;
        this.scheduleEndTime = scheduleEndTime;
        this.scheduleOneDayEvent = scheduleOneDayEvent;
        this.scheduleExtra = scheduleExtra;
        this.venueId = venueId;
    }
    public Event(){

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

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
}
