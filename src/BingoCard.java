public class BingoCard {
    private BingoCell[][] nums;
    private final BingoGame game;
    private int id;
    private static int ID = 1;
    public BingoCard(BingoGame game){
        this.game = game;
        nums = new BingoCell[5][5];
        boolean[] picked = new boolean[76];
        nums[2][2] = game.getCell(0);
        picked[0] = true;
        id = ID++;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(i == 2 && j == 2)
                    continue;
                int rand = 0;
                while(picked[rand])
                    rand = (int)(Math.random() * 15 + (i*15) + 1);
                picked[rand] = true;
                nums[i][j] = game.getCell(rand);
            }
        }
    }
    public BingoCell getCell(int i, int j){
        return nums[i][j];
    }

    public int getId() {
        return id;
    }

    public void print(){
        System.out.println("Card "+id);
        System.out.println("B\tI\tN\tG\tO");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++)
                System.out.print(nums[j][i].getNum()+"\t");
            System.out.println();
        }
    }
}
