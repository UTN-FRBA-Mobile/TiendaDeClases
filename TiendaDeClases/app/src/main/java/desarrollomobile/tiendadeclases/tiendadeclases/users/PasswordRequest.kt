package desarrollomobile.tiendadeclases.tiendadeclases.users

class PasswordRequest {

    private lateinit var userName: String
    private lateinit var oldPassword: String
    private lateinit var newPassword: String

    constructor(userName:String, oldPassword: String, newPassword: String) {
        this.userName = userName
        this.oldPassword = oldPassword
        this.newPassword = newPassword
    }
}