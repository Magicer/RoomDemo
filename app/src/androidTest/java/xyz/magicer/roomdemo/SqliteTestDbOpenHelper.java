package xyz.magicer.roomdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Helper class for creating the test database version 1 with SQLite.
 */
public class SqliteTestDbOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;

    public SqliteTestDbOpenHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i("test","SqliteTestDbOpenHelper onCreate ");
//        db.execSQL("CREATE TABLE users ( userid INTEGER  PRIMARY KEY, username TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`name` TEXT, `age` INTEGER NOT NULL, `address` TEXT, `image` TEXT NOT NULL, `phone` TEXT, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `persons` (`name` TEXT, `address` TEXT, `phone` TEXT, `sex` TEXT, `migration_test` TEXT, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `pets` (`name` TEXT, `age` INTEGER, `person_id` INTEGER, `id` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`person_id`) REFERENCES `persons`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}