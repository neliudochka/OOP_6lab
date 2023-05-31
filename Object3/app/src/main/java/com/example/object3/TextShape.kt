package com.example.object3

import android.graphics.Canvas

class TextShape (val paintOptions: PaintOptions): Shape(paintOptions) {
    private lateinit var text: String
    override fun showFinal(canvas: Canvas) {
        canvas.drawText(text,x1, y1, paintOptions.text)
    }

    fun setText(txt: String){
        text = txt
    }
}