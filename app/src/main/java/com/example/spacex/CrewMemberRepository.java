package com.example.spacex;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CrewMemberRepository {

    private CrewMemberDoa crewMemberDoa;
    private LiveData<List<CrewMember>> allCrewMembers;

    public CrewMemberRepository (Application application) {
        CrewMemberDatabase db = CrewMemberDatabase.getInstance(application);
        crewMemberDoa = db.crewMemberDoa();
        allCrewMembers = crewMemberDoa.getAllCrewMembers();
    }

    public void insert (CrewMember crewMember) {
        new InsertCreMemberAsyncTask(crewMemberDoa).execute(crewMember);
    }

    public void delete () {
        new DeleteAllCreMemberAsyncTask(crewMemberDoa).execute();
    }

    public LiveData<List<CrewMember>> getAllCrewMembers() {
        return allCrewMembers;
    }

    private static class InsertCreMemberAsyncTask extends AsyncTask<CrewMember, Void, Void>{
        private CrewMemberDoa crewMemberDoa;

        private InsertCreMemberAsyncTask(CrewMemberDoa crewMemberDoa) {
            this.crewMemberDoa = crewMemberDoa;
        }

        @Override
        protected Void doInBackground(CrewMember... crewMembers) {
            crewMemberDoa.insert(crewMembers[0]);
            return null;
        }
    }

    private static class DeleteAllCreMemberAsyncTask extends AsyncTask<Void, Void, Void>{
        private CrewMemberDoa crewMemberDoa;

        private DeleteAllCreMemberAsyncTask(CrewMemberDoa crewMemberDoa) {
            this.crewMemberDoa = crewMemberDoa;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewMemberDoa.deleteAll();
            return null;
        }
    }

}
