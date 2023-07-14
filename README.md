# STEPY

#### Open source step counter

## Project explanation

This project saves your steps, calories burned and mileage calculation of steps taken on the local server, allowing you to monitor weekly without transmitting your data to the outside and lead a healthy life.

You can examine this project thanks to its open source structure and learn where and how the data is kept.

<a href="https://play.google.com/store/apps/details?id=com.rk.stepy">
   <img src="https://static-00.iconduck.com/assets.00/google-play-icon-2048x2048-487quz63.png" width="200" height="200">
</a>

## Project features
 - Jetpack
     - [Flow][1] : Flow is conceptually a stream of data that can be computed asynchronously.
     - [View Binding][2]: View binding is a feature that allows us to more easily write code that interacts with views.
 - MVVM with Clean architecture
 - [Hilt][3] for dependency injection
 - [Navigation Components][4]
 - Firebase Crashlytics
 - Firebase Analytics
 - [DataStore][5]
 - [Room][6]

## Project structure

This project is written with MVVM architecture.

While the step data in this project is stored in the room, temporary and short data are stored in the data store preferences.

This picture will help you understand :

<p>
  <img src="https://github.com/rznkolds/STEPY/assets/97980164/9ceca9fb-5e0d-4603-910d-b53e48894d85" width="1000" height="450"/>  
</p>

* ROOM : This component helps us to perform operations on SQLITE server in an organized and fast manner.
* DATASTORE : This component stores small data snippets as key-data.
* REPOSITORY : This layer is an interface for reading and writing application data.
* USECASE : This part creates a central layer to facilitate data management and ensure the independence of application layers.
* SERVICE : This service component runs long running processes in the background.
* VIEWMODEL : This component is used to hold and manage UI data and state.
* FRAGMENT : This component is part of the basic user interface.

Note : The service file is not included in the github repository to prevent the project from being used for commercial purposes.

## Project UI

<img src="https://github.com/rznkolds/STEPY/assets/97980164/fe2afcc7-a3c3-4df9-9b0e-6136ae9a1dd3" width= "300" height="600"/> <img src="https://github.com/rznkolds/STEPY/assets/97980164/61c46a54-96c1-48f7-ad30-2fa29ef7adce" width="300" height="600"/>


[1]: https://developer.android.com/kotlin/flow
[2]: https://developer.android.com/topic/libraries/view-binding
[3]: https://developer.android.com/training/dependency-injection/hilt-android
[4]: https://developer.android.com/guide/navigation/navigation-navigate
[5]: https://developer.android.com/topic/libraries/architecture/datastore
[6]: https://developer.android.com/training/data-storage/room


