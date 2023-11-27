package enhanceTest01;

public class PCB {
    private int processId; // 进程ID
    private int priority; // 优先级
    private String status; // 状态
    private String processName; // 进程名称

    public PCB(int processId, int priority, String processName) {
        this.processId = processId;
        this.priority = priority;
        this.status = "Ready";
        this.processName = processName;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Override
    public String toString() {
        return processName + " (PID: " + processId + ", Priority: " + priority + ", Status: " + status + ")";
    }
}

