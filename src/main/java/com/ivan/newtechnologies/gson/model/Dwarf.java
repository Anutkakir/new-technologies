package com.ivan.newtechnologies.gson.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan_Stankov on 12.02.2016.
 */
public class Dwarf {

    private String name;
    @SerializedName("age")
    private int dwarfAge;
    private FacialHair beard;

    public Dwarf(String name, int dwarfAge, FacialHair beard) {
        this.name = name;
        this.dwarfAge = dwarfAge;
        this.beard = beard;
    }

    public String getName() {
        return name;
    }

    public int getDwarfAge() {
        return dwarfAge;
    }

    public FacialHair getBeard() {
        return beard;
    }

    @Override
    public String toString() {
        return "\nDwarf[name: " + name + "; age: " + dwarfAge + "; " + beard.toString() + "]";
    }
}
