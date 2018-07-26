package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich;

        try {
            JSONObject object = new JSONObject(json);

            String mainName = defaultValueIfNull(object.getJSONObject("name").getString("mainName"));
            List<String> alsoKnownAs = defaultValueIfNull(jsonArrayToList(object.getJSONObject("name").getJSONArray("alsoKnownAs")));

            String placeOfOrigin = defaultValueIfNull(object.getString("placeOfOrigin"));
            String description = defaultValueIfNull(object.getString("description"));
            String image = object.getString("image");

            List<String> ingredients = defaultValueIfNull(jsonArrayToList(object.getJSONArray("ingredients")));

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
            input = "No data available";
        }

        return input;
    }


    private static List<String> defaultValueIfNull(List<String> input) {
        if (input.size() == 0) {
            input.add("No data available");
        }

        return input;
    }
}
