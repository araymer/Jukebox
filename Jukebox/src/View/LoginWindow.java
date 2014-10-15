package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controller.Jukebox;
import Model.CardReader;
import Model.JukeboxAccountCollection;

public class LoginWindow extends JPanel {
 
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private CardReader acct;
    private Jukebox theBox;
 
    public LoginWindow(Jukebox b) {
        acct = new CardReader();
        theBox = b;
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridLayout = new GridBagConstraints();
        this.setVisible(true);
        gridLayout.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        gridLayout.gridx = 0;
        gridLayout.gridy = 0;
        gridLayout.gridwidth = 1;
        panel.add(lbUsername, gridLayout);
 
        tfUsername = new JTextField(20);
        gridLayout.gridx = 1;
        gridLayout.gridy = 0;
        gridLayout.gridwidth = 2;
        panel.add(tfUsername, gridLayout);
 
        lbPassword = new JLabel("Password: ");
        gridLayout.gridx = 0;
        gridLayout.gridy = 1;
        gridLayout.gridwidth = 1;
        panel.add(lbPassword, gridLayout);
 
        pfPassword = new JPasswordField(20);
        gridLayout.gridx = 1;
        gridLayout.gridy = 1;
        gridLayout.gridwidth = 2;
        panel.add(pfPassword, gridLayout);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
            	//Checks if the username and password are correct
                if(acct.getAccount(getUsername()).getPassword() == getPassword()) {
                	//
                    theBox.login(acct.getAccount(getUsername()));
                    succeeded = true;
                   
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
 
                }
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnLogin);
  
    }
    
    
    
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
    

}