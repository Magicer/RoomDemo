
## Note
记得在`build.gradle`添加 `apply plugin: 'kotlin-kapt'`

```
    kapt "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

```

### Tips

建表语句可以直接复制`AppDatabase_Impl.java`-> `createAllTables`的。需要注意的就是改变了表结构之后重新`Build`一下

### Relations

  Database relations with Room

  https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542


### Migration

Migration测试 https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975

[Migration测试官方Sample](https://github.com/android/architecture-components-samples/tree/master/PersistenceMigrationsSample)
