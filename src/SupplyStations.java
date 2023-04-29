public class SupplyStations extends MapTile {
    private Gold gold;
    public SupplyStations(int x, int y, Gold gold) {
        super(x,y);
    }
    public String intro_text(){
        return "You found supply stations, you will get " + gold.amt + " golds.";
    }

}
