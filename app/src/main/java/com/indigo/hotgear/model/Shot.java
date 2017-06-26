package com.indigo.hotgear.model;

import com.google.gson.annotations.SerializedName;

public class Shot {
    @SerializedName("title")
    private Title title;
    @SerializedName("description")
    private Descr descr;
    @SerializedName("images")
    private ImageSize imageSize;
    @SerializedName("animated")
    private Animated animated;


}
