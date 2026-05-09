package com.example.alyah_cat.Message

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarResId: Int // Menggunakan Int untuk ID resource drawable
)