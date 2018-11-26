package desarrollomobile.tiendadeclases.tiendadeclases.classes

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("/")
    fun getClasses(): Observable<ClassesResponse>

    companion object {
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://demo6160290.mockable.io")
                    .client(OkHttpClient.Builder().build())
                    .build()
            return retrofit.create(Api::class.java)
        }
    }
}