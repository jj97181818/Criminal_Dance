public class Card{
    //卡片名字對應
    public void card_name(int c){
        if(c == 0)
            System.out.println("");
        else if(c == 1)
            System.out.println("第一發現者");
        else if(c == 2)
            System.out.println("犯人");
        else if((c >= 3) && (c <= 6))
            System.out.println("偵探");
        else if((c >= 7) && (c <= 11))
            System.out.println("不在場證明");
        else if((c >= 12) && (c <= 13))
            System.out.println("共犯");
        else if((c >= 14) && (c <= 16))
            System.out.println("目擊者");
        else if((c >= 17) && (c <= 18))
            System.out.println("普通人");
        else if(c == 19)
            System.out.println("神犬");
        else if((c >= 20) && (c <= 23))
            System.out.println("情報交換");
        else if((c >= 24) && (c <= 28))
            System.out.println("謠言");
        else if((c >= 29) && (c <= 32))
            System.out.println("交易");
        
        /*
        第一發現者：1
        犯人：2
        偵探：3 ~ 6
        不在場證明：7 ~ 11
        共犯：12 ~ 13
        目擊者：14 ~ 16
        普通人：17 ~ 18
        神犬：19
        情報交換：20 ~ 23
        謠言：24 ~ 28
        交易：29 ~ 32
        */
    }
}

