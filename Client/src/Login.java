import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField idField;
    private JTextField ipField;
    private JButton loginButton;

    public Login() {
        setTitle("로그인");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        idField = new JTextField(20);
        ipField = new JTextField(20);
        loginButton = new JButton("로그인");

        loginButton.addActionListener(e -> {
            String id = idField.getText();
            if (Login.this.authenticate()) {
                Board board = new Board();
                board.setVisible(true);
                Login.this.dispose();
            } else {
                JOptionPane.showMessageDialog(Login.this, "error");
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("IP:"));
        add(ipField);
        add(loginButton);

        setLocationRelativeTo(null);
    }

    private boolean authenticate() {
        return true;
    }
}