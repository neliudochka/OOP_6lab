package com.example.object2

import android.annotation.SuppressLint
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.object2.databinding.ActivityMainBinding
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private lateinit var clipboard: ClipboardManager
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val parameters = getData()
        if (parameters != null) {
            val output = processData(parameters)
            copyToClipboard(output)
            startNextActivity()
        }
    }

    private fun getData(): IntArray? {
        if(intent.action == Intent.ACTION_SEND) {
            val param = intent.getSerializableExtra("parameters")
            return param as IntArray
        }
        return null
    }

    private fun processData(parameters: IntArray): String {
        var output = ""
        repeat(parameters[0]) {
            val x = (parameters[1]..parameters[2]).random()
            val y = (parameters[3]..parameters[4]).random()
            addPairToView(x, y)
            output += "$x\t$y\n"
        }
        return output
    }

    @SuppressLint("SetTextI18n")
    private fun addPairToView(x: Int, y:Int) {
        val newPair = TextView(this)
        newPair.text = "X:$x Y:$y"
        newPair.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        binding.holder.addView(newPair)
    }

    private fun copyToClipboard(input: String) {
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("text", input)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun startNextActivity() {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_MAIN
        }
        //щоб доаток відкривався в новому вікні
        intent.setFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        val share = intent.setComponent(ComponentName("com.example.object3",
            "com.example.object3.MainActivity"))
        startActivity(share)
    }
}
