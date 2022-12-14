class MockDatabaseController {
    val partyList = mutableListOf<Party>()
    val userList = mutableListOf<User>()
    val gameList = mutableListOf<Game>()

    init {
        //mock users
        userList.add(User("Budi123"))
        userList.add(User("JajangSokbreker"))
        userList.add(User("Sucipto"))
        //mock games
        gameList.add(Game("001", "Valorant", 5))
        gameList.add(Game("002", "Dota 2", 5))
        gameList.add(Game("003", "Mobile Legend", 5))
        //mock parties
        partyList.add(Party(gameList[0]))
        partyList.add(Party(gameList[2]))
        //
        addUserToParty(User("Ujang"), partyList[0].id)
        addUserToParty(User("Bambang"), partyList[0].id)
        addUserToParty(User("Siti"), partyList[0].id)
        addUserToParty(User("Luki"), partyList[0].id)
        addUserToParty(User("Wawan"), partyList[1].id)
        addUserToParty(User("Sukijan"), partyList[1].id)
    }


    //partyController
    fun createNewParty(game:Game):String {
        val party = Party(game)
        partyList.add(party)
        return party.id
    }

    fun disbandParty(partyID: String) {
        val partyIndex = partyList.indexOfFirst { it.id == partyID }
        partyList.removeAt(partyIndex)
    }

    fun addUserToParty(user:User, partyID: String) {
        val partyIndex = partyList.indexOfFirst { it.id == partyID }
        if (partyList[partyIndex].userList.count() >= partyList[partyIndex].game.maxPlayer) {
            return
        }
        partyList[partyIndex].userList.add(user)
    }

    fun removeUserfromParty(user:User, partyID: String) {
        val partyIndex = partyList.indexOfFirst { it.id == partyID }
        val userIndex = partyList[partyIndex].userList.indexOfFirst { it.id == user.id }
        partyList[partyIndex].userList.removeAt(userIndex)
        if (partyList[partyIndex].userList.count() == 0) {
            disbandParty(partyList[partyIndex].id)
        }
    }
}