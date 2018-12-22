package com.example.charl.heroes;

public class Hero {

    public String id;
    public String name;
    public String realName;
    public String team;
    public String year;

    public Hero(String id,String name, String realName, String team, String year) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.team = team;
        this.year = year;
    }
    public Hero(){

    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public String setId() {return id;}

    public void setYear(String year) {
        this.year = year;
    }
}
