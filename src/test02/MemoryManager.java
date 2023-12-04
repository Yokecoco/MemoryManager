package test02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MemoryManager {
    //模拟空闲内存块大小
    private static final int MEMORY_SIZE = 1024;  // 总内存大小
    private List<MemoryBlock> freeBlocks = new ArrayList<>();  // 空闲内存块队列
    private List<PCB> processes = new ArrayList<>();  // 进程队列

    MemoryManager() {
        // 初始化空闲内存块
        freeBlocks.add(new MemoryBlock(0, MEMORY_SIZE - 1));
        //模拟已被占用的内存
        this.createProcess("A", 256);
        this.createProcess("B", 48);
        this.createProcess("C", 128);
        this.createProcess("D", 24);
        this.createProcess("E", 64);
        this.killProcess("B");
        this.killProcess("D");
    }

    // 最先分配算法：在空闲内存块队列中查找大小 >= processSize 的第一个内存块
    private MemoryBlock FIFOFindFreeBlock(int processSize) {
        //Collections.sort(freeBlocks, (o1, o2)-> o1.startAddress - o2.startAddress);
        for (MemoryBlock block : freeBlocks) {
            if (block.isFree && block.size >= processSize)
                return block;
        }
        return null;
    }


    // 最优分配算法：空闲分区按容量递增排序，优先分配最小的内存块
    private MemoryBlock BestFindFreeBlock(int processSize) {
        //Collections.sort(freeBlocks, Comparator.comparingInt(block->block.size));
        for (MemoryBlock block : freeBlocks) {
            if (block.isFree && block.size >= processSize)
                return block;
        }
        return null;
    }

    // 最坏分配算法：空闲分区按容量递减排序，优先分配最大的内存块
    private MemoryBlock WorseFindFreeBlock(int processSize){
        //Collections.sort(freeBlocks, (o1, o2) -> o2.size- o1.size);
        for (MemoryBlock block : freeBlocks) {
            if (block.isFree && block.size >= processSize)
                return block;
        }
        return null;
    }

    // 分配内存给进程
    public void createProcess(String name, int size) {
        PCB process = new PCB(name, size);

        MemoryBlock freeBlock = FIFOFindFreeBlock(size);
        if (freeBlock == null) {
            System.out.println("No enough memory space for process " + name);
            return;
        }

        processes.add(process);
        MemoryBlock mb = new MemoryBlock(freeBlock.startAddress, freeBlock.startAddress + size - 1);
        mb.isFree = false;
        process.setMemoryBlock(mb);

        freeBlock.startAddress += size;

        System.out.println("Process " + name + " allocated memory from " +
                process.getMemoryBlock().startAddress + " to " + process.getMemoryBlock().endAddress);

        //按照FIFO排序输出
        Collections.sort(freeBlocks, (o1, o2)-> o1.startAddress - o2.startAddress);
        //按照best排序输出
        //Collections.sort(freeBlocks, Comparator.comparingInt(block->block.size));
        //按照worse排序输出
        //Collections.sort(freeBlocks, (o1, o2) -> o2.size- o1.size);
        printFreeBlocks();
    }

    // 回收进程所占用的内存
    public void killProcess(String name) {
        PCB process = null;
        for (PCB p : processes) {
            if (p.getName().equals(name)) {
                process = p;
                break;
            }
        }
        if (process == null) {
            System.out.println("Process " + name + " not found");
            return;
        }

        MemoryBlock blockToRelease = process.getMemoryBlock();
        blockToRelease.isFree = true;
        processes.remove(process);
        freeBlocks.add(blockToRelease);

        // 合并相邻的空闲内存块
        MemoryBlock prevBlock = null;
        MemoryBlock nextBlock = null;
        for (MemoryBlock block : freeBlocks) {
            if (block.endAddress + 1 == blockToRelease.startAddress)
                prevBlock = block;
            if (block.startAddress - 1 == blockToRelease.endAddress)
                nextBlock = block;
        }

        if (prevBlock != null && prevBlock.isFree) {
            prevBlock.endAddress = blockToRelease.endAddress;
            freeBlocks.remove(blockToRelease);
        }

        if (nextBlock != null && nextBlock.isFree) {
            blockToRelease.endAddress = nextBlock.endAddress;
            freeBlocks.remove(nextBlock);
        }

        System.out.println("Process " + name + " memory released");

        //按照FIFO排序输出
        Collections.sort(freeBlocks, (o1, o2)-> o1.startAddress - o2.startAddress);
        //按照best排序输出
        //Collections.sort(freeBlocks, Comparator.comparingInt(block->block.size));
        //按照worse排序输出
        //Collections.sort(freeBlocks, (o1, o2) -> o2.size- o1.size);

        printFreeBlocks();
    }

    // 打印空闲内存块队列状态
    public void printFreeBlocks() {
        System.out.print("Free memory blocks:");
        for (MemoryBlock block : freeBlocks) {
            if (block.isFree)
                System.out.print(" [" + block.startAddress + "," + block.endAddress + "]");
        }
        System.out.println();
    }
}
