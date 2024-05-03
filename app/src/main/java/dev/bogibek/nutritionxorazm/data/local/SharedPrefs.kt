package dev.bogibek.nutritionxorazm.data.local

import android.content.Context

class SharedPrefs(context: Context) {
    private val pref = context.getSharedPreferences("nutrition_prefs", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String?) {
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return pref.getString(key, null)
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    fun saveInt(key: String, value: Int) {
        val editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveUserId(value: Long) {
        val editor = pref.edit()
        editor.putLong("UserId", value)
        editor.apply()
    }

    fun getUserId(): Long {
        return pref.getLong("UserId", 0)
    }

    fun getInt(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun saveFloat(key: String, value: Float) {
        val editor = pref.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getFloat(key: String): Float {
        return pref.getFloat(key, 0F)
    }

    fun clear() {
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }

}