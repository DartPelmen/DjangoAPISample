package com.example.flmysqlconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flmysqlconnect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /**
     * Привязка представлений. Дает доступ к виджетам на активности и к ней самой.
     * */
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         * Поведение приложения в случае клика по кнопке перехода на вторую активность.
         * */
        binding.toSecond.setOnClickListener {
            //Намерение описывает то, откуда мы собираеся перейти и куда (из текущей активности в SecondActivity)
            val intent = Intent(this@MainActivity,SecondActivity::class.java)
            //Запуск активности согласно намерению
            startActivity(intent)
        }
    }
}