package xyz.magicer.roomdemo.data

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val TAG_M = "migration"
fun migration_1_2() = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {

        Log.i(TAG_M, "migration_1_2")
        //SQLite的alter不支持添加多列，一列一列来吧：
        database.execSQL("ALTER TABLE users ADD COLUMN phone text")
        database.execSQL("ALTER TABLE users ADD COLUMN email text")

        //将数据库表名改为user2 不要乱改。改了会出问题。这里是为了列出来怎么改名
//        database.execSQL("ALTER TABLE user RENAME TO user2")
    }
}


//删除 数据库中字段 email
//或者为某个字段更改名称也是一样的操作
fun migration_2_3() = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        Log.i(TAG_M, "migration_2_3 ")
        database.execSQL(
            "CREATE TABLE user_temp (id INTEGER PRIMARY KEY NOT NULL ," +
                    "name TEXT, " +
                    "age INTEGER NOT NULL," + //注意这里。由于 data class里设置了初始值 这里需要设置为NOT NULL
                    "address TEXT," +
                    "image TEXT NOT NULL," + //注意这里
                    "phone TEXT)"
        )
        database.execSQL("INSERT INTO user_temp(id,name,age,address,image,phone) SELECT id,name,age,address,image,phone from users")
        database.execSQL("DROP TABLE users")

        database.execSQL("ALTER TABLE user_temp RENAME TO users")

    }
}

