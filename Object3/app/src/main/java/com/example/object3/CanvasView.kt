package com.example.object3

import android.content.Context
import android.graphics.*
import android.view.View

class CanvasView(context: Context) :  View(context) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in shapes) {
            i.showFinal(canvas)
        }
    }

    private val shapes = mutableListOf<Shape>()


    //for drawing
    private fun drawLine(x1: Float, y1: Float, x2: Float, y2: Float) {
        val currentShape = LineShape(regPaintOptions)
        currentShape.setStartCoords(x1, y1)
        currentShape.setEndCoords(x2, y2)
        ShapeAdder.addShape(currentShape, shapes)
    }

    private fun drawPoint(x1: Float, y1: Float) {
        val currentShape = PointShape(regPaintOptions)
        currentShape.setStartCoords(x1, y1)
        ShapeAdder.addShape(currentShape, shapes)
    }

    private fun drawText(text: String, x1: Float, y1: Float) {
        val currentShape = TextShape(regPaintOptions)
        currentShape.setStartCoords(x1, y1)
        currentShape.setText(text)
        ShapeAdder.addShape(currentShape, shapes)
    }

    fun drawGraph(
        coordinates: MutableList<Coordinates>,
        height: Float,
        width: Float
    ) {
        setParam(coordinates, height, width)
        drawCL()
        drawIntervals(xMax.toInt(), yMax.toInt())
        drawFunction(coordinates)
        invalidate()
    }

    private fun setParam(coordinates: MutableList<Coordinates>, height: Float, width: Float) {
        cHeight = height
        cWidth = width
        cHeightPadding = cHeight/40
        cWidthPadding = cWidth/40
        xMax = coordinates.last().x.toFloat()
        yMax = getYMax(coordinates)
        xInterval = cWidth/(xMax+1)
        yInterval = cHeight/(yMax+1)
    }

    private var cHeight: Float = 0F
    private var cWidth: Float = 0F
    private var cHeightPadding: Float = 0F
    private var cWidthPadding: Float = 0F
    private var xInterval: Float = 0F
    private var yInterval: Float = 0F
    private var xMax: Float = 0F
    private var yMax: Float = 0F

    private fun getYMax(coordinates: MutableList<Coordinates>): Float {
        coordinates.sortBy { it.y }
        return coordinates.last().y.toFloat()
    }

    private fun drawCL() {
        drawLine(cWidthPadding,cHeight-cHeightPadding, cWidth-cWidthPadding,cHeight-cHeightPadding)
        drawLine(cWidthPadding, cHeightPadding, cWidthPadding, cHeight-cHeightPadding)
    }

    fun drawIntervals(xCount: Int, yCount: Int) {
        var x = cWidthPadding + xInterval
        val y1 = cHeight
        val y2 = cHeight - 2*cHeightPadding
        var textX = 1
        repeat(xCount) {
            drawLine(x, y1, x, y2)
            drawText(textX.toString(),x+10F,y1-5F)
            x += xInterval
            textX++
            println("try"+x+"c"+cWidth)
        }

        var y = cHeight-cHeightPadding - yInterval
        val x1 = 0F
        val x2 = 2*cWidthPadding
        var textY = 1
        repeat(yCount) {
            drawLine(x1, y, x2, y)
            drawText(textY.toString(),x1,y-10F)
            textY++
            y -= yInterval
        }
    }

    fun drawFunction(coordinates: MutableList<Coordinates>) {
        coordinates.sortBy { it.x }
        var oldX: Float? = null
        var oldY: Float? = null
        val upTo = coordinates.count() - 1
        for(i in 0..upTo) {
            val newCoord = coordinates[i]
            val x = cWidthPadding + xInterval * newCoord.x
            val y = cHeight - cHeightPadding - yInterval * newCoord.y
            drawPoint(x, y)
            if(oldX != null && oldY != null){
                drawLine(oldX, oldY, x, y)
            }
            oldX = x
            oldY = y
        }
    }
}

