package day8;

import java.util.*;

public class PlaystoreStats {
    
    private String category;
    private float lowestRating = 6;
    private String lowestRatingApp;
    private float highestRating = 0;
    private String highestRatingApp;
    private List<Float> allRatings = new ArrayList<Float>();

    public PlaystoreStats(String category) {
        this.category = category;
    }

    public void setStats(PlaystoreEntry entry) {
        allRatings.add(entry.rating());
        if (entry.rating() < getLowestRating()) {
            setLowestRating(entry.rating());
            setLowestRatingApp(entry.appName());
        }
        if (entry.rating() > getHighestRating()) {
            setHighestRating(entry.rating());
            setHighestRatingApp(entry.appName());
        }
    }

    public float avgRating() {
        return (allRatings.stream().reduce(0.0f, (c,e) -> c+e)) / allRatings.size();
    }

    //setters and getters
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getLowestRating() {
        return lowestRating;
    }
    public void setLowestRating(float lowestRating) {
        this.lowestRating = lowestRating;
    }
    public String getLowestRatingApp() {
        return lowestRatingApp;
    }
    public void setLowestRatingApp(String lowestRatingApp) {
        this.lowestRatingApp = lowestRatingApp;
    }
    public float getHighestRating() {
        return highestRating;
    }
    public void setHighestRating(float highestRating) {
        this.highestRating = highestRating;
    }
    public String getHighestRatingApp() {
        return highestRatingApp;
    }
    public void setHighestRatingApp(String highestRatingApp) {
        this.highestRatingApp = highestRatingApp;
    }
    public List<Float> getAllRatings() {
        return allRatings;
    }
    public void setAllRatings(List<Float> allRatings) {
        this.allRatings = allRatings;
    };
    //end setters and getters
    
    

    
}
