class Party(val game:Game) {
    val id:String = generateUID()
    val userList = mutableListOf<User>()
    val chatHistory = Chat(id)
}
