package com.example.api_project_group.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.api_project_group.R
import com.example.api_project_group.ResponseDataUserItem
import com.example.api_project_group.RetrofitClientUser
import com.example.api_project_group.UserAdapter
import com.example.api_project_group.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var sharedpref : SharedPreferences
    lateinit var adapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sharedpref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            //data inputan user
            var inputUsername = binding.editUsernameLog.text.toString()
            var inputPass = binding.editPasswordLog.text.toString()

            if(inputUsername != null && inputPass !=null) requestLogin(inputUsername, inputPass)
            else if(inputUsername == null && inputPass == null) toast("Empty Username or Password!")
        }

        binding.txtRegister.setOnClickListener{
            val pindah = Intent(this, RegisterActivity::class.java)
            startActivity(pindah)
        }

        // kalo gada sharedpref, masuk ke login trus dicek datanya.
        // kalo gada di api, gagal masuk harus regis dulu.
        // di register input data ke api trus back to login.
        // kalo ada di api, ntar otomatis kesimpen di shared trus masuk ke home.
    }

    fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }

    fun requestLogin(username:String, password:String){
        RetrofitClientUser.instance.getAllUser().enqueue(object : Callback<List<ResponseDataUserItem>>{
            override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        val respon = response.body()
                        for (i in 0 until respon!!.size){
                            if(respon[i].username.equals(username) && respon[i].password.equals(password)){
                                toast("Login Success!")
                                var pinda = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(pinda)
                            }
                        }
                    }
                    else toast("Empty Response!")
                }
                else toast("Failed Load Data!")
            }

            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}