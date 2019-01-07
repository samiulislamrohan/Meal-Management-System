package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Manage extends JFrame implements ActionListener, MouseListener{
    JPanel panel;
    JButton addNewBtn, removeBtn, clearBtn, searchBtn, dashboardButton, overviewButton, transactionButton, mealButton, manageButton, logoutBtn;
    JLabel firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, repasswordLabel, pageNameLabel, searchLabel, uLabel, fNLabel, lNLabel;
    JTextField firstNTF, lastNTF, usernameTF, searchTF;
    JPasswordField passwordF, repasswordF;
    JComboBox selectAction;
    String actions[]=new String[2];
    Statement statement;
    int role, searchRole;
    String username, firstname, lastname, SUser, name, user;
    JCheckBox managerCheckBox;

    public Manage(String username, String name, Statement statement,  int role){
        this.setTitle("Group Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.username = username;
        this.name=name;
        this.statement=statement;
        this.role=role;
        this.SUser = getSUser();
        this.user="";
        this.firstname="";
        this.lastname="";

        panel = new JPanel();
        panel.setBackground(new Color(255,250,250));
        panel.setLayout(null);
        this.add(panel);

        logoutBtn=new JButton();
        logoutBtn.setText("Log Out");
        logoutBtn.setBounds(670, 510, 100, 30);
        logoutBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        logoutBtn.setForeground(Color.decode("#003366"));
        logoutBtn.setBackground(Color.decode("#cce6ff"));
        logoutBtn.addMouseListener(this);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(0, 5, 200, 30);
        dashboardButton.setFont(new Font("century gothic", Font.BOLD, 16));
        dashboardButton.setForeground(Color.decode("#003366"));
        dashboardButton.setBackground(Color.decode("#cce6ff"));
        dashboardButton.addActionListener(this);
        dashboardButton.addMouseListener(this);
        panel.add(dashboardButton);

        overviewButton = new JButton("Overview");
        overviewButton.setBounds(0, 35, 200, 30);
        overviewButton.setFont(new Font("century gothic", Font.BOLD, 16));
        overviewButton.setForeground(Color.decode("#003366"));
        overviewButton.setBackground(Color.decode("#cce6ff"));
        overviewButton.addActionListener(this);
        overviewButton.addMouseListener(this);
        panel.add(overviewButton);

        transactionButton = new JButton("Transation History");
        transactionButton.setBounds(0, 65, 200, 30);
        transactionButton.setFont(new Font("century gothic", Font.BOLD, 16));
        transactionButton.setForeground(Color.decode("#003366"));
        transactionButton.setBackground(Color.decode("#cce6ff"));
        transactionButton.addActionListener(this);
        transactionButton.addMouseListener(this);
        panel.add(transactionButton);

        mealButton = new JButton("Meal History");
        mealButton.setBounds(0, 95, 200, 30);
        mealButton.setFont(new Font("century gothic", Font.BOLD, 16));
        mealButton.setForeground(Color.decode("#003366"));
        mealButton.setBackground(Color.decode("#cce6ff"));
        mealButton.addActionListener(this);
        mealButton.addMouseListener(this);
        panel.add(mealButton);

        manageButton = new JButton("Manage Member");
        manageButton.setBounds(0, 125, 200, 30);
        manageButton.setFont(new Font("century gothic", Font.BOLD, 16));
        manageButton.setForeground(Color.decode("#003366"));
        manageButton.setBackground(Color.decode("#cce6ff"));
        if(role == 1)
            manageButton.setVisible(false);
        manageButton.addActionListener(this);
        manageButton.addMouseListener(this);
        panel.add(manageButton);

        pageNameLabel=new JLabel();
        pageNameLabel.setText("Add New Member");
        pageNameLabel.setFont(new Font("century gothic", Font.PLAIN, 24));
        pageNameLabel.setBounds(270, 0, 600, 40);
        pageNameLabel.setForeground(Color.decode("#004d99"));
        panel.add(pageNameLabel);

        searchLabel=new JLabel("Search By Username:");
        searchLabel.setBounds(270, 80, 200, 25);
        searchLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        searchLabel.setForeground(Color.decode("#003366"));
        panel.add(searchLabel);
        searchLabel.setVisible(false);

        searchTF=new JTextField();
        searchTF.setBounds(270, 110, 200, 25);
        panel.add(searchTF);
        searchTF.setVisible(false);

        uLabel=new JLabel("Username: "+ user);
        uLabel.setBounds(270, 140, 400, 25);
        uLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        uLabel.setForeground(Color.decode("#003366"));
        panel.add(uLabel);
        uLabel.setVisible(false);

        fNLabel=new JLabel("First Name: "+ firstname);
        fNLabel.setBounds(270, 170, 400, 25);
        fNLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        fNLabel.setForeground(Color.decode("#003366"));
        panel.add(fNLabel);
        fNLabel.setVisible(false);

        lNLabel=new JLabel("Last Name: "+ lastname);
        lNLabel.setBounds(270, 200, 400, 25);
        lNLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        lNLabel.setForeground(Color.decode("#003366"));
        panel.add(lNLabel);
        lNLabel.setVisible(false);

        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(270, 70, 100, 25);
        firstNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        firstNameLabel.setForeground(Color.decode("#003366"));
        panel.add(firstNameLabel);

        firstNTF = new JTextField();
        firstNTF.setBounds(270, 95, 250, 25);
        panel.add(firstNTF);

        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(270, 122, 100, 25);
        lastNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        lastNameLabel.setForeground(Color.decode("#003366"));
        panel.add(lastNameLabel);

        lastNTF = new JTextField();
        lastNTF.setBounds(270, 147, 250, 25);
        panel.add(lastNTF);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(270, 174, 100, 25);
        usernameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.decode("#003366"));
        panel.add(usernameLabel);

        usernameTF = new JTextField();
        usernameTF.setBounds(270, 199, 250, 25);
        panel.add(usernameTF);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(270, 226, 100, 25);
        passwordLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.decode("#003366"));
        panel.add(passwordLabel);

        passwordF = new JPasswordField();
        passwordF.setBounds(270, 251, 250, 25);
        passwordF.setEchoChar('*');
        panel.add(passwordF);

        repasswordLabel = new JLabel("Retype Password:");
        repasswordLabel.setBounds(270, 278, 150, 25);
        repasswordLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        repasswordLabel.setForeground(Color.decode("#003366"));
        panel.add(repasswordLabel);

        repasswordF = new JPasswordField();
        repasswordF.setBounds(270, 303, 250, 25);
        repasswordF.setEchoChar('*');
        panel.add(repasswordF);

        managerCheckBox=new JCheckBox("Set as Manager");
        managerCheckBox.setBounds(270, 335, 200, 25);
        managerCheckBox.setFont(new Font("century gothic", Font.PLAIN, 16));
        managerCheckBox.setForeground(Color.decode("#003366"));
        managerCheckBox.setOpaque(true);
        managerCheckBox.setBackground(new Color(255,250,250));
        panel.add(managerCheckBox);

        if(role==1)
        {
            managerCheckBox.setVisible(false);
        }
        else if(role==0)
        {
            managerCheckBox.setVisible(true);
        }

        addNewBtn=new JButton("Add Member");
        addNewBtn.setBounds(340, 380, 150, 25);
        addNewBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        addNewBtn.setForeground(Color.decode("#003366"));
        addNewBtn.setBackground(Color.decode("#cce6ff"));
        addNewBtn.addActionListener(this);
        addNewBtn.addMouseListener(this);
        panel.add(addNewBtn);

        removeBtn=new JButton("Remove");
        removeBtn.setBounds(330, 260, 100, 25);
        removeBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        removeBtn.setForeground(Color.decode("#003366"));
        removeBtn.setBackground(Color.decode("#cce6ff"));
        removeBtn.addActionListener(this);
        removeBtn.addMouseListener(this);
        panel.add(removeBtn);
        removeBtn.setVisible(false);
        removeBtn.setEnabled(false);

        clearBtn=new JButton("Clear");
        clearBtn.setBounds(450, 260, 80, 25);
        clearBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        clearBtn.setForeground(Color.decode("#003366"));
        clearBtn.setBackground(Color.decode("#cce6ff"));
        clearBtn.addActionListener(this);
        clearBtn.addMouseListener(this);
        panel.add(clearBtn);
        clearBtn.setVisible(false);
        clearBtn.setEnabled(false);

        searchBtn=new JButton("Search");
        searchBtn.setBounds(480, 110, 90, 25);
        searchBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        searchBtn.setForeground(Color.decode("#003366"));
        searchBtn.setBackground(Color.decode("#cce6ff"));
        searchBtn.addActionListener(this);
        searchBtn.addMouseListener(this);
        panel.add(searchBtn);
        searchBtn.setVisible(false);

        actions[0]="Add New Member";
        actions[1]="Remove Member";
        selectAction=new JComboBox(actions);
        selectAction.setBounds(550, 40, 140, 25);
        selectAction.setFont(new Font("century gothic", Font.PLAIN, 12));
        selectAction.setForeground(Color.decode("#003366"));
        selectAction.setBackground(Color.decode("#cce6ff"));
        selectAction.addActionListener(this);
        selectAction.addMouseListener(this);
        panel.add(selectAction);
    }

    private void optionOneElements(){
        pageNameLabel.setText("Add New Member");

        firstNameLabel.setVisible(true);
        firstNTF.setVisible(true);
        lastNameLabel.setVisible(true);
        lastNTF.setVisible(true);
        usernameLabel.setVisible(true);
        usernameTF.setVisible(true);
        passwordLabel.setVisible(true);
        passwordF.setVisible(true);
        repasswordLabel.setVisible(true);
        repasswordF.setVisible(true);
        addNewBtn.setVisible(true);
        managerCheckBox.setVisible(true);

        searchLabel.setVisible(false);
        searchTF.setVisible(false);
        uLabel.setVisible(false);
        fNLabel.setVisible(false);
        lNLabel.setVisible(false);
        removeBtn.setVisible(false);
        clearBtn.setVisible(false);
        searchBtn.setVisible(false);
    }

    private void optionTwoElements(){
        pageNameLabel.setText("Remove Member");

        firstNameLabel.setVisible(false);
        firstNTF.setVisible(false);
        lastNameLabel.setVisible(false);
        lastNTF.setVisible(false);
        usernameLabel.setVisible(false);
        usernameTF.setVisible(false);
        passwordLabel.setVisible(false);
        passwordF.setVisible(false);
        repasswordLabel.setVisible(false);
        repasswordF.setVisible(false);
        addNewBtn.setVisible(false);
        managerCheckBox.setVisible(false);

        searchLabel.setVisible(true);
        searchTF.setVisible(true);
        uLabel.setVisible(true);
        fNLabel.setVisible(true);
        lNLabel.setVisible(true);
        removeBtn.setVisible(true);
        clearBtn.setVisible(true);
        searchBtn.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == logoutBtn){
            ObjectRefer.setNull();
            new Login();
            this.dispose();
        }
        else if(e.getSource() == dashboardButton){
            ObjectRefer.getDashboard().setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == overviewButton){
            if(ObjectRefer.getOverview() == null){
                ObjectRefer.setOverview(new Overview(username, name, statement, role));
                ObjectRefer.getOverview().setVisible(true);
            }
            else
                ObjectRefer.getOverview().setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == transactionButton){
            if(ObjectRefer.getTransactionHistory() == null){
                ObjectRefer.setTransactionHistory(new TransactionHistory(username, name, statement, role));
                ObjectRefer.getTransactionHistory().setVisible(true);
            }
            else
                ObjectRefer.getTransactionHistory().setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == mealButton){
            if(ObjectRefer.getMealHistory() == null){
                ObjectRefer.setMealHistory(new MealHistory(username, name, statement, role));
                ObjectRefer.getMealHistory().setVisible(true);
            }
            else
                ObjectRefer.getMealHistory().setVisible(true);
            this.setVisible(false);
        }

        if(e.getSource()==clearBtn)
        {
            searchTF.setText("");
            searchTF.setEditable(true);
            user="";
            firstname="";
            lastname="";
            uLabel.setText("Username: "+ user);
            fNLabel.setText("First Name: "+ firstname);
            lNLabel.setText("Last Name: "+ lastname);
            searchBtn.setEnabled(true);
            clearBtn.setEnabled(false);
            removeBtn.setEnabled(false);
        }

        if(e.getSource()==selectAction)
        {
            String option=selectAction.getSelectedItem().toString();
            if(option.equals("Add New Member"))
            {
                optionOneElements();
            }
            else if(option.equals("Remove Member"))
            {
                optionTwoElements();
            }
        }

        if(e.getSource()==addNewBtn)
        {

            boolean flag = true;
            if(firstNTF.getText().equals("") || lastNTF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter First/Last Name");
                flag = false;
            }
            else if(usernameTF.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter username");
                flag = false;
            }
            else if(!(checkPassword())){
                JOptionPane.showMessageDialog(this, "Password did not match");
                flag = false;
            }
            try
            {
                if(flag)
                {
                    int role = 2;

                    if(managerCheckBox.isSelected())
                    {
                        role=1;
                    }
                    String query = "INSERT INTO account VALUES('"+usernameTF.getText()+"','"+firstNTF.getText()+"','"+lastNTF.getText()+"','"+new String(passwordF.getPassword())+"','"+role+"','"+SUser+"');";
                    if(statement!=null)
                    {
                        statement.execute(query);
                        JOptionPane.showMessageDialog(this, "New member, "+usernameTF.getText()+" successfully added");
                        firstNTF.setText("");
                        lastNTF.setText("");
                        usernameTF.setText("");
                        passwordF.setText("");
                        repasswordF.setText("");
                        managerCheckBox.setSelected(false);
                    }
                }
            }
            catch(Exception ex)
            {
                if(ex.getMessage().contains("Duplicate entry"))
                {
                    JOptionPane.showMessageDialog(this, "Username already exists!");
                }
                System.out.println("Exception: " + ex.getMessage());
            }
        }

        if(e.getSource()==searchBtn)
        {
            if(!searchTF.getText().equals(""))
            {
                try
                {
                    searchRole=0;

                    String query = "SELECT Username, FirstName, LastName, Role FROM account WHERE Username='" +searchTF.getText()+ "';";
                    ResultSet rs=statement.executeQuery(query);
                    if(rs.next())
                    {
                        searchRole=rs.getInt("Role");
                        if(searchRole==2 || searchRole == 1)
                        {
                            searchTF.setEditable(false);
                            user=rs.getString("Username");
                            firstname=rs.getString("FirstName");
                            lastname=rs.getString("LastName");
                            uLabel.setText("Username: "+ user);
                            fNLabel.setText("First Name: "+ firstname);
                            lNLabel.setText("Last Name: "+ lastname);
                            searchBtn.setEnabled(false);
                            clearBtn.setEnabled(true);
                            removeBtn.setEnabled(true);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "No such "+ searchTF.getText() +" exists");
                            searchTF.setText("");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "No such "+ searchTF.getText() +" exists");
                        searchTF.setText("");
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("Exception: "+ ex.getMessage());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Search bar is empty");
            }
        }

        if(e.getSource()==removeBtn)
        {
            if(!user.equals(""))
            {
                try
                {
                    if(searchRole==role)
                    {
                        JOptionPane.showMessageDialog(this, "You cannot remove another manager");
                    }
                    else
                    {
                        String query1="DELETE FROM account WHERE Username='"+ user +"';";
                        String query2="DELETE FROM meal WHERE Username='"+ user +"';";
                        String query3="DELETE FROM balance WHERE Username='"+ user +"';";
                        
                        statement.execute(query1);
                        statement.execute(query2);
                        statement.execute(query3);
    
                        JOptionPane.showMessageDialog(this, user +" successfully removed");
    
                        searchTF.setText("");
                        searchTF.setEditable(true);
                        user="";
                        firstname="";
                        lastname="";
                        uLabel.setText("Username: "+ user);
                        fNLabel.setText("First Name: "+ firstname);
                        lNLabel.setText("Last Name: "+ lastname);
    
                        searchBtn.setEnabled(true);
                        clearBtn.setEnabled(false);
                        removeBtn.setEnabled(false);
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("Exception: "+ ex.getMessage());
                }
            }
            else
            {
                System.out.println("Empty username");
            }
        }

    }


    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==logoutBtn)
        {
            logoutBtn.setForeground(Color.white);
            logoutBtn.setBackground(Color.decode("#ff4d4d"));
        }
        else if(e.getSource()==addNewBtn)
        {
            addNewBtn.setForeground(Color.white);
            addNewBtn.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource()==removeBtn)
        {
            removeBtn.setForeground(Color.white);
            removeBtn.setBackground(Color.decode("#ff4d4d"));
        }
        else if(e.getSource()==clearBtn)
        {
            clearBtn.setForeground(Color.white);
            clearBtn.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource()==searchBtn)
        {
            searchBtn.setForeground(Color.white);
            searchBtn.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == dashboardButton)
        {
            dashboardButton.setForeground(Color.white);
            dashboardButton.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == overviewButton)
        {
            overviewButton.setForeground(Color.white);
            overviewButton.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == transactionButton)
        {
            transactionButton.setForeground(Color.white);
            transactionButton.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == mealButton)
        {
            mealButton.setForeground(Color.white);
            mealButton.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == manageButton)
        {
            manageButton.setForeground(Color.white);
            manageButton.setBackground(Color.decode("#1e90ff"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==addNewBtn)
        {
            addNewBtn.setForeground(Color.decode("#003366"));
            addNewBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource()==removeBtn)
        {
            removeBtn.setForeground(Color.decode("#003366"));
            removeBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource()==clearBtn)
        {
            clearBtn.setForeground(Color.decode("#003366"));
            clearBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource()==searchBtn)
        {
            searchBtn.setForeground(Color.decode("#003366"));
            searchBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == dashboardButton)
        {
            dashboardButton.setForeground(Color.decode("#003366"));
            dashboardButton.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == overviewButton)
        {
            overviewButton.setForeground(Color.decode("#003366"));
            overviewButton.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == transactionButton)
        {
            transactionButton.setForeground(Color.decode("#003366"));
            transactionButton.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == mealButton)
        {
            mealButton.setForeground(Color.decode("#003366"));
            mealButton.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == manageButton)
        {
            manageButton.setForeground(Color.decode("#003366"));
            manageButton.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == logoutBtn)
        {
            logoutBtn.setForeground(Color.decode("#003366"));
            logoutBtn.setBackground(Color.decode("#cce6ff"));
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

    String getSUser(){
        String SUser = "";
        String sUserQuery = "SELECT  SUser FROM account WHERE Username='"+username+"';";
        try{
            ResultSet dataResultSet =  statement.executeQuery(sUserQuery);
    
            if(dataResultSet.next())
                SUser = dataResultSet.getString("SUser");

        }catch(Exception ae){
            System.out.println("retriveData Exception: " + ae.getMessage());
        }
        return SUser;
    }
    public boolean checkPassword(){
        return new String(passwordF.getPassword()).equals(new String(repasswordF.getPassword()) );
    }
}