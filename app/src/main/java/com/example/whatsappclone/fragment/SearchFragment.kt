package com.example.whatsappclone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsappclone.R
import com.example.whatsappclone.adapter.UserSearchItemAdapter
import com.example.whatsappclone.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


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

        val firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid

        val refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

        refUsers.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                (mUser as ArrayList<User>).clear()
                for (snapshot in p0.children) {
                    val user: User? = p0.getValue(User::class.java)
                    if (!(user!!.getUID()).equals(firebaseUserID)) {
                        (mUser as ArrayList<User>).add(user)
                    }
                }

                UserSearchItemAdapter = UserSearchItemAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun searchForUser(str: String) {

        val firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val queryUsers = FirebaseDatabase.getInstance().reference
            .child("Users")
            .orderByChild("search")
            .startAt(str)
            .endAt(str + "\uf8ff ")

        queryUsers.addValueEventListener(object : ValueEventListener {


            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}