package xyz.magicer.roomdemo.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xyz.magicer.roomdemo.entity.House

//将list类型的house 转成字符存入到数据库中。取出后自动转成list
class HouseConverter {
    @TypeConverter
    fun stringToList(value: String): List<House>? {
        val listType = object : TypeToken<List<House>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(houses: List<House>?): String {
        return Gson().toJson(houses)
    }
}