package com.example.whatsappclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.databinding.UserSearchItemLayoutBinding
import com.example.whatsappclone.model.User

class UserSearchItemAdapter : RecyclerView.Adapter<UserSearchItemVH>() {

    private val listAdapter = ArrayList<User>()

    fun addData(data: List<User>) {
        listAdapter.clear()
        listAdapter.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserSearchItemLayoutBinding.inflate(layoutInflater, parent, false)
        return UserSearchItemVH(binding)
    }

    override fun onBindViewHolder(holder: UserSearchItemVH, position: Int) {
        val data = listAdapter[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listAdapter.size
    }
}