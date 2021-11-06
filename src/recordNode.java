import java.nio.ByteBuffer;

public class recordNode implements Comparable<recordNode> {

    private byte[] smallArray;
    long getID;
    double getKey;

    //Create constructor with array
    public recordNode(byte[] copyOfRange){
        smallArray = copyOfRange;
        ByteBuffer buff = ByteBuffer.wrap(smallArray);
        getID = buff.getLong();
        getKey = buff.getDouble(8);
    }

    public double getGetKey() {
        return getKey;
    }

    public recordNode(byte[] copyOfRange, long getID, double getKey) {
        smallArray = copyOfRange;
        this.getID = getID;
        this.getKey = getKey;
    }

    public byte[] getSmallArray() {
        return smallArray;
    }



    @Override
    public int compareTo(recordNode o) {
        return Double.compare(this.getKey, o.getKey);
    }
}
