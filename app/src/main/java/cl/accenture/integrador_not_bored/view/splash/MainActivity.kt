package cl.accenture.integrador_not_bored.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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

        binding.mainBtnStart.isEnabled = false

        binding.tosTxtLink.setOnClickListener {
            onTOSLinkClicked()
        }

        binding.mainBtnStart.setOnClickListener {
            onStartBtnClicked()
        }
        /////////codigo fco///////////////////////////
        this.setTitle("Not Bored")
        ////////////////////////////////////

        binding.editMainTxtNumber.addTextChangedListener {
            onParticipantsNumberChanged()
        }
    }

    fun onTOSLinkClicked() {
        val intent = Intent(this, TosActivity::class.java)
        startActivity(intent)
    }

    fun onStartBtnClicked() {
        val intent = Intent(this, ActivityListActivity::class.java).apply {
            putExtra("participants", binding.editMainTxtNumber.text.toString())
        }
        startActivity(intent)
    }

    fun onParticipantsNumberChanged() {
        when {
            !binding.editMainTxtNumber.text.isNullOrBlank() &&
                    binding.editMainTxtNumber.text.toString().toInt() <= 0 ->
                Toast.makeText(this, "Participants must be 1 or above", Toast.LENGTH_SHORT).show()

            !binding.editMainTxtNumber.text.isNullOrBlank() ->
                binding.mainBtnStart.isEnabled = true

            binding.editMainTxtNumber.text.isNullOrBlank() ->
                binding.mainBtnStart.isEnabled = false
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)


        return super.onCreateOptionsMenu(menu)
    }
}