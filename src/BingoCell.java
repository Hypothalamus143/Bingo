public class BingoCell {
    private int num;
    private boolean picked;

    public BingoCell(int num){
        this.num = num;
        picked = false;
    }
    public int getNum(){
        return num;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked() {
        this.picked = true;
        synchronized (this){
            notifyAll();
        }

    }
}
