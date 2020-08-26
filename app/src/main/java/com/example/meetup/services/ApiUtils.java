package com.example.meetup.services;

public class ApiUtils {
    private  UserService userService;
    private BaseService service;
    public static final String BASE_URL = "http://meetup.rikkei.org/api/v0/";
    public ApiUtils(){
    }

    public BaseService getService() {
        if(service == null){
            service = RetrofitClient.getClient(BASE_URL).create(BaseService.class);
        }
        return service;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = RetrofitClient.getClient(BASE_URL).create(UserService.class);
        }
        return userService;
    }

}
