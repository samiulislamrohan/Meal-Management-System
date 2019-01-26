package src;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class Profile extends JFrame implements ActionListener,MouseListener
{
    JPanel panel;
    JLabel editProfile,messageLabel,userNameLabel, curPassLabel, newPassLabel, rePassLabel, firstNameLabel,  lastNameLabel,userNameAvailability,checkPasswordLabel;
    JTextField newUserName,  firstNTF, lastNTF;
    JPasswordField currentPassword, newPassword, retypedPassword;
    JButton saveBtn,cancelBtn,logoutBtn,editBtn, dashboardButton, transactionButton, mealButton, manageButton;
    ResultSet resultset;
    Statement statement;
    boolean validUserName;
    
    String username, name, firstName,lastName;
    int role;
    public Profile(String username, String name, Statement statement, int role)
    {
        super(name + " - Profile");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.name=name;
        this.username=username;
        this.statement=statement;
        this.role=role;

        boolean validUserName=false;

        panel=new JPanel();
        panel.setBackground(new Color(255,250,250));
        panel.setLayout(null);
        
        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(0, 5, 200, 30);
        dashboardButton.setFont(new Font("century gothic", Font.BOLD, 16));
        dashboardButton.setForeground(Color.decode("#003366"));
        dashboardButton.setBackground(Color.decode("#cce6ff"));
        dashboardButton.addActionListener(this);
        dashboardButton.addMouseListener(this);
        panel.add(dashboardButton);

        transactionButton = new JButton("Transation History");
        transactionButton.setBounds(0, 35, 200, 30);
        transactionButton.setFont(new Font("century gothic", Font.BOLD, 16));
        transactionButton.setForeground(Color.decode("#003366"));
        transactionButton.setBackground(Color.decode("#cce6ff"));
        transactionButton.addActionListener(this);
        transactionButton.addMouseListener(this);
        panel.add(transactionButton);

        mealButton = new JButton("Meal History");
        mealButton.setBounds(0, 65, 200, 30);
        mealButton.setFont(new Font("century gothic", Font.BOLD, 16));
        mealButton.setForeground(Color.decode("#003366"));
        mealButton.setBackground(Color.decode("#cce6ff"));
        mealButton.addActionListener(this);
        mealButton.addMouseListener(this);
        panel.add(mealButton);

        manageButton = new JButton("Manage Member");
        manageButton.setBounds(0, 95, 200, 30);
        manageButton.setFont(new Font("century gothic", Font.BOLD, 16));
        manageButton.setForeground(Color.decode("#003366"));
        manageButton.setBackground(Color.decode("#cce6ff"));
        if(role == 1)
            manageButton.setVisible(false);
        manageButton.addActionListener(this);
        manageButton.addMouseListener(this);
        panel.add(manageButton);


        editProfile=new JLabel();
        editProfile.setText("EDIT PROFILE");
        editProfile.setFont(new Font("century gothic", Font.PLAIN, 24));
        editProfile.setBounds(275, 5, 150, 40);
        editProfile.setForeground(Color.decode("#004d99"));
        panel.add(editProfile);

        messageLabel = new JLabel("");
        messageLabel.setBounds(275, 70, 500, 20);
        messageLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        messageLabel.setForeground(Color.red);
        panel.add(messageLabel);

     
       
        //Edit Button

        editBtn = new JButton("Edit");
        editBtn.setBounds(625,130, 100, 25);
        editBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        editBtn.setForeground(Color.decode("#003366"));
        editBtn.setBackground(Color.decode("#cce6ff"));
        editBtn.addActionListener(this);
        editBtn.addMouseListener(this);
        panel.add(editBtn);



        //Edit username
        userNameLabel = new JLabel("User Name:");
        userNameLabel.setBounds(275,120 , 100, 25);
        userNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        userNameLabel.setForeground(Color.decode("#003366"));
        panel.add(userNameLabel);


        //
        newUserName = new JTextField();
        newUserName.setEditable(false);
        newUserName.setBounds(275, 148, 250, 25);
        panel.add(newUserName);
       


        //Username not available 
        userNameAvailability=new JLabel();
        userNameAvailability.setBounds(275, 176, 400, 15);
        userNameAvailability.setText("Sorry! this user name has already been taken. Try another one");
        userNameAvailability.setVisible(false);
        userNameAvailability.setFont(new Font("century gothic", Font.PLAIN, 12));
        userNameAvailability.setForeground(Color.RED);
        panel.add(userNameAvailability);


        //Edit Name
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(275, 195, 200, 25);
        firstNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        firstNameLabel.setForeground(Color.decode("#003366"));
        panel.add(firstNameLabel);

        firstNTF = new JTextField();
        firstNTF.setEditable(false);
        firstNTF.setBounds(275, 220, 250, 25);
        panel.add(firstNTF);

        lastNameLabel = new JLabel("Last Name:");
      
        lastNameLabel.setBounds(275, 250, 200, 25);
        lastNameLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        lastNameLabel.setForeground(Color.decode("#003366"));
        panel.add(lastNameLabel);

        lastNTF = new JTextField();
        lastNTF.setEditable(false);
        lastNTF.setBounds(275, 278, 250, 25);
        panel.add(lastNTF);

        //Edit password
        curPassLabel = new JLabel("Current Password: ");
        curPassLabel.setBounds(275, 308, 200, 25);
        curPassLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        curPassLabel.setForeground(Color.decode("#003366"));
        panel.add(curPassLabel);


        currentPassword = new JPasswordField();
        currentPassword.setBounds(275, 336, 250, 25);
        currentPassword.setEditable(false);
        currentPassword.setFont(new Font("century gothic", Font.PLAIN, 16));
        currentPassword.setForeground(Color.decode("#003366"));
        panel.add(currentPassword);


        newPassLabel = new JLabel("New Password: ");
        newPassLabel.setBounds(275, 366, 200, 25);
        newPassLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        newPassLabel.setForeground(Color.decode("#003366"));
        panel.add(newPassLabel);

        newPassword = new JPasswordField();
        newPassword.setBounds(275, 394, 250, 25);
        newPassword.setEditable(false);
        newPassword.setFont(new Font("century gothic", Font.PLAIN, 16));
        newPassword.setForeground(Color.decode("#003366"));
        panel.add(newPassword);

        rePassLabel= new JLabel("Re-type Password: ");
        rePassLabel.setBounds(275, 424, 200, 25);
        rePassLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        rePassLabel.setForeground(Color.decode("#003366"));
        panel.add(rePassLabel);

        retypedPassword = new JPasswordField();
        retypedPassword.setBounds(275, 452, 250, 25);
        retypedPassword.setEditable(false);
        retypedPassword.setFont(new Font("century gothic", Font.PLAIN, 16));
        retypedPassword.setForeground(Color.decode("#003366"));
        panel.add(retypedPassword);



        //Current password not matched or retyped password has not been matched with new password
        checkPasswordLabel=new JLabel();
        checkPasswordLabel.setBounds(275, 480, 400, 15);
       // checkPasswordLabel.setText("Incorrect current password!");
        checkPasswordLabel.setFont(new Font("century gothic", Font.PLAIN, 12));
        checkPasswordLabel.setVisible(false);
        checkPasswordLabel.setForeground(Color.RED);
        panel.add(checkPasswordLabel);


        //Save Button

        saveBtn = new JButton("Save");
        saveBtn.setBounds(275, 515, 80, 25);
        saveBtn.setEnabled(false);
        saveBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        saveBtn.setForeground(Color.decode("#003366"));
        saveBtn.setBackground(Color.decode("#cce6ff"));
        saveBtn.addActionListener(this);
        saveBtn.addMouseListener(this);
        panel.add(saveBtn);

        //Cancel Button

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(375, 515, 100, 25);
        cancelBtn.setEnabled(false);
        cancelBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        cancelBtn.setForeground(Color.decode("#003366"));
        cancelBtn.setBackground(Color.decode("#cce6ff"));
        cancelBtn.addActionListener(this);
        cancelBtn.addMouseListener(this);
        panel.add(cancelBtn);

        //Logout Button
        logoutBtn=new JButton();
        logoutBtn.setText("Log Out");
        logoutBtn.setBounds(670, 510, 100, 30);
        logoutBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        logoutBtn.setForeground(Color.decode("#003366"));
        logoutBtn.setBackground(Color.decode("#cce6ff"));
        logoutBtn.addMouseListener(this);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        this.add(panel);



        try{
            String query="SELECT * from account where UserName= '"+username+"';";
            resultset=statement.executeQuery(query);
            if (resultset.next())
            {
                firstName=resultset.getString("FirstName");
                lastName=resultset.getString("LastName");
                newUserName.setText(username);
                firstNTF.setText(firstName);
                lastNTF.setText(lastName);
            }
        }
        catch (Exception e)
        {

        }

    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        validUserName=false;
        if(e.getSource() == logoutBtn){
            ObjectRefer.setNull();
            new Login();
            this.dispose();
        }
        else if(e.getSource() == dashboardButton){
            ObjectRefer.getDashboard().setVisible(true);
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
        else if(e.getSource() == manageButton){
            if(ObjectRefer.getManage() == null){
                ObjectRefer.setManage(new Manage(username, name, statement, role));
            }
            else
                ObjectRefer.getManage().setVisible(true);
            this.setVisible(false);
        }
        else if (e.getSource()==editBtn)
        {
           
            editBtn.setEnabled(false);
            saveBtn.setEnabled(true);
            cancelBtn.setEnabled(true);
            newUserName.setEditable(true);
            firstNTF.setEditable(true);
            lastNTF.setEditable(true);
            currentPassword.setEditable(true);
            newPassword.setEditable(true);
            retypedPassword.setEditable(true);



        }
        else if (e.getSource()==cancelBtn)
        {
            editBtn.setEnabled(true);
            saveBtn.setEnabled(false);
            cancelBtn.setEnabled(false);
            userNameAvailability.setVisible(false);
            checkPasswordLabel.setVisible(false);
            newUserName.setText(username);
            firstNTF.setText(firstName);
            lastNTF.setText(lastName);
            newUserName.setEditable(false);
            firstNTF.setEditable(false);
            lastNTF.setEditable(false);
            currentPassword.setEditable(false);
            newPassword.setEditable(false);
            retypedPassword.setEditable(false);
        }

        else if (e.getSource()==saveBtn)
        {

            
            if (newUserName.getText().equals(username))
            {
                  
                validUserName=true;
               
            }
            else if (this.isUserNameTaken(newUserName.getText()))
            {
                userNameAvailability.setVisible(true);
            }
            else
            {
                validUserName=true;
                
            }
            if (validUserName)
            {
                if ((currentPassword.getText()).length()==0)
                    {
                        checkPasswordLabel.setText("Type your current password!");
                        checkPasswordLabel.setVisible(true);
                    }
                  
                else if (this.currentPasswordCheck(currentPassword.getText()))
                {

                        
                    if ((newPassword.getText()).length()==0&&(retypedPassword.getText()).length()==0)
                    {
                        this.updateUserName(newUserName.getText());
                        this.updateFirstName(firstNTF.getText());
                        this.updateLastName(lastNTF.getText());
                        username=newUserName.getText();
                        firstName=firstNTF.getText();
                        lastName=lastNTF.getText();
                        newUserName.setText(username);
                        firstNTF.setText(firstName);
                        lastNTF.setText(lastName);
                        currentPassword.setText("");
                        newPassword.setText("");
                        retypedPassword.setText("");
                        saveBtn.setEnabled(false);
                        cancelBtn.setEnabled(false);
                        editBtn.setEnabled(true);
                        newUserName.setEditable(false);
                        firstNTF.setEditable(false);
                        lastNTF.setEditable(false);
                        currentPassword.setEditable(false);
                        newPassword.setEditable(false);
                        retypedPassword.setEditable(false);
                        checkPasswordLabel.setVisible(false);
                        userNameAvailability.setVisible(false);
                        JOptionPane.showMessageDialog(this, "Successfully updated!");
                        

                    }
                    else if (newPassword.getText().equals(retypedPassword.getText()))
                    {
                        this.updateUserName(newUserName.getText());
                        this.updateFirstName(firstNTF.getText());
                        this.updateLastName(lastNTF.getText());
                        this.updatePassword(newPassword.getText());
                        username=newUserName.getText();
                        firstName=firstNTF.getText();
                        lastName=lastNTF.getText();
                        newUserName.setText(username);
                        firstNTF.setText(firstName);
                        lastNTF.setText(lastName);
                        currentPassword.setText("");
                        newPassword.setText("");
                        retypedPassword.setText("");
                        saveBtn.setEnabled(false);
                        cancelBtn.setEnabled(false);
                        editBtn.setEnabled(true);
                        newUserName.setEditable(false);
                        firstNTF.setEditable(false);
                        lastNTF.setEditable(false);
                        currentPassword.setEditable(false);
                        newPassword.setEditable(false);
                        retypedPassword.setEditable(false);
                        checkPasswordLabel.setVisible(false);
                        userNameAvailability.setVisible(false);
                        JOptionPane.showMessageDialog(this, "Successfully updated!");

                            
                    }
                    else
                    {
                        checkPasswordLabel.setText("New password does not match with retyped password!");
                        checkPasswordLabel.setVisible(true);
                    }

                }
                else
                {

                    checkPasswordLabel.setText("Invalid current password!");
                    checkPasswordLabel.setVisible(true);

                }
            }
            else 
            {
                userNameAvailability.setVisible(true);

            }
        }
            
    }

    @Override
    public void mouseEntered(MouseEvent e) {


        if(e.getSource() == cancelBtn&&cancelBtn.isEnabled())
        {
            cancelBtn.setForeground(Color.white);
            cancelBtn.setBackground(Color.decode("#ff4d4d"));
        }
        else if (e.getSource() == editBtn&&editBtn.isEnabled())
        {
            editBtn.setForeground(Color.white);
            editBtn.setBackground(Color.decode("#1e90ff"));

        }
        else if (e.getSource()==saveBtn&&saveBtn.isEnabled())
        {
            saveBtn.setForeground(Color.white);
            saveBtn.setBackground(Color.decode("#1e90ff"));
        }
        else if (e.getSource()==logoutBtn)
        {
            logoutBtn.setForeground(Color.white);
            logoutBtn.setBackground(Color.decode("#ff4d4d"));

        }
        
        
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == cancelBtn)
        {
            cancelBtn.setForeground(Color.decode("#003366"));
            cancelBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if (e.getSource() == editBtn)
        {
            editBtn.setForeground(Color.decode("#003366"));
            editBtn.setBackground(Color.decode("#cce6ff"));

        }
        else if (e.getSource()==saveBtn)
        {
            saveBtn.setForeground(Color.decode("#003366"));
            saveBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if (e.getSource()==logoutBtn)
        {
            logoutBtn.setForeground(Color.decode("#003366"));
            logoutBtn.setBackground(Color.decode("#cce6ff"));

        }

        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {

        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }


    public boolean isUserNameTaken(String newUserName)
    {
        try
        {
            String query="SELECT Username from account where UserName='"+newUserName+"';";
            resultset=statement.executeQuery(query);
            
            if (resultset.next())
            {
                
                return true;

            }
            else
            {
                
                return false;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Exception occured "+ex.getMessage());
            return false;
        }

    
    }

    public void updateUserName(String newUserName)
    {
        try
        {
            String oldName=username;
            String query="UPDATE account SET UserName= '"+newUserName+"' where UserName='" +oldName+"';";
            username=newUserName;
            statement.execute(query);

            //For updating balance table
            query="UPDATE balance SET UserName= '"+newUserName+"' where UserName='" +oldName+"';";
            statement.execute(query);

            //For updating meal table
            query="UPDATE meal SET UserName= '"+newUserName+"' where UserName='" +oldName+"';";
            statement.execute(query);

            int role;
            query="SELECT role from account where UserName= '"+username+"';";

            resultset=statement.executeQuery(query);
            
            if (resultset.next())
            {
                role=resultset.getInt("Role");
                if (role==0)
                {
                    query="UPDATE account SET SUser= '"+newUserName+"' where SUser='" +oldName+"';";
                    statement.execute(query);
                }
            }

        }
      
        catch (Exception ex)
        {
            System.out.println("Exception Occured"+ex.getMessage());

        }

    }

    public void updateFirstName(String newFirstName)
    {
        try
        {
            

            String query="UPDATE account SET FirstName= '"+newFirstName+"' where UserName='" +username+"';";
            
            statement.execute(query);
            
        }
        catch (Exception ex)
        {
            System.out.println("Exception Occured when first name is updated "+ex.getMessage());
    
        }

    }

//Update last name
    public void updateLastName(String newLastName)
    {
        try
        {
           
            String query="UPDATE account SET LastName= '"+newLastName+"' where UserName='" +username+"';";
            

            statement.execute(query);
        
        }
        catch (Exception ex)
        {
            System.out.println("Exception Occured when lastname is updated"+ex.getMessage());
    
        }
    }


//Check current password
    public boolean currentPasswordCheck(String cp)
    {
        try
        {
            String query="SELECT Password from account where UserName='"+username+"';";
            resultset=statement.executeQuery(query);
            
           
              String password=null;
              if (resultset.next())
              {
                  password=resultset.getString("Password");
                 
              }
              

        
            if (cp.equals(password))
            {
                return true;
            }
            else
            {
                return false;
            }


        }
        catch(Exception ex)
        {
            System.out.println("Exception Occured when current password is checked. "+ex.getMessage());
            return false;
        }

    }

//Update password

    public void updatePassword(String newPassword)
    {
        try
        {
           
            String query="UPDATE account SET Password= '"+newPassword+"' where UserName='" +username+"';";

            statement.execute(query);
            
        }
        catch (Exception ex)
        {
            System.out.println("Exception Occured when password is updated"+ex.getMessage());
    
        }

    }

}

