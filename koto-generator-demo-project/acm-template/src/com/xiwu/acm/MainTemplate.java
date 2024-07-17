package com.koto.acm;
import java.util.Scanner;

/**
 * ACM输入模板(多数之和)
 */
public class MainTemplate{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int[] arr=new int[n];
            for (int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            int sum=0;
            for(int num:arr){
                sum+=num;
            }
            System.out.println("Sum:"+sum);
        }
        sc.close();
    }
}