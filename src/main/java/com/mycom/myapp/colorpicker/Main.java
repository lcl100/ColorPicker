package com.mycom.myapp.colorpicker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

/**
 * @author lck100
 */
public class Main {
    private JFrame mainFrame;
    private JSplitPane splitPane;
    private JPanel showColorPane, showValuePane, settingPane, descriptionPane, changePane, rgbToHexPane, rgbTextFieldPane, transformButtonPane, transformButtonPane2, hexToRgbPane, hexTextFieldPane;
    private JButton startColorPickerButton, rgbToHexButton, hexToRgbButton;
    private JTextArea textArea, hexTextArea, rgbTextArea;
    private JLabel RLabel, GLabel, BLabel, HEXLabel, descriptionLabel;
    private JTextField RTextField, GTextField, BTextField, HEXTextField, rTextField, gTextField, bTextField, hexResultTextField, inputHexTextField, rResultTextField, gResultTextField, bResultTextField;
    private JCheckBox checkBox;
    private Box vbox;

    /**
     * 得到一个主窗体对象
     *
     * @return 返回得到的JFrame
     */
    public JFrame mainFrame() {
        // 实例化一个主窗体对象
        mainFrame = new JFrame();
        // 设置窗体的标题
        mainFrame.setTitle("取色器");
        // 设置窗体的尺寸大小
        mainFrame.setSize(900, 450);
        // 设置窗体的不允许用户自由调整大小
        mainFrame.setResizable(false);
        // 设置窗体的图标（注意图标路径问题）
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().createImage("src/main/resources/images/colorpicker.png"));

        // 创建拆分面板作为主面板
        splitPane = new JSplitPane();
        // 设置分隔条上折叠/展开按钮的显示
        splitPane.setOneTouchExpandable(true);
        // 设置当拖动分隔条时连续重绘组件
        splitPane.setContinuousLayout(true);
        // 设置分隔条的位置
        splitPane.setDividerLocation(mainFrame.getWidth() / 2);
        // 设置拆分面板的左侧组件为取色器面板
        splitPane.setLeftComponent(getColorPickerPane());
        // 设置拆分面板的右侧组件为转换面板
        splitPane.setRightComponent(getChangePane());

        // 调用设置的样式
        setFrameAllConponentStyles();

        // 将拆分面板设为主窗体的内容面板
        mainFrame.setContentPane(splitPane);
        // 设置窗体的关闭方式
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 返回主窗体
        return mainFrame;
    }

    /**
     * 设置取色器的面板内容
     *
     * @return 返回设置好的取色器面板
     */
    private Box getColorPickerPane() {
        vbox = Box.createVerticalBox();

        // 实例化一个显示取色的面板
        showColorPane = new JPanel();
        // 设置该面板的布局方式
        showColorPane.setLayout(new BorderLayout());
        // 创建一个启动取色功能的按钮
        startColorPickerButton = new JButton("取色");
        // 创建一个文本域来显示取到的颜色
        textArea = new JTextArea(10, 25);
        // 设置文本域不可编辑
        textArea.setEditable(false);
        // 设置文本域默认背景颜色
        textArea.setBackground(Color.BLACK);
        // 设置文本域无边界
        textArea.setBorder(null);
        // 将按钮添加到面板中并设置显示在北部位置
        showColorPane.add(startColorPickerButton, BorderLayout.NORTH);
        // 将文本域添加到面板中并设置显示到中部位置
        showColorPane.add(textArea, BorderLayout.CENTER);
        // 将面板添加到vbox中
        vbox.add(showColorPane);

        // 实例化一个显示RGB值和HEX值的面板
        showValuePane = new JPanel();
        // 设置面板的布局方式为流动布局
        showValuePane.setLayout(new FlowLayout());
        // 创建R标签
        RLabel = new JLabel("红(R):");
        // 创建R文本框
        RTextField = new JTextField(2);
        // 创建G标签
        GLabel = new JLabel("绿(G):");
        // 创建G文本框
        GTextField = new JTextField(2);
        // 创建B标签
        BLabel = new JLabel("蓝(B):");
        // 创建B文本框
        BTextField = new JTextField(2);
        // 创建HEX标签
        HEXLabel = new JLabel("HEX:");
        // 创建HEX文本框
        HEXTextField = new JTextField(6);
        // 将上面这些组件添加到面板中
        showValuePane.add(RLabel);
        showValuePane.add(RTextField);
        showValuePane.add(GLabel);
        showValuePane.add(GTextField);
        showValuePane.add(BLabel);
        showValuePane.add(BTextField);
        showValuePane.add(HEXLabel);
        showValuePane.add(HEXTextField);
        // 将面板添加到vbox中
        vbox.add(showValuePane);

        // 创建一个设置面板
        settingPane = new JPanel();
        // 设置面板的边界
        settingPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true), "设置", TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 16), BLACK));
        // 创建一个复选框，默认被勾选
        checkBox = new JCheckBox("总是在最上层", false);
        // 设置复选框的字体
        checkBox.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        // 将复选框添加到面板中
        settingPane.add(checkBox);
        // 将该面板添加到vbox中
        vbox.add(settingPane);

        // 创建一个说明面板
        descriptionPane = new JPanel();
        // 设置面板的边界
        descriptionPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true), "说明", TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 16), BLACK));
        // 创建一个说明标签
        descriptionLabel = new JLabel();
        // 设置标签内容
        descriptionLabel.setText("按下Alt键取色，可以直接在剪贴板复制RGB颜色");
        // 设置标签字体
        descriptionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        // 将标签添加到面板中
        descriptionPane.add(descriptionLabel);
        // 将该面板添加到vbox中
        vbox.add(descriptionPane);

        /**
         * 复选框状态改变监听器
         */
        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // 判断复选框是否被选中
                if (checkBox.isSelected()) {
                    // 设置主窗体置于最上层
                    mainFrame.setAlwaysOnTop(true);
                } else {
                    // 取消主窗体置于最上层
                    mainFrame.setAlwaysOnTop(false);
                }
            }
        });

        /**
         * 给startColorPickerButton按钮添加键盘事件监听器
         */
        startColorPickerButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 键入某个键
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 释放某个键
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 按下某个键
                // 当按下Alt键时触发事件
                if (e.getKeyCode() == 18) {
                    try {
                        // Robot 可以模拟鼠标和键盘的输入。基于给定屏幕设备创建一个Robot
                        Robot robot = new Robot();
                        // 获取鼠标在屏幕中移动的坐标位置
                        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                        // 获取鼠标所在位置的X值
                        int X = mousePoint.x;
                        // 获取鼠标所在位置的Y值
                        int Y = mousePoint.y;
                        // 获取指定屏幕坐标处的像素颜色
                        Color pixelColor = robot.getPixelColor(X, Y);
                        // 分别获取Color的R,G,B值
                        int R = pixelColor.getRed();
                        int G = pixelColor.getGreen();
                        int B = pixelColor.getBlue();
                        // 分别将获取到的R,G,B及HEX值显示在界面中
                        RTextField.setText(String.valueOf(R));
                        GTextField.setText(String.valueOf(G));
                        BTextField.setText(String.valueOf(B));
                        HEXTextField.setText("#" + Integer.toHexString(R) + Integer.toHexString(G) + Integer.toHexString(B));
                        // 对取得的颜色进行显示
                        textArea.setBackground(new Color(R, G, B));
                        // 把RGB值复制到剪贴板中直接使用
                        // 获取系统的剪贴板
                        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        // 对RGB值进行封装
                        StringSelection stringSelection = new StringSelection(R + "," + G + "," + B);
                        // 把文本内容设置到系统剪贴板
                        systemClipboard.setContents(stringSelection, null);
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // 返回设置好的vbox
        return vbox;
    }

    /**
     * 设置转换面板的面板内容
     *
     * @return 返回设置好的转换面板
     */
    private JPanel getChangePane() {
        // 创建一个转换面板
        changePane = new JPanel();
        // 设置面板的布局方式为网格布局
        changePane.setLayout(new GridLayout(2, 1));

        // 创建一个rgb转hex的面板
        rgbToHexPane = new JPanel();
        // 设置面板布局方式为边界布局
        rgbToHexPane.setLayout(new BorderLayout());
        // 设置面板的边界
        rgbToHexPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true), "RGB颜色代码转化成16位颜色", TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.BOLD, 16), RED));
        // 创建一个放置输入RGB值文本框的面板
        rgbTextFieldPane = new JPanel();
        // 创建R文本框
        rTextField = new JTextField(2);
        // 创建G文本框
        gTextField = new JTextField(2);
        // 创建B文本框
        bTextField = new JTextField(2);
        // 将R,G,B文本框添加到面板中
        rgbTextFieldPane.add(rTextField);
        rgbTextFieldPane.add(gTextField);
        rgbTextFieldPane.add(bTextField);
        // 将该面板添加到rgbToHexPane中
        rgbToHexPane.add(rgbTextFieldPane, BorderLayout.NORTH);
        // 创建一个文本域来显示颜色
        hexTextArea = new JTextArea(6, 6);
        // 设置文本域不可编辑
        hexTextArea.setEditable(false);
        // 将文本域添加到rgbToHexPane中
        rgbToHexPane.add(hexTextArea, BorderLayout.CENTER);
        // 创建一个转换结果面板
        transformButtonPane = new JPanel();
        // 创建一个转换按钮
        rgbToHexButton = new JButton("转换");
        // 创建一个显示HEX值的文本框
        hexResultTextField = new JTextField(5);
        // 添加rgbToHexButton和hexResultTextField到transformButtonPane中
        transformButtonPane.add(rgbToHexButton);
        transformButtonPane.add(hexResultTextField);
        // 将transformButtonPane添加到rgbToHexPane中
        rgbToHexPane.add(transformButtonPane, BorderLayout.SOUTH);
        // 将rgbToHexPane添加到转换面板
        changePane.add(rgbToHexPane);

        // 创建一个hex转成rgb的面板
        hexToRgbPane = new JPanel();
        // 设置面板的布局方式为边界边界
        hexToRgbPane.setLayout(new BorderLayout());
        // 设置面板的边界
        hexToRgbPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true), "十六位颜色代码转化成RGB颜色", TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.BOLD, 16), RED));
        // 创建一个输入hex的文本框面板
        hexTextFieldPane = new JPanel();
        // 创建一个输入hex的文本框
        inputHexTextField = new JTextField(6);
        // 将文本框添加到文本框面板中
        hexTextFieldPane.add(inputHexTextField);
        // 将hexTextFieldPane添加到hexToRgbPane中
        hexToRgbPane.add(hexTextFieldPane, BorderLayout.NORTH);
        // 创建一个显示颜色的文本域
        rgbTextArea = new JTextArea();
        // 设置文本域不可编辑
        rgbTextArea.setEditable(false);
        // 将rgbTextArea添加到hexToRgbPane中
        hexToRgbPane.add(rgbTextArea, BorderLayout.CENTER);
        // 创建转换按钮面板transformButtonPane2
        transformButtonPane2 = new JPanel();
        // 创建转换按钮
        hexToRgbButton = new JButton("转换");
        // 创建R结果显示文本框
        rResultTextField = new JTextField(2);
        // 创建G结果显示文本框
        gResultTextField = new JTextField(2);
        // 创建B结果显示文本框
        bResultTextField = new JTextField(2);
        // 将这些文本框和按钮添加到transformButtonPane2中
        transformButtonPane2.add(hexToRgbButton);
        transformButtonPane2.add(rResultTextField);
        transformButtonPane2.add(gResultTextField);
        transformButtonPane2.add(bResultTextField);
        // 将transformButtonPane2添加到hexToRgbPane中
        hexToRgbPane.add(transformButtonPane2, BorderLayout.SOUTH);
        // 将hexToRgbPane添加到转换面板
        changePane.add(hexToRgbPane);

        /**
         * 为rgbToHexButton添加活动监听器
         */
        rgbToHexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取文本框的R,G,B值被转换成整型
                int R = Integer.parseInt(rTextField.getText());
                int G = Integer.parseInt(gTextField.getText());
                int B = Integer.parseInt(bTextField.getText());
                // 在hexTextArea显示要转换的颜色
                hexTextArea.setBackground(new Color(R, G, B));
                // 显示转换成功的HEX值
                hexResultTextField.setText(new Utils().rgbToHex(R, G, B));
            }
        });

        /**
         * 为hexToRgbButton添加活动监听器
         */
        hexToRgbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的HEX值
                String text = inputHexTextField.getText();
                // 获取转换后的Color对象
                Color color = new Utils().hexToRgb(text);
                // 显示转换的颜色
                rgbTextArea.setBackground(color);
                // 显示转换的结果R,G,B
                rResultTextField.setText(String.valueOf(color.getRed()));
                gResultTextField.setText(String.valueOf(color.getGreen()));
                bResultTextField.setText(String.valueOf(color.getBlue()));
            }
        });

        // 返回设置好的转换面板
        return changePane;
    }

    /**
     * 设置界面一些组件的样式
     */
    private void setFrameAllConponentStyles() {
        Styles styles = new Styles();
        // 设置按钮的样式
        styles.setButton(startColorPickerButton, hexToRgbButton, rgbToHexButton);
        // 设置文本框的样式
        styles.setTextField(RTextField, GTextField, BTextField, HEXTextField, rTextField, gTextField, bTextField, hexResultTextField, inputHexTextField, rResultTextField, gResultTextField, bResultTextField);
        // 设置标签的样式
        styles.setLabel(RLabel, GLabel, BLabel, HEXLabel, descriptionLabel);
    }
}
