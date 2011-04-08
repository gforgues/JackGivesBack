package participant;

/**
 * 
 * @author JackGivesBack
 *
 */
public interface Participant {

    void saveGame();
    void requestLeave();
    void closeGame();
    String getName();
    
}