package com.ivan.newtechnologies.gson.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan_Stankov on 12.02.2016.
 */
public class DwarfBand {

    private List<Dwarf> dwarfs = new ArrayList<>();

    public List<Dwarf> getDwarfs() {
        return dwarfs;
    }

    public void add(Dwarf dwarf) {
        dwarfs.add(dwarf);
    }

    @Override
    public String toString() {
        return dwarfs.toString();
    }
}
