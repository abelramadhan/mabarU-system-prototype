class User(val name: String) {
    var id: String
    var gameAccounts = mutableListOf<GameAccount>()
    var discordAccount: DiscordAccount? = null
    var chatHistory: Chat
    var currentPartyID: String

    init {
        this.id = generateUID()
        this.chatHistory = Chat(id)
        this.currentPartyID = ""
    }

    fun bindGameAccount(game:Game, nickname:String, rank:String) {
        val newGameAccount = GameAccount(this.id, game, nickname, rank)
        this.gameAccounts.add(newGameAccount)
    }

    fun bindDiscordAccount(discordID:String, discordNickname:String, discordTagline:String) {
        this.discordAccount = DiscordAccount(discordID, discordNickname, discordTagline)
    }


}
