public class RowChecker extends BingoChecker{
    private int row;
    public RowChecker(BingoCard card, int row){
        super(card);
        this.row = row;
    }
    public void run(){
        for(int i = 0; i < 5; i++){
            if(!getCard().getCell(row, i).isPicked()){
                synchronized (getCard().getCell(row, i)){
                    try {
                        getCard().getCell(row, i).wait();
                    } catch (InterruptedException e) {
                        System.out.println("Card "+getCard().getId()+"'s row loses while waiting for "+getCard().getCell(row, i).getNum());
                        return;
                    }
                }
            }
        }
    }
}
