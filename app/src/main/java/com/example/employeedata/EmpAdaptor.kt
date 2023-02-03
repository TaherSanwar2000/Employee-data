package com.example.employeedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedata.EmpAdaptor.ViewHolder

class EmpAdaptor(private val empList:ArrayList<EmployeeData>):
    RecyclerView.Adapter<ViewHolder>() {
   inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
       val tvEmp : TextView = itemView.findViewById(R.id.tv_item)
       val tvage : TextView = itemView.findViewById(R.id.tv_item2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvEmp.text = currentEmp.etName
        holder.tvage.text =currentEmp.etAge
    }

    override fun getItemCount(): Int {
        return empList.size
    }
}