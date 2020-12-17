//待优化任务：
//界面背景
//多电梯任务调度（必要性较低
//电梯移动方向判断

import frame.setFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // write your code here

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                new setFrame();
            }
            //new setFrame();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}