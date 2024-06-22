package ClientSide.Views.Admin;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ADMINSignup3_Controller implements ActionListener, ListSelectionListener {
    private ADMINSignup3_Views adminSignup3Views;

    public ADMINSignup3_Controller(ADMINSignup3_Views adminSignup3Views) {
        this.adminSignup3Views = adminSignup3Views;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        if (nsk.equals("Thêm")) {
            this.adminSignup3Views.addUser();
        } else if (nsk.equals("Sửa")) {
            this.adminSignup3Views.editUser();
        } else if (nsk.equals("Xóa")) {
            this.adminSignup3Views.deleteUser();
        } else if (nsk.equals("Back")) {
            this.adminSignup3Views.getMainViews().batCuaSo();
            this.adminSignup3Views.setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //        getValueIsAdjusting kiểm tra xem sự thay đổi lựa chọn trong bảng hoặc
        //        danh sách có còn đang tiếp diễn hay không.
        if (!e.getValueIsAdjusting()) {
            this.adminSignup3Views.showUserDetails();
        }
    }
}
