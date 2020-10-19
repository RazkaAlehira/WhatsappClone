package com.example.whatsappclone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsappclone.R
import com.example.whatsappclone.adapter.UserSearchItemAdapter
import com.example.whatsappclone.adapter.UserSearchItemVH
import com.example.whatsappclone.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SearchFragment : Fragment() {

    private var UserSearchItemAdapter: UserSearchItemAdapter? = null
    private var mUser: List<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mUser = ArrayList()
        retrieveAlluser()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun retrieveAlluser() {
        var firebaseUser: FirebaseAuth
    }
}