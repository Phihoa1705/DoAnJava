package ClientSide.Views.Admin;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Controller implements ActionListener, ListSelectionListener {
    private Main_Views mainViews;

    public Main_Controller(Main_Views mainViews) {
        this.mainViews = mainViews;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        if (nsk.equals("Thêm")) {
            this.mainViews.addUser();
        } else if (nsk.equals("Sửa")) {
            this.mainViews.editUser();
        } else if (nsk.equals("Xóa")) {
            this.mainViews.deleteUser();
        } else if (nsk.equals("Tiếp theo")) {
            new ADMINSignup3_Views(this.mainViews);
            this.mainViews.setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
//        getValueIsAdjusting kiểm tra xem sự thay đổi lựa chọn trong bảng hoặc
//        danh sách có còn đang tiếp diễn hay không.
        if (!e.getValueIsAdjusting()) {
            this.mainViews.showUserDetails();
        }
    }
}
