package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ders 1 Java Variables and Data types

       int myNum = 5;
       myNum = 6;
       System.out.println(myNum);
       System.out.println("*********");

       String webSite= "Academy";
       System.out.println(webSite);
       System.out.println("-----------");

       char letter = 'R';
       System.out.println(letter);
       System.out.println("-----------");

      double oran = 5.23;
      System.out.println(oran);
      System.out.println("-----------");

      boolean myDecision= true;
      System.out.println(myDecision);
      System.out.println("-----------");

      System.out.println(myNum + " benim sıra numaram"+ " bahis oranım " + oran + " ve bu konuda kararım kesin "+ myDecision);
        System.out.println("-----------");
      // Arrays
        int [] array = new int[5];
        array[0] =1;
        array[1]= 2;
        array[2]= 4;
        array[3]= 5;
        array[4]= 6;

        int [] arr = {1,2,4,5,6};
        System.out.println(arr[2]);
        System.out.println(array[2]);
        System.out.println("Array lenght degeri " + array.length);
        System.out.println("-----------");

        // for loop
        for (int i=0; i<array.length;i++){
            System.out.println("Array "+i +"inci degeri" + array[i]);
        }
        System.out.println("-----------");

        String [] name = {"Advanced", "Software", "Testing","Lecture"};
        for (int i =0; i< name.length; i++){
            System.out.println(name[i]);
        }
        System.out.println("-----------");
        // Yukarıdaki kodu başka sekilde yazalım
        for (String s : name){
            System.out.println(s);
        }
        System.out.println("-----------");
        for (int i :arr){
            System.out.println(i);
        }
        System.out.println("------------------------------------------------------------------------------");
        int [] arr3 = {1,2,4,5,6,7,8,9,10,122};
        // Yukarıdaki dizide 2 ye bülünebilenleri getiren bir kod yazalım : 2,4,6,8,10,122

        for(int i=0; i<arr3.length;i++) {

            if(arr3[i] % 2==0) {
                System.out.println(arr3[i]);
              //   break; eğer ilk 2 ye tam bolüneni bulunca dongüyü kırılsın dersem
             }
            else{
                System.out.println(arr3[i]+ " Sayisi 2 ye bölünmez");
            }
        }
        System.out.println("------------------------------------------------------------------------------");

        // Arraylist ve Arrays
        // Bizim calısırken dinamik arraylere ihtiyacımız olur genelde.
        ArrayList<String> a = new ArrayList<String>();
        a.add("Alper");
        a.add("Keles");
        a.add("Fırat");
        a.add("Kamil");
        a.add("Selenium");
        System.out.println(a);
        System.out.println(a.get(2));
        a.remove(2);
        System.out.println(a);
        System.out.println(a.get(2));
        System.out.println(a.size());
        System.out.println("------------------------------------------------------------------------------");
        // for loop with array list
        for(int i=0; i<a.size();i++){
            System.out.println( a.get(i));
        }
        System.out.println("------------------------------------------------------------------------------");
        // aradıgımız bir elemanın listede olup olmadığına bakalım
        System.out.println(a.contains("Selenium")); // true donecek

        // Eger elimdeki listeyi array liste cevrimek istersem
        List<String> nameArrayList = Arrays.asList(name);
        //String [] name = {"Advanced", "Software", "Testing","Lecture"};
        Boolean C = nameArrayList.contains("Selenium");
        System.out.println(C);

        // STRING Object
        String s = "Advanced Software Testing";
        String s1 = "Advanced Software Testing";
        // Yukarıda sadece 1 tane object yaratılıyor
        // new ile 2 tane object yaratılıyor memoryde
        String s2 = new String("Advanced Software Testing");
        String s3 = new String("Advanced Software Testing");

        String s4 = "Advanced Software Test ing";
        String[] bolunmusText = s4.split(" "); // bu sayede bosluk olan yerlerden bülüyorum

        System.out.println(bolunmusText[0]);
        System.out.println(bolunmusText[1]);
        System.out.println(bolunmusText[2]);

        String [] bolum = s4.split("t");
        System.out.println(bolum[0]);
        System.out.println(bolum[1]);
        System.out.println(bolum[2]);
        // bir cok durumda kelimeyi aldıktan sonra boslukları temizlemek isteriz
        System.out.println(bolum[2].trim());

        //s4 stringini tek karaker olacak şekilde yazdıralım. --> Stringe de list of chars diyebilirz
        String s5 = "Advanced Software Testing";
        for(int i=0;i<s5.length();i++){
            System.out.println(s.charAt(i));
        }
        // Tersten yazdırma ödev olacak. g n i t .....


    }
}