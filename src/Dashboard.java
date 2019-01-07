package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends JFrame implements ActionListener, MouseListener{
    JPanel panel;
    JLabel dashboardLabel, mealLabel, paymentLabel, marketLabel, balanceLabel, messageLabel;
    JButton logoutBtn, profileButton, submitBtn, editBtn, dashboardButton, overviewButton, transactionButton, mealButton, manageButton;
    JTextField mealTF, paymentTF, marketTF;
    String username, name, meal, payment, marketCost;
    double balance, totalMeal, personMeal, totalBalance, cost;
    Statement statement;
    boolean checkStatement = true;
    int role;

    public Dashboard(String username, String name, Statement statement, int role){
        this.setTitle("Dashboard - Welcome, " + name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.username = username;
        this.name = name;
        this.statement = statement;
        this.role = role;
        ObjectRefer.setDashboard(this);

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

        dashboardLabel=new JLabel("Dashboard");
        dashboardLabel.setFont(new Font("century gothic", Font.PLAIN, 24));
        dashboardLabel.setBounds(275, 5, 150, 40);
        dashboardLabel.setForeground(Color.decode("#004d99"));
        panel.add(dashboardLabel);

        messageLabel = new JLabel("");
        messageLabel.setBounds(275, 70, 500, 20);
        messageLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        messageLabel.setForeground(Color.red);
        panel.add(messageLabel);

        profileButton=new JButton();
        profileButton.setText("Profile");
        profileButton.setBounds(670, 5, 100, 30);
        profileButton.setFont(new Font("century gothic", Font.BOLD, 16));
        profileButton.setForeground(Color.decode("#003366"));
        profileButton.setBackground(Color.decode("#cce6ff"));
        profileButton.addMouseListener(this);
        profileButton.addActionListener(this);
        panel.add(profileButton);

        logoutBtn=new JButton();
        logoutBtn.setText("Log Out");
        logoutBtn.setBounds(670, 510, 100, 30);
        logoutBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        logoutBtn.setForeground(Color.decode("#003366"));
        logoutBtn.setBackground(Color.decode("#cce6ff"));
        logoutBtn.addMouseListener(this);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        mealLabel = new JLabel("Meal:");
        mealLabel.setBounds(260, 120, 120, 30);
        mealLabel.setFont(new Font("century gothic", Font.PLAIN, 20));
        mealLabel.setForeground(Color.decode("#003366"));
        panel.add(mealLabel);

        paymentLabel = new JLabel("Payment:");
        paymentLabel.setBounds(380, 120, 120, 30);
        paymentLabel.setFont(new Font("century gothic", Font.PLAIN, 20));
        paymentLabel.setForeground(Color.decode("#003366"));
        panel.add(paymentLabel);

        marketLabel = new JLabel("Market cost:");
        marketLabel.setBounds(520, 120, 120, 30);
        marketLabel.setFont(new Font("century gothic", Font.PLAIN, 20));
        marketLabel.setForeground(Color.decode("#003366"));
        panel.add(marketLabel);
        
        mealTF = new JTextField("0");
        mealTF.setBounds(260, 150, 100, 30);
        panel.add(mealTF);

        paymentTF = new JTextField("0.0");
        paymentTF.setBounds(380, 150, 100, 30);
        panel.add(paymentTF);

        marketTF = new JTextField("0.0");
        marketTF.setBounds(520, 150, 120, 30);
        panel.add(marketTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(650, 150, 100, 30);
        submitBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        submitBtn.setForeground(Color.decode("#003366"));
        submitBtn.setBackground(Color.decode("#cce6ff"));
        submitBtn.addMouseListener(this);
        submitBtn.addActionListener(this);
        panel.add(submitBtn);

        editBtn = new JButton("Edit");
        editBtn.setBounds(650, 150, 100, 30);
        editBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        editBtn.setForeground(Color.decode("#003366"));
        editBtn.setBackground(Color.decode("#cce6ff"));
        editBtn.setVisible(false);
        editBtn.addMouseListener(this);
        editBtn.addActionListener(this);
        panel.add(editBtn);

        balance = getBalance();
        balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setBounds(260, 200, 200, 30);
        balanceLabel.setFont(new Font("century gothic", Font.PLAIN, 20));
        balanceLabel.setForeground(Color.decode("#003366"));
        panel.add(balanceLabel);

        this.add(panel);
        this.setVisible(true);

        if(checkInputStatus()){
            mealTF.setText(meal);
            paymentTF.setText(payment);
            marketTF.setText(marketCost);

            mealTF.setEditable(false);
            paymentTF.setEditable(false);
            marketTF.setEditable(false);
            submitBtn.setVisible(false);
            editBtn.setVisible(true);
            submitBtn.setText("Update");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == logoutBtn){
            ObjectRefer.setNull();
            new Login();
            this.dispose();
        }
        else if(e.getSource() == profileButton){
            if(ObjectRefer.getProfile() == null){
                ObjectRefer.setProfile(new Profile(username, name, statement, role));
                ObjectRefer.getProfile().setVisible(true);
            }
            else
                ObjectRefer.getProfile().setVisible(true);
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
        else if(e.getSource() == overviewButton){
            if(ObjectRefer.getOverview() == null){
                ObjectRefer.setOverview(new Overview(username, name, statement, role));
                ObjectRefer.getOverview().setVisible(true);
            }
            else
                ObjectRefer.getOverview().setVisible(true);
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

        else if(e.getSource() == submitBtn){

            if(!checkStatement){
                this.statement = DBConnect.getStatement(this);
                if(this.statement == null)
                    return;
                checkStatement = true;
            }
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String balanceQuery = "INSERT INTO balance VALUES('"+username+"','"+date+"','"+paymentTF.getText()+"','"+marketTF.getText()+"');";
            String mealQuery = "INSERT INTO meal VALUES('"+username+"','"+date+"','"+mealTF.getText()+"');";
            try{
                statement.execute(balanceQuery);
                statement.execute(mealQuery);
                mealTF.setEditable(false);
                paymentTF.setEditable(false);
                marketTF.setEditable(false);
                submitBtn.setText("Update");
                submitBtn.setVisible(false);
                editBtn.setVisible(true);

                messageLabel.setText("Data successfully submitted!");

                TransactionHistory transactionHistory = new TransactionHistory(username, name, statement, role);
                transactionHistory.setVisible(false);
                ObjectRefer.setTransactionHistory(transactionHistory);

                MealHistory mealHistory = new MealHistory(username, name, statement, role);
                mealHistory.setVisible(false);
                ObjectRefer.setMealHistory(mealHistory);
                
            }catch(Exception ae){
                if(ae.getMessage().contains("Duplicate entry")){
                    String updateBalanceQuery = "UPDATE balance SET Payment='"+paymentTF.getText()+"', MarketCost='"+marketTF.getText()+"' WHERE Username='"+username+"' AND Date='"+date+"';";
                    String updateMealQuery = "UPDATE meal SET TotalMeal='"+mealTF.getText()+"' WHERE Username='"+username+"' AND Date='"+date+"';";
                    try{
                        statement.execute(updateBalanceQuery);
                        statement.execute(updateMealQuery);
                        mealTF.setEditable(false);
                        paymentTF.setEditable(false);
                        marketTF.setEditable(false);
                        submitBtn.setVisible(false);
                        editBtn.setVisible(true);

                        messageLabel.setText("Data successfully updated!");

                        Overview overview = new Overview(username, name, statement, role);
                        overview.setVisible(false);
                        ObjectRefer.setOverview(overview);

                        TransactionHistory transactionHistory = new TransactionHistory(username, name, statement, role);
                        transactionHistory.setVisible(false);
                        ObjectRefer.setTransactionHistory(transactionHistory);

                        MealHistory mealHistory = new MealHistory(username, name, statement, role);
                        mealHistory.setVisible(false);
                        ObjectRefer.setMealHistory(mealHistory);

                    }catch(Exception iae){
                        System.out.println(iae.getMessage());
                    }
                }
                else if(ae.getMessage().contains("Communications link failure")){
                    messageLabel.setText("Internet connection failure, check internet connection");
                    checkStatement = false;
                }
                else if(ae.getMessage().contains("No operations allowed after statement closed.")){
                    System.out.println("statementcloseExp: "+ae.getMessage());
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, ae.getMessage());
                }
            }

            if(checkStatement)
                balance = getBalance();
            balanceLabel.setText("Balance: "+ balance);
        }
        else if(e.getSource() == editBtn){
            messageLabel.setText("");
            mealTF.setEditable(true);
            paymentTF.setEditable(true);
            marketTF.setEditable(true);
            editBtn.setVisible(false);
            submitBtn.setVisible(true);
        }
    }

    double getBalance(){
        //Generate Year, Month-->
        DBConnect.getDate();
        String mealQuery = "SELECT SUM(TotalMeal) TotalMeal FROM meal WHERE (Date BETWEEN '"+ DBConnect.fromDate + "' AND '" + DBConnect.toDate+ "') AND Username='"+username+"';";
        String costQuery = "SELECT SUM(Payment)+SUM(MarketCost) Cost FROM balance WHERE (Date BETWEEN '"+ DBConnect.fromDate + "' AND '" + DBConnect.toDate+ "') AND Username='"+username+"';";
        String totalMealQuery = "SELECT SUM(TotalMeal) TotalMeal FROM meal WHERE (Date BETWEEN '"+ DBConnect.fromDate + "' AND '" + DBConnect.toDate+ "') AND Username IN (SELECT Username FROM account WHERE Suser IN (SELECT Suser FROM account WHERE Username='"+username+"'));";
        String totalCostQuery = "SELECT SUM(Payment)+SUM(MarketCost) Cost FROM balance WHERE (Date BETWEEN '"+ DBConnect.fromDate + "' AND '" + DBConnect.toDate+ "') AND Username IN (SELECT Username FROM account WHERE Suser IN (SELECT Suser FROM account WHERE Username='"+username+"'));";
        try{
            ResultSet mealResultSet =  statement.executeQuery(mealQuery);
            if(mealResultSet.next()){
                personMeal = Double.parseDouble(mealResultSet.getString("TotalMeal"));
            }

            ResultSet totalMealResultSet = statement.executeQuery(totalMealQuery);
            if(totalMealResultSet.next()){
                totalMeal = Double.parseDouble(totalMealResultSet.getString("TotalMeal"));
            }

            ResultSet costResultSet = statement.executeQuery(costQuery);
            if(costResultSet.next()){
                cost = Double.parseDouble(costResultSet.getString("Cost"));
            }

            ResultSet totalCostResultSet = statement.executeQuery(totalCostQuery);
            if(totalCostResultSet.next()){
                totalBalance = Double.parseDouble(totalCostResultSet.getString("Cost"));
            }

            return cost - ((totalBalance/totalMeal)*personMeal);

        }catch(Exception ae){
            System.out.println("getBalance Exception: " + ae.getMessage());
        }
        return balance;
    }

    boolean checkInputStatus(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String query = "SELECT * FROM meal WHERE Username='"+username+"' AND Date='"+date+"';";
        String bquery = "SELECT * FROM balance WHERE Username='"+username+"' AND Date='"+date+"';";
        try{
            
            ResultSet bResultSet = statement.executeQuery(bquery);
            boolean flag = false;
            if(bResultSet.next()){
                payment = bResultSet.getString("Payment");
                marketCost = bResultSet.getString("MarketCost");
            }
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                meal = resultSet.getString("TotalMeal");
                flag = true;
            }
            return flag;
        }catch(Exception ae){
            System.out.println("checkInputException: " + ae.getMessage());
            return false;
        } 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==logoutBtn)
        {
            logoutBtn.setForeground(Color.white);
            logoutBtn.setBackground(Color.decode("#ff4d4d"));
        }
        else if(e.getSource() == submitBtn)
        {
            submitBtn.setForeground(Color.white);
            submitBtn.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == editBtn)
        {
            editBtn.setForeground(Color.white);
            editBtn.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == dashboardButton)
        {
            dashboardButton.setForeground(Color.white);
            dashboardButton.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == transactionButton)
        {
            transactionButton.setForeground(Color.white);
            transactionButton.setBackground(Color.decode("#1e90ff"));
        }
        else if(e.getSource() == overviewButton)
        {
            overviewButton.setForeground(Color.white);
            overviewButton.setBackground(Color.decode("#1e90ff"));
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
        if(e.getSource()==logoutBtn)
        {
            logoutBtn.setForeground(Color.decode("#003366"));
            logoutBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == submitBtn)
        {
            submitBtn.setForeground(Color.decode("#003366"));
            submitBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == overviewButton)
        {
            overviewButton.setForeground(Color.decode("#003366"));
            overviewButton.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == editBtn)
        {
            editBtn.setForeground(Color.decode("#003366"));
            editBtn.setBackground(Color.decode("#cce6ff"));
        }
        else if(e.getSource() == dashboardButton)
        {
            dashboardButton.setForeground(Color.decode("#003366"));
            dashboardButton.setBackground(Color.decode("#cce6ff"));
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
}