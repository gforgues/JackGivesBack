package participant;

public interface Participant {

    void saveGame();
    void requestLeave();
    void closeGame();
    String getName();
    
}