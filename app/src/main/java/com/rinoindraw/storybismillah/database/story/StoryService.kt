package com.rinoindraw.storybismillah.database.story

import com.rinoindraw.storybismillah.database.model.AddStoriesResponse
import com.rinoindraw.storybismillah.database.model.GetStoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface StoryService {

    @GET("stories")
    suspend fun getAllStories(
        @Header("Authorization") token: String,
        @Query("size") size: Int
    ): GetStoriesResponse

    @Multipart
    @POST("stories")
    suspend fun addNewStories(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ): AddStoriesResponse

}