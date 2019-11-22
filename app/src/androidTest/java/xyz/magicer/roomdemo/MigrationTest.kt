package xyz.magicer.roomdemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.magicer.roomdemo.data.AppDatabase
import xyz.magicer.roomdemo.data.MIGRATION_5_6
import xyz.magicer.roomdemo.data.MIGRATION_6_7


// https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975
@RunWith(AndroidJUnit4::class)
class MigrationTest {
    public val TAG = "test"

    private val TEST_DB_NAME = "test-db"
    private var sqliteTestDbHelper: SqliteTestDbOpenHelper? = null


    @get:Rule
    var testHelper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )


    @Before
    @Throws(Exception::class)
    fun setUp() {
        // To test migrations from version 1 of the database, we need to create the database
        // with version 1 using SQLite API
        sqliteTestDbHelper = SqliteTestDbOpenHelper(
            ApplicationProvider.getApplicationContext<Context>(),
            TEST_DB_NAME
        )
        SqliteDatabaseTestHelper.createTable(sqliteTestDbHelper)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        // Clear the database after every test
//        SqliteDatabaseTestHelper.clearDatabase(sqliteTestDbHelper)
    }

    @Test
    fun testMigration_6_7() {
        val db = testHelper.createDatabase(TEST_DB_NAME, 6)
        insertUser(1, "migration1", "", db)
        Log.i("test", "version = " + db.version.toString())
        db.close()
        testHelper.runMigrationsAndValidate(TEST_DB_NAME, 7, true, MIGRATION_6_7)

        val database = getMigratedRoomDatabase()
        Log.i(TAG, database.personDao().toString())

        GlobalScope.launch {
            val user = withContext(Dispatchers.IO) {
                database.userDao().getUser(1)
            }
            Log.i(TAG, user.toString())
        }
    }

    private fun getMigratedRoomDatabase(): AppDatabase {
        val database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java, TEST_DB_NAME
        )
            .addMigrations(MIGRATION_5_6,MIGRATION_6_7)
            .build()
        // close the database and release any stream resources when the test finishes
        testHelper.closeWhenFinished(database)
        return database
    }

    private fun insertUser(id: Int, userName: String, address: String, db: SupportSQLiteDatabase) {
        val values = ContentValues()
        values.put("id", id)
        values.put("name", userName)
        values.put("address", address)
        values.put("age", 0)
        values.put("image", "sds")

        db.insert("users", SQLiteDatabase.CONFLICT_REPLACE, values)
    }

}