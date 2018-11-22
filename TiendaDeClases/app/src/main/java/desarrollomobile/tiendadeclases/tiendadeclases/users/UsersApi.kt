package desarrollomobile.tiendadeclases.tiendadeclases.classes

import desarrollomobile.tiendadeclases.tiendadeclases.users.User
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {
    @GET("usuario/{userName}")
    fun getUser(@Path("userName") userName: String): Observable<User>
}