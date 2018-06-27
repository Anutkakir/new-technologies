package com.ivan.newtechnologies.gson;

import com.google.gson.*;
import com.ivan.newtechnologies.gson.deserializer.FacialHairDeserializer;
import com.ivan.newtechnologies.gson.serializer.FacialHairSerializer;
import com.ivan.newtechnologies.gson.model.Dwarf;
import com.ivan.newtechnologies.gson.model.DwarfBand;
import com.ivan.newtechnologies.gson.model.FacialHair;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by Ivan_Stankov on 12.02.2016.
 */
public class Main {

    public static void main1(String[] args) {
        DwarfBand dwarfBand = new DwarfBand();
        dwarfBand.add(new Dwarf("Balin", 90, new FacialHair("red", true, true)));
        dwarfBand.add(new Dwarf("Dvalin", 99, new FacialHair("green", false, true)));
        dwarfBand.add(new Dwarf("Gimli", 145, new FacialHair("brown", true, false)));
        dwarfBand.add(new Dwarf("Gloin", 145, new FacialHair(false, false)));

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(FacialHair.class, new FacialHairSerializer())
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(dwarfBand);

        System.out.println(json);
    }

    public static void main(String[] args) {
        String json = getStringFormFile();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(FacialHair.class, new FacialHairDeserializer())
                .create();

        DwarfBand dwarfBand = gson.fromJson(json, DwarfBand.class);
        System.out.println(dwarfBand);
    }

    private static String getStringFormFile() {
        String json = null;
        try {
            json = IOUtils.toString(Main.class.getClassLoader().getResourceAsStream("gson/dwarfBand.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
