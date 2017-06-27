package com.indigo.hotgear.model;

import android.app.ActivityManager;
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
        imageSize.hiRes = object.optString("hidpi", null);
        imageSize.mediumRes = object.optString("normal", null);
        imageSize.lowRes = object.optString("teaser", null);

        return imageSize;
    }

    public String getHighestSize() {
        if (!TextUtils.isEmpty(hiRes)) {
            return hiRes;
        }
        if (!TextUtils.isEmpty(mediumRes)) {
            return mediumRes;
        }
        if (!TextUtils.isEmpty(lowRes)) {
            return lowRes;
        }
        throw new RuntimeException("Image must have a size");
    }

}
