package project.evermorebakery.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import project.evermorebakery.Model.ModelAccount;

public class HelperSharedPreferences
{
    private static final String SHARED_PREFERENCES_NAME = "account";
    private static final String MODEL = "saved_account";

    private final SharedPreferences shared_preferences;
    private final Gson gson;

    public HelperSharedPreferences(Context context)
    {
        shared_preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveAccount(ModelAccount account)
    {
        SharedPreferences.Editor editor = shared_preferences.edit();
        String jsonString = gson.toJson(account);
        editor.putString(MODEL, jsonString);
        editor.apply();
    }

    public ModelAccount getSavedAccount()
    {
        String jsonString = shared_preferences.getString(MODEL, null);
        if (jsonString != null) return gson.fromJson(jsonString, ModelAccount.class);
        return null;
    }

    public void clearSavedAccount()
    {
        SharedPreferences.Editor editor = shared_preferences.edit();
        editor.remove(MODEL);
        editor.apply();
    }
}
