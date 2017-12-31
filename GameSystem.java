import java.util.*;


public class GameSystem {
    int people = 0; //玩家人數
    int card[] = new int[33];   //所有卡片種類與數量
    int player[][] = new int[8][4]; //紀錄每個玩家擁有的卡片
    int win = 0;    //這局是否結束
    int option = 0; //玩家選擇的卡片
    int crime[] = new int[8]; //紀錄犯人與共犯

    public int judge_points() {
        // 一、使用「偵探」抓到犯人：使用「偵探」的人得2分，其他人得1分，犯人和共犯不能得分。
        // 二、使用「神犬」或「警部」抓到犯人：使用「神犬」或「警部」的人得3分，其他人得1分，犯人和共犯不能得分。
        // 三、打出「犯人」卡獲勝：犯人和共犯得2分，其他人不能得分。
        // 一名玩家先得到10分，遊戲就會結束。
        return win;
    }

    public int own_card(int who, int player[][]) {  //判斷某個玩家有多少張卡片
        int total = 0;
        for(int i = 0; i < 4; i++) {
            if(player[who][i] != 0) {
                total++;
            }
        }
        return total;
    }
    
    public void judge_first(int people, int player[][]){
        int c = 0;
        for(int i = 0; i < people; i++) {
            for(int j = 0; j < 4; j++){
                if(player[i][j] == 1){  //搜尋哪個玩家有第一發現者
                    Player a = new Player();    
                    while(win == 0) {
                        option = a.choose_card(i, player);  //出牌
                        c = player[i][option];  //出的那張牌是哪張
                        
                        if(c == 1){ //第一發現者
                            player[i][option] = 0;  //出過的牌消失
                            first();
                        }  
                        else if(c == 2){ //犯人
                            int total = 0;
                            total = own_card(i, player);    //判斷是否符合出卡條件
                            if(total == 1) {    //符合
                                player[i][option] = 0;  //出過的牌消失
                                prisoner(i, player);
                                crime[i] = 1;
                            }
                            else {  //不符合
                                System.out.println("當手上只有這張卡時才能出。");
                                i--;    //再選牌一次
                            }
                        }
                        else if((c >= 3) && (c <= 6)) { // 偵探
                            detective();
                        }
                        else if((c >= 7) && (c <= 11)) {  //不在場證明
                            alibi();
                        }
                        else if((c >= 12) && (c <= 13)) { //共犯
                            player[i][option] = 0;  //出過的牌消失
                            accomplice();
                            crime[i] = 1;
                        }
                        else if((c >= 14) && (c <= 16)) { //目擊者
                            player[i][option] = 0;  //出過的牌消失
                            witness();
                        }
                        else if((c >= 17) && (c <= 18)) { //普通人
                            player[i][option] = 0;  //出過的牌消失
                            ordinary_people();
                        }
                        else if(c == 19) {   //神犬
                            dog();
                        }
                        else if((c >= 20) && (c <= 23)) {//情報交換
                            player[i][option] = 0;  //出過的牌消失
                            exchange(people);
                        }
                        else if((c >= 24) && (c <= 28)) { //謠言
                            player[i][option] = 0;  //出過的牌消失
                            rumor(people);
                        }
                        else if((c >= 29) && (c <= 32)) {//交易
                            player[i][option] = 0;  //出過的牌消失
                            transaction(i, player);
                        }
                        i = (i + 1) % people;
                    }   
                }
            }
        }
    }
    //第一發現者
    public void first(){        
        System.out.println("你是第一發現者！由你開始！");
    }

    //犯人
    public void prisoner(int i, int player[][]){  
        
    }

    //偵探
    public void detective(){ 
        
    }

    //不在場證明
    public void alibi(){
        
    }

    //共犯
    public void accomplice(){
        
    }

    //目擊者
    public void witness(){
        int i = 0;
        System.out.println("指定任一名玩家，自己私底下看他所有的手牌。");
        System.out.println("請選擇：(輸入數字)");
        Scanner scn = new Scanner(System.in);
        i = scn.nextInt() - 1;
        System.out.println("正在偷看玩家" + (i + 1) + "的手牌～");
        show(i);
    }

    //普通人
    public void ordinary_people(){
        System.out.println("就是個普通人owo/");
    }

    //神犬
    public void dog(){
        
    }

    //情報交換 大 -> 小
    public void exchange(int people){
        int cho[] = new int[8];
        int tem = 0;

        System.out.println("所有人向旁邊的玩家傳遞一張牌，自己可決定要傳什麼牌。剛收到的那張牌不可以傳給別人，沒有手牌的玩家也要加入。");
        //對所有玩家端
        for(int i = 0; i < people; i++) {
            System.out.println("請選擇一張卡片，傳給你下一個玩家。");
            show(i);
            Scanner scn = new Scanner(System.in);
            cho[i] = scn.nextInt() - 1;
        }

        //系統做交換
        tem = player[0][cho[0]];
        for(int i = 0; i < people - 1; i++) {
            player[i][cho[i]] = player[i + 1][cho[i + 1]];
        }
        player[people - 1][cho[people - 1]] = tem;
    }

    //謠言
    public void rumor(int people){
        int cho[] = new int[8];
        int tem = 0;

        System.out.println("所有人向旁邊的玩家抽一張手牌，抽到的牌先不要加入手牌，不要讓剛抽到的牌被抽走。沒有手牌的人也要加入。");
        //對所有玩家端
        for(int i = 0; i < people; i++) {
            System.out.println("玩家" + (i + 1) + "請抽下一個玩家的其中一張卡片。");
            Scanner scn = new Scanner(System.in);
            cho[i] = scn.nextInt() - 1;
        }

        //系統做交換 --------------------------------------------------------從此開始
        tem = player[0][cho[people - 1]];
        player[0][cho[people - 1]] =  player[1][cho[0]];
        for(int i = 1; i < people - 1; i++) {
            player[i][cho[i - 1]] = player[i + 1][cho[i]];
        }
        player[people - 1][cho[people - 2]] = tem;
    }

    //交易
    public void transaction(int j, int player[][]){
        int i = 0;
        int tem = 0, cho1 = 0, cho2 = 0;
        System.out.println("指定任意一名玩家，彼此選擇一張卡牌與對方交換。但不能指定無手牌的玩家。");
        System.out.println("請選擇：(輸入數字)");
        Scanner scn = new Scanner(System.in);   //挑玩家
        i = scn.nextInt() - 1;
        System.out.println("你想要給玩家" + (i + 1) + "哪一張卡呢？");
        Scanner scn1 = new Scanner(System.in);  //挑交易的卡
        cho1 = scn1.nextInt() - 1;

        //被選到的玩家端
        System.out.println("玩家" + (i + 1) + "你被選到了！你想要給玩家" + (j + 1) + "哪一張卡呢？");
        show(i);
        Scanner scn2 = new Scanner(System.in); //挑交易的卡
        cho2 = scn2.nextInt() - 1;

        //系統做交換
        tem = player[j][cho1];
        player[j][cho1] = player[i][cho2];
        player[i][cho2] = tem;
    }

    public void show(int i){
        System.out.println("第" +  (i + 1) + "個玩家的卡片：");
        for(int j = 0; j < 4; j++){
            Card b = new Card();
            b.card_name(player[i][j]);
            System.out.println();
        }
    }  
    //玩家隨機選卡（在決定好的卡片內選）
    public void player_card_distribution(int card[], int player[][], int people){
        int[] lucky = new int[people * 4];
        int temp = 0;
        int repeat = 1;
        int index = 0;

        Random run = new Random();
        temp = run.nextInt(people * 4) + 1;
        lucky[0] = temp;
        for(int i = 1; i < people * 4; i++) {
            repeat = 1;
            while(repeat == 1) {
                temp = run.nextInt(people * 4) + 1;

                for(int j = 0; j < i; j++) {
                    if(temp != lucky[j]) {
                        lucky[i] = temp;
                        repeat = 0;
                    }
                    else{
                        repeat = 1;
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < people; i++) {
            for(int j = 0; j < 4; j++){
                player[i][j] = lucky[index];
                index++;
            }
        }
        // for(int i = 0; i < people; i++) {
        //     for(int j = 0; j < 4; j++){
               
        //         Card b = new Card();
        //         System.out.println("第" +  (i + 1) + "個玩家的卡片：" + player[i][j]);
        //         b.card_name(player[i][j]);
        //         System.out.println();
        //     }
        // }


        // Vector v = new Vector();
        // for (int i = 0; i < people; i++) {
        //     v.add(new Player());
        // }
        // for(int i = 0; i < people; i++) {
        //     for(int j = 0; j < 4; j++) {
        //         Object a = v.get(i);
        //         a.cards[j] = lucky[index];
        //         index++;
        //     }
        // } 
        judge_first(people, player);
    }


    //未指定的隨機選卡
    public void random_norepeat_number(int player[][], int card[], int choose[], int num, int front, int back){
        int[] lucky = new int[num];
        int temp = 0;
        int repeat = 1;
        
        Random run = new Random();
        temp = run.nextInt(32 -front);
        lucky[0] = temp;
        for(int i = 1; i < num; i++) {
            repeat = 1;
            while(repeat == 1) {
                temp = run.nextInt(32 - front);

                for(int j = 0; j < i; j++) {
                    if(temp != lucky[j]) {
                        lucky[i] = temp;
                        repeat = 0;
                    }
                    else{
                        repeat = 1;
                        break;
                    }
                }
            }
        }
        for(int i = front; i < back; i++) {   
            card[i] = choose[lucky[i - front]];
        }
        
        player_card_distribution(card, player, people);
    }

    //根據人數選擇會用上的卡片(指定選卡)
    public void card_distribution(int people, int card[], int player[][]){ 
        card[0] = 1; //第一發現者
        card[1] = 2; //犯人
        card[2] = 3; //偵探
        card[3] = 7; //不在場證明
        
        switch(people) {
            case 3:
                int choose3[] = {4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};  
                random_norepeat_number(player, card, choose3, 8, 4, 12);
                break;
            case 4:
                card[4] = 12;   //共犯
                int choose4[] = {4, 5, 6, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};  
                random_norepeat_number(player, card, choose4, 11, 5, 16);
                break;
            case 5:
                card[4] = 12;   //共犯
                card[5] = 8;    //不在場證明
                int choose5[] = {4, 5, 6, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};  
                random_norepeat_number(player, card, choose5, 14, 6, 20);
                break;
            case 6:
                card[4] = 12;   //共犯
                card[5] = 8;    //不在場證明
                card[6] = 4;    //偵探
                card[7] = 13;   //共犯
                int choose6[] = {5, 6, 9, 10, 11, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};  
                random_norepeat_number(player, card, choose6, 16, 8, 24);
                break;
            case 7:
                card[4] = 12;   //共犯
                card[5] = 8;    //不在場證明
                card[6] = 4;    //偵探
                card[7] = 13;   //共犯
                card[8] = 9;    //不在場證明
                int choose7[] = {5, 6, 10, 11, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};  
                random_norepeat_number(player, card, choose7, 19, 9, 28);
                break;
            case 8:
                for (int i = 0; i < 32; i++) {  //用上全部的卡
                    card[i] = i + 1;
                }
                player_card_distribution(card, player, people);
                break;
        }
    }

    //確認人數是否在範圍內
    public void people_limitation(int people){  

    }

    //玩家有幾人
    public void how_many_players() {
        Scanner scn = new Scanner(System.in);
        System.out.println("請輸入有多少玩家：（人數限制：3 ~ 8 人）");
        people = scn.nextInt();
        people_limitation(people);
        System.out.println("將有 " + people + " 位玩家進行遊戲。");
        card_distribution(people, card, player);
    }
    
}

