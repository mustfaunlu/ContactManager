<h1 align="center">Contact Manager App</h1></br>
<p align="center">  
The Contact Manager application is developed using Room database to perform CRUD (create, read, update, delete) operations for creating, deleting, updating, and reading contacts. 
The application follows the MVVM + Clean Architecture as its architectural pattern.
</p></br>

<p align="center">
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/mustfaunlu"><img alt="Profile" src="https://img.shields.io/badge/github-mustfaunlu-blue"/></a> 
</p>


## Screenshots
<p align="center">
<img src="/previews/home-screen.png" width="20%"/>
<img src="/previews/app-drawer.png" width="20%"/>
<img src="/previews/detail-screen.png" width="20%"/>
<img src="/previews/create-contact-screen.png" width="20%"/>
<img src="/previews/create-screen-1.png" width="20%"/>
<img src="/previews/created-contact.png" width="20%"/>
<img src="https://github.com/mustfaunlu/AutobiographyApp/assets/38860392/516af44d-e3f3-41c0-907c-84251ff52039" width="20%"/>
<img src="https://github.com/mustfaunlu/AutobiographyApp/assets/38860392/07183ec3-e023-448f-80ab-6865874dd1a1" width="20%"/>
<img src="/previews/delete-screen.png" width="20%"/>

</p>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow) & [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    -  A single-activity architecture, using the [Navigation Component](https://developer.android.com/guide/navigation) to manage fragment navigation operations.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Room](https://developer.android.com/training/data-storage/room) The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture

![](/previews/architecture.jpg)