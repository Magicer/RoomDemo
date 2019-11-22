package xyz.magicer.roomdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_relation.*
import kotlinx.coroutines.*
import xyz.magicer.roomdemo.R
import xyz.magicer.roomdemo.data.relations.Person
import xyz.magicer.roomdemo.data.relations.PersonRepository
import xyz.magicer.roomdemo.data.relations.Pet
import xyz.magicer.roomdemo.data.relations.PetRepository

class RelationActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relation)

        initDatas()

        addBtn.setOnClickListener {
            launch {
                val person = withContext(Dispatchers.IO) {
                    PersonRepository.getInstance().getPersonPets()
                }
                val s = person.map {
                    "\n Person id = ${it.person.id} name = ${it.person.name} address = ${it.person.address} " +
                            " ${it.pets.map { p ->
                                "\n       pets id = ${p.id}  name = ${p.name} age = ${p.age}"
                            }} \n"
                }

                textView.text = s.toString()
            }


        }
    }

    private fun initDatas() {

        val ps1 = Person(1)
        ps1.name = "哪吒"
        ps1.address = "陈塘关"
        ps1.phone = "100023210"
        ps1.sex = "unKnown"

        val ps2 = Person(2)
        ps2.name = "孙悟空"
        ps2.address = "花果山"
        ps2.phone = "110"
        ps2.sex = "unKnown"

        val ps3 = Person(3)
        ps3.name = "李靖"
        ps3.address = "陈塘关"
        ps3.phone = "119"
        ps3.sex = "男"

        val ps4 = Person(4)
        ps4.name = "二郎神"
        ps4.address = "灌江口"
        ps4.phone = "120"
        ps4.sex = "男"


        val p1 = Pet(1)
        p1.name = "哮天犬"
        p1.age = 110
        p1.personId = ps4.id

        val p2 = Pet(2)
        p2.name = "小狗狗2"
        p2.age = 9
        p2.personId = ps4.id

        val p3 = Pet(3)
        p3.name = "小鱼儿"
        p3.age = 78
        p3.personId = ps3.id

        val p4 = Pet(4)
        p4.name = "小白龙"
        p4.age = 200
        p4.personId = ps2.id

        val p5 = Pet(5)
        p5.name = "猴崽子1"
        p5.age = 23
        p5.personId = ps2.id

        val p6 = Pet(6)
        p6.name = "猴崽子3号"
        p6.age = 24
        p6.personId = ps2.id

        val p7 = Pet(7)
        p7.name = "宠物007"
        p7.age = 7
        p7.personId = ps1.id

        launch {
            withContext(Dispatchers.IO) {
                PersonRepository.getInstance().insert(ps1, ps2, ps3, ps4)
                PetRepository.getInstance().insertAll(p1, p2, p3, p4, p5, p6, p7)
            }
        }

    }
}
