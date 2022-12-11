class Party(val _game:Game) {
    val id:String = generateUID()
    val game:Game = _game
    val userList = mutableListOf<User>()
    val chatHistory = Chat(id)
}
