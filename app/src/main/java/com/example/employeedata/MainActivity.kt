package com.example.employeedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var  buttonInsert : Button
    private lateinit var  buttonFetch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonInsert = findViewById(R.id.btn_insert)
        buttonFetch = findViewById(R.id.btn_fetch)

        buttonInsert.setOnClickListener{
            saveData()
        }
        buttonFetch.setOnClickListener{
            fetchData()
        }
    }

    private fun fetchData() {
        val intent = Intent(this,FetchData::class.java)
        startActivity(intent)


    }

    private fun saveData() {
        val intent = Intent(this,InsertData::class.java)
        startActivity(intent)

    }
}