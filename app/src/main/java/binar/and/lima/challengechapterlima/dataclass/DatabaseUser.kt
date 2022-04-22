package binar.and.lima.challengechapterlima.dataclass

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)


abstract class DatabaseUser : RoomDatabase() {

    abstract fun Daoo():Daoo

    companion object{
        private var Instancee : DatabaseUser? = null

        fun getInstancee(context: Context): DatabaseUser?{
            if ( Instancee == null){
                synchronized(DatabaseUser::class){
                    Instancee = Room.databaseBuilder(context.applicationContext,DatabaseUser::class.java, "databaseuser")
                        .build()
                }
            }
            return Instancee
        }

        fun destroyInstancee(){
            Instancee = null
        }
    }
}