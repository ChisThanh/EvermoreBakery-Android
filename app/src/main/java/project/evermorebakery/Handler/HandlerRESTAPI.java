package project.evermorebakery.Handler;

import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.evermorebakery.Interface.InterfaceVolleyResponseListener;

public class HandlerRestAPI
{
    private final RequestQueue requestQueue;
    /** @noinspection SpellCheckingInspection*/
    private final String TOKEN = "3DGZ4XjitciAaD06GQHjOqOL7PVOxGlVpyd6evogBjGUJmMVJUUKlIglXuQi0Qt0Qx9ci4bipqSPWHkVBbclAhzQe2cxzjO0cUhZ";
    public HandlerRestAPI(RequestQueue requestQueue)
    {
        this.requestQueue =  requestQueue;
    }

    public void fetchData(String select, InterfaceVolleyResponseListener listener)
    {
        String url = "https://android-nam3.000webhostapp.com/select.php";
        select(url, select, listener);
    }

    private void select(String url, String select,  InterfaceVolleyResponseListener listener)
    {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("select", select);
        builder.appendQueryParameter("token", TOKEN);
        String finalUrl = builder.build().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, finalUrl, response ->
        {
            if (response != null && !response.isEmpty())
            {
                try
                {
                    JSONArray jsonArray = parseJsonData(response);
                    listener.onResponse(jsonArray);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
            }
            else listener.onError("Empty response");
        },
        error -> listener.onError("Volley error: " + error.getMessage()));

        requestQueue.add(stringRequest);
    }
    public void updateData(String select, InterfaceVolleyResponseListener listener)
    {
        String url = "https://android-nam3.000webhostapp.com/update.php";
        update(url, select, listener);
    }

    private void update(String url, String select, InterfaceVolleyResponseListener listener)
    {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("query", select);
        builder.appendQueryParameter("token", TOKEN);
        String finalUrl = builder.build().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, finalUrl, response ->
        {
            if (response != null && !response.isEmpty())
            {
                try
                {
                    JSONArray jsonArray = parseJsonData(response);
                    listener.onResponse(jsonArray);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
            } else listener.onError("Empty response");
        },
        error -> listener.onError("Volley error: " + error.getMessage()));

        requestQueue.add(stringRequest);
    }

    public JSONArray parseJsonData(String response) throws JSONException
    {
        try
        {
            return new JSONArray(response);
        }
        catch (JSONException e)
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray objArray = new JSONArray();
            objArray.put(jsonObject);
            return objArray;
        }
    }
}
