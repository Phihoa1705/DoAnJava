package ClientSide.Views.Admin;

import Models.Signup3_Models;
import Models.UserLogin_Models;
import ServerSide.DAO.Signup3DAO;
import ServerSide.DAO.UserLoginDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ADMINSignup3_Views extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea textArea;
    private Main_Views mainViews;
    private JButton addButton, editButton, deleteButton, nextButton,backButton;

    public ADMINSignup3_Views(Main_Views mainViews) {
        this.mainViews = mainViews;
        this.init();
        this.setVisible(true);
    }

    private void init() {
        this.setSize(800, 600);
        this.setTitle("Table Signup3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Tạo bảng và JScrollPane cho bảng
        tableModel = new DefaultTableModel(new Object[]{"form_no", "sccount_Type", "card_number","pin","facility"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Tạo JTextArea và JScrollPane cho JTextArea
        textArea = new JTextArea(10, 30);
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);

        ActionListener ac = new ADMINSignup3_Controller(this);

        // Tạo các nút và thêm vào JPanel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        nextButton = new JButton("Tiếp theo");
        backButton = new JButton("Back");
        addButton.addActionListener(ac);
        editButton.addActionListener(ac);
        deleteButton.addActionListener(ac);
        nextButton.addActionListener(ac);
        backButton.addActionListener(ac);
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(nextButton);

        // Thêm bảng, JTextArea và buttonPanel vào JFrame
        this.add(tableScrollPane, BorderLayout.CENTER);
//        this.add(textAreaScrollPane, BorderLayout.SOUTH);
        this.add(buttonPanel, BorderLayout.NORTH);

        // Tải dữ liệu vào bảng
        loadData();

        // Thêm listener để hiển thị chi tiết sinh viên khi chọn hàng trong bảng
        table.getSelectionModel().addListSelectionListener((ListSelectionListener) ac);
    }

    public void showUserDetails() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String form_no = (String) tableModel.getValueAt(selectedRow,0);
            String sccount_Type = (String) tableModel.getValueAt(selectedRow,1);
            String card_number = (String) tableModel.getValueAt(selectedRow,2);
            String pin = (String) tableModel.getValueAt(selectedRow,3);
            String facility = (String) tableModel.getValueAt(selectedRow,4);

            textArea.setText("form_no: " + form_no + "\nsccount_Type: "
                    + sccount_Type + "\ncard_number: " + card_number + "\npin: " + pin
                    + "\nfacility: " + facility);
        }
    }

    private void loadData(){
        ArrayList<Signup3_Models> listSignup3 = Signup3DAO.getInstance().selectAll();
        for (Signup3_Models signup3 : listSignup3) {
            tableModel.addRow(new Object[]{signup3.getFormNo(),signup3.getAccountType(),signup3.getCardNumber(),signup3.getPin(),signup3.getFacility()});
        }
    }

    public void addUser() {
        JTextField form_noTF = new JTextField(30);
        JTextField sccount_TypeTF = new JTextField(30);
        JTextField card_numberTF = new JTextField(60);
        JTextField pinTF =new JTextField(4);
        JTextField facilityTF = new JTextField(60);

        JPanel panel = new JPanel(new GridLayout(5,2));
        panel.add(new JLabel("Form No"));
        panel.add(form_noTF);
        panel.add(new JLabel("Sccount Type"));
        panel.add(sccount_TypeTF);
        panel.add(new JLabel("Card Number"));
        panel.add(card_numberTF);
        panel.add(new JLabel("Pin"));
        panel.add(pinTF);
        panel.add(new JLabel("Facility"));
        panel.add(facilityTF);

        int result = JOptionPane.showConfirmDialog(this,panel,"Add Signup3",JOptionPane.OK_CANCEL_OPTION);
        /*
               Hành động của người dùng       |         Giá trị trả về
               Nhấn nút OK                    |         JOptionPane.OK_OPTION (0)
               Nhấn nút Cancel                |         JOptionPane.CANCEL_OPTION (2)
               Đóng hộp thoại (nhấn nút X)    |         JOptionPane.CLOSED_OPTION (-1)
        */
        if (result == JOptionPane.OK_OPTION) {
            String form_no = form_noTF.getText();
            String sccount_Type = sccount_TypeTF.getText();
            String card_number = card_numberTF.getText();
            String pin = pinTF.getText();
            String facility = facilityTF.getText();

            if (form_no.equals("")  | sccount_Type.equals("") | card_number.equals("") | pin.equals("") | facility.equals("")) {
                JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
            }

            Signup3_Models signup3Models = new Signup3_Models(form_no,sccount_Type,card_number,pin,facility);

            Signup3DAO.getInstance().insert(signup3Models);
            tableModel.addRow(new Object[]{form_no,sccount_Type,card_number,pin,facility});
        }
    }

    public void editUser(){
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String card_number = (String) tableModel.getValueAt(selectedRow,2);
            JTextField form_noTF = new JTextField((String) tableModel.getValueAt(selectedRow,0),50);
            JTextField sccount_TypeTF = new JTextField(tableModel.getValueAt(selectedRow,1).toString(),20);
            JTextField pinTF = new JTextField(tableModel.getValueAt(selectedRow,3).toString(),10);
            JTextField facilityTF = new JTextField(tableModel.getValueAt(selectedRow,4).toString(),50);


            JPanel panel = new JPanel(new GridLayout(5,2));
            panel.add(new JLabel("Form No"));
            panel.add(form_noTF);
            panel.add(new JLabel("Sccount Type"));
            panel.add(sccount_TypeTF);
            panel.add(new JLabel("Pin"));
            panel.add(pinTF);
            panel.add(new JLabel("Facility"));
            panel.add(facilityTF);

            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Signup3 Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String form_no = form_noTF.getText();
                String sccount_Type = sccount_TypeTF.getText();
                String pin = pinTF.getText();
                String facility = facilityTF.getText();

                Signup3_Models signup3Models = new Signup3_Models(form_no,sccount_Type,card_number,pin,facility);

                Signup3DAO.getInstance().update(signup3Models); // Cập nhật trong cơ sở dữ liệu
                tableModel.setValueAt(form_no, selectedRow, 0);
                tableModel.setValueAt(sccount_Type, selectedRow, 1);
                tableModel.setValueAt(pin,selectedRow,3);
                tableModel.setValueAt(facility,selectedRow,4);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.");
        }
    }

    public void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 ) {
            String card_number = (String) tableModel.getValueAt(selectedRow,2);
            Signup3DAO.getInstance().delete(new Signup3_Models(card_number));
            tableModel.removeRow(selectedRow); // Xóa khỏi bảng
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
        }
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Main_Views getMainViews() {
        return mainViews;
    }

    public void setMainViews(Main_Views mainViews) {
        this.mainViews = mainViews;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public void setNextButton(JButton nextButton) {
        this.nextButton = nextButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}
