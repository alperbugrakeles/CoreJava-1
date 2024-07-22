package org.example;

public class methoDemo {
    public static void main(String[] args) {

        //sınıftaki methoda erişmek için ne yapmamız lazım, önce create object sonra methoda erişim.
        methoDemo newDemo = new methoDemo();
        newDemo.getData();
        newDemo.getString();
        String name = newDemo.getString();
        System.out.println(name);
        System.out.println("*****************");

//        //methoDemo2 de olusturdugumu methoda burada nasıl erişiriz
        methoDemo2 newDemo2 = new methoDemo2();
        newDemo2.getUserString();
        String txt = newDemo2.getUserString();
        System.out.println(txt);
        System.out.println("*****************");

        String response = getString2(); // gorüldügü gibi bu metodu cagırıken obje olusturmadım cunkü statik olarak tanımladım.
        System.out.println(response);


    }

    //methodları burada yazıyoruz main içide calıstırıyoruz.

    public void getData(){
        System.out.println("Hello world");
       // return "neden olmasın"; diyor ki void methodda string dünduremezsin
    }

    public String getString(){
        System.out.println("Hello Baby");
        return "Class can dismiss";
    }
    // simdi bu methodları baska sınıfa tasıyalım

    // static key word -> methodu static olarka tanımladığımız da artık classa ait bir method oluyor ve instance yaratıp kullanmamıza gerek olmuyor

    public static String getString2(){
        System.out.println("getString2 Methodu calıstı");
        return "Dammmmmnn";
    }


}
