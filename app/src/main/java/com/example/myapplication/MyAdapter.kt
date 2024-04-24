package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val memoriesList :  ArrayList<Memory>) : RecyclerView.Adapter<MyAdapter.MyViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.memory,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
     val currentitem = memoriesList[position]
        var memoryitem = Memory("1", currentitem.title, currentitem.content, currentitem.date)
        holder.title.text = currentitem.title
        holder.content.text = currentitem.content
        holder.day.text =memoryitem.getDay().toString()
         var memyeaar = memoryitem.getYear()
        var memmonth = memoryitem.getMonth().toString()
        var res = memmonth + " " + memyeaar
        holder.monthYear.text =res
    }

    override fun getItemCount(): Int {
        return memoriesList.size
    }

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
     val title : TextView = itemView.findViewById(R.id.title)
    val content : TextView = itemView.findViewById(R.id.content)
        val day : TextView = itemView.findViewById(R.id.day)
        val monthYear : TextView = itemView.findViewById(R.id.monthYear)
    }


}