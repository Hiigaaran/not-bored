package cl.accenture.integrador_not_bored.view.activitydetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import cl.accenture.integrador_not_bored.databinding.ActivityDetailBinding
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding
import cl.accenture.integrador_not_bored.view.activitylist.ActivityListActivity

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, ActivityListActivity::class.java)
        intent.extras?.run {
            binding.participantsCount.text = getString("activityParticipants")
            binding.toolbar2.title = getString("activityTitle")
        }
    }
}