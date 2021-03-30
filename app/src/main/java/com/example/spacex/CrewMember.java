package com.example.spacex;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CrewMembers")
public class CrewMember {

        @PrimaryKey
        @NonNull
        private String id;

        @ColumnInfo(name = "Name")
        private String name;

        @ColumnInfo(name = "Agency")
        private String agency;

//        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//        private byte[] img;

        @ColumnInfo(name = "Image")
        private String img;


        @ColumnInfo(name = "Wikipedia")
        private String wiki;

        @ColumnInfo(name = "Status")
        private String status;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAgency() {
            return agency;
        }

        public String getImg() {
            return img;
        }

        public String getWiki() {
            return wiki;
        }

        public String getStatus() {
            return status;
        }

    public CrewMember(String id, String name, String agency, String img, String wiki, String status) {
        this.id = id;
        this.name = name;
        this.agency = agency;
        this.img = img;
        this.wiki = wiki;
        this.status = status;
    }
}
