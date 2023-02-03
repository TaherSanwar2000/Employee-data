package com.example.employeedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.*

class FetchData : AppCompatActivity() {

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var empList: ArrayList<EmployeeData>
    private lateinit var  dbf: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_data)

        empRecyclerView = findViewById(R.id.rv_emp)
        empRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        empRecyclerView.setHasFixedSize(true)

        empList = arrayListOf<EmployeeData>()

        getEmployeeData()

    }

    private fun getEmployeeData() {
        empRecyclerView.visibility = View.GONE

        dbf = FirebaseDatabase.getInstance().getReference("Employees")

        dbf.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if(snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(EmployeeData::class.java)
                        empList.add(empData!!)
                    }
                    val mAdaptor = EmpAdaptor(empList)
                    empRecyclerView.adapter = mAdaptor

                    empRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}