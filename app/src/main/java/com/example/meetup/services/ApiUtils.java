package com.example.meetup.services;

public class ApiUtils {
    private static UserService userService;
    public static final String BASE_URL = "http://meetup.rikkei.org/api/v0/";

    public static UserService getUserService() {
        if (userService == null) {
            userService = RetrofitClient.getClient(BASE_URL).create(UserService.class);
        }
        return userService;
    }

    public static NewsService getNewsService(){
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }
}
