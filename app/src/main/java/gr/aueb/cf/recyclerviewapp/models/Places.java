package gr.aueb.cf.recyclerviewapp.models;

public class Places {

    private String heading;
    private int placeImage;
    private int month;
    private String shortDescription;
    private boolean visibility;

    public Places(String heading, int placeImage, int month, String shortDescription) {
        this.heading = heading;
        this.placeImage = placeImage;
        this.month = month;
        this.shortDescription = shortDescription;
        this.visibility = false;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(int placeImage) {
        this.placeImage = placeImage;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
