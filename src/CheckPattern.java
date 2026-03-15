import java.util.ArrayList;
import java.util.List;

abstract public class CheckPattern implements Runnable{
    protected List<Thread> threads = new ArrayList<>();
    private final BingoGame game;
    private BingoCard card;

    public CheckPattern(BingoGame game, BingoCard card){
        this.card = card;
        this.game = game;

    }

    public void run(){
        for(Thread t : threads) {
            t.start();
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                return;
            }
        }
        game.setBingo();
        System.out.println("Card "+card.getId()+" completes a pattern!");
    }
    public void killAll(){
        for(Thread t : threads) {
            t.interrupt();
        }
    }
    public BingoCard getCard() {
        return card;
    }
}
