package com.mycom.myapp.colorpicker;

import javax.swing.*;
import java.awt.*;

/**
 * @author lck100
 */
public class Styles {
    /**
     * 设置按钮的样式
     *
     * @param buttons 按钮组
     */
    public void setButton(JButton... buttons) {
        for (JButton button : buttons) {
            // 设置按钮的字体
            button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            // 设置当鼠标放在按钮上的样式
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // 设置按钮的前景色
            button.setForeground(Color.BLACK);
            // 设置按钮的背景色
            button.setBackground(new Color(221, 232, 243));
            // 设置按钮获取焦点后不显示线框
            button.setFocusPainted(false);
        }
    }

    /**
     * 设置文本框的样式
     *
     * @param textFields 文本框组
     */
    public void setTextField(JTextField... textFields) {
        for (JTextField textField : textFields) {
            // 设置文本框显示字体
            textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            // 设置文本框前景色
            textField.setForeground(Color.BLUE);
            // 设置文本框背景色
            textField.setBackground(Color.WHITE);
            // 设置文本框最小尺寸
            textField.setMinimumSize(new Dimension(3, 5));
            // 设置文本框选中文本时文本颜色
            textField.setSelectedTextColor(Color.BLUE);
        }
    }

    /**
     * 设置标签的样式
     *
     * @param labels 标签组
     */
    public void setLabel(JLabel... labels) {
        for (JLabel label : labels) {
            label.setFont(new Font("微软雅黑", Font.PLAIN, 19));
            label.setForeground(Color.BLACK);
        }
    }

}
