package project.evermorebakery.Interface;

import org.json.JSONArray;

public interface VolleyResponseListener {
    void onResponse(JSONArray response);
    void onError(String errorMessage);
}
