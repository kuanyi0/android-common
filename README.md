# Android common library

## Download

Gradle:

```
implementation 'com.kimyi:android-common:0.2.0'
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

