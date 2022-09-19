package com.example.flmysqlconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flmysqlconnect.databinding.ActivitySecondBinding
import com.example.flmysqlconnect.network.DjangoAPI
import com.example.flmysqlconnect.network.RetrofitObject
import com.example.flmysqlconnect.network.Status
import com.example.flmysqlconnect.network.Table1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //будет происходить переход на первую активность
        binding.toFirst.setOnClickListener {
            startActivity(Intent(this@SecondActivity,MainActivity::class.java))
        }
        //Обработка события кнопки select. Вторая кнопка работает аналогично
        binding.selectData.setOnClickListener {
            //получение объекта для работы с сетью
            val retrofit = RetrofitObject.getInstance()
            //подключение API и обращение к серверу. onResponse - обработка успешного обращения к серверу, onFailure - ошибки.
            retrofit.create<DjangoAPI>().select().enqueue(object :Callback<Table1>{
                override fun onResponse(call: Call<Table1>, response: Response<Table1>) {
                    Log.d("MyActivity",response.raw().toString())
                    binding.textView.text = response.body()?.data2.toString()
                }
                override fun onFailure(call: Call<Table1>, t: Throwable) {
                    t.printStackTrace()
                    binding.textView.text = resources.getString(R.string.network_error_text)
                }

            })
        }

        binding.insertData.setOnClickListener {
            val retrofit = RetrofitObject.getInstance()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")
            val time = LocalDateTime.now().format(formatter)
            val table1 = Table1()
            table1.data2 = time
            retrofit.create<DjangoAPI>().insert(table1).enqueue(object : Callback<Status>{
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    Toast.makeText(this@SecondActivity,response.body()?.status.toString(),Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    Toast.makeText(this@SecondActivity,resources.getString(R.string.insert_error_text),Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}