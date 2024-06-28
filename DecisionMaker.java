package ѡ������֢;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DecisionMaker {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton eatButton, playButton, customButton;
    private List<CustomPanel> customPanels; // �洢�����Զ������
    private JFrame foodFrame;
    private JPanel foodPanel;
    private JList<String> foodList;
    private JButton addButton, removeButton, randomButton, returnButton; // ��ӷ��ذ�ť
    private DefaultListModel<String> foodListModel;

    public DecisionMaker() {
        initMainFrame();
        initFoodFrame();
        customPanels = new ArrayList<>();
    }

    private void initMainFrame() {
        mainFrame = new JFrame("ѡ������������");
        mainPanel = new JPanel();

        eatButton = new JButton("��ʲô");
        eatButton.addActionListener(e -> {
        	//�򿪳�ʲô����
            foodFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        playButton = new JButton("��ʲô");
        playButton.addActionListener(e -> {
            // ����ʲô����
            JFrame playWhatFrame = new JFrame("��ʲô��");
            PlayWhatPanel playWhatPanel = new PlayWhatPanel(playWhatFrame);
            playWhatFrame.setContentPane(playWhatPanel);
            playWhatFrame.pack();
            playWhatFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        customButton = new JButton("�Զ���");
        customButton.addActionListener(e -> {
            // �����Ի������û��������ִ����µ��Զ��尴ť
            String customName = JOptionPane.showInputDialog("�������Զ��尴ť�����֣�");
            if (customName != null && !customName.isEmpty()) {
                addCustomButton(customName);
            }
        });

        mainPanel.add(eatButton);
        mainPanel.add(playButton);
        mainPanel.add(customButton);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        // ��ӷ���������İ�ť
        returnButton = new JButton("����������");
        returnButton.addActionListener(e -> {
            mainFrame.setVisible(true);
        });
    }

    private void addCustomButton(String customName) {
        JButton customButton = new JButton(customName);
        customButton.addActionListener(e -> {
            // ���Զ������
            JFrame customFrame = new JFrame(customName);
            CustomPanel customPanel = new CustomPanel(customFrame);
            customPanels.add(customPanel);
            customFrame.setContentPane(customPanel);
            customFrame.pack();
            customFrame.setVisible(true);
            mainFrame.setVisible(false);
        });
        mainPanel.add(customButton);
        mainFrame.pack();
    }

    private void initFoodFrame() {
        foodFrame = new JFrame("��ʲô��");

        foodPanel = new JPanel();

        foodListModel = new DefaultListModel<>();
        foodListModel.addElement("�׷�");
        foodListModel.addElement("����");
        foodListModel.addElement("����");

        foodList = new JList<>(foodListModel);
        addButton = new JButton("���");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newFood = JOptionPane.showInputDialog("�������µ�ʳ�");
                if (newFood != null && !newFood.isEmpty()) {
                    foodListModel.addElement(newFood);
                }
            }
        });
        removeButton = new JButton("ɾ��");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = foodList.getSelectedIndex();
                if (selectedIndex != -1) {
                    foodListModel.removeElementAt(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "��ѡ��һ��ʳ�����ɾ����");
                }
            }
        });

        randomButton = new JButton("��ʼ�����ѡ");
        randomButton.addActionListener(e -> {
            if (!foodListModel.isEmpty()) {
                Random rand = new Random();
                int index = rand.nextInt(foodListModel.size());
                JOptionPane.showMessageDialog(null, "��ѡ�����" + foodListModel.elementAt(index));
            } else {
                JOptionPane.showMessageDialog(null, "�б�Ϊ�գ��������ʳ�");
            }
        });

        foodPanel.add(new JScrollPane(foodList));
        foodPanel.add(addButton);
        foodPanel.add(removeButton);
        foodPanel.add(randomButton);
        foodPanel.add(returnButton); // �����ذ�ť��ӵ�ʳ��ѡ�����

        foodFrame.add(foodPanel);
        foodFrame.pack();
        foodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // ��ʼʱ����ʾʳ��ѡ�񴰿�
        foodFrame.setVisible(false);
    }

    class PlayWhatPanel extends JPanel {

        private JFrame parentFrame;
        private JList<String> playList;
        private DefaultListModel<String> playListModel;

        public PlayWhatPanel(JFrame parentFrame) {
            this.parentFrame = parentFrame;
            setLayout(new BorderLayout());

            playListModel = new DefaultListModel<>();
            playListModel.addElement("��Ӿ");
            playListModel.addElement("�ܲ�");
            playListModel.addElement("������");

            playList = new JList<>(playListModel);
            add(new JScrollPane(playList), BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            JButton addButton = new JButton("���");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newPlay = JOptionPane.showInputDialog("�������µ��淨��");
                    if (newPlay != null && !newPlay.isEmpty()) {
                        playListModel.addElement(newPlay);
                    }
                }
            });
            JButton removeButton = new JButton("ɾ��");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = playList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        playListModel.removeElementAt(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(null, "��ѡ��һ���淨����ɾ����");
                    }
                }
            });
            JButton randomButton = new JButton("��ʼ���ѡ��");
            randomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!playListModel.isEmpty()) {
                        Random rand = new Random();
                        int index = rand.nextInt(playListModel.size());
                        JOptionPane.showMessageDialog(null, "���ѡ����淨�ǣ�" + playListModel.elementAt(index));
                    } else {
                        JOptionPane.showMessageDialog(null, "�б�Ϊ�գ���������淨��");
                    }
                }
            });
            JButton returnButton = new JButton("����������");
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parentFrame.setVisible(false);
                    mainFrame.setVisible(true);
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(randomButton);
            buttonPanel.add(returnButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    class CustomPanel extends JPanel {

        private JFrame parentFrame;
        private String customName;
        private JList<String> itemList;
        private DefaultListModel<String> itemListModel;

        public CustomPanel(JFrame parentFrame) {
            this.parentFrame = parentFrame;
            this.customName = parentFrame.getTitle();
            setLayout(new BorderLayout());

            itemListModel = new DefaultListModel<>();
            
            itemList = new JList<>(itemListModel);
            add(new JScrollPane(itemList), BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            JButton addButton = new JButton("���");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newItem = JOptionPane.showInputDialog("�������µ�" + customName + "��");
                    if (newItem != null && !newItem.isEmpty()) {
                        itemListModel.addElement(newItem);
                    }
                }
            });
            JButton removeButton = new JButton("ɾ��");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = itemList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        itemListModel.removeElementAt(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(null, "��ѡ��һ��" + customName + "����ɾ����");
                    }
                }
            });
            JButton randomButton = new JButton("��ʼ���ѡ��");
            randomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!itemListModel.isEmpty()) {
                        Random rand = new Random();
                        int index = rand.nextInt(itemListModel.size());
                        JOptionPane.showMessageDialog(null, "���ѡ���" + customName + "�ǣ�" + itemListModel.elementAt(index));
                    } else {
                        JOptionPane.showMessageDialog(null, "�б�Ϊ�գ��������" + customName + "��");
                    }
                }
            });
            JButton returnButton = new JButton("����������");
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parentFrame.setVisible(false);
                    mainFrame.setVisible(true);
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(randomButton);
            buttonPanel.add(returnButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    public static void main(String[] args) {
        new DecisionMaker();
    }
}