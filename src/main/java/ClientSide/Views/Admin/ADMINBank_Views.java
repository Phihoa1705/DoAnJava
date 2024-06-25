package ClientSide.Views.Admin;

import Models.Bank_Models;
import Models.Signup3_Models;
import ServerSide.DAO.BankDAO;
import ServerSide.DAO.Signup3DAO;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ADMINBank_Views extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea textArea;
    private Main_Views mainViews;
    private JButton addButton, editButton, deleteButton, nextButton,backButton;

    public ADMINBank_Views(Main_Views mainViews) {
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
        tableModel = new DefaultTableModel(new Object[]{"pin", "date", "type","amount"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Tạo JTextArea và JScrollPane cho JTextArea
        textArea = new JTextArea(10, 30);
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);

        ActionListener ac = new ADMINBank_Controller(this);

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
            String pin = (String) tableModel.getValueAt(selectedRow,0);
            String date = (String) tableModel.getValueAt(selectedRow,1);
            String type = (String) tableModel.getValueAt(selectedRow,2);
            String amount = (String) tableModel.getValueAt(selectedRow,3);

            textArea.setText("pin: " + pin + "\ndate: "
                    + date + "\ntype: " + type + "\namount: " + amount);
        }
    }

    private void loadData(){
        ArrayList<Bank_Models> listBank = BankDAO.getInstance().selectAll();
        for (Bank_Models bankModels : listBank) {
            tableModel.addRow(new Object[]{bankModels.getPin(),bankModels.getDate(),
                    bankModels.getType(),bankModels.getAmount()});
        }
    }

    public void addUser() {
        JTextField pinTF = new JTextField(30);
        JTextField dateTF = new JTextField(80);
        JTextField typeTF = new JTextField(60);
        JTextField amountTF =new JTextField(4);

        JPanel panel = new JPanel(new GridLayout(4,2,20,20));
        panel.add(new JLabel("Pin:"));
        panel.add(pinTF);
        panel.add(new JLabel("Date:"));
        panel.add(dateTF);
        panel.add(new JLabel("Type:"));
        panel.add(typeTF);
        panel.add(new JLabel("Amount:"));
        panel.add(amountTF);

        int result = JOptionPane.showConfirmDialog(this,panel,"Add Bank",JOptionPane.OK_CANCEL_OPTION);
        /*
               Hành động của người dùng       |         Giá trị trả về
               Nhấn nút OK                    |         JOptionPane.OK_OPTION (0)
               Nhấn nút Cancel                |         JOptionPane.CANCEL_OPTION (2)
               Đóng hộp thoại (nhấn nút X)    |         JOptionPane.CLOSED_OPTION (-1)
        */
        if (result == JOptionPane.OK_OPTION) {
            String pin = pinTF.getText();
            String date = dateTF.getText();
            String type = typeTF.getText();
            String amount = amountTF.getText();

            if (pin.equals("")  | date.equals("") | type.equals("") | amount.equals("")) {
                JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
            }

            Bank_Models bankModels = new Bank_Models(pin,date,type,amount);

            BankDAO.getInstance().insert(bankModels);
            tableModel.addRow(new Object[]{pin,date,type,pin,amount});
        }
    }

    public void editUser(){
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String pin = (String) tableModel.getValueAt(selectedRow,0);
            JTextField dateTF = new JTextField((String) tableModel.getValueAt(selectedRow,1),80);
            JTextField typeTF = new JTextField(tableModel.getValueAt(selectedRow,2).toString(),20);
            JTextField amountTF = new JTextField(tableModel.getValueAt(selectedRow,3).toString(),10);


            JPanel panel = new JPanel(new GridLayout(3,2));
            panel.add(new JLabel("Date:"));
            panel.add(dateTF);
            panel.add(new JLabel("Type:"));
            panel.add(typeTF);
            panel.add(new JLabel("Amount:"));
            panel.add(amountTF);

            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Bank Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String date = dateTF.getText();
                String type = typeTF.getText();
                String amount = amountTF.getText();

                Bank_Models bankModels = new Bank_Models(pin,date,type,amount);

                BankDAO.getInstance().update(bankModels); // Cập nhật trong cơ sở dữ liệu
                tableModel.setValueAt(date, selectedRow, 1);
                tableModel.setValueAt(type, selectedRow, 2);
                tableModel.setValueAt(amount,selectedRow,3);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.");
        }
    }

    public void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 ) {
            String pin = (String) tableModel.getValueAt(selectedRow,0);
            BankDAO.getInstance().delete(new Bank_Models(pin));
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
