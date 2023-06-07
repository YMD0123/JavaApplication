package prime.sample;

import java.util.*;


public class PrimeNumeber {
    public static List<Integer> generate(int number){

        List<Integer> plist = new ArrayList<>();
        boolean flag;

        int i = 2;

        while(i < 100){
            flag = false;

            flag = isPrime(i);

            if(flag){
                //配列に追加(素数)
                plist.add(i);
            }

            if(plist.size() == number){
                break;
            }
            i++;
        }

        return plist;
    }

    public static boolean isPrime(int num){
        boolean pflg = true;

        for(int r=2; r < num;r++) {

            if(num % r == 0){
                //素数ではない
                pflg = false;
                break;
            }
        }

        return pflg;
    }

    public static int getPrime(int num){
        int n;

        List<Integer> getlist;
        getlist = generate(100);

        n = getlist.get(num-1);

        return n;
    }
}
