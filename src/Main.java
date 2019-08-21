import com.fasterxml.jackson.databind.ObjectMapper;
import main.ER;
import main.MainRedistributable;
import java.io.File;
import java.io.IOException;

/**
 * Main.
 * <p>
 * 17-Dec-18
 *
 * @author Gheoace Mihai
 */

public final class Main {
    private static ER er = ER.getInstance();

    private Main() {

    }

    /**
     * Input loader class.
     */
    private static final class InputLoader {
        private final String fileName;

        private InputLoader(final String fileName) {
            this.fileName = fileName;
        }

        public MainRedistributable load() {
            ObjectMapper objectMapper = new ObjectMapper();
            MainRedistributable main = null;
            try {
                main = objectMapper.readValue(new File(fileName), MainRedistributable.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return main;
        }
    }

    public static void main(String[] args) {
        InputLoader inputLoader = new InputLoader(args[0]);
        MainRedistributable main = inputLoader.load();
        Main.er.init(main);
        for (int i = 0; i < main.getSimulationLength(); ++i) {
            System.out.println("~~~~ " + "Patients in round " + (i + 1) + " ~~~~");
            Main.er.update();
        }
    }
}
