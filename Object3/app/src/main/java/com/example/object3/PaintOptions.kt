package com.example.object3

import android.graphics.Color
import android.graphics.Paint

val paintFinal = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.BLACK
    // Smooths out edges of what is drawn without affecting shape.
    isAntiAlias = true
    // Dithering affects how colors with higher-precision than the device are down-sampled.
    isDither = true
    style = Paint.Style.STROKE // default: FILL
    strokeJoin = Paint.Join.ROUND // default: MITER
    strokeCap = Paint.Cap.ROUND // default: BUTT
    strokeWidth = 10f // default: Hairline-width (really thin)
}


val paintPoint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.BLACK
    style = Paint.Style.FILL_AND_STROKE
    strokeWidth = 10f
}

val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.BLACK
    style = Paint.Style.FILL_AND_STROKE
    textSize = 40F
}


class PaintOptions (finalP: Paint, point: Paint) {
    val final: Paint = finalP
    val point: Paint = point
    val text: Paint = paintText
}

val regPaintOptions = PaintOptions(paintFinal, paintPoint)