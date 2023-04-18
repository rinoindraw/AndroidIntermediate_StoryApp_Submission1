package com.rinoindraw.storybismillah.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.rinoindraw.storybismillah.R
import com.rinoindraw.storybismillah.database.ApiResponse
import com.rinoindraw.storybismillah.databinding.ActivityMainBinding
import com.rinoindraw.storybismillah.ui.story.add.AddStoryActivity
import com.rinoindraw.storybismillah.ui.profile.ProfileActivity
import com.rinoindraw.storybismillah.ui.story.StoryAdapter
import com.rinoindraw.storybismillah.ui.story.StoryViewModel
import com.rinoindraw.storybismillah.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val storyViewModel: StoryViewModel by viewModels()

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding!!

    private lateinit var pref: SessionManager
    private var token: String? = null
    private var limit: Int? = null


    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(_activityMainBinding?.root)

        pref = SessionManager(this)
        token = pref.getToken
        limit = pref.getLimit

        initUI()
        initAction()

        getAllStories("Bearer $token", limit!!)

    }

    private fun initUI() {
        supportActionBar?.hide()
        binding.rvStories.layoutManager = LinearLayoutManager(this)
        binding.tvGreetingName.text = getString(R.string.label_greeting_user, pref.getUserName)
    }

    private fun initAction() {
        binding.fabNewStory.setOnClickListener {
            AddStoryActivity.start(this)
        }
        binding.btnAccount.setOnClickListener {
            ProfileActivity.start(this)
        }
        binding.btnSetting.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }

    private fun getAllStories(token: String, limit: Int) {
        storyViewModel.getAllStories(token, limit).observe(this) { response ->
            when (response) {
                is ApiResponse.Loading -> isLoading(true)
                is ApiResponse.Success -> {
                    isLoading(false)
                    val adapter = StoryAdapter(response.data.listStory)
                    binding.rvStories.adapter = adapter
                }
                is ApiResponse.Error -> isLoading(false)
                else -> {
                    Timber.e(getString(R.string.message_unknown_state))
                }
            }
        }
    }

    private fun isLoading(loading: Boolean) {
        if (loading) {
            binding.apply {
                shimmerLoading.visibility = View.VISIBLE
                shimmerLoading.startShimmer()
                rvStories.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                rvStories.visibility = View.VISIBLE
                shimmerLoading.stopShimmer()
                shimmerLoading.visibility = View.INVISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllStories("Bearer $token", limit!!)
    }
}