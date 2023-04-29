public class UnknownPlanet extends MapTile {
    Aliens enemy;
    public UnknownPlanet(int x, int y,Aliens alienss) {
        super(x,y);
        this.enemy = alienss;
    }
    public String intro_text(){
        if(enemy.is_alive()){return "\n You found a unknown planet , be pair to fight";}
        else{
            return"You already killed the enemy.";
        }

    }


}
