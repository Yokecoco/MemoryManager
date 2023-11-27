package enhanceTest01;

public class PCB {
    private int processId; // 进程ID
    private String status; // 状态
    private String processName; // 进程名称

    public PCB(int processId, String processName) {
        this.processId = processId;
        this.status = "Ready";
        this.processName = processName;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
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
        return processName + " (PID: " + processId + ", Priority: " + ", Status: " + status + ")";
    }
}

