import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile;

        byte[] blockofdata = new byte[65536]; // this is one block of data
        byte[] inputBuffer = new byte[8192];
        byte[] outputBuffer = new byte[8192];
        recordNode[] recordBuffer = new recordNode[512];
        boolean isReadable = true;
        String runfile = "run1.bin";
        String mergefile = "merge.bin";
        int sizeOfOutput = 0;
        ArrayList<mergeInfo> info = new ArrayList<>();
        ArrayList<recordNode> blocks = new ArrayList<>();
        int runcount =0;
        //read the data

        accessFile = new RandomAccessFile("src/bigSizeInput.bin", "r");

        //Creating run and merge files
        RandomAccessFile run = new RandomAccessFile(runfile, "rw");

        RandomAccessFile merge = new RandomAccessFile(mergefile, "rw");

        recordNode[] nodearray = new recordNode[512];
        //initializing minHeap
        Minheap<recordNode> minHeap = new Minheap<recordNode>();
        minHeap.setSize(512);
        minHeap.setHeap(nodearray);


        //read data into block of data
        try {
            accessFile.read(blockofdata);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //insert 1 block into min heap
        for (int i = 0; i < 512; i++) {
            minHeap.insert(new recordNode(Arrays.copyOfRange(blockofdata, i * 16, i * 16 + 16)));

        }

        //while the bin file is being read
        int k = 0;
        System.out.println(accessFile.length());

        //Replacement Selection Sort
        while (accessFile.getFilePointer() < accessFile.length()){
            try {
                accessFile.read(inputBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int sizeOfinputBuffer = 512;


            //Read input buffer until it is empty.
            while (sizeOfinputBuffer > 0) {
                //If the output buffer is not full then added to it
                if (minHeap.getNum() > 0 ) {
                    if (sizeOfOutput < 512) {
                        for (int i = 0; i < 16; i++) {
                            outputBuffer[sizeOfOutput * 16 + i] = minHeap.getRoot().getSmallArray()[i];
                        }

                        recordNode temp = minHeap.removeMin();
                        recordNode current = new recordNode(Arrays.copyOfRange(inputBuffer,
                                (sizeOfinputBuffer - 1) * 16, ((sizeOfinputBuffer - 1) * 16) + 16));

                        if (current.compareTo(temp) < 0) {
                            minHeap.insertHide(current);
                        } else {
                            minHeap.insert(current);
                        }

                        sizeOfOutput++;
                        sizeOfinputBuffer--;
                    }
                    //output buffer full write to run file
                    else {
                        run.write(outputBuffer);
                        sizeOfOutput = 0;
                    }
                }
                else {
                    run.write(outputBuffer);
                    sizeOfOutput = 0;
                    minHeap.setNum(512);
                    minHeap.buildHeap();
                }
            }

            //Heap is returned to full size
           // while (tempHeap.getNum() > 0){
             //   minHeap.insert(tempHeap.removeMin());
            //}

            //Writing output buffer to run size

            //info.add(new mergeInfo(k * 8192, k * 8192 + 8191));
            //k++;

        }

        //after reading file empty heap
        while (minHeap.getNum() > 0) {
            //write heap to output buffer

            System.out.println(minHeap.getNum());
            for (int i = 0; i < 16; i++) {
                outputBuffer[sizeOfOutput * 16 + i] = minHeap.removeMin().getSmallArray()[i];
            }
            sizeOfOutput++;

            if (sizeOfOutput == 512) {
                run.write(sizeOfOutput);
                info.add(new mergeInfo(k * 8192, k * 8192 + 8192));
            }

        }

        byte [] massiveByte = new byte[info.size() * 8196];
        run.read(massiveByte);

        int numBlock = info.size();

        //Merge begins
       /*while (numBlock > 1){
            int divideNum = numBlock/8;
            int modNum = numBlock%8;

            numBlock = divideNum++;

            for (int i = 0; i < divideNum; i++){

            }
        }*/

    }
}
