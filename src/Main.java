import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    //Method declarations
    public static void BubbleSort(ArrayList<Iris> a, int size){
        //fix me
    }
    public static void mergeSort(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int right){
        //fix me
    }
    public static void mergeSortedLists(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int middle, int right){
        //fix me
    }

    public static void main(String [] args){
        //.....
        //....
        ArrayList<Iris> list=new ArrayList<Iris>();   // list to be sorted
        ArrayList<Iris> tmp=new ArrayList<Iris>();   // temporary workspace
        //fill list
        //....
        //...
        //Create a copy from list for Bubble sort
        ArrayList <Iris> list2=new ArrayList<Iris>();
        for(int i=0;i<list.size();i++)
            list2.add(list.get(i));

        // sort list using mergesort
        mergeSort(list, tmp, 0, list.size());
        //sort list2 using Bubble sort
        BubbleSort(list2, list2.size());

           }
}
