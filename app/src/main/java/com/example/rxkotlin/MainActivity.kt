package com.example.rxkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.example.rxkotlin.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  val TAG = "MainActivity"
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
          ozgartirishniEshitish().subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .debounce (5L,TimeUnit.SECONDS)       //5 sekunddan keyin ish faqat bacground uchun viewlar uchun emas
              .filter {
                 it.length==4
              }
              .map {
                  it+"Botir"
              }

              .subscribe {
                  Log.d(TAG, "onCreate: jdjkqaslqedsjbsLJFBJVSFBVBC")
                  binding.textView.text=it
              }


    }


    fun ozgartirishniEshitish():Observable<String>{
        return Observable.create<String>{emitter->

            binding.edtName.addTextChangedListener{
                emitter.onNext(binding.edtName.text.toString())
            }
        }
    }
}