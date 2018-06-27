package com.ivan.newtechnologies.gson.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ivan.newtechnologies.gson.model.FacialHair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ivan_Stankov on 12.02.2016.
 */
public class FacialHairSerializer implements JsonSerializer<FacialHair> {

    @Override
    public JsonElement serialize(FacialHair facialHair, Type type, JsonSerializationContext jsonSerializationContext) {
        List<String> hairs = new ArrayList<>();
        if (facialHair.isHasMustache()) {
            hairs.add("mustache");
        }
        if (facialHair.isHasBeard()) {
            hairs.add("beard");
        }
        String result;
        if (hairs.size() == 0) {
            result = "Is he really dwarf???";
        } else {
            result = facialHair.getColor() + " " + hairs.stream().collect(Collectors.joining(" and "));
        }
        return new JsonPrimitive(result);
    }

}
