package ClientSide.Views.Admin;

import Models.UserLogin_Models;
import ServerSide.DAO.UserLoginDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main_Views extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea textArea;
    private JButton addButton, editButton, deleteButton, nextButton;

    public Main_Views() {
        this.init();
        this.setVisible(true);
    }

    private void init() {
        this.setSize(800, 600);
        this.setTitle("Table UserLogin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Tạo bảng và JScrollPane cho bảng
        tableModel = new DefaultTableModel(new Object[]{"userName", "passWord", "fullName"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Tạo JTextArea và JScrollPane cho JTextArea
        textArea = new JTextArea(10, 30);
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);

        ActionListener ac = new Main_Controller(this);

        // Tạo các nút và thêm vào JPanel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        nextButton = new JButton("Tiếp theo");
        addButton.addActionListener(ac);
        editButton.addActionListener(ac);
        deleteButton.addActionListener(ac);
        nextButton.addActionListener(ac);
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
            String userName = (String) tableModel.getValueAt(selectedRow, 0);
            String passWord = (String) tableModel.getValueAt(selectedRow, 1);
            String fullName =  (String) tableModel.getValueAt(selectedRow, 2);

            textArea.setText("UserName: " + userName + "\nPassWord: " + passWord + "\nFullName: " + fullName);
        }
    }

    private void loadData(){
        ArrayList<UserLogin_Models> listUser = UserLoginDAO.getInstance().selectAll();
        for (UserLogin_Models user : listUser) {
            tableModel.addRow(new Object[]{user.getUserName(),user.getPassWord(),user.getFullName()});
        }
    }

    public void addUser() {
        JTextField userNameTF = new JTextField(30);
        JTextField passWordTF = new JTextField(30);
        JTextField fullNameTF = new JTextField(60);

        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(new JLabel("User Name"));
        panel.add(userNameTF);
        panel.add(new JLabel("Pass Word"));
        panel.add(passWordTF);
        panel.add(new JLabel("Full Name"));
        panel.add(fullNameTF);

        int result = JOptionPane.showConfirmDialog(this,panel,"Add User",JOptionPane.OK_CANCEL_OPTION);
        /*
               Hành động của người dùng       |         Giá trị trả về
               Nhấn nút OK                    |         JOptionPane.OK_OPTION (0)
               Nhấn nút Cancel                |         JOptionPane.CANCEL_OPTION (2)
               Đóng hộp thoại (nhấn nút X)    |         JOptionPane.CLOSED_OPTION (-1)
        */
        if (result == JOptionPane.OK_OPTION) {
            String userName = userNameTF.getText();
            String passWord = passWordTF.getText();
            String fullName = fullNameTF.getText();
            if (userName.equals("")  | passWord.equals("") | fullName.equals("")) {
                JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin");
            }

            UserLogin_Models userLoginModels = new UserLogin_Models(userName,passWord,fullName);

            UserLoginDAO.getInstance().insert(userLoginModels);
            tableModel.addRow(new Object[]{userName,passWord,fullName});
        }
    }

    public void editUser(){
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String userName = (String) tableModel.getValueAt(selectedRow,0);
            JTextField passWordTF = new JTextField((String) tableModel.getValueAt(selectedRow,1),10);
            JTextField fullNameTF = new JTextField((String) tableModel.getValueAt(selectedRow,2).toString(),10);

            JPanel panel = new JPanel(new GridLayout(2,2));
            panel.add(new JLabel("Pass Word"));
            panel.add(passWordTF);
            panel.add(new JLabel("Full Name"));
            panel.add(fullNameTF);

            int result = JOptionPane.showConfirmDialog(this, panel, "Edit User Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String passWord = passWordTF.getText();
                String fullName = fullNameTF.getText();

                UserLogin_Models userLoginModels = new UserLogin_Models(userName,passWord,fullName);

                UserLoginDAO.getInstance().update(userLoginModels); // Cập nhật trong cơ sở dữ liệu
                tableModel.setValueAt(passWord, selectedRow, 1);
                tableModel.setValueAt(fullName, selectedRow, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.");
        }
    }

    public void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 ) {
            String userName = (String) tableModel.getValueAt(selectedRow,0);
            UserLoginDAO.getInstance().delete(new UserLogin_Models(userName));
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

    public void batCuaSo() {
        this.setVisible(true);
    }
}
