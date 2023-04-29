import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class World {
    private static String[][] _world;
    static Point Starting_Position = new Point(4, 2);

    private static List<MapTile> history = new ArrayList<MapTile>();

    public static void load_tiles() { // Parses a file that describes the world space into the _world object
        List<String> rows = new ArrayList<String>();
        try {
            BufferedReader f = new BufferedReader(new FileReader("C:\\Users\\jonep\\Java\\Future Quest\\src\\map.txt"));
            String row;
            while ((row = f.readLine()) != null) {
                rows.add(row);
            }
            f.close();
            if (rows.isEmpty()) {
                throw new Exception("File is empty");
            }
            int x_max = rows.get(0).split("\t").length;
            for (String r : rows) {
                String[] cols = r.split("\t");
                if (cols.length != x_max) {
                    throw new Exception("File contains rows with different number of columns");
                }
            }
            _world = new String[rows.size()][x_max];
            String[] cols;
            String tile_name;
            for (int y = 0; y < rows.size(); y++) {
                cols = rows.get(y).split("\t");
                for (int x = 0; x < x_max; x++) {
                    tile_name = cols[x];
                    if (tile_name.equals("StartingPlanet")) {
                        MapTile startingPosition = new MapTile(x, y);
                        history.add(startingPosition);
                    }
                    _world[y][x] = tile_name.equals(" ") ? null : tile_name;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // closing block for load_tiles Method

    public static MapTile tile_exists(int x, int y) {
        MapTile mt = null;
        if ((x >= 0 && x < _world.length) && (y >= 0 && y < _world[0].length) && _world[x][y] != null) {
            switch (_world[x][y]) {
                case "StartingPlanet":
                    mt = new StartingPlanet(x, y);
                    mt = checkRoomExists(mt);
                    break;
                case "Mars":
                    mt = new Mars(x, y);
                    mt = checkRoomExists(mt);
                    break;
                case "SupplyStations":
                    mt = new SupplyStations(x, y,new Gold(100));
                    mt = checkRoomExists(mt);
                    break;
                case "UnknownPlanet":
                    mt = new UnknownPlanet(x, y,new Aliens());
                    mt = checkRoomExists(mt);
                    break;
                case "Mercury":
                    mt = new Mercury(x, y);
                    mt = checkRoomExists(mt);
                    break;
                case "Jupiter":
                    mt = new Jupiter(x, y);
                    mt = checkRoomExists(mt);
                case "EndPlanet":
                    mt = new EndPlanet(x, y);
                    mt = checkRoomExists(mt);

            }
        }
        return mt;
    }

    private static MapTile checkRoomExists(MapTile mt){
        if (history.indexOf(mt) != -1) {
            mt = history.get(history.indexOf(mt));
        } else {
            history.add(mt);
        }
        return mt;
    }
}
