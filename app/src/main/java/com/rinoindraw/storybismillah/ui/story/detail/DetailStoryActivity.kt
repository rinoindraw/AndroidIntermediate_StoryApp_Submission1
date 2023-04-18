package com.rinoindraw.storybismillah.ui.story.detail

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rinoindraw.storybismillah.database.model.Story
import com.rinoindraw.storybismillah.databinding.ActivityDetailStoryBinding
import com.rinoindraw.storybismillah.utils.ConstVal
import com.rinoindraw.storybismillah.utils.ext.setImageUrl
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailStoryActivity : AppCompatActivity() {

    private var _activityDetailStoryBinding: ActivityDetailStoryBinding? = null
    private val binding get() = _activityDetailStoryBinding!!

    private lateinit var story: Story

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailStoryBinding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(_activityDetailStoryBinding?.root)

        initIntent()
        initUI()
        initAction()

        setResult(Activity.RESULT_OK)

    }

    private fun initUI() {
        supportActionBar?.hide()
        binding.apply {
            imgStoryThumbnail.setImageUrl(story.photoUrl, true)
            tvStoryTitle.text = story.name
            tvStoryDesc.text = story.description
        }
    }

    private fun initAction() {
        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
    }

    private fun initIntent() {
        story = intent.getParcelableExtra(ConstVal.BUNDLE_KEY_STORY)!!
    }
}