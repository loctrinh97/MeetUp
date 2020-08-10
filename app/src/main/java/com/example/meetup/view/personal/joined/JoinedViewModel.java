package com.example.meetup.view.personal.joined;

import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.repository.PersonalJoinedRepository;

import java.util.List;

public class JoinedViewModel extends ViewModel {
    PersonalJoinedRepository personalJoinedRepository = PersonalJoinedRepository.getInstance();

    public List<UsersEvents> getUserEventList(int pageSize) {
        // TODO: 8/10/2020
        return null;
    }
}
