package com.indigo.hotgear.model;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Shot extends RealmObject {
    private String title;
    private String description;
    @PrimaryKey
    private long id;
    private boolean animated;
    private ImageSize imageSize;


    public static Shot fromJson(JSONObject object) throws JSONException {
        Shot shot = new Shot();
        shot.id = object.getLong("id");
        if (object.has("title") && !object.isNull("title")) {
            shot.title = object.getString("title");
        }
        if (object.has("description") && !object.isNull("description")) {
            shot.description = object.getString("description");
        }
        shot.animated = object.optBoolean("animated", false);

        shot.imageSize = ImageSize.fromJson(object.getJSONObject("images"));

        copyToRealmOrUpdate(shot);

        return shot;

    }

    public static void copyToRealmOrUpdate(final Shot shot) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(shot);
            }
        });

    }

    //region getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }
    //endregion

}
