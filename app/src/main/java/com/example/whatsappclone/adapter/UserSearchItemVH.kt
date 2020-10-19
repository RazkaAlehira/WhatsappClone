package com.example.whatsappclone.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.databinding.UserSearchItemLayoutBinding
import com.example.whatsappclone.model.User
import de.hdodenhof.circleimageview.CircleImageView

class UserSearchItemVH(private val binding: UserSearchItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    private val circleImageView: CircleImageView = binding.profileImage
    private val text: TextView = binding.username
    private val circleImageView2: CircleImageView = binding.imageOnline
    private val circleImageView3: CircleImageView = binding.imageOffline
    private val circleImageView4: TextView = binding.messageLast

    fun bind(adapter: User) {}
}