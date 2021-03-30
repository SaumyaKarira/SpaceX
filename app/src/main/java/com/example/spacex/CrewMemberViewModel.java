package com.example.spacex;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CrewMemberViewModel extends AndroidViewModel {

    private CrewMemberRepository repository;
    private LiveData<List<CrewMember>> allCrewMembers;
    public CrewMemberViewModel(@NonNull Application application) {
        super(application);
        repository = new CrewMemberRepository(application);
        allCrewMembers = repository.getAllCrewMembers();
    }

    public void insert(CrewMember crewMember) {
        repository.insert(crewMember);
    }

    public void delete(){
        repository.delete();
    }

    public LiveData<List<CrewMember>> getAllCrewMembers() {
        return allCrewMembers;
    }
}
