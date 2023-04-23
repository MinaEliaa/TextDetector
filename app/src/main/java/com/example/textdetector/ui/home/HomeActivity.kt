package com.example.textdetector.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.textdetector.R
import com.example.textdetector.ui.home.fragments.AddFragment
import com.example.textdetector.ui.home.fragments.HomeFragment
import com.example.textdetector.ui.home.fragments.archive.ArchiveFragment
import com.example.textdetector.ui.login.LoginActivity
import com.facebook.AccessToken
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class HomeActivity : AppCompatActivity() {

    lateinit var bottoNavigation:BottomNavigationView
    lateinit var menuu: ImageView
    lateinit var mAuth:FirebaseAuth
    var accessToken: AccessToken? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottoNavigation=findViewById(R.id.bottom_nav_bar)
        bottoNavigation.setOnItemSelectedListener (NavigationBarView.OnItemSelectedListener {
            menuitem->
            if(menuitem.itemId == R.id.navigation_home){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_contanier,HomeFragment())
                    .commit()
            }
            else if (menuitem.itemId  == R.id.navigation_add){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_contanier, AddFragment())
                    .commit()
            }
            else if (menuitem.itemId == R.id.navigation_archive){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_contanier, ArchiveFragment())
                    .commit()
            }
            return@OnItemSelectedListener true
        })
        bottoNavigation.selectedItemId=R.id.navigation_home

        menuu = findViewById(R.id.kebab_menu)
        menuu.setOnClickListener(View.OnClickListener {
            showMessage("Do you want to logout?","LOGOUT",{_,_->logOut()},"CANCEL",{p0, p1->p0.dismiss()
            },false)
        })

        mAuth = FirebaseAuth.getInstance()
        accessToken = AccessToken.getCurrentAccessToken();
        val currentUser: FirebaseUser? = mAuth.getCurrentUser()
        if (currentUser == null && accessToken == null) {
            val i = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(i)
        }





    }


    fun logOut(){
        FirebaseAuth.getInstance().signOut();
        val i = Intent(this@HomeActivity, LoginActivity::class.java)
        startActivity(i)
    }

    var alertDialog: AlertDialog? = null

    fun showMessage(
        message: String,
        posActionTitle: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionTitle: String? = null,
        negAction : DialogInterface.OnClickListener? = null, cancelLabel:Boolean = true)
    {
        var messageDialogBuilder = AlertDialog.Builder(this)
        messageDialogBuilder.setMessage(message)
        if (posAction != null){
            messageDialogBuilder.setPositiveButton(
                posActionTitle,posAction
            )
        }

        if (negAction != null){
            messageDialogBuilder.setNegativeButton(
                negActionTitle,negAction
            )
        }
        messageDialogBuilder.setCancelable(cancelLabel)

        alertDialog = messageDialogBuilder.show()

    }

//    private fun popmenu() {
//        val popupMenu = PopupMenu(applicationContext,kebab_menu)
//        popupMenu.inflate(R.menu.popup_menu)
//        popupMenu.setOnMenuItemClickListener {
//        when (it.itemId){
//            R.id.nav_logout -> {
//                FirebaseAuth.getInstance().signOut();
//                val i = Intent(this@HomeActivity, LoginActivity::class.java)
//                startActivity(i)
//                true
//            }
//
//            else -> {}
//        }
//        }
//    }
}