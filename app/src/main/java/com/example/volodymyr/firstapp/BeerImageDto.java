package com.example.volodymyr.firstapp;

import java.io.Serializable;

public class BeerImageDto implements Serializable {
    private int id;
    private String url;
    private String tagline;
    private String year;
    private String description;
    private String abv;
    private String name;

    public BeerImageDto(int id, String name, String url, String tagline, String year, String description, String abv) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.tagline = tagline;
        this.year = year;
        this.description = description;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
