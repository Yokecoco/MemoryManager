package enhanceTest01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Scheduler extends JFrame {
    private DefaultListModel<PCB> pcbListModel; // PCB列表模型
    private JList<PCB> pcbList; // PCB列表
    private JButton addButton; // 添加按钮
    private JButton removeButton; // 删除按钮
    private JButton blockButton; // 阻塞按钮
    private JButton resourceAllocationButton; //资源分配按钮
    private ArrayList<PCB> readyQueue; // 就绪队列
    private ArrayList<PCB> blockQueue; // 阻塞队列
    private ArrayList<PCB> runningQueue; //运行队列


    public Scheduler() {
        setTitle("PCB-进程管理"); // 设置窗口标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭操作
        setSize(400, 300); // 设置窗口大小
        setLocationRelativeTo(null); // 将窗口居中显示

        // PCB列表
        pcbListModel = new DefaultListModel<>();
        pcbList = new JList<>(pcbListModel);
        JScrollPane pcbListScrollPane = new JScrollPane(pcbList);
        add(pcbListScrollPane, BorderLayout.CENTER);

        // 按钮
        addButton = new JButton("添加进程"); // 添加进程按钮
        removeButton = new JButton("移除进程"); // 移除进程按钮
        blockButton = new JButton("阻塞进程"); // 阻塞进程按钮
        resourceAllocationButton = new JButton(("资源分配")); // 资源分配按钮

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(blockButton);
        buttonPanel.add(resourceAllocationButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProcess();
            }
        });

        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blockProcess();
            }
        });

        resourceAllocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resourceAllocation();
            }
        });

        readyQueue = new ArrayList<>(); // 初始化就绪队列
        blockQueue = new ArrayList<>(); // 初始化阻塞队列
        runningQueue = new ArrayList<>(); //初始化运行队列
    }

    // 添加进程
    private void addProcess() {
        int processId = Integer.parseInt(JOptionPane.showInputDialog("输入进程ID（Process ID）:")); // 获取用户输入的进程ID
        String processName = JOptionPane.showInputDialog("输入进程名称（Process Name）:"); // 获取用户输入的进程名称

        PCB pcb = new PCB(processId,  processName); // 创建PCB对象
        if (runningQueue.size() < 1) { //运行队列最大值为1
            pcb.setStatus("running");
            runningQueue.add(pcb); //若运行队列中无进程则将进程添加到运行队列中
        } else {
            readyQueue.add(pcb); // 将PCB对象添加到就绪队列中
        }
        pcbListModel.addElement(pcb); // 将PCB对象添加到列表模型中
    }

    //移除进程
    private void removeProcess() {
        int selectedIndex = pcbList.getSelectedIndex(); // 获取选定的进程索引
        if (selectedIndex != -1) { // 如果有选定的进程
            PCB pcb = pcbListModel.getElementAt(selectedIndex); // 获取选定的进程
            if (pcb.getStatus().equals("Ready")) {
                readyQueue.remove(pcb); // 从就绪队列中移除选定的进程
            } else if (pcb.getStatus().equals("Blocked")) {
                blockQueue.remove(pcb); // 从阻塞队列中移除选定的进程
            } else if (pcb.getStatus().equals("running")) {
                runningQueue.remove(pcb);
                // 将就绪队列中第一个进程状态改为运行态
                PCB newRunningPcb = readyQueue.get(0);
                newRunningPcb.setStatus("running");
                readyQueue.remove(0);
                runningQueue.add(newRunningPcb);
            }
            pcbListModel.remove(selectedIndex); // 从列表模型中移除选定的进程
        }
    }

    //阻塞进程
    private void blockProcess() {
        int selectedIndex = pcbList.getSelectedIndex(); // 获取选定的进程索引
        if (selectedIndex != -1) { // 如果有选定的进程
            PCB pcb = pcbListModel.getElementAt(selectedIndex); // 获取选定的进程
            if (pcb.getStatus().equals("Ready")) {
                readyQueue.remove(pcb); // 从就绪队列中移除选定的进程
                pcb.setStatus("Blocked"); // 设置进程状态为阻塞
                blockQueue.add(pcb); // 将选定的进程添加到阻塞队列中
            } else if (pcb.getStatus().equals("running")) {
                runningQueue.remove(pcb); //从运行队列中删除选定的进程
                pcb.setStatus("Blocked"); // 设置进程状态为阻塞
                blockQueue.add(pcb); // 将选定的进程添加到阻塞队列中

                // 将就绪队列中第一个进程状态改为运行态
                PCB newRunningPcb = readyQueue.get(0);
                newRunningPcb.setStatus("running");
                readyQueue.remove(0);
                runningQueue.add(newRunningPcb);
            }
        }
    }

    //资源分配
    private void resourceAllocation(){
        int selectedIndex = pcbList.getSelectedIndex(); // 获取选定的进程索引
        if (selectedIndex != -1) { // 如果有选定的进程
            PCB pcb = pcbListModel.getElementAt(selectedIndex); // 获取选定的进程
            if(pcb.getStatus().equals("Blocked")){
                if(runningQueue.size()<1){ //运行队列为空时将已分配资源的进程设置为运行态
                    blockQueue.remove(pcb);
                    pcb.setStatus("running");
                    runningQueue.add(pcb);
                }else{  //运行队列不为空时则将已分配资源的进程设置为就绪态
                    blockQueue.remove(pcb);
                    pcb.setStatus("Ready");
                    readyQueue.add(pcb);
                }
            }
        }
    }
}

