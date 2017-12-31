import java.util.Scanner;

public class Player {
    public int[] cards = new int[4];
    public int option = 0;
    
    //抽牌
    public int choose_card(int i, int player[][]){ 
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("現在由玩家" + (i + 1) + "出牌。");
   
        for(int j = 0; j < 4; j++){
            Card a = new Card();
            System.out.print((j + 1) + " ");
            a.card_name(player[i][j]);
            System.out.println();
        }
        System.out.println("請選擇一張卡：");
        Scanner run = new Scanner(System.in);
        option = run.nextInt() - 1;
        return option;
    }   
    
    
    
}