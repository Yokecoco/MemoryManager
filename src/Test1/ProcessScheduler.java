package Test1;

import java.util.ArrayList;
import java.util.List;

//进程调度器
public class ProcessScheduler {
    private List<PCB> readyQueue;   // 就绪队列
    private List<PCB> blockQueue;   // 阻塞队列
    private List<PCB> runningQueue; // 运行队列
    private int processIdCount;     // 进程ID计数器

    //初始化三个队列
    public ProcessScheduler() {
        this.readyQueue = new ArrayList<>();
        this.blockQueue = new ArrayList<>();
        this.runningQueue = new ArrayList<>();
        this.processIdCount = 0;//计数器初始为0
    }

    // 创建进程
    public void createProcess(String processName, int priority, List<String> resources) {
        PCB pcb = new PCB(processName, processIdCount++, 0, priority);
        //加入就绪队列
        readyQueue.add(pcb);
    }

    // 撤销进程
    public void destroyProcess(int processId) {
        //将进程从运行队列中撤销
        for (int i = 0; i < runningQueue.size(); i++) {
            if (runningQueue.get(i).getProcessId() == processId) {
                runningQueue.remove(i);
                return;
            }
        }
        //将进程从就绪队列中撤销
        for (int i = 0; i < readyQueue.size(); i++) {
            if (readyQueue.get(i).getProcessId() == processId) {
                readyQueue.remove(i);
                return;
            }
        }
        //将进程从阻塞队列中撤销
        for (int i = 0; i < blockQueue.size(); i++) {
            if (blockQueue.get(i).getProcessId() == processId) {
                blockQueue.remove(i);
                return;
            }
        }
    }

    // 进程就绪
    public void readyProcess(int processId) {
        for (int i = 0; i < runningQueue.size(); i++) {
            if (runningQueue.get(i).getProcessId() == processId) {
                runningQueue.get(i).setProcessState(1);
                readyQueue.add(runningQueue.remove(i));
                return;
            }
        }
    }

    // 进程阻塞
    public void blockProcess(int processId) {
        for (int i = 0; i < runningQueue.size(); i++) {
            if (runningQueue.get(i).getProcessId() == processId) {
                runningQueue.get(i).setProcessState(2);
                blockQueue.add(runningQueue.remove(i));
                return;
            }
        }
    }

    // 进程调度
    public PCB scheduleProcess() {
        if (runningQueue.isEmpty()) {
            if (!readyQueue.isEmpty()) {
                PCB pcb = readyQueue.remove(0);
                pcb.setProcessState(3);
                runningQueue.add(pcb);
                return pcb;
            }
        } else {
            if (!readyQueue.isEmpty()) {
                PCB pcb1 = runningQueue.get(0);
                PCB pcb2 = readyQueue.get(0);
                if (pcb2.getPriority() > pcb1.getPriority()) {
                    pcb1.setProcessState(1);
                    readyQueue.add(runningQueue.remove(0));
                    pcb2.setProcessState(3);
                    runningQueue.add(readyQueue.remove(0));
                    return pcb2;
                }
            }
        }
        return null;
    }
}
