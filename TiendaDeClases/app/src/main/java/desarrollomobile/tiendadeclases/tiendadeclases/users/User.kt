package desarrollomobile.tiendadeclases.tiendadeclases.users

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("userName") var userName: String = ""
    @SerializedName("password") var password: String = ""
    @SerializedName("firstName") var firstName: String = ""
    @SerializedName("lastName") var lastName: String = ""
    @SerializedName("profilePicture") var profilePicture: ByteArray? = null

    constructor(userName: String, password: String, firstName: String, lastName: String, profilePicture: ByteArray?) {
        this.userName = userName
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.profilePicture = profilePicture
    }

}