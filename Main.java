public class Main{
    public void restart() {
        System.out.println();
        System.out.println("新的一局！");
        GameSystem a = new GameSystem();
        a.how_many_players();
    }

    public static void main(String args[]){ 
        System.out.println("新的一局！");
        GameSystem a = new GameSystem();
        a.how_many_players();
    }
}