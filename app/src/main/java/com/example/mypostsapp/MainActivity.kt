package com.example.mypostsapp

import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchPosts()
    }
    fun fetchPosts(){
        var retrofit=ApiClient.buildService(ApiInterface::class.java)
        var request=retrofit.getPosts() //Defining the request
        request.enqueue(object :retrofit2.Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
               if (response.isSuccessful){
                   var postsList=response.body()
                   Toast.makeText(baseContext,postsList!!.size.toString(),Toast.LENGTH_LONG).show()

               }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()

            }
        })
    }
}