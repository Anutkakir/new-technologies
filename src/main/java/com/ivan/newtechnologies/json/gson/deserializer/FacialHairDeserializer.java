package com.ivan.newtechnologies.json.gson.deserializer;

import com.google.gson.*;
import com.ivan.newtechnologies.json.gson.model.FacialHair;

import java.lang.reflect.Type;

/**
 * Created by Ivan_Stankov on 15.02.2016.
 */
public class FacialHairDeserializer implements JsonDeserializer<FacialHair> {

    @Override
    public FacialHair deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) {
        boolean beard = false;
        boolean mustache = false;
        String color = null;

        String hair = element.getAsString();

        if (hair.contains("beard")) {
            beard = true;
        }
        if (hair.contains("mustache")) {
            mustache = true;
        }
        if (beard || mustache) {
            color = hair.substring(0, hair.indexOf(" "));
        }

        return new FacialHair(color, beard, mustache);
    }
}
