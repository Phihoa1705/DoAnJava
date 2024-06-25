package ClientSide.Views.Admin;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ADMINBank_Controller implements ActionListener, ListSelectionListener {
    private ADMINBank_Views adminBankViews;

    public ADMINBank_Controller(ADMINBank_Views adminBankViews) {
        this.adminBankViews = adminBankViews;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nsk = e.getActionCommand();
        if (nsk.equals("Thêm")) {
            this.adminBankViews.addUser();
        } else if (nsk.equals("Sửa")) {
            this.adminBankViews.editUser();
        } else if (nsk.equals("Xóa")) {
            this.adminBankViews.deleteUser();
        } else if (nsk.equals("Back")) {
            this.adminBankViews.getMainViews().batCuaSo();
            this.adminBankViews.setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //        getValueIsAdjusting kiểm tra xem sự thay đổi lựa chọn trong bảng hoặc
        //        danh sách có còn đang tiếp diễn hay không.
        if (!e.getValueIsAdjusting()) {
            this.adminBankViews.showUserDetails();
        }
    }
}
