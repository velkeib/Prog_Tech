package test;

public class Model {
    private int size;
    private int[][] table;
    private Player[][] who;
    public static boolean playerturn;
    private int p1p=0;
    private int p2p=0;
    
    public Model(int size) {
        this.size = size;
        playerturn = true;
        
        who = new Player[size][size];
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                who[i][j] = Player.SENKI;
            }
        }

        table = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                table[i][j] = 0;
            }
        }
    }
    
    public int step(int row, int column) {
    if(playerturn){    
        if(table[row][column] < 4){
            table[row][column]++;
            if(table[row][column]==4){
                p1p++;
                who[row][column] = Player.PIROS;
            }
        }
        if(row<size-1 && table[row+1][column] < 4){
            table[row+1][column]++;
            if(table[row+1][column]==4){
                p1p++;
                who[row+1][column] = Player.PIROS;
            }
        }
        if(row>0 && table[row-1][column] < 4){
            table[row-1][column]++;
            if(table[row-1][column]==4){
                p1p++;
                who[row-1][column] = Player.PIROS;
            }
        }
        if(column<size-1 && table[row][column+1] < 4){
            table[row][column+1]++;
            if(table[row][column+1]==4){
                p1p++;
                who[row][column+1] = Player.PIROS;
            }
        }
        if(column>0 && table[row][column-1] < 4){
            table[row][column-1]++;
            if(table[row][column-1]==4){
                p1p++;
                who[row][column-1] = Player.PIROS;
            }
        }
    }else{
        if(table[row][column] < 4){
            table[row][column]++;
            if(table[row][column]==4){
                p2p++;
                who[row][column] = Player.KEK;
            }
        }
        if(row<size-1 && table[row+1][column] < 4){
            table[row+1][column]++;
            if(table[row+1][column]==4){
                p2p++;
                who[row+1][column] = Player.KEK;
            }
        }
        if(row>0 && table[row-1][column] < 4){
            table[row-1][column]++;
            if(table[row-1][column]==4){
                p2p++;
                who[row-1][column] = Player.KEK;
            }
        }
        if(column<size-1 && table[row][column+1] < 4){
            table[row][column+1]++;
            if(table[row][column+1]==4){
                p2p++;
                who[row][column+1] = Player.KEK;
            }
        }
        if(column>0 && table[row][column-1] < 4){
            table[row][column-1]++;
            if(table[row][column-1]==4){
                p2p++;
                who[row][column-1] = Player.KEK;
            }
        }
        
    }
    playerturn = !playerturn;
    return table[row][column];
    }
    
    public int getTable(int row, int column){
        return table[row][column];
    }
    
    public Player getScorer(int row, int column){
        return who[row][column];
    }
    
    public int getP1(){
        return p1p;
    }
    
    public int getP2(){
        return p2p;
    }
    
    public boolean getWho(){
        return playerturn;
    }
    
    public String getPlayer(){
        if(playerturn){
            return "Piros";
        }else{
            return "Kék";
        }
         
    }
}
