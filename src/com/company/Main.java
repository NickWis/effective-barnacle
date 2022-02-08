package com.company;
import java.io.*;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args)  {
        //Проверка введенных параметров
        if ((args.length < 2) | args[args.length-1].startsWith("-")) {
            System.out.println("Uncorrected parameters");
            System.exit(1);
        }
        Boolean stringB = true, ascending= true;
        String line;
        int outf = 0;
        ArrayList<Integer> MergeI = new ArrayList<Integer>();
        ArrayList<String> MergeS = new ArrayList<String>();
        //Считываем и запоминаем данные
        for(int i = 0; i < args.length-1; ++i) {
            if (args[i].startsWith("-")) {
                switch (args[i]) {
                    case "-s": {
                        outf++;
                        stringB=true;
                        break;
                    }
                    case "-i": {
                        outf++;
                        stringB = false;
                        break;
                    }
                    case "-a": {
                        outf++;
                        ascending = true;
                        break;
                    }
                    case "-d": {
                        outf++;
                        ascending = false;
                        break;
                    }
                    default: {
                        System.err.println("\nInvalid key\n");
                    }
                }
            }
            else{
                i++;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(args[i]));
                    while((line = br.readLine()) != null){
                        MergeS.add(line);
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
                i--;
                //считали все данные из файлов
            }
        }
        if(MergeS.size()==0)
        {
            System.out.println("Files are empty!");
            System.exit(1);
        }
        BufferedWriter bw=null;
        try {
            bw = new BufferedWriter(new FileWriter(args[outf]));
        }
        catch(IOException e) {
            System.err.println("\nThe file cannot be opened for writing\n");
            e.printStackTrace();
            System.exit(1);

        }
        //сортируем и выводим в зависимости от типа данных
        if(stringB)
        {
            MergeSortS(MergeS, ascending);
            for(int i = 0;i< MergeS.size();i++)
            {
                try {
                    bw.write(MergeS.get(i) + "\n");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else
        {
            for(int i = 0;i< MergeS.size();i++)
            {
                MergeI.add(i,Integer.parseInt(MergeS.get(i)));
            }
            MergeSortI(MergeI, ascending);
            for(int i = 0;i< MergeI.size();i++)
            {
                line = Integer.toString(MergeI.get(i));
                try {
                    bw.write(line + "\n");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        try {
            if(bw != null)
                bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static  void MergeSortI(ArrayList<Integer> array, Boolean ascending)//Сортировка для integer
    {
        for (int i = 1; i <= array.size()-1; i = 2*i)
        {
            for (int j = 0; j < array.size()-1; j += 2*i)
            {
                int mid = Math.min(j + i - 1, array.size()-1);
                int right_end = Math.min(j + 2*i - 1, array.size()-1);
                MergeI(array, j, mid, right_end, ascending);
            }
        }
    }
    public static void MergeI(ArrayList<Integer> array, int l, int m, int r, Boolean ascending) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (i = 0; i < n1; i++)
            L[i] = array.get(l + i);
        for (j = 0; j < n2; j++)
            R[j] = array.get(m + 1 + j);
        i = 0;
        j = 0;
        k = l;
        if (ascending) {
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    array.set(k, L[i]);
                    i++;
                } else {
                    array.set(k, R[j]);
                    j++;
                }
                k++;
            }
            while (i < n1) {
                array.set(k, L[i]);
                i++;
                k++;
            }
            while (j < n2) {
                array.set(k, R[j]);
                j++;
                k++;
            }
        } else {
            while (i < n1 && j < n2) {
                if (L[i] >= R[j]) {
                    array.set(k, L[i]);
                    i++;
                } else {
                    array.set(k, R[j]);
                    j++;
                }
                k++;
            }
            while (i < n1) {
                array.set(k, L[i]);
                i++;
                k++;
            }
            while (j < n2) {
                array.set(k, R[j]);
                j++;
                k++;
            }
        }
    }
    public static  void MergeSortS(ArrayList<String> array, Boolean ascending)//Сортировка для string
    {
        for (int i = 1; i <= array.size()-1; i = 2*i)
        {
            for (int j = 0; j < array.size()-1; j += 2*i)
            {
                int mid = Math.min(j + i - 1, array.size()-1);
                int right_end = Math.min(j + 2*i - 1, array.size()-1);
                MergeS(array, j, mid, right_end, ascending);
            }
        }
    }
    public static void MergeS(ArrayList<String> array, int l, int m, int r, Boolean ascending) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;
        String L[] = new String[n1];
        String R[] = new String[n2];
        for (i = 0; i < n1; i++)
            L[i] = array.get(l + i);
        for (j = 0; j < n2; j++)
            R[j] = array.get(m + 1 + j);
        i = 0;
        j = 0;
        k = l;
        if (ascending) {
            while (i < n1 && j < n2) {
                if (L[i].compareTo(R[j])<=0) {
                    array.set(k, L[i]);
                    i++;
                } else {
                    array.set(k, R[j]);
                    j++;
                }
                k++;
            }
            while (i < n1) {
                array.set(k, L[i]);
                i++;
                k++;
            }
            while (j < n2) {
                array.set(k, R[j]);
                j++;
                k++;
            }
        } else {
            while (i < n1 && j < n2) {
                if (L[i].compareTo(R[j])>=0) {
                    array.set(k, L[i]);
                    i++;
                } else {
                    array.set(k, R[j]);
                    j++;
                }
                k++;
            }
            while (i < n1) {
                array.set(k, L[i]);
                i++;
                k++;
            }
            while (j < n2) {
                array.set(k, R[j]);
                j++;
                k++;
            }
        }
    }
}
