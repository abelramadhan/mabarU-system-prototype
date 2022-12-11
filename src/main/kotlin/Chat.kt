class Chat(val _id: String) {
    val id :String = _id
    val chatMessages = mutableListOf<Message>()
}
