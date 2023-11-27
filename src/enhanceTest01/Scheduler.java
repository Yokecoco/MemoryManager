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
    private ArrayList<PCB> readyQueue; // 就绪队列
    private ArrayList<PCB> blockQueue; // 阻塞队列


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
        removeButton = new JButton("删除进程"); // 删除进程按钮
        blockButton = new JButton("阻塞进程"); // 阻塞进程按钮

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(blockButton);
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
        readyQueue = new ArrayList<>(); // 初始化就绪队列
        blockQueue = new ArrayList<>(); // 初始化阻塞队列
    }

    private void addProcess() {
        int processId = Integer.parseInt(JOptionPane.showInputDialog("输入进程ID（Process ID）:")); // 获取用户输入的进程ID
        int priority = Integer.parseInt(JOptionPane.showInputDialog("输入进程优先级（Priority）:")); // 获取用户输入的优先级
        String processName = JOptionPane.showInputDialog("输入进程名称（Process Name）:"); // 获取用户输入的进程名称

        PCB pcb = new PCB(processId, priority, processName); // 创建PCB对象
        readyQueue.add(pcb); // 将PCB对象添加到就绪队列中
        pcbListModel.addElement(pcb); // 将PCB对象添加到列表模型中
    }

    private void removeProcess() {
        int selectedIndex = pcbList.getSelectedIndex(); // 获取选定的进程索引
        if (selectedIndex != -1) { // 如果有选定的进程
            PCB pcb = pcbListModel.getElementAt(selectedIndex); // 获取选定的进程
            if (pcb.getStatus().equals("Ready")) {
                readyQueue.remove(pcb); // 从就绪队列中移除选定的进程
            } else if (pcb.getStatus().equals("Blocked")) {
                blockQueue.remove(pcb); // 从阻塞队列中移除选定的进程
            }
            pcbListModel.remove(selectedIndex); // 从列表模型中移除选定的进程
        }
    }

    private void blockProcess() {
        int selectedIndex = pcbList.getSelectedIndex(); // 获取选定的进程索引
        if (selectedIndex != -1) { // 如果有选定的进程
            PCB pcb = pcbListModel.getElementAt(selectedIndex); // 获取选定的进程
            if (pcb.getStatus().equals("Ready")) {
                readyQueue.remove(pcb); // 从就绪队列中移除选定的进程
                pcb.setStatus("Blocked"); // 设置进程状态为阻塞
                blockQueue.add(pcb); // 将选定的进程添加到阻塞队列中
            }
        }
    }
}

