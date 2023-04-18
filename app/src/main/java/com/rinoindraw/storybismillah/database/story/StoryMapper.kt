package com.rinoindraw.storybismillah.database.story

import com.rinoindraw.storybismillah.database.model.Story

fun storyToStoryEntity(story: Story): StoryEntity {
    return StoryEntity(
        id = story.id,
        photoUrl = story.photoUrl
    )
}