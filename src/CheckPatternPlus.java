public class CheckPatternPlus extends CheckPattern{
    public CheckPatternPlus(BingoGame game, BingoCard card){
        super(game, card);
        threads.add(new Thread(new RowChecker(card, 2)));
        threads.add(new Thread(new ColumnChecker(card, 2)));
    }
}
