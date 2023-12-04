package test02;

public class PCB {
    private String name;  // 进程名称
    private int size;  // 进程大小
    private MemoryBlock memoryBlock;  // 分配的内存块

    public PCB() {
    }

    public PCB(String name, int size) {
        this.name = name;
        this.size = size;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取
     * @return memoryBlock
     */
    public MemoryBlock getMemoryBlock() {
        return memoryBlock;
    }

    /**
     * 设置
     * @param memoryBlock
     */
    public void setMemoryBlock(MemoryBlock memoryBlock) {
        this.memoryBlock = memoryBlock;
    }
}
