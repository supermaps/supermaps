# supermaps
Android Maps

[![Build Status](https://travis-ci.org/supermaps/supermaps.svg?branch=develop)](https://travis-ci.org/supermaps/supermaps)

Project Slack: https://supermapslibrary.slack.com


An extension on top of Android's Google Maps that allows for live views as Annotations. 
The current implementation of GoogleMaps for Android only allows for Bitmaps to be markers. 
This makes it incredibly hard and expensive to animate custom views as markers.

We believe this project is critically missing in the Android world. This project can benefit almost every company using maps as their base library. We're looking for enthusiastic developers, let us know if you're interested in helping out. 
Email me (Max Alexander) mbalex99@gmail.com


Eventually we'd like to extend this implementation to MapBox, CartoDB, ArcGIS implemetations. 
This API will similar to the MapKit API for iOS and OSX which allow for almost unlimited customization to annotation maps. 


### DEVELOPMENT 

If you want to run the sample app you’ll need to create a `keys.xml` in
`app/src/main/res/values/keys.xml`

It should look something like this:
```xml
<resources>
    <string name="google_maps_api_key">blahBlahBlahGoogleMapsApiKeyblahblah</string>
</resources>
```

Please create your own key at https://console.developers.google.com
If it asks you for a SHA-1 on the google console... 

The package name is `supermaps.supermaps`

To get a SHA-1 key can be generated with : 

`keytool -list -v -keystore ~/.android/debug.keystore`
If you haven't done anything fancy then the password should just be "android" 
