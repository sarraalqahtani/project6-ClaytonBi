import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    //Method declarations
    public static void BubbleSort(ArrayList<Iris> a, int size){
        boolean loop = true;
        for (int i = 0; i < size - 1; ++i){
            for (int j = 0; j < size - i - 1; ++j){
                if (a.get(j+1).isLessThan(a.get(j))){//if the element of index j+1 is smaller than j, then swap the two elements
                    Collections.swap(a, j, j+1);
                    loop = false;
                }
            }
            if (loop){//if loop id true, it means there is no wap in the first loop of i, which means the array is already sorted, so break the loop
                break;
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
        fileReader.close();

        //examination part
        FileOutputStream myChart = null;
        //try find the analysis file in src
        try{
            myChart = new FileOutputStream("src/analysis.csv");
        }
        catch (FileNotFoundException e4){
            System.out.println("File not found. Ending program.");//if file is not found, print error message and end program
            System.exit(-1);
        }
        PrintWriter writeFile = new PrintWriter(myChart);//set up PrintWriter

        writeFile.println(",1k,10k,20k,30k,40k,50k,60k,70k,80k,90k,100k");//print title

        long startTime;
        long finishTime;
        writeFile.print("Bubble Sort,");
        //Create a copy from list for Bubble sort

        //first, calculate time needed for bubble sort of 1k file
        ArrayList <Iris> list3=new ArrayList<Iris>();
        for(int k=0;k<1000;k++)
            list3.add(list.get(k));
        startTime = System.nanoTime();
        BubbleSort(list3, list3.size());
        finishTime = System.nanoTime();
        writeFile.print((finishTime - startTime) + ",");
        int fileSize = 10000;
        for (int i = 0; i < 10; ++i){//use a for loop to calculate time in 10 different bubble sort conditions
            ArrayList <Iris> list2=new ArrayList<Iris>();
            for(int l=0;l<fileSize;l++)
                list2.add(list.get(i));
            startTime = System.nanoTime();
            BubbleSort(list2, list2.size());
            finishTime = System.nanoTime();
            writeFile.print((finishTime - startTime) + ",");
            fileSize += 10000;
        }
        writeFile.println();

        writeFile.print("Mergesort,");
        ArrayList<Iris> list4 = new ArrayList<Iris>();
        for(int k=0;k<1000;k++)
            list4.add(list.get(k));
        startTime = System.nanoTime();
        mergeSort(list4, tmp, 0, list4.size());
        finishTime = System.nanoTime();
        writeFile.print((finishTime - startTime) + ",");
        // sort list using mergesort
        fileSize = 10000;
        for (int i = 0; i < 10; ++i){
            ArrayList<Iris> list5 = new ArrayList<Iris>();
            for(int l=0;l<fileSize;l++)
                list5.add(list.get(l));
            startTime = System.nanoTime();
            mergeSort(list5, tmp, 0, list5.size());
            finishTime = System.nanoTime();
            writeFile.print((finishTime - startTime) + ",");
        }
        writeFile.close();
    }
}
