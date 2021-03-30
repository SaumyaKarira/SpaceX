package com.example.spacex;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrewMemberDoa {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CrewMember crewMember);

    @Query("DELETE FROM CrewMembers")
    void deleteAll();

    @Query("SELECT * FROM CrewMembers ORDER BY Name ASC")
    LiveData<List<CrewMember>> getAllCrewMembers();
}
