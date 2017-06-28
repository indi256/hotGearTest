package com.indigo.hotger.model;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

public class ImageSize extends RealmObject {
    private String hiRes;
    private String mediumRes;
    private String lowRes;

    //region getters and setters
    public String getHiRes() {
        return hiRes;
    }

    public void setHiRes(String hiRes) {
        this.hiRes = hiRes;
    }

    public String getMediumRes() {
        return mediumRes;
    }

    public void setMediumRes(String mediumRes) {
        this.mediumRes = mediumRes;
    }

    public String getLowRes() {
        return lowRes;
    }

    public void setLowRes(String lowRes) {
        this.lowRes = lowRes;
    }
    //endregion

    public static ImageSize fromJson(JSONObject object) throws JSONException {
        ImageSize imageSize = new ImageSize();
        imageSize.hiRes = checkForNull(object, "hires");
        imageSize.mediumRes = checkForNull(object, "normal");
        imageSize.lowRes = checkForNull(object, "teaser");

        return imageSize;
    }

    public String getHighestSize() {
        if (!TextUtils.isEmpty(hiRes)) {
            System.out.println("High Res");
            return hiRes;
        } else if (!TextUtils.isEmpty(mediumRes)) {
            System.out.println("Medium Res");
            return mediumRes;
        } else if (!TextUtils.isEmpty(lowRes)) {
            System.out.println("Low Res");
            return lowRes;
        }
        throw new RuntimeException("Image must have a size");


    }

    public static String checkForNull(JSONObject jsonObject, String key) {
        if (jsonObject.isNull(key)) {
            return null;
        } else {
            return jsonObject.optString(key, null);
        }
    }

}
