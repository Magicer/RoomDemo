
## Note
记得在`build.gradle`添加 `apply plugin: 'kotlin-kapt'`

```groovy
    kapt "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor
    androidTestImplementation "androidx.room:room-testing:$room_version"
```

### Tips

建表语句可以直接复制`AppDatabase_Impl.java`-> `createAllTables`的。需要注意的就是改变了表结构之后重新`Build`一下

### Relations

  [Database relations with Room](https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542)

  注意Dao文件中`@Transaction`的使用
  
#### Many-to-Many 

  当两个类的`PrimaryKey`都叫`id`时 `join`表里又自定义了两个字段的id：如下：
  
  ```kotlin
        @Entity
        data class PersonSubjectJoin(
        
            @ColumnInfo(name = "p_id")
            val personId: Int,
            
            @ColumnInfo(name = "s_id")
            val subjectId: Int
        )
  ```
  
  这个时候定义`PersonWithSubjects`或者 `SubjectWithPerson`类的时候需要指定`associateBy`的`parentColumn`、`entityColumn`，像下边这样：
  
  ```kotlin
   data class PersonWithSubjects(
   
       @Embedded val person: Person,
   
       @Relation(
           parentColumn = "id",
           entityColumn = "id",
           associateBy = Junction(
               PersonSubjectJoin::class,
               parentColumn = "p_id", //note
               entityColumn = "s_id"  //note
           )
       )
       val subjects: List<Subject>
   )
    
  ```


### Migration

Migration测试 https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975

[Migration测试官方Sample](https://github.com/android/architecture-components-samples/tree/master/PersistenceMigrationsSample)


### TypeConverter

```java

//官方Simple中的Converter例子
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

```



### 文章
[Understanding migrations with Room](https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929)
[理解Room的数据迁移](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0728/8278.html)
[Room 🔗 Coroutines](https://medium.com/androiddevelopers/room-coroutines-422b786dc4c5)
[[译] Room 🔗 Coroutines](https://juejin.im/post/5d39a95f51882563453256d1)




[Florina Muntenescu](https://medium.com/@florina.muntenescu)