package com.example.meetup.viewmodel;

import androidx.databinding.BaseObservable;

import com.example.meetup.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends BaseObservable {
//    Flowable<List<News>> newsList ;
    List<News> newsList;
    public NewsViewModel(){

        newsList = new ArrayList<>();
        newsList.add(new News(1,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUOtF_mAcIqdSTf10G4YvEsEomVUn0Yelc6A&usqp=CAU","4838383838","Get awesome thing and make it perject","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(2,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRd94lDNO4hxlIg3-ehkAtPb7urRytGNbY2VQ&usqp=CAU","4838383838","Get awesome thing and make it perject","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(3,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRY3sQFM0N1hEk4KYM06oRu8031x67joQuD5w&usqp=CAU","4838383838","Nothing change if nothing change","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(4,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIYiVvQQ_v3EKQN5p3E_uoWP80WzhLtk7VQQ&usqp=CAU","4838383838","Nothing Change if Nothing change","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(5,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUOtF_mAcIqdSTf10G4YvEsEomVUn0Yelc6A&usqp=CAU","4838383838","Untill you not until ","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(6,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUOtF_mAcIqdSTf10G4YvEsEomVUn0Yelc6A&usqp=CAU","4838383838","Get awesome thing and make it perject","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(7,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUOtF_mAcIqdSTf10G4YvEsEomVUn0Yelc6A&usqp=CAU","4838383838","Get awesome thing and make it perject, unbbelievabe","Nguyễn Thanh Tùng","instagram","baomoi.com"));
        newsList.add(new News(8,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUOtF_mAcIqdSTf10G4YvEsEomVUn0Yelc6A&usqp=CAU","4838383838","Get awesome thing and make it perject","Nguyễn Thanh Tùng","instagram","baomoi.com"));
//        newsList = ListNewsRepository.getInstance().getListNews(10);

    }

    public List<News> getNewsList() {
        return newsList;
    }
}