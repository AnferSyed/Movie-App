# Movie App

This Application loads latest movies from the internet movie database [TMDB](https://www.themoviedb.org/) and shows the movie list in RecyclerView. When an movie item is tapped the details is shown in MovieDetailsActivity. As special feature, the app will have a fab button in the main screen to filter the movies by year. Infinite scroll has been implemented to RecyclerView to browse more movies.

## Libraries user are

* [Retrofit](http://square.github.io/retrofit/) - Networking Library
* [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/index.html) - Data Binding Library to write declarative layouts
* [Glide](https://github.com/bumptech/glide/wiki) - Image downloading and caching library to display Poster & Backdrop Images
* [google-gson - GsonConverterFactory](https://github.com/google/gson) - To convert JSON string to an equivalent Java object


### Versions

```
* compileSdkVersion : 27
* gradle : 4.1
```