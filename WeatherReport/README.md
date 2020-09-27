## Overview
This is a basic app to **show weather information of the current location of the Android device.** This app is great for beginners to understand. The app :-
1.  has Minimal UI.
2.  fetches data from OpenWeatherMap API.
3.  accesses Phone Location by using the permissions in Android.
4. is designed in using a scalable approach

This README also contains the approach that was followed while developing this app so that beginners can relate to it.  
## Approach
### The First Steps
I first began by thinking about the UI for the app. Since the app has to show only the weather information of the current location of the device, I decided that app should have only one screen in which the information would be displayed. 
I chose the ```RelativeLayout``` ViewGroup because it is easier to align views using this view group. Inside the group, there would be two ```textViews``` - one for displaying temperature and other for displaying the city. 