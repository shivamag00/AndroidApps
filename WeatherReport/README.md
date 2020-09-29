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
I chose the ```RelativeLayout``` ViewGroup because it is easier to align views using this view group. Inside the group, there would be two ```textViews``` - one for displaying temperature and other for displaying the city.  I inserted dummy data while I was working on the UI. The UI finally looks like the following:- 

![UI](https://github.com/shivamag00/AndroidApps/blob/WeatherUI/WeatherReport/Images/UI.jpg)

### Fetching Data from API
Now that the UI is created, it is time for our app to fetch data from an API. On google, I found a weather API called **openweathermap**. On its website, I registered myself for the free tier and generated an API key. This key must be sent as a parameter in the request URL. Next, I saw their documentation to know the format of the request URL. It had the following format

``https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}``

Now, I created a new class called ``QueryUtils`` which contained all the methods for making an HTTP request and parsing the JSON response obtained. The QueryClass had the methods to perform the following functions:-
1. Create an URL object.
2. Establishing connection to API and obtain the response
3. Parse the response (obtained from API) to String. (The response of the API is in binary, hence we must convert it to ASCII characters.)
4. Fetch the relevant details i.e. City and Temperature from the String (obtained from Step 3) (The string contains the data in JSON format). 

Also, remember to add Internet Permission in AndroidManifest.xml.

### And Crash
If I try to run the app, it crashes as soon as it starts. On checking the logs in Android Studio, I found out that the app crashed because an ``NetworkOnMainThread Exception`` was thrown as soon as the app starts.

On Searching about this error on the internet, I realised that Android Platform does not allow network calls on Main Thread to ensure a smooth UI Experience for the user. 
 
On further searching on the internet, I found that we use ``AsyncTask`` to execute network calls on a background thread.