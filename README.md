# Android Ambient Display Live Wallpaper
Bare-minimum example of a live wallpaper that works in ambient display.

## Try it out
Clone this repository

Run `gradlew build`


## Create your own
To show any live wallpaper project on the ambient display, add the `androidprv:supportsAmbientMode="true"` attribute to the wallpaper tag in the `android.service.wallpaper` resource xml file. See the example for more detail.


## Screenshots
![Ambient Mode Example](/screenshots/screencap_ambient.png?raw=true)

![Keyguard Example](/screenshots/screencap_keyguard.png?raw=true)
