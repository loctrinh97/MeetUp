package com.example.meetup.repository;

import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.repository.dao.VenueDao;

public class VenueRepository {
    private static VenueRepository venueRepository;

    public static VenueRepository getInstance() {
        if (venueRepository == null) {
            venueRepository = new VenueRepository(AppDatabase.getInstance().getVenueDao());
        }
        return venueRepository;
    }

    private VenueDao dao;

    private VenueRepository(VenueDao dao) {
        this.dao = dao;
    }

    public void insertVenue(Venue venue) {
        dao.insertVenue(venue);
    }

    public int getCountVenues() {
        return dao.getCountVenues();
    }


}
