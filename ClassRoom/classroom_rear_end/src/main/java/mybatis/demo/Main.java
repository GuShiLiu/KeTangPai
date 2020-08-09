package mybatis.demo;

import java.util.Arrays;
import java.util.Scanner;

// 鸭脖问题
public class Main {
    public static void main(String args[]){
        int n, k;
        Scanner scanner = new Scanner(System.in);

        k = scanner.nextInt();
        int happy[] = new int[k];
        for (int i = 0; i<k; i++){
            happy[i]=0;
        }
        int a[],b[];
        for (int z = 0; z<k;z++) {
            n = scanner.nextInt();
            a = new int[n];
            b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                b[i] = scanner.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);

            for(int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    if (a[i] < b[j]){
                        happy[z]++;
                        b[j]=-1;
                        break;
                    }
                    if (a[i] > b[j] && (j == n-1))
                        happy[z]--;
                }
            }
        }

        for (int c:happy){
            System.out.println(c);
        }

    }
}
