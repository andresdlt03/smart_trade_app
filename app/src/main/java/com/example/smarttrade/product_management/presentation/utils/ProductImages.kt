package com.example.smarttrade.product_management.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream

// Convertir Uri a ByteArray
fun convertImageToBytes(uri: Uri, context: Context): ByteArray? {
    // Convertir Uri a Bitmap
    val inputStream = context.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    // Convertir Bitmap a ByteArray
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    return outputStream.toByteArray()
}

// Convertir ByteArray a Bitmap
fun displayImageFromBytes(imageBytes: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

