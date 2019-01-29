package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener, MouseListener{
    JPanel panel;
    JLabel usernameLabel, passwordLabel, applicationNameLabel, registerMessageLabel, messageLabel;
    JTextField usernameTF;
    JPasswordField passwordF;
    JButton loginBtn, registerBtn, exitBtn;
    Statement statement;
    
    public Login(){
        this.setTitle("Meal Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(255,250,250));
        panel.setLayout(null);

        applicationNameLabel=new JLabel();
        applicationNameLabel.setText("Meal Management System");
        applicationNameLabel.setFont(new Font("century gothic", Font.PLAIN, 40));
        applicationNameLabel.setBounds(34, 0, 600, 60);
        applicationNameLabel.setForeground(Color.decode("#004d99"));
        panel.add(applicationNameLabel);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(50, 80, 500, 30);
        messageLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        messageLabel.setForeground(Color.red);
        panel.add(messageLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(160, 120, 120, 30);
        usernameLabel.setFont(new Font("century gothic", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.decode("#003366"));
        panel.add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(161, 200, 120, 30);
        passwordLabel.setFont(new Font("century gothic", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.decode("#003366"));
        panel.add(passwordLabel);
        
        usernameTF = new JTextField();
        usernameTF.setBounds(162, 150, 250, 30);
        panel.add(usernameTF);

        passwordF = new JPasswordField();
        passwordF.setBounds(162, 230, 250, 30);
        passwordF.setEchoChar('*');
        panel.add(passwordF);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(310, 275, 80, 30);
        loginBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        loginBtn.setForeground(Color.decode("#003366"));
        loginBtn.setBackground(Color.decode("#cce6ff"));
        loginBtn.addMouseListener(this);
        loginBtn.addActionListener(this);
        panel.add(loginBtn);

        registerMessageLabel=new JLabel();
        registerMessageLabel.setText("Don't have an account ? CLick to ");
        registerMessageLabel.setFont(new Font("century gothic", Font.PLAIN, 14));
        registerMessageLabel.setForeground(Color.decode("#003366"));
        registerMessageLabel.setBounds(161, 315, 250, 34);
        panel.add(registerMessageLabel);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(402, 318, 100, 30);
        registerBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        registerBtn.setForeground(Color.decode("#003366"));
        registerBtn.setBackground(Color.decode("#cce6ff"));
        registerBtn.addMouseListener(this);
        registerBtn.addActionListener(this);
        panel.add(registerBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(403, 390, 80, 30);
        exitBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        exitBtn.setForeground(Color.decode("#003366"));
        exitBtn.setBackground(Color.decode("#cce6ff"));
        exitBtn.addMouseListener(this);
        exitBtn.addActionListener(this);
        panel.add(exitBtn);


        this.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitBtn)
            System.exit(0);
        if(e.getSource()==registerBtn){
            new Register();
            this.dispose();
        }
        if(e.getSource()==loginBtn){
            check();
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == exitBtn)
        {
            exitBtn.setForeground(Color.white);
            exitBtn.setBackground(Color.decode("#ff4d4d"));
        }
        if(e.getSource() == loginBtn)
        {
            loginBtn.setForeground(Color.white);
            loginBtn.setBackground(Color.decode("#1e90ff"));
        }
        if(e.getSource() == registerBtn)
        {
            registerBtn.setForeground(Color.white);
            registerBtn.setBackground(Color.decode("#1e90ff"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == exitBtn)
        {
            exitBtn.setForeground(Color.decode("#003366"));
            exitBtn.setBackground(Color.decode("#cce6ff"));
        }
        if(e.getSource() == loginBtn)
        {
            loginBtn.setForeground(Color.decode("#003366"));
            loginBtn.setBackground(Color.decode("#cce6ff"));
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

    private void check(){
        messageLabel.setText("Please wait! while checking your credentials");
        checkLogin check = new checkLogin(this);
        loginBtn.setEnabled(false);
        registerBtn.setEnabled(false);
        check.execute();
    }

    private  class checkLogin extends SwingWorker<Object, String>{
        Login login;
        checkLogin(Login login){
            this.login = login;
        }
        @Override
        protected Object doInBackground() throws Exception {
            try{
                String query = "SELECT * FROM account WHERE Username='" +usernameTF.getText()+ "';";

                statement = DBConnect.getStatement(login);
                ResultSet rs = statement.executeQuery(query);

                String username;
                String password;
                String name;
                int role;

                if(rs.next()){
                    username = rs.getString("Username");
                    password = rs.getString("Password");
                    name = rs.getString("FirstName");
                    role = Integer.parseInt(rs.getString("Role"));

                    if(password.equals(new String(passwordF.getPassword()))){
                        Dashboard dashboard = new Dashboard(username, name, statement, role);
                        dashboard.checkInput();
                        dashboard.sync();

                        login.dispose();
                    }
                    else{
                        messageLabel.setText("Invalid password");
                        enableButton();
                        Thread.sleep(2000);
                        messageLabel.setText("");
                    }
                }
                else{
                    messageLabel.setText("Invalid username");
                    enableButton();
                    Thread.sleep(2000);
                    messageLabel.setText("");
                }
            }catch(Exception ex){
                System.out.println("Login Exception: " + ex.getMessage());
            }
            return null;
        }
    }

    void enableButton(){
        loginBtn.setEnabled(true);
        registerBtn.setEnabled(true);
    }
}