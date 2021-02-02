# Android Common Library

## Download

Gradle:

```
implementation 'com.kimyi:android-common:0.2.10'
```



## Get started

Optional:

```
class App : Application() {

    override fun onCreate() {
    	...
	AndroidCommon.init(this)
	...
    }
}
```

## Feature

- base
	- mvp: BaseView, BasePresenter
	- mvp2: BaseActivity, BaseFragment, BasePresenter
	- observe: BaseObservable, BaseObserver, BaseObserveManager
	- widget: BaseDialog, BaseLayout, BaseBindingLayout, BaseLifecycleBindingLayout, BaseComponentLayout
- ui: CustomDialog, MovableLayout...
- util: BitmapUtils, CrashHandler, DateUtils...