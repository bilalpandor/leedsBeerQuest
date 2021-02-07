package com.xlab.leedsbeerquest.helpers;

import com.opencsv.bean.CsvBindByName;

public class CsvFileEntry {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "category")
    private String category;

    @CsvBindByName(column = "url")
    private String url;

    @CsvBindByName(column = "date")
    private String date;

    @CsvBindByName(column = "excerpt")
    private String excerpt;

    @CsvBindByName(column = "thumbnail")
    private String thumbnail;

    @CsvBindByName(column = "lat")
    private String lat;

    @CsvBindByName(column = "lng")
    private String lng;

    @CsvBindByName(column = "address")
    private String address;

    @CsvBindByName(column = "phone")
    private String phone;

    @CsvBindByName(column = "twitter")
    private String twitter;

    @CsvBindByName(column = "stars_beer")
    private String stars_beer;

    @CsvBindByName(column = "stars_atmosphere")
    private String stars_atmosphere;

    @CsvBindByName(column = "stars_amenities")
    private String stars_amenities;

    @CsvBindByName(column = "stars_value")
    private String stars_value;

    @CsvBindByName(column = "tags")
    private String tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExcerpt() { return excerpt; }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getStars_beer() {
        return stars_beer;
    }

    public void setStars_beer(String stars_beer) {
        this.stars_beer = stars_beer;
    }

    public String getStars_atmosphere() {
        return stars_atmosphere;
    }

    public void setStars_atmosphere(String stars_atmosphere) {
        this.stars_atmosphere = stars_atmosphere;
    }

    public String getStars_amenities() {
        return stars_amenities;
    }

    public void setStars_amenities(String stars_amenities) {
        this.stars_amenities = stars_amenities;
    }

    public String getStars_value() {
        return stars_value;
    }

    public void setStars_value(String stars_value) {
        this.stars_value = stars_value;
    }

    public String getTags() { return tags; }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

