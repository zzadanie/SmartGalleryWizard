package retrofit

import android.telecom.Call
import retrofit2.Call
import retrofit2.http.GET

interface GetData {
    @GET("https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=true")
    fun getPhotos() : Call<Feed>
}