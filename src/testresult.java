import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class testresult {
    public static void main(String[] arg) throws FileNotFoundException, IOException {
        RandomAccessFile readIn2 = new RandomAccessFile("run1.bin", "r");

        byte[] blockofdata2=new byte[(int) readIn2.length()];//8*8192 65536
        readIn2.readFully(blockofdata2);//read in all binary digit into the file
        readIn2.close();

        System.out.println(blockofdata2.length);
      for(int i=0;i< blockofdata2.length;i++){
           System.out.print(blockofdata2[i]);
        }
    }
}
