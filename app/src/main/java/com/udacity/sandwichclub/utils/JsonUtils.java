package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static final String NO_DATA_AVAILABLE = "No data available";


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich;

        try {
            JSONObject object = new JSONObject(json);

            String mainName = defaultValueIfNull(object.getJSONObject(NAME).getString(MAIN_NAME));
            List<String> alsoKnownAs = defaultValueIfNull(jsonArrayToList(object.getJSONObject(NAME).getJSONArray(ALSO_KNOWN_AS)));

            String placeOfOrigin = defaultValueIfNull(object.getString(PLACE_OF_ORIGIN));
            String description = defaultValueIfNull(object.getString(DESCRIPTION));
            String image = object.getString(IMAGE);

            List<String> ingredients = defaultValueIfNull(jsonArrayToList(object.getJSONArray(INGREDIENTS)));

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image,
                    ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return sandwich;
    }

    private static List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }


    private static String defaultValueIfNull(String input) {
        if (input == null || input.equals("")) {
            input = NO_DATA_AVAILABLE;
        }

        return input;
    }


    private static List<String> defaultValueIfNull(List<String> input) {
        if (input == null || input.size() == 0) {
            input.add(NO_DATA_AVAILABLE);
        }

        return input;
    }
}
