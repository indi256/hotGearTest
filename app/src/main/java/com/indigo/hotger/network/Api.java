package com.indigo.hotger.network;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.indigo.hotger.HotgerApp;
import com.indigo.hotger.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Api {
    private static final String TAG = "API";
    private static Api ourInstance;
    private RequestQueue requestQueue;
    private static Context context;
    private JsonObjectRequest jsonObject;
    final String BASE_URL = "https://api.dribbble.com/v1";


    public static synchronized Api getInstance() {
        if (ourInstance == null) {
            ourInstance = new Api();
        }
        return ourInstance;
    }

    private Api() {
        context = HotgerApp.getContext();
        requestQueue = getRequestQueue();

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


    public void callMethod(Method method, @Nullable HashMap<String, String> par, final ResponseListener listener) {
        final String url = BASE_URL + Method.GET_SHOTS.getUrl();
        HashMap<String, String> params = new HashMap<>();

        if (par != null) {
            params.putAll(par);
        }
        params.put("access_token", context.getString(R.string.dribbble_api_key));


        JsonArrayRequest request = new JsonArrayRequest(method.getRequestMethod(), appendParams(params, url), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("=========JSON RESPONSE=========");
                Log.d(TAG, "For request: " + url);
                Log.d(TAG, response.toString());
                System.out.println("===============================");
                try {
                    listener.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError();
            }
        });

        addToRequestQueue(request);
    }

    private String appendParams(HashMap<String, String> params, String baseUrl) {
        StringBuilder sb = new StringBuilder(baseUrl);

        List<String> list = new ArrayList<>(params.keySet());


        for (String s : list) {
            if (list.indexOf(s) == 0) {
                sb.append("?");
            } else {
                sb.append("&");
            }
            sb.append(s);
            sb.append("=");
            sb.append(params.get(s));

        }
        return sb.toString();

    }

    public interface ResponseListener {
        void onSuccess(JSONArray object) throws JSONException;

        void onError();
    }


}
