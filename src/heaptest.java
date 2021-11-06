public class heaptest {
    public static void main (String [] arg){

        Minheap<Integer> testheap = new Minheap<>();

        Integer[] intArray = new Integer[20];

        testheap.setSize(20);
        testheap.setHeap(intArray);

        for (int i = 0; i < 20; i++){
            testheap.insert(20 - i);
        }

        System.out.println(testheap.getRoot());

    }
}
