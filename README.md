# Path like ScrollBarPanel for Android

![Screenshot](https://github.com/rno/Android-ScrollBarPanel/raw/master/demo_capture.png)

This project aims to provide a reusable ScrollBarPanel for Android ala Path.

## Features

 * Supports custom ScrollBarPanel.

Repository at <https://github.com/rno/Android-ScrollBarPanel>.

## Usage

### Layout

``` xml
<!--
  The ExtendedListView replaces a standard ListView widget
  and provides the ScrollBarPanel capability.
-->
<com.dafruits.android.library.widgets.ExtendedListView
    android:id="@android:id/list"
    android:layout_height="fill_parent"
    android:layout_width="fill_parent" />
```

### Activity

``` java
// Set your scrollBarPanel
ExtendedListView listView = (ExtendedListView) findViewById(android.R.id.list);
listView.setScrollBarPanel(scrollBarPanel);

// Attach a scroll listener on the listview and play with your scrollBarPanel
// when you need to update its content
```

## Pull Requests

I will gladly accept pull requests for fixes and feature enhancements but please do them in the dev branch. The master branch is for the latest stable code,  dev is where I try things out before releasing them as stable. Any pull requests that are against master from now on will be closed asking for you to do another pull against dev.

## Changelog

### 0.1.0

* first commit :-)

## License

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
