package xyz.magicer.roomdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import xyz.magicer.roomdemo.R
import xyz.magicer.roomdemo.data.basic.User
import xyz.magicer.roomdemo.data.basic.UserRepository
import xyz.magicer.roomdemo.random

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val TAG = MainActivity::class.java.simpleName
    var currentId = 1
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadUsers()

        Log.i(TAG, "thread name = ${Thread.currentThread().name}")


        Log.i(TAG, "currentId = $currentId")

        //添加一条数据
        addBtn.setOnClickListener {
            val u = User(currentId)
            u.name = "Magicer"
            u.age = random()
            u.address = "北京市交通委家属院"
            u.password = "password$currentId"
            launch {
                withContext(Dispatchers.IO) {
                    UserRepository.getInstance().insert(u)
                }
                loadUsers()
            }
        }

        //删除最后一条数据
        deleteLastBtn.setOnClickListener {
            launch {
                withContext(Dispatchers.IO) {
                    UserRepository.getInstance().delete(currentId - 1)
                }
                loadUsers()
            }
        }

        //更新用户的年龄
        updateLastBtn.setOnClickListener {
            launch {
                withContext(Dispatchers.IO) {
                    UserRepository.getInstance().updateAgeById(10086, currentId - 1)
                }
                loadUsers()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.relations -> {
                startActivity(Intent(this, RelationActivity::class.java))
            }
            R.id.typeConverter -> {
                startActivity(Intent(this, TypeConvertersActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun loadUsers() {
        textView.text = ""
        launch {
            val users = withContext(Dispatchers.IO) {
                //子线程
                Log.i(TAG, "withContext thread name = ${Thread.currentThread().name}")
                UserRepository.getInstance().getUsers()
            }
            if (users.isNullOrEmpty()) {
                currentId = 1
                addBtn.text = "添加一个用户 $currentId"
                return@launch
            }
            //主线程
            Log.i(TAG, "size = ${users.size} lastUser ${users[users.size - 1]}")
            currentId = users[users.size - 1].uid + 1
            Log.i(TAG, "launch currentId = $currentId")
            addBtn.text = "添加一个用户 $currentId"
            Log.i(TAG, "launch thread name = ${Thread.currentThread().name}")
            val text = users.map {
                it.toString() + "\n"
            }
            textView.text = text.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}
