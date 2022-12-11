class User(val _name: String) {
    private var id: String
    private var name: String
    private var gameAccounts = mutableListOf<GameAccount>()
    private var discordAccount: DiscordAccount? = null
    private var chatHistory: Chat
    private var currentPartyID: Int

    init {
        this.id = generateUID()
        this.name = _name
        this.chatHistory = Chat(id)
        this.currentPartyID = 0
    }

    fun bindGameAccout(game:Game, nickname:String, rank:String) {
        val newGameAccount = GameAccount(this.id, game, nickname, rank)
        this.gameAccounts.add(newGameAccount)
    }

    fun bindDiscordAccount(discordID:String, discordNickname:String, discordTagline:String) {
        this.discordAccount = DiscordAccount(discordID, discordNickname, discordTagline)
    }
}
