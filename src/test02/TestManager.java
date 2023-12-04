package test02;

public class TestManager {
    public static void main(String[] args) {
        MemoryManager memoryManager = new MemoryManager();
        memoryManager.createProcess("ProcessA", 256 * 1024);
        memoryManager.createProcess("ProcessB", 512 * 1024);
        memoryManager.createProcess("ProcessC", 128 * 1024);
        memoryManager.killProcess("ProcessB");
        memoryManager.createProcess("ProcessD", 256 * 1024);
    }
}
