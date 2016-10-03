# VertalSwipeContent[![](https://jitpack.io/v/Brioal/VertalSwipeContent.svg)](https://jitpack.io/#Brioal/VertalSwipeContent)
####仿小米运动设备界面,垂直滑动菜单布局
####小米运动设备界面效果
![](https://github.com/Brioal/VertalSwipeContent/blob/master/art/3.gif)
####本Demo库效果
![](https://github.com/Brioal/VertalSwipeContent/blob/master/art/2.gif)

###添加库方法
####Step 1. Add the JitPack repository to your build file
####Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
###Step 2. Add the dependency
```
	dependencies {
	        compile 'com.github.Brioal:VertalSwipeContent:1.4'
	}
```

###使用方式
####1.xml(内嵌两个View即可)
```
<com.brioal.vertalswipe.VerticalSwipe
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/container"
    tools:context="com.brioal.vertalswipecontent.MainActivity">
    <!--固定布局-->
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/colorAccent">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="350dp"
            android:gravity="center"
            android:text="Main"
            android:textColor="@color/colorWhite"
            android:textSize="30sp"/>
    </RelativeLayout>
    <!--可滑动布局-->
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/colorPrimary">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="600dp"
            android:gravity="center"
            android:text="SwipeView"
            android:textColor="@color/colorWhite"
            android:textSize="30sp"/>
    </RelativeLayout>
</com.brioal.vertalswipe.VerticalSwipe>

```
####2.java代码中设置
####设置滑动界面滑动监听器0~1.0f
```
mContainer.setSwipeListener(new OnSwipeListener() {
    @Override
    public void progress(float progress) {
                
    }
});
```
####判断是否显示完整滑动界面|设置是否完整显示滑动界面
```
if (mContainer.isSwipeViewShowing()) {
   mContainer.setSwipeViewShowing(false);
}
```
