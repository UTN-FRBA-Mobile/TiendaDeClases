package desarrollomobile.tiendadeclases.tiendadeclases.users

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("userName") var userName: String = ""
    @SerializedName("password") var password: String = ""
    @SerializedName("firstName") var firstName: String = ""
    @SerializedName("lastName") var lastName: String = ""
    @SerializedName("birthday") var birthday: String = ""
    @SerializedName("position") var position: Position?
    @SerializedName("profilePicture") var profilePicture: ByteArray? = null

    constructor(userName: String, password: String, firstName: String, lastName: String, birthday: String, position: Position?, profilePicture: ByteArray?) {
        this.userName = userName
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = birthday
        this.position = position
        this.profilePicture = profilePicture
    }

}

class Position {

    @SerializedName("latitude") var latitude: Double = 0.0
    @SerializedName("longitude") var longitude: Double = 0.0

    constructor(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }
}