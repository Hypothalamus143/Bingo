public class ColumnChecker extends BingoChecker{
    private int col;
    public ColumnChecker(BingoCard card, int col){
        super(card);
        this.col = col;
    }
    public void run(){
        for(int i = 0; i < 5; i++){
            if(!getCard().getCell(i, col).isPicked()){
                synchronized (getCard().getCell(i, col)){
                    try {
                        getCard().getCell(i, col).wait();
                    } catch (InterruptedException e) {
                        System.out.println("Card "+getCard().getId()+"'s column loses while waiting for "+getCard().getCell(i, col).getNum());
                        return;
                    }
                }
            }
        }
    }
}
