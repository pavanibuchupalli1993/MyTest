package com.example.mytest

import android.widget.RelativeLayout

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import android.widget.Toast

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton


class MyListAdapter(listdata: Array<String>) :
    RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
    private val listdata: Array<String>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData: String = listdata[position]
        holder.textView.setText(myListData)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
               if(holder.textView.isChecked)
               {
                   holder.textView.isChecked=false
               }else
               {
                   holder.textView.isChecked=true
               }
            }
        })
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: CheckBox


        init {

            textView = itemView.findViewById(R.id.item)

        }
    }

    // RecyclerView recyclerView;
    init {
        this.listdata = listdata
    }
}