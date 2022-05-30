package cl.accenture.integrador_not_bored.view.activitydetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import cl.accenture.integrador_not_bored.databinding.ActivityDetailBinding
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}