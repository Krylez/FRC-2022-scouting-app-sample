# Scouting Sample App
A sample Android FRC scouting app for 2023's Charged Up showcasing a number of technologies.

Everything is written in [kotlin](https://kotlinlang.org/).

The app utilizes Square's [Retrofit](https://square.github.io/retrofit/) to fetch event, match, and team data from [The Blue Alliance API](https://www.thebluealliance.com/apidocs/v3). Json parsing is accomplished with Square's [Moshi](https://square.github.io/moshi/1.x/moshi/).

Data is cached locally through Google's [Room](https://developer.android.com/jetpack/androidx/releases/room), allowing the app to function even without a network connection.

Dependency Injection is handled with Google's [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).
