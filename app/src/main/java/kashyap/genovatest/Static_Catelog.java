package kashyap.genovatest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Static_Catelog {

    public static String getStringProperty(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        String res = null;
        if (sharedPreferences != null) {
            res = sharedPreferences.getString(key, null);
        }
        return res;
    }

    public static void setStringProperty(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static void setSuggestion(Context context, List<String> key){
        SharedPreferences settings = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if (settings!=null) {
            SharedPreferences.Editor editor = settings.edit();

            Set<String> myStrings = settings.getStringSet("myStrings", new HashSet<String>());
            myStrings.addAll(key);

            editor.putStringSet("myStrings", myStrings);
            editor.apply();
        }
    }

    public static Set<String> getSuggestion(Context context) {
        SharedPreferences settings= context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        Set<String > res = null;
        if (settings != null) {
            res = settings.getStringSet("myStrings",null);
        }
        return res;
    }







    public static void log(String s)
    {
        Log.i("Static_Catelog", "" + s);
    }

    public static void saveJson(Context context, JSONObject jsonObject)
    {
        SharedPreferences sp = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        if (sp != null) {
            SharedPreferences.Editor mEdit1 = sp.edit();
            int size = sp.getInt("Status_size", 0);
            mEdit1.putString("Status_" + size+1, jsonObject.toString());
            mEdit1.putInt("Status_size", size+1);

            mEdit1.commit();
        }
    }
    public static ArrayList<JSONObject> loadJson(Context context)
    {
        ArrayList<JSONObject> jsonObjects=new ArrayList<>();
        SharedPreferences sp = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        int size=0;
        if (sp != null) {
            size = sp.getInt("Status_size", 0);
        }
        JSONObject obj;
        for(int i=0;i<size;i++)
        {
            try {
                obj=new JSONObject(sp.getString("Status_" + i+1, null));
                jsonObjects.add(obj);
            } catch (JSONException e) {

            }
        }
        return jsonObjects;
    }

    public static void removeSuggestion(Context context, String str) {
        SharedPreferences settings= context.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
        Set<String > res = null;
        if (settings != null) {
            res = settings.getStringSet("myStrings",null);
            try {
                if (res.contains(str)){
                    res.remove(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
}

