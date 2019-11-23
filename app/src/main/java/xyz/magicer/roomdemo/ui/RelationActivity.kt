package xyz.magicer.roomdemo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_relation.*
import kotlinx.coroutines.*
import xyz.magicer.roomdemo.R
import xyz.magicer.roomdemo.data.relations.Person
import xyz.magicer.roomdemo.data.relations.PersonRepository
import xyz.magicer.roomdemo.data.relations.Pet
import xyz.magicer.roomdemo.data.relations.PetRepository
import xyz.magicer.roomdemo.data.relations.m2m.PersonSubjectJoin
import xyz.magicer.roomdemo.data.relations.m2m.PersonSubjectJoinRepository
import xyz.magicer.roomdemo.data.relations.m2m.Subject
import xyz.magicer.roomdemo.data.relations.m2m.SubjectRepository

class RelationActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    var current = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relation)

        initDatas()

        o2mBtn.setOnClickListener {
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

        stateBtn.setOnClickListener {
            if (current == 1) {
                current = 2
                stateBtn.text = "根据用户"
            } else {
                current = 1
                stateBtn.text = "根据学科"
            }
        }


        m2mBtn.setOnClickListener {


            val input = inputEt.text.toString().trim()

            launch {

                val list = withContext(Dispatchers.IO) {
                    PersonSubjectJoinRepository.getInstance().getPersonWithSubjects()
                }
                val allStr = list.map {
                    " Person id = ${it.person.id} name = ${it.person.name} phone = ${it.person.phone}" +
                            " \n ${it.subjects.map { sub ->
                                "    Subject id = ${sub.id} name = ${sub.name} code = ${sub.code}\n"

                            }} \n "
                }

                val swpList = withContext(Dispatchers.IO) {
                    PersonSubjectJoinRepository.getInstance().getSubjectWithPersons()
                }

                val swpStr = swpList.map {
                    " Subject id = ${it.subject.id} name = ${it.subject.name} code = ${it.subject.code}" +
                            " \n ${it.persons.map { p ->
                                "    Person id = ${p.id} name = ${p.name} address = ${p.address}\n"

                            }} \n "
                }

                if (current == 1) {
                    if (input.toInt() > 6) {
                        Toast.makeText(this@RelationActivity, "最大为6", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    val s = withContext(Dispatchers.IO) {
                        PersonSubjectJoinRepository.getInstance()
                            .getPersonsForSubject(input.toInt())

                    }
                    val subject = withContext(Dispatchers.IO) {
                        SubjectRepository.getInstance().getSubjectById(input.toInt())
                    }


                    val t = s.map {
                        "    Person id = ${it.id} name = ${it.name}  phone = ${it.phone} \n"
                    }

                    textView.text =
                        "Subject id = ${subject.id} name ${subject.name} code${subject.code} \n $t \n\n\n $swpStr"

                } else {
                    if (input.toInt() > 4) {
                        Toast.makeText(this@RelationActivity, "最大为4", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    val s = withContext((Dispatchers.IO)) {
                        PersonSubjectJoinRepository.getInstance()
                            .getSubjectsForPerson(input.toInt())
                    }

                    val person = withContext(Dispatchers.IO) {
                        PersonRepository.getInstance().getPersonById(input.toInt())
                    }

                    val t = s.map {
                        "    Subject id = ${it.id} name = ${it.name}  code = ${it.code} \n"
                    }

                    textView.text =
                        "Person id = ${person.id} name = ${person.name}    \n $t \n\n\n $allStr"


                }
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
            initM2MDatas()
        }

    }

    private fun initM2MDatas() {

        val s1 = Subject(1)
        s1.name = "高数"
        s1.code = "1001"

        val s2 = Subject(2)
        s2.name = "电路"
        s2.code = "1002"

        val s3 = Subject(3)
        s3.name = "计算机基础"
        s3.code = "1003"

        val s4 = Subject(4)
        s4.name = "模拟电路"
        s4.code = "1004"
        val s5 = Subject(5)
        s5.name = "数字电路"
        s5.code = "1005"
        val s6 = Subject(6)
        s6.name = "数据库原理"
        s6.code = "1006"


        val ps1 = PersonSubjectJoin(1, 1)
        val ps2 = PersonSubjectJoin(1, 2)
        val ps3 = PersonSubjectJoin(1, 5)

        val ps4 = PersonSubjectJoin(2, 6)
        val ps5 = PersonSubjectJoin(2, 1)

        val ps6 = PersonSubjectJoin(3, 1)
        val ps7 = PersonSubjectJoin(3, 5)
        val ps8 = PersonSubjectJoin(3, 2)

        val ps9 = PersonSubjectJoin(4, 2)
//        val ps10 = PersonSubjectJoin(1,1)
//        val ps11 = PersonSubjectJoin(1,1)
//        val ps12 = PersonSubjectJoin(1,1)
//        val ps13 = PersonSubjectJoin(1,1)
//        val ps14 = PersonSubjectJoin(1,1)
//        val ps15 = PersonSubjectJoin(1,1)
//        val ps16 = PersonSubjectJoin(1,1)
//


        launch {
            withContext(Dispatchers.IO) {
                SubjectRepository.getInstance().insertAll(s1, s2, s3, s4, s5, s6)
                PersonSubjectJoinRepository.getInstance()
                    .insertAll(ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9)
            }


        }

    }
}
