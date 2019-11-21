
## Note
记得在`build.gradle`添加 `apply plugin: 'kotlin-kapt'`

```
    kapt "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

```