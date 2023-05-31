package com.example.object3

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.object3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var myCanvasView: CanvasView
    private lateinit var clipboard: ClipboardManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //draw
        initMyCanvasView()

        //getData
        if(intent.action == Intent.ACTION_MAIN){
            var input: String?
            binding.draw.setOnClickListener{
                input = pasteClipboard()
                val coordinates = input?.let { serialize(it) }
                if (coordinates != null) {
                    val sortedCoordinates = sortCoordinates(coordinates)
                    myCanvasView.drawGraph(sortedCoordinates,
                        binding.canvasLayout.height.toFloat(),
                        binding.canvasLayout.width.toFloat())
                    drawTextViews()
                }
            }
        }

    }

    private fun pasteClipboard(): String? {
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if(clipboard.hasPrimaryClip()) {
            val item = clipboard.primaryClip?.getItemAt(0)
            return item?.text.toString()
        }
        return null
    }

    private fun serialize(text: String): MutableList<Coordinates> {
        val coordinates = mutableListOf<Coordinates>()
        val lines = text.split("\n")
        lines.forEach{
            val line = it.split("\t")
            if(!line.contains("")) coordinates.add(Coordinates(line[0].toInt(), line[1].toInt()))
        }
        return coordinates
    }

    private fun sortCoordinates(coordinates: MutableList<Coordinates>): MutableList<Coordinates> {
        coordinates.sortBy { it.x }
        return coordinates
    }

    //drawGraph
    private fun initMyCanvasView () {
        myCanvasView = CanvasView(this)
        myCanvasView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        binding.canvasLayout.addView(myCanvasView)
    }

    private fun drawTextViews() {
        val rowW = binding.row.width.toFloat()
        val x = TextView(this)
        x.text = "X"
        x.x = rowW - rowW/40
        x.y = 0F
        binding.row.addView(x)


        val columnW = binding.column.width.toFloat()
        val y = TextView(this)
        y.text = "Y"
        y.x = columnW - columnW/5
        y.y = 0F
        binding.column.addView(y)

        val zero = TextView(this)
        zero.text = "0"
        zero.x = columnW -columnW/5
        zero.y = 0F
        binding.row.addView(zero)
    }
}