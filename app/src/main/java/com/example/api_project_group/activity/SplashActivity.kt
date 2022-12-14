package com.example.api_project_group.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.api_project_group.R
import com.example.api_project_group.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var dbUser = sharedPref.getString("username", "")

        //splash : seleksi data di sharedpreferencenya ada atau ngga.
        if(dbUser == "") {
            var dbUsername = sharedPref.getString("username", "You!")
            var bundle = Bundle()
            bundle.putString("username", dbUsername)

            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
        else {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}