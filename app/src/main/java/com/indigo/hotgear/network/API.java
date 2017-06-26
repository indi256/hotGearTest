package com.indigo.hotgear.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.indigo.hotgear.HotgearApp;

import org.json.JSONObject;

public class API {
    private static API ourInstance;
    private RequestQueue requestQueue;
    private static Context context;
    private Toast toast;
    private JsonObjectRequest jsonObject;
    final String URL = "https://api.dribbble.com/v1/shots";


    public static synchronized API getInstance() {
        if (ourInstance == null) {
            ourInstance = new API();
        }
        return ourInstance;
    }

    private API() {
        context = HotgearApp.getContext();
        requestQueue = getRequestQueue();

        jsonObject = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                toast = Toast.makeText(context, "Response: " + response.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toast = Toast.makeText(context, "Didn't work", Toast.LENGTH_SHORT);
                toast.show();           }
        });

    }


    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

    public JsonObjectRequest getJsonObject() {
        return jsonObject;
    }
}
