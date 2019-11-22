package xyz.magicer.roomdemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.magicer.roomdemo.App
import xyz.magicer.roomdemo.data.basic.User
import xyz.magicer.roomdemo.data.basic.UserDao
import xyz.magicer.roomdemo.data.relations.Person
import xyz.magicer.roomdemo.data.relations.PersonDao
import xyz.magicer.roomdemo.data.relations.Pet
import xyz.magicer.roomdemo.data.relations.PetDao

@Database(
    entities = [User::class, Person::class, Pet::class],
    version = 5,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun personDao(): PersonDao
    abstract fun petDao(): PetDao

    companion object {
        private const val DATABASE_NAME = "room-db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(App.getInstance()!!.applicationContext)
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            AppDatabase_Impl.MAX_BIND_PARAMETER_CNT
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,MIGRATION_4_5)
                .build()
        }
    }

}
