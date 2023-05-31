package com.example.a6lab

import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.a6lab.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initParam()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initParam() {
        val parameters = arrayOf(
            binding.nPoint,
            binding.xMin,
            binding.xMax,
            binding.yMin,
            binding.yMax)
        binding.execute.setOnClickListener {
            val output = getOutput(parameters)
            output?.let { it1 -> share(it1) }
        }
    }

    private fun getOutput(parameters: Array<TextInputEditText>): IntArray? {
        if(checkParam(parameters)){
            val output = makeOutput(parameters)
            if(checkOutput(parameters, output)){
                return output.toIntArray()
            }
        }
        return null
    }
    private fun checkParam(parameters: Array<TextInputEditText>): Boolean {
        parameters.forEach {
            println("text"+it.text)
            if(it.text.toString() == "") {
                it.error = "Порожнє поле"
                return false
            }
        }
        return true
    }

    private fun makeOutput(parameters: Array<TextInputEditText>): MutableList<Int> {
        val output = mutableListOf<Int>()
        parameters.forEach {
            output.add(it.text.toString().toInt())
        }
        return output
    }

    private fun checkOutput(parameters: Array<TextInputEditText>, output: MutableList<Int>): Boolean {
        if(output[0]<=0){
            parameters[0].error = "nPoint > 0"
            return false
        }
        if(output[1]>=output[2]){
            parameters[1].error = "xMin < xMax"
            parameters[2].error = "xMin < xMax"
            return false
        }
        if(output[3]>=output[4]){
            parameters[3].error = "yMin < yMax"
            parameters[4].error = "yMin < yMax"
            return false
        }
        parameters.forEach { it.error = null }
        return true
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun share(output: IntArray) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra("parameters",output)
        }
        //якщо акт існувало, то очищується перед запуском
        intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        val share = intent.setComponent(ComponentName("com.example.object2",
            "com.example.object2.MainActivity"))
        startActivity(share)
    }
}