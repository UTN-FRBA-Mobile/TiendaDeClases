package desarrollomobile.tiendadeclases.tiendadeclases.users

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("userName") var userName: String = ""
    @SerializedName("password") var password: String = ""
    @SerializedName("firstName") var firstName: String = ""
    @SerializedName("lastName") var lastName: String = ""
    @SerializedName("position") var position: Point?
    @SerializedName("profilePicture") var profilePicture: ByteArray? = null

    constructor(userName: String, password: String, firstName: String, lastName: String, position: Point?, profilePicture: ByteArray?) {
        this.userName = userName
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.position = position
        this.profilePicture = profilePicture
    }

}

class Point {

    @SerializedName("latitude") var latitude: String = ""
    @SerializedName("longitude") var longitude: String = ""

    constructor(latitude: String, longitude: String) {
        this.latitude = latitude
        this.longitude = longitude
    }
}