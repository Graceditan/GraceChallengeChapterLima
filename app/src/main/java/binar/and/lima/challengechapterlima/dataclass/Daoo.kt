package binar.and.lima.challengechapterlima.dataclass

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Daoo {

    @Insert
    fun register (user: User) : Long

    @Query("SELECT * FROM User WHERE User.email = :email")
    fun getuser(email:String) : User
}