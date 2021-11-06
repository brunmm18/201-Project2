public class mergeInfo {
    private int starter;
    private int destination;

    public mergeInfo (int starter, int destination){
        this.starter = starter;
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    public int getStarter() {
        return starter;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setStarter(int starter) {
        this.starter = starter;
    }

    public void addStarter (){
        this.starter++;
    }
}
