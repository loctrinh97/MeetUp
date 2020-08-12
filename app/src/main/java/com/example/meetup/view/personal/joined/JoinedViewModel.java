package com.example.meetup.view.personal.joined;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.repository.PersonalJoinedRepository;

import java.util.ArrayList;
import java.util.List;

public class JoinedViewModel extends ViewModel {
    PersonalJoinedRepository personalJoinedRepository = PersonalJoinedRepository.getInstance();
    List<UsersEvents> usersEvents;

    private MutableLiveData<List<UsersEvents>> personalJoinedList = new MutableLiveData<>();

    public MutableLiveData<List<UsersEvents>> getCurrentList(){
        personalJoinedList.setValue(usersEvents);
        return personalJoinedList;
    }

    public JoinedViewModel(){
        usersEvents = new ArrayList<>();
    }

    public List<UsersEvents> getUserEventList() {
     //   usersEvents = personalJoinedRepository.getJoinedList();
        // TODO: 8/11/2020  
        personalJoinedList.postValue(usersEvents);
        return usersEvents;
    }
}
