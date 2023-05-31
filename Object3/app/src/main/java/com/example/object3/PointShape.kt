package com.example.object3

import android.graphics.Canvas

class PointShape (val paintOptions: PaintOptions): Shape(paintOptions) {

    override fun showFinal(canvas: Canvas) {
        canvas.drawCircle(x1, y1, 10f, paintOptions.point)
    }

}