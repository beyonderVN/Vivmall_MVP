package com.longngo.footballfan.data.model;

import java.io.Serializable;

/**
 * Created by Long on 10/5/2016.
 */

public class Competition implements Serializable
{
    private String id;

    private String numberOfGames;

    private String numberOfTeams;

    private String lastUpdated;

    private String year;

    private String caption;

    private String league;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNumberOfGames ()
    {
        return numberOfGames;
    }

    public void setNumberOfGames (String numberOfGames)
    {
        this.numberOfGames = numberOfGames;
    }

    public String getNumberOfTeams ()
    {
        return numberOfTeams;
    }

    public void setNumberOfTeams (String numberOfTeams)
    {
        this.numberOfTeams = numberOfTeams;
    }

    public String getLastUpdated ()
    {
        return lastUpdated;
    }

    public void setLastUpdated (String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    public String getLeague ()
    {
        return league;
    }

    public void setLeague (String league)
    {
        this.league = league;
    }

    @Override
    public String toString()
    {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Competition Model Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("caption=" + this.getCaption() + "\n");

        stringBuilder.append("numberOfGames=" + this.getNumberOfGames() + "\n");
        stringBuilder.append("numberOfTeams=" + this.getNumberOfTeams() + "\n");

        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
