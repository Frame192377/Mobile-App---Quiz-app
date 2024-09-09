buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        //noinspection UseTomlInstead
        classpath("com.android.tools.build:gradle:8.5.2") // ใช้เวอร์ชันที่เหมาะสม
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // เพิ่มบรรทัดนี้เพื่อรองรับ Kotlin
    }
}

allprojects {


}
