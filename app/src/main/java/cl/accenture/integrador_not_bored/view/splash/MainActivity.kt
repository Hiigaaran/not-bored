package cl.accenture.integrador_not_bored.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.databinding.ActivityMainBinding
import cl.accenture.integrador_not_bored.view.activitylist.ActivityListActivity
import cl.accenture.integrador_not_bored.view.tos.TosActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tosTxtLink.setOnClickListener {
            onTOSLinkClicked()
        }

        binding.mainBtnStart.setOnClickListener {
            onStartBtnClicked()
        }
    }

    fun onTOSLinkClicked() {
        val intent = Intent(this, TosActivity::class.java)
        startActivity(intent)
    }

    fun onStartBtnClicked() {
        val intent = Intent(this, ActivityListActivity::class.java).apply {
            putExtra("participants", binding.editMainTxtNumber.text)
        }
        startActivity(intent)
    }
}