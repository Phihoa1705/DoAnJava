package ClientSide.Views.LimitText;

import javax.swing.text.*;

public class LimitDocumentFilter extends DocumentFilter {
    private int limit;

    public LimitDocumentFilter(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((fb.getDocument().getLength() + str.length()) <= limit) {
            super.insertString(fb, offset, str, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }

        if ((fb.getDocument().getLength() + text.length() - length) <= limit) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
