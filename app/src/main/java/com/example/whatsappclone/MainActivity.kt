package com.example.whatsappclone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whatsappclone.databinding.ActivityMainBinding
import com.example.whatsappclone.fragment.ChatFragment
import com.example.whatsappclone.fragment.SearchFragment
import com.example.whatsappclone.fragment.SettingsFragment
import com.example.whatsappclone.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var userCurrent: User
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(ChatFragment(), "Chat")
        viewPagerAdapter.addFragment(SearchFragment(), "Search")
        viewPagerAdapter.addFragment(SettingsFragment(), "Settings")

        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        mAuth = FirebaseAuth.getInstance()
        userId = mAuth.currentUser?.uid.toString()
        dbRef = FirebaseDatabase.getInstance().reference.child("Users").child(userId)
        dbRef.addListenerForSingleValueEvent (
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    userCurrent = snapshot.getValue(User::class.java) as User
                    user_name.text = userCurrent.username
                    if (userCurrent.profile.isNotBlank()) Picasso.get().load(userCurrent.profile)
                        .centerCrop().placeholder(R.drawable.profile).into(profile_image)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                mAuth.signOut()
                val intentWelcome = Intent(this, WelcomeActivity::class.java)
                intentWelcome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentWelcome)
                toast("User Log Out")
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    internal class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragments = arrayListOf<Fragment>()
        private val titles = arrayListOf<String>()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}