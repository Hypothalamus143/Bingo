public class CheckPatternHash extends CheckPattern{
    public CheckPatternHash(BingoGame game, BingoCard card){
        super(game, card);
        threads.add(new Thread(new RowChecker(card, 1)));
        threads.add(new Thread(new RowChecker(card, 3)));
        threads.add(new Thread(new ColumnChecker(card, 1)));
        threads.add(new Thread(new ColumnChecker(card, 3)));
    }
}
