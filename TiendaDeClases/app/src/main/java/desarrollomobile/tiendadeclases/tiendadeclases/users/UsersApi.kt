package desarrollomobile.tiendadeclases.tiendadeclases.users

import io.reactivex.Observable
import retrofit2.http.*

interface UsersApi {

    @GET("usuario/{userName}")
    fun getUser(@Path("userName") userName: String): Observable<User>

    @POST("usuario")
    fun addUser(@Body user: User): Observable<UserResponse>

    @PUT("usuario")
    fun modifyUser(@Body user: User): Observable<UserResponse>

    @POST("login")
    fun loginUser(@Body user: User): Observable<UserResponse>

    @PUT("updatePassword")
    fun updatePassword(@Body passwordReq: PasswordRequest): Observable<UserResponse>
}