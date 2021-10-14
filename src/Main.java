import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    //Method declarations
    public static void BubbleSort(ArrayList<Iris> a, int size){
        for (int i = 0; i < size - 1; ++i){
            for (int j = 0; j < size - i - 1; ++j){
                if (a.get(j+1).isLessThan(a.get(j))){
                    Collections.swap(a, j, j+1);
                }
            }
        }
    }
    public static void mergeSort(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int right){
        //base case is left==right, if it is the case then nothing needs to be done in the base case
        if (left != right){//recursive step
            mergeSort(a, tmp, left, (left+right)/2);
            mergeSort(a, tmp, ((left+right)/2)+1, right);
            mergeSortedLists(a, tmp, left, (left+right)/2, right);
        }
    }
    public static void mergeSortedLists(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int middle, int right){
        tmp.clear();
        int i = left;//initial index of the left array
        int j = middle+1;//initial index of the right array
        while ((i <= middle) && (j <= right)){
            //compare the smallest object in each sub-array, and add the smallest one to tmp until one of the array is out of elements
            if (a.get(i).isLessThan(a.get(j))){
                tmp.add(a.get(i));
                ++i;
            }
            else{
                tmp.add(a.get(j));
                ++j;
            }
        }
        //use while loop to put the remaining sorted elements in the sub-array to tmp
        while (i <= middle){
            tmp.add(a.get(i));
            ++i;
        }
        while (j <= right){
            tmp.add(a.get(j));
            ++j;
        }
        i = left;
        //set elements from left to right in a to the elements in tmp
        for (int loopVal = 0; loopVal < right - left + 1; ++loopVal){
            a.set(i, tmp.get(loopVal));
            ++i;
        }
    }

    public static void main(String [] args){
        //.....
        //....
        ArrayList<Iris> list=new ArrayList<Iris>();   // list to be sorted
        ArrayList<Iris> tmp=new ArrayList<Iris>();   // temporary workspace
        //fill list
        FileInputStream myFile = null;
        //find file fish-iris.csv.txt
        try{
            myFile = new FileInputStream("src/fish-iris.csv.txt");
        }
        catch(FileNotFoundException e1){
            System.out.println("File not found. Ending program.");//if file is not found, print error message and end program
            System.exit(-1);
        }
        //read from fish-iris.csv.text and put data into ArrayList a
        Scanner fileReader = new Scanner(myFile);
        fileReader.nextLine();//read the title line first
        int line = 0;
        while(fileReader.hasNextLine()){
            line += 1;
            String[] data = fileReader.nextLine().split(",");//split the line based on "," and put the Strings into array
            Iris i = null;
            try{//assign the corresponding data to the parameters of i
                i = new Iris(Double.parseDouble(data[0]),
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        data[4]);
            }
            catch (IndexOutOfBoundsException e2){
                System.out.println("Line " + line + " has wrong format.");//if yield IndexOutOfBoundException then print error message and jump to next loop
                continue;
            }
            catch (NumberFormatException e3){
                System.out.println("Line " + line + " has wrong number format.");//if yield NumberFormatException then print error message and jump to next loop
                continue;
            }
            list.add(i);
        }

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
