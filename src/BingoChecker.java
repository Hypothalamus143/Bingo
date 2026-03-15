
abstract public class BingoChecker implements Runnable{
    private BingoCard card;
    public BingoChecker(BingoCard card){
        this.card = card;
    }

    public BingoCard getCard(){
        return card;
    }
}
