package Test1;

import java.util.List;

public class PCB {
    private String processName;     // 进程名称
    private int processId;          // 进程ID
    private int processState;       // 进程状态
    private int priority;           // 进程优先级

    //初始化
    public PCB() {
    }

    public PCB(String processName, int processId, int processState, int priority) {
        this.processName = processName;
        this.processId = processId;
        this.processState = processState;
        this.priority = priority;
    }

    /**
     * 获取
     * @return processName
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * 设置
     * @param processName
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * 获取
     * @return processId
     */
    public int getProcessId() {
        return processId;
    }

    /**
     * 设置
     * @param processId
     */
    public void setProcessId(int processId) {
        this.processId = processId;
    }

    /**
     * 获取
     * @return processState
     */
    public int getProcessState() {
        return processState;
    }

    /**
     * 设置
     * @param processState
     */
    public void setProcessState(int processState) {
        this.processState = processState;
    }

    /**
     * 获取
     * @return priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 设置
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
