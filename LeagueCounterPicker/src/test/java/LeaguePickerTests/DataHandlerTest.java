package LeaguePickerTests;



import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import leaguecounter.DataHandler;

/**
 *
 * @author miikakoskela
 */
public class DataHandlerTest {
    
    DataHandler handler;
       
    
    @BeforeEach
    public void setUp() throws SQLException{
        handler = new DataHandler();       
    }
    
    @Test
    public void counterAkali() throws SQLException{
        ArrayList<String> champs = new ArrayList<>();
        champs.add("Akali");
        String retValue = handler.getNormPick(champs);
        assertEquals("Zyra with 50.0%", retValue);
    }
}
