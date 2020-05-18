package kashyap.genovatest

import android.app.Activity
import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object Static_Catelog {
    fun getStringProperty(context: Context, key: String?): String? {
        val sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE)
        var res: String? = null
        if (sharedPreferences != null) {
            res = sharedPreferences.getString(key, null)
        }
        return res
    }

    fun setStringProperty(context: Context, key: String?, value: String?) {
        val sharedPreferences = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE)
        if (sharedPreferences != null) {
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.commit()
        }
    }

    fun setSuggestion(context: Context, key: List<String>?) {
        val settings = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        if (settings != null) {
            val editor = settings.edit()
            val myStrings = settings.getStringSet("myStrings", HashSet())
            myStrings!!.addAll(key!!)
            editor.putStringSet("myStrings", myStrings)
            editor.apply()
        }
    }

    fun getSuggestion(context: Context): Set<String>? {
        val settings = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE)
        var res: Set<String>? = null
        if (settings != null) {
            res = settings.getStringSet("myStrings", null)
        }
        return res
    }

    fun log(s: String) {
        Log.i("Static_Catelog", "" + s)
    }

    fun saveJson(context: Context, jsonObject: JSONObject) {
        val sp = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE)
        if (sp != null) {
            val mEdit1 = sp.edit()
            val size = sp.getInt("Status_size", 0)
            mEdit1.putString("Status_" + size + 1, jsonObject.toString())
            mEdit1.putInt("Status_size", size + 1)
            mEdit1.commit()
        }
    }

    fun loadJson(context: Context): ArrayList<JSONObject> {
        val jsonObjects = ArrayList<JSONObject>()
        val sp = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE)
        var size = 0
        if (sp != null) {
            size = sp.getInt("Status_size", 0)
        }
        var obj: JSONObject
        for (i in 0 until size) {
            try {
                obj = JSONObject(sp!!.getString("Status_" + i + 1, null))
                jsonObjects.add(obj)
            } catch (e: JSONException) {
            }
        }
        return jsonObjects
    }

    fun removeSuggestion(context: Context, str: String?) {
        val settings = context.getSharedPreferences("preferences", Activity.MODE_PRIVATE)
        var res: MutableSet<String?>? = null
        if (settings != null) {
            res = settings.getStringSet("myStrings", null)
            try {
                if (res!!.contains(str)) {
                    res.remove(str)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}