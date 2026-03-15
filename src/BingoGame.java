import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BingoGame {
    private BingoCell nums[];
    private boolean bingo;
    public BingoGame(){
        nums = new BingoCell[76];
        for(int i = 0; i <= 75; i++)
            nums[i] = new BingoCell(i);
        nums[0].setPicked();
        bingo = false;
    }
    public BingoCell getCell(int index){
        return nums[index];
    }

    public void setBingo(){
        this.bingo = true;
    }
    public boolean getBingo(){
        return bingo;
    }
    public static void main(String[] args) {
        BingoGame game = new BingoGame();
        int players;
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of players: ");
        players = sc.nextInt();
        sc.nextLine();
        char op;
        System.out.print("Choose Pattern (+, #): ");
        op = sc.nextLine().charAt(0);
        List<BingoCard> cards = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        List<CheckPattern> checkers= new ArrayList<>();
        boolean[] picked = new boolean[76];
        for(int i = 1; i < 76; i++)
            picked[i] = false;
        picked[0] = true;
        for(int i = 0; i < players; i++){
            cards.add(new BingoCard(game));
            cards.getLast().print();
            if(op == '+')
                checkers.add(new CheckPatternPlus(game, cards.getLast()));
            else if(op == '#')
                checkers.add(new CheckPatternHash(game, cards.getLast()));
            else{
                System.out.println("Invalid Pattern");
                return;
            }
            threads.add(new Thread(checkers.getLast()));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        for(Thread t : threads)
            t.start();
        int rand;
        int count = 0;
        while(!game.getBingo()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
            if(count == 75){
                System.out.println("No one wins hahaha");
                break;
            }
            rand = 0;
            while(picked[rand])
                rand = (int)(Math.random() * 75 + 1);
            picked[rand] = true;
            count++;
            game.nums[rand].setPicked();
            System.out.println("Picked: ");
            for(int i = 1; i <= 75; i++){
                if(game.nums[i].isPicked())
                    System.out.print(game.nums[i].getNum()+" ");
            }
            System.out.println();
        }
        for(CheckPattern c : checkers){
            c.killAll();
        }
        for(Thread t : threads){
            t.interrupt();
        }
    }
}
