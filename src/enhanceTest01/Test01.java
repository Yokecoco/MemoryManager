package enhanceTest01;

import javax.swing.*;

public class Test01 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Scheduler scheduler = new Scheduler();
                scheduler.setVisible(true);
            }
        });
    }
}