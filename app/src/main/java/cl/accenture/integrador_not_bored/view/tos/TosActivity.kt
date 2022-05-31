package cl.accenture.integrador_not_bored.view.tos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.databinding.ActivityTosBinding
import cl.accenture.integrador_not_bored.view.splash.MainActivity

class TosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener { onReturnBtnClicked() }
    }

    fun onReturnBtnClicked() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }
}