package com.example.textdetector

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomeActivity : AppCompatActivity() {

    lateinit var menuu: ImageView
    lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        popmenu();

        menuu = findViewById(R.id.kebab_menu)
        menuu.setOnClickListener(View.OnClickListener {
            showMessage("Do you want to logout?","LOGOUT",{_,_->logOut()},"CANCEL",{p0, p1->p0.dismiss()
            },false)
        })

        mAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = mAuth.getCurrentUser()
        if (currentUser == null) {
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