package xyz.magicer.roomdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import xyz.magicer.roomdemo.R
import xyz.magicer.roomdemo.data.relations.Person
import xyz.magicer.roomdemo.data.relations.PersonRepository
import xyz.magicer.roomdemo.entity.House

class TypeConvertersActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val TAG = TypeConvertersActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDatas()


        addBtn.setOnClickListener {
            launch {
              val  p =  withContext(Dispatchers.IO){
                    PersonRepository.getInstance().getPersonById(10086)
                }
                textView.text = p.toString()
            }
        }
    }

    private fun initDatas() {
        launch(Dispatchers.IO) {
            val p = Person(10086)
            p.name = "Magicer"
            p.address = "北京市第三区交通委家属院"
            p.phone = "10086"
            p.sex = "unKnown"

            val h1 = House("北京", "2千万", "1层")
            val h2 = House("上海", "5千万", "2层")
            val h3 = House("深圳", "1千万", "3层")
            val h4 = House("深圳", "5千万", "4层")
            val h5 = House("上海", "3千万", "5层")
            val h6 = House("北京", "9千万", "6层")
            p.houses = arrayListOf(h1, h2, h3, h4, h5, h6)

            PersonRepository.getInstance().insert(p)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
