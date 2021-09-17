package com.janvirautela.studybuddy.models

data class MemoryCard(
    val identifier: Int,
    val ImageUrl: String? = null,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)