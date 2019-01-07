package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener, MouseListener{
    JPanel panel;
    JLabel firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, repasswordLabel, registerPageNameLabel, errorLabel;
    JTextField firstNTF, lastNTF, usernameTF;
    JPasswordField passwordF, repasswordF;
    JButton registerBtn, cancelBtn;
    
    public Register(){
        this.setTitle("Meal Management System-Register New Account");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(255,250,250));
        panel.setLayout(null);

        registerPageNameLabel=new JLabel();
        registerPageNameLabel.setText("Register New Account");
        registerPageNameLabel.setFont(new Font("century gothic", Font.PLAIN, 24));
        registerPageNameLabel.setBounds(155, 0, 600, 40);
        registerPageNameLabel.setForeground(Color.decode("#004d99"));
        panel.add(registerPageNameLabel);

        errorLabel = new JLabel();
        errorLabel.setBounds(100, 45, 500, 20);
        errorLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);
        panel.add(errorLabel);

        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(165, 70, 100, 25);
        firstNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        firstNameLabel.setForeground(Color.decode("#003366"));
        panel.add(firstNameLabel);

        firstNTF = new JTextField();
        firstNTF.setBounds(165, 95, 250, 25);
        panel.add(firstNTF);

        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(165, 122, 100, 25);
        lastNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        lastNameLabel.setForeground(Color.decode("#003366"));
        panel.add(lastNameLabel);

        lastNTF = new JTextField();
        lastNTF.setBounds(165, 147, 250, 25);
        panel.add(lastNTF);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(165, 174, 100, 25);
        usernameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.decode("#003366"));
        panel.add(usernameLabel);

        usernameTF = new JTextField();
        usernameTF.setBounds(165, 199, 250, 25);
        panel.add(usernameTF);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(165, 226, 100, 25);
        passwordLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.decode("#003366"));
        panel.add(passwordLabel);

        passwordF = new JPasswordField();
        passwordF.setBounds(165, 251, 250, 25);
        passwordF.setEchoChar('*');
        panel.add(passwordF);

        repasswordLabel = new JLabel("Retype Password:");
        repasswordLabel.setBounds(165, 278, 150, 25);
        repasswordLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        repasswordLabel.setForeground(Color.decode("#003366"));
        panel.add(repasswordLabel);

        repasswordF = new JPasswordField();
        repasswordF.setBounds(165, 303, 250, 25);
        repasswordF.setEchoChar('*');
        panel.add(repasswordF);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(335, 360, 100, 30);
        registerBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        registerBtn.setForeground(Color.decode("#003366"));
        registerBtn.setBackground(Color.decode("#cce6ff"));
        registerBtn.addMouseListener(this);
        registerBtn.addActionListener(this);
        panel.add(registerBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(145, 360, 100, 30);
        cancelBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        cancelBtn.setForeground(Color.decode("#003366"));
        cancelBtn.setBackground(Color.decode("#cce6ff"));
        cancelBtn.addMouseListener(this);
        cancelBtn.addActionListener(this);
        panel.add(cancelBtn);

        this.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancelBtn){
            new Login();
            this.dispose();
        }
        if(e.getSource()==registerBtn){
            boolean flag = true;
            if(usernameTF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter username");
                flag = false;
            }
            else if(firstNTF.getText().equals("") || lastNTF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter First/Last Name");
                flag = false;
            }
            else if(!(checkPassword())){
                JOptionPane.showMessageDialog(this, "Password did not match");
                flag = false;
            }
            try{
                if(flag){
                    String rquery = "INSERT INTO account VALUES('"+usernameTF.getText()+"','"+firstNTF.getText()+"','"+lastNTF.getText()+"','"+new String(passwordF.getPassword())+"','0','"+usernameTF.getText()+"');";
                    
                    Statement st = DBConnect.getStatement(this);
                    if(st != null){
                        st.execute(rquery);
                        JOptionPane.showMessageDialog(this, "Registration Successful");
                        Login login = new Login();
                        login.usernameTF.setText(usernameTF.getText());
                        this.dispose();
                    }
                }
            }catch(Exception ex){
                if(ex.getMessage().contains("Duplicate entry"))
                    JOptionPane.showMessageDialog(this, "Username already exist!");
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == cancelBtn)
        {
            cancelBtn.setForeground(Color.white);
            cancelBtn.setBackground(Color.decode("#ff4d4d"));
        }
        if(e.getSource() == registerBtn)
        {
            registerBtn.setForeground(Color.white);
            registerBtn.setBackground(Color.decode("#1e90ff"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == cancelBtn)
        {
            cancelBtn.setForeground(Color.decode("#003366"));
            cancelBtn.setBackground(Color.decode("#cce6ff"));
        }
        if(e.getSource() == registerBtn)
        {
            registerBtn.setForeground(Color.decode("#003366"));
            registerBtn.setBackground(Color.decode("#cce6ff"));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    public boolean checkPassword(){
        return new String(passwordF.getPassword()).equals(new String(repasswordF.getPassword()) );
    }
}