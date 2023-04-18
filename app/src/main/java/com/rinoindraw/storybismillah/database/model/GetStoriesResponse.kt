package com.rinoindraw.storybismillah.database.model

import com.google.gson.annotations.SerializedName
import com.rinoindraw.storybismillah.database.model.Story

data class GetStoriesResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<Story>
)