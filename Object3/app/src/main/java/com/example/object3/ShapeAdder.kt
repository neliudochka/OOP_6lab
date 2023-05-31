package com.example.object3

class ShapeAdder {
    companion object {
        fun addShape(shape: Shape, shapes: MutableList<Shape>) {
            shapes.add(shape)
        }
    }
}