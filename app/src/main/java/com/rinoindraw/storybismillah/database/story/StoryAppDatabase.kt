package com.rinoindraw.storybismillah.database.story

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =[StoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StoryAppDatabase: RoomDatabase() {

    abstract fun getStoryDao(): StoryDao

}