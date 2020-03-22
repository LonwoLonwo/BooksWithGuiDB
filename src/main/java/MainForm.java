import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JLabel enterName;
    private JTextField bookName;
    private JButton buttonCheck;
    private JLabel bookSay;
    private JLabel authorNameOut;
    private JLabel pubYearOut;

    public MainForm(){

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public JTextField getBookName() {
        return bookName;
    }

    public void pressButton(final String authorName, final String publishingYear) {
        buttonCheck.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                authorNameOut.setText(authorName);
                pubYearOut.setText(publishingYear);
            }
        });
    }
}
