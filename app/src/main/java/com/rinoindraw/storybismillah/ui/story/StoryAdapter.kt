package com.rinoindraw.storybismillah.ui.story

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.rinoindraw.storybismillah.database.model.Story
import com.rinoindraw.storybismillah.databinding.ItemStoryRowBinding
import com.rinoindraw.storybismillah.ui.story.detail.DetailStoryActivity
import com.rinoindraw.storybismillah.utils.ConstVal
import com.rinoindraw.storybismillah.utils.ext.setImageUrl
import com.rinoindraw.storybismillah.utils.ext.timeStamptoString

class StoryAdapter(private val storyList: List<Story>): RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        storyList[position].let { story ->
            holder.bind(story)
        }
    }

    override fun getItemCount(): Int = storyList.size

    inner class StoryViewHolder(private val binding: ItemStoryRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            with(binding) {
                tvStoryUsername.text = story.name
                tvStoryDescription.text = story.description
                tvStoryDate.text = story.createdAt.timeStamptoString()

                ivStoryImage.setImageUrl(story.photoUrl, false)
            }
            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailStoryActivity::class.java)
                intent.putExtra(ConstVal.BUNDLE_KEY_STORY, story)
                itemView.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity).toBundle())
            }
        }
    }
}