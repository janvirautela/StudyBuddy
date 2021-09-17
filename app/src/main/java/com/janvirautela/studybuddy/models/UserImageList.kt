package com.janvirautela.studybuddy.models

import com.google.firebase.firestore.PropertyName

data class UserImageList(
    @PropertyName("Images") val images: List<String>? = null
)
