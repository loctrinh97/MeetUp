package com.example.meetup.services;

import com.example.meetup.repository.UserRepository;

public class ApiUtils {
    public static final String BASE_URL = "http://meetup.rikkei.org/api/v0/";

    public static UserService getUserService() {
        return UserRepository.getClient(BASE_URL).create(UserService.class);
    }
}
