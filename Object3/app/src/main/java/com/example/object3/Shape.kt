package com.example.object3

import android.graphics.Canvas
import com.example.object3.PaintOptions

abstract class Shape (paintOptions: PaintOptions) {
    protected var x1: Float = 0f
    protected var y1: Float = 0f
    protected var x2: Float = 0f
    protected var y2: Float = 0f

    open fun setStartCoords(x: Float, y: Float) {
        x1 = x
        y1 = y
    }

    fun setEndCoords(x: Float, y: Float) {
        x2 = x
        y2 = y
    }

    open fun showFinal(canvas: Canvas) {

    }
}