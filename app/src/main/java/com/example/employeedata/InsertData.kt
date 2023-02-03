package com.example.employeedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.android.gms.common.internal.Objects.ToStringHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.System.err

class InsertData : AppCompatActivity() {
    private lateinit var etName : EditText
    private lateinit var etAge : EditText
    private lateinit var etSalary : EditText
    private lateinit var btnSaveData : Button

    private  lateinit var dbf : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        etSalary = findViewById(R.id.et_salary)
        btnSaveData = findViewById(R.id.btn_saveData)

        dbf = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener{
            saveData()
        }
    }

    private fun saveData() {
        val E_Name = etName.text.toString()
        val E_Age = etAge.text.toString()
        val E_Salary = etSalary.text.toString()

        if(E_Name.isEmpty()){
            etName.error = "Please Enter Name"
        }
        if(E_Age.isEmpty()){
            etAge.error = "Please Enter Age"
        }
        if(E_Salary.isEmpty()){
            etSalary.error = "Please Enter Salary"
        }

        val empID = dbf.push().key!!

        val employee = EmployeeData(empID,E_Name,E_Age,E_Salary)

        dbf.child(empID).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this,"Data is Inserted",Toast.LENGTH_LONG).show()
                etName.text.clear()
                etAge.text.clear()
                etSalary.text.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this,"Error ${err.message} ",Toast.LENGTH_LONG).show()
            }
    }
}