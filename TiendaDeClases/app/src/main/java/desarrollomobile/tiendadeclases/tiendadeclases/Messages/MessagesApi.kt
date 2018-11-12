package desarrollomobile.tiendadeclases.tiendadeclases.Messages


import desarrollomobile.tiendadeclases.tiendadeclases.classes.ClassesResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MessagesApi {


    @GET("/messages")
    fun getMessages(): Observable<MessagesResponse>

    @GET("/messages/{id}")
    fun getMessagesClasses(@Path("id") id: String): Observable<ClassesResponse>

    companion object {
        fun create(): MessagesApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://demo3719509.mockable.io")
                    .client(OkHttpClient.Builder().build())
                    .build()
            return retrofit.create(MessagesApi::class.java)
        }
    }
}