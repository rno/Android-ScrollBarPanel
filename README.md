# Path 2.0 like ScrollBarPanel for Android

![Screenshot](https://github.com/rno/Android-ScrollBarPanel/raw/master/demo_capture.png)

Android-ScrollBarPanel allows to attach a View to a scroll indicator like it's done in Path 2.0.

## Features

 * Supports custom View as ScrollBarPanel.

Repository at <https://github.com/rno/Android-ScrollBarPanel>.

## Usage

### Layout

``` xml
<!--
  The ExtendedListView replaces a standard ListView widget
  and provides the ScrollBarPanel capability.
-->
<com.dafruits.android.library.widgets.ExtendedListView
    xmlns:dafruits="http://schemas.android.com/apk/res/YOUR_APP_PACKAGE_NAME"
    android:id="@android:id/list"
    android:layout_height="fill_parent"
    android:layout_width="fill_parent"
    dafruits:scrollBarPanel="@layout/YOUR_SCROLLBARPANEL_LAYOUT" />
```

### Activity

``` java
// Set your scrollBarPanel
ExtendedListView listView = (ExtendedListView) findViewById(android.R.id.list);

// Attach a position changed listener on the listview and play with your scrollBarPanel
// when you need to update its content
mListView.setOnPositionChangedListener(new OnPositionChangedListener() {

	@Override
	public void onPositionChanged(ExtendedListView listView, int firstVisiblePosition, View scrollBarPanel) {
		((TextView) scrollBarPanel).setText("Position " + firstVisiblePosition);
	}
});

```

## Pull Requests

I will gladly accept pull requests for fixes and feature enhancements but please do them in the dev branch. The master branch is for the latest stable code,  dev is where I try things out before releasing them as stable. Any pull requests that are against master from now on will be closed asking for you to do another pull against dev.

## Changelog

### 0.1.0

* first commit :-)

### 0.1.1

* added scrollBarPanel attribute to ExtendedListView

### 0.1.2

* Optimisations

### 0.1.3

* More precision regarding scrollBarPanel fade in and fade out (Thanks [Cyril Mottier](https://github.com/cyrilmottier) for the tip!)

## Acknowledgments

* [Chris Banes](https://github.com/chrisbanes)

## License

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
