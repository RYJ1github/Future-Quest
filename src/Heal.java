public class Heal extends Action{
    private static final int HEAL_AMOUNT = 10;
    public Heal(){
        super(String.valueOf(Method.Heal),"Heal your self",'h',null);
    }
    public void Action(Player player){
        player.setHp((short)(player.getHp() + HEAL_AMOUNT));
        System.out.println(("You healed yourself for "+ HEAL_AMOUNT +"HP."));
    }
}
