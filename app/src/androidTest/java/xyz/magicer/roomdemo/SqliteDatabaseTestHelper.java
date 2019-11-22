package xyz.magicer.roomdemo;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 * https://github.com/android/architecture-components-samples/blob/master/PersistenceMigrationsSample/app/src/androidTestRoom_Common/java/com/example/android/persistence/migrations/SqliteDatabaseTestHelper.java
 *
 * Helper class for working with the SQLiteDatabase.
 */
public class SqliteDatabaseTestHelper {

    public static void insertUser(int userid, String userName, SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userid", userid);
        values.put("username", userName);

        db.insertWithOnConflict("users", null, values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
    }

    public static void createTable(SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`name` TEXT, `age` INTEGER NOT NULL, `address` TEXT, `image` TEXT NOT NULL, `phone` TEXT, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `persons` (`name` TEXT, `address` TEXT, `phone` TEXT, `sex` TEXT, `migration_test` TEXT, `id` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `pets` (`name` TEXT, `age` INTEGER, `person_id` INTEGER, `id` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`person_id`) REFERENCES `persons`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.close();
    }

    public static void clearDatabase(SqliteTestDbOpenHelper helper) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS persons");
        db.execSQL("DROP TABLE IF EXISTS pets");
        db.execSQL("DROP TABLE IF EXISTS `subjects`");
        db.execSQL("DROP TABLE IF EXISTS `person_subject_join`");

        db.close();
    }
}