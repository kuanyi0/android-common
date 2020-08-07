# Android Common Library

## Download

Gradle:

```
implementation 'com.kimyi:android-common:0.2.4'
```



## Get started

```
class App : Application() {

    override fun onCreate() {
    	...
	AndroidCommon.init(this)
	...
    }
}
```

