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

            String mainName = object.getJSONObject("name").getString("mainName");
            List<String> alsoKnownAs = jsonArrayToList(object.getJSONObject("name").getJSONArray("alsoKnownAs"));

            String placeOfOrigin = object.getString("placeOfOrigin");
            String description = object.getString("description");
            String image = object.getString("image");

            List<String> ingredients = jsonArrayToList(object.getJSONArray("ingredients"));

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
}
