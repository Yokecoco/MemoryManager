package test02;

import java.util.Scanner;

public class TestManager {
    public static void main(String[] args) {
        System.out.println("系统模拟内存：");
        MemoryManager memoryManager = new MemoryManager();
        System.out.println("========================================================");

        //创建新进程
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你想创建的进程数量：");
        int number = sc.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("请输入进程的名称：");
            String name = sc.next();
            System.out.println("请输入进程的大小：");
            int size = Integer.parseInt(sc.next());
            memoryManager.createProcess(name,size);
        }
    }
}
