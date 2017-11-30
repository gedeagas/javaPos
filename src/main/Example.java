package main;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * Created by gedeagas on 27/11/17.
 */
public class Example {



    public static void cetak(String nama){

        new Thread(new Runnable() {
            public void run() {
                int x = 0;
                while (x<500){
                    System.out.println(nama);
                    x++;
                }
            }
        }).start();



    }


    public static void main(String[] args) {


        System.out.println("HELO");
        cetak("Zain");
        System.out.println("Helo 2");

    }
}
