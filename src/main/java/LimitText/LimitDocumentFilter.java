package LimitText;

import javax.swing.text.*;

// Lớp LimitDocumentFilter kế thừa DocumentFilter để giới hạn số ký tự có thể nhập vào tài liệu
public class LimitDocumentFilter extends DocumentFilter {
    // Biến instance để lưu trữ giới hạn số ký tự
    private int limit;

    // Constructor nhận vào tham số limit để thiết lập giới hạn ký tự
    public LimitDocumentFilter(int limit) {
        this.limit = limit;
    }

    // Phương thức được ghi đè để xử lý việc chèn chuỗi ký tự mới vào tài liệu
    @Override
    public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
        // Kiểm tra nếu chuỗi ký tự là null thì không làm gì cả
        if (str == null) {
            return;
        }

        // Kiểm tra nếu chiều dài hiện tại của tài liệu cộng với chiều dài của chuỗi mới
        // nhỏ hơn hoặc bằng giới hạn thì cho phép chèn chuỗi mới
        if ((fb.getDocument().getLength() + str.length()) <= limit) {
            // Gọi phương thức insertString của lớp cha để thực hiện việc chèn chuỗi
            super.insertString(fb, offset, str, attr);
        }
    }

    // Phương thức được ghi đè để xử lý việc thay thế một phần của tài liệu bằng chuỗi ký tự mới
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Kiểm tra nếu chuỗi ký tự là null thì không làm gì cả
        if (text == null) {
            return;
        }

        // Kiểm tra nếu chiều dài hiện tại của tài liệu trừ đi chiều dài của đoạn bị thay thế
        // cộng với chiều dài của chuỗi mới nhỏ hơn hoặc bằng giới hạn thì cho phép thay thế
        if ((fb.getDocument().getLength() + text.length() - length) <= limit) {
            // Gọi phương thức replace của lớp cha để thực hiện việc thay thế
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
