package com.example.object3

import android.graphics.Canvas

class LineShape (var paintOptions: PaintOptions): Shape(paintOptions) {

    override fun showFinal(canvas: Canvas) {
        canvas.drawLine(x1, y1, x2, y2, paintOptions.final)
    }

}