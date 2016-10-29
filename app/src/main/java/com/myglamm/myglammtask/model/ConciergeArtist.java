package com.myglamm.myglammtask.model;

/**
 * Created by Akash Wangalwar on 29-10-2016.
 */
public class ConciergeArtist {

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public float getAggregatedRating() {
        return aggregatedRating;
    }

    public void setAggregatedRating(float aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    public int getPinCodeId() {
        return pinCodeId;
    }

    public void setPinCodeId(int pinCodeId) {
        this.pinCodeId = pinCodeId;
    }

    public int[] getOfferedServiceIds() {
        return offeredServiceIds;
    }

    public void setOfferedServiceIds(int[] offeredServiceIds) {
        this.offeredServiceIds = offeredServiceIds;
    }

    private int artistId;
    private String firstName;
    private String lastName;
    private String appVersion;
    private String email;
    private String mobileNumber;
    private String description;
    private String gender;
    private String dob;
    private String imageURL;
    private boolean enabled;
    private int experience;
    private float aggregatedRating;
    private double totalPrice;
    private int totalDuration;
    private int franchiseId;
    private int pinCodeId;
    private int[] offeredServiceIds;

}
