import java.time.Instant
import java.time.format.DateTimeFormatter

class Message(val _senderID:String, val _text:String) {
    val senderID: String = _senderID
    val text: String = _text
    val timeStamp = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")
        .format(Instant.now())
}
