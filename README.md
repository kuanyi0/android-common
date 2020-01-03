# Android common library

## Download

Gradle:

```
implementation 'com.kimyi:android-common:0.1.9'
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

