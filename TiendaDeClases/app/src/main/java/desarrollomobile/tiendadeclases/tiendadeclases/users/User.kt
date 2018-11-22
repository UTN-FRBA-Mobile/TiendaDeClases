package desarrollomobile.tiendadeclases.tiendadeclases.users

import com.google.gson.annotations.SerializedName

class User() {

    @SerializedName("userName") val userName: String = ""
    @SerializedName("password")val password: String = ""
    @SerializedName("firstName")val firstName: String = ""
    @SerializedName("lastName")val lastName: String = ""


}