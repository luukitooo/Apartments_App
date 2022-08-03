package com.example.apartmentsapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApartmentInfo(
    val content: List<Content>
) {
    data class Content(
        val id: String,
        val descriptionKA: String,
        val titleKA: String,
        val cover: String,
        @SerializedName("publish_date")
        val publishDate: String
    ): Serializable
}