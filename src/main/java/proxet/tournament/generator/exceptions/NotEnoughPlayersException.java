package proxet.tournament.generator.exceptions;

public class NotEnoughPlayersException extends Exception{
    public NotEnoughPlayersException() {
        super("There are not enough players to start matchmaking");
    }
}
