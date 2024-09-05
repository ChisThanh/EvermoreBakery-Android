package project.evermorebakery.Interface;

import org.json.JSONArray;

public interface InterfaceVolleyResponseListener
{
    void onResponse(JSONArray response);
    void onError(String errorMessage);
}
