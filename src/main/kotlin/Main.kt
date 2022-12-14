lateinit var controller: MockDatabaseController
lateinit var user:User
var isRunning:Boolean = true

fun User.createParty(game: Game): String {
    return controller.createNewParty(game)
}
fun User.joinParty(partyID : String) {
    controller.addUserToParty(this, partyID)
    this.currentPartyID = partyID
}
fun User.leaveParty() {
    controller.removeUserfromParty(this, this.currentPartyID)
    this.currentPartyID = ""
}

fun User.getPartyChat():List<Message> {
    val partyIndex = controller.partyList.indexOfFirst { it.id == user.currentPartyID }
    return controller.partyList[partyIndex].chatHistory.chatMessages
}

fun User.sendPartyChat(message: String) {
    val partyIndex = controller.partyList.indexOfFirst { it.id == user.currentPartyID }
    controller.partyList[partyIndex].chatHistory.chatMessages.add(Message(this.id, message))
}

fun main(args: Array<String>) {

    controller = MockDatabaseController()

    println("===================")
    println("Welcome to MabarU")
    println("===================")
    user = login()
    controller.userList.add(user)


    do {
        displayUI()
        receiveInput()
    } while (isRunning)
}

fun login():User {
    print("please enter username : ")
    val username = readLine()!!
    return User(username)
}

fun displayUI() {
    println()
    println("== Hello ${user.name} ==")
    if (user.currentPartyID == "") {
        println("you are currently not in a party")
        println("available parties :")
        val parties =  controller.partyList
        for (i in parties.indices) {
            println("${i+1}. ${parties[i].game.name.uppercase()} - members : ${parties[i].userList.count()}")
        }
    } else {
        val partyIndex = controller.partyList.indexOfFirst { it.id == user.currentPartyID }
        val currentParty = controller.partyList[partyIndex]
        println("== your party ==")
        println("playing - ${currentParty.game.name}")
        println("party members :")
        for (member in currentParty.userList) {
            println("- ${member.name}")
        }
    }
}

fun receiveInput() {
    println("enter a command (enter 'help' to get command list:")
    val input = readLine()!!
    val inputArr = input.split("\\s".toRegex()).toTypedArray()
    when (inputArr[0]) {
        "help" -> { displayCommands() }
        "join" -> {
            if (inputArr.count() < 2) {
                println("please specify the party number")
                return
            }
            val partyIndex = inputArr[1].toInt()
            if (user.currentPartyID != "") {
                println("already in a party")
                return
            }
            if (partyIndex > controller.partyList.count()) {
                println("party not found")
                return
            }
            user.joinParty(controller.partyList[partyIndex-1].id)
            println("joined party")
        }
        "create" -> {
            if (user.currentPartyID != "") {
                println("already in a party")
                return
            }
            println("select the game :")
            for (i in controller.gameList.indices) {
                println("${i+1}. ${controller.gameList[i].name}")
            }
            print("what game are you playing? : ")
            var gameInput:Int
            do {
                gameInput = readLine()!!.toInt()
                if (gameInput <= controller.gameList.count()) {
                    break
                }
                println("invalid input")
            } while (true)
            val partyID = user.createParty(controller.gameList[gameInput-1])
            user.joinParty(partyID)
            println("party created")
        }
        "leave" -> {
            if (user.currentPartyID == "") {
                println("not in a party")
                return
            }
            user.leaveParty()
        }
        "chat" -> {
            if (user.currentPartyID == "") {
                println("not in a party")
                return
            }
            println("== chatroom ==")
            do {
                val messages = user.getPartyChat()
                for (message in messages) {
                    val userIndex = controller.userList.indexOfFirst { it.id == message.senderID }
                    println("${controller.userList[userIndex].name} : ${message.text}")
                }
                print("enter message : ")
                val msgInput = readLine()!!
                if (msgInput == "x") {
                    break
                }
                user.sendPartyChat(msgInput)
            } while (true)
        }
        "exit" -> {
            isRunning = false
            return
        }
        else -> {
            println("invalid command")
        }
    }
}

fun displayCommands() {
    println("help - display all avalaible commands")
    println("join [party number] - join specified party")
    println("create - create new party")
    println("leave - leave current party")
    println("chat - display chat room")
    println("exit - exit app")
}



