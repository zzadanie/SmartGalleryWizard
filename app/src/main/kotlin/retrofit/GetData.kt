package retrofit

import retrofit.http.GET

interface GetData {
    @GET("https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=true")
    fun getPhotos() : Call<Feed>
}