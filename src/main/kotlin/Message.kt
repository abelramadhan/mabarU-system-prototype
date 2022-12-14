import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class Message(val senderID:String, val text:String) {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
}
