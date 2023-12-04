package test02;

public class MemoryBlock {
    int startAddress;  // 内存块起始地址
    int endAddress;  // 内存块结束地址
    boolean isFree;  // 标记内存块是否空闲

    MemoryBlock(int startAddress, int endAddress) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.isFree = true;
    }
}
