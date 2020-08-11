package com.example.meetup.services;

public class ApiUtils {
    private  UserService userService;
    private EventJoinedServices eventJoinedServices;
    private  EventService eventService;
    private NewsService newsService;
    private CategoryService categoryService;
    public static final String BASE_URL = "http://meetup.rikkei.org/api/v0/";
    public ApiUtils(){

    }

    public CategoryService getCategoryService(){
        if(categoryService==null){
            categoryService = RetrofitClient.getClient(BASE_URL).create(CategoryService.class);
        }
        return categoryService;
    }
    public  EventService getEventService() {
        if(eventService==null){
            eventService = RetrofitClient.getClient(BASE_URL).create(EventService.class);
        }
        return eventService;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = RetrofitClient.getClient(BASE_URL).create(UserService.class);
        }
        return userService;
    }

    public NewsService getNewsService() {
        if (newsService == null) {
           newsService = RetrofitClient.getClient(BASE_URL).create(NewsService.class);
        }
        return newsService;
    }

    public EventJoinedServices getEventJoinedServices(){
        if (eventJoinedServices == null) {
            eventJoinedServices = RetrofitClient.getClient(BASE_URL).create(EventJoinedServices.class);
        }
        return eventJoinedServices;
    }
}
