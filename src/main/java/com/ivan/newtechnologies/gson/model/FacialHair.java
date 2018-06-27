package com.ivan.newtechnologies.gson.model;

/**
 * Created by Ivan_Stankov on 12.02.2016.
 */
public class FacialHair {

    private String color;
    private boolean hasBeard;
    private boolean hasMustache;

    public FacialHair(boolean hasBeard, boolean hasMustache) {
        this.hasBeard = hasBeard;
        this.hasMustache = hasMustache;
    }

    public FacialHair(String color, boolean hasBeard, boolean hasMustache) {
        this.color = color;
        this.hasBeard = hasBeard;
        this.hasMustache = hasMustache;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHasBeard() {
        return hasBeard;
    }

    public void setHasBeard(boolean hasBeard) {
        this.hasBeard = hasBeard;
    }

    public boolean isHasMustache() {
        return hasMustache;
    }

    public void setHasMustache(boolean hasMustache) {
        this.hasMustache = hasMustache;
    }

    @Override
    public String toString() {
        return "FacialHair[color: " + color + "; hasBeard: " + hasBeard + "; hasMustache: " + hasMustache + "]";
    }
}
