package src;
<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
=======

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
>>>>>>> update

public class Overview extends JFrame implements ActionListener, MouseListener{
    JPanel panel;
    JLabel overviewLabel , messageLabel;
    JButton logoutBtn, dashboardButton, transactionButton, mealButton, manageButton, overviewButton;
    JTable table;
    JScrollPane scrollPane;
    String username, name;
    Statement statement;
    Dashboard dashboard;
    Vector <String> column, row[];
    Vector <Vector<String>> data;
    int role;

    public Overview(String username, String name, Statement statement, int role){
        this.setTitle("Overview");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.username = username;
        this.name = name;
        this.statement = statement;
        this.role = role;

        panel=new JPanel();
        panel.setBackground(new Color(255,250,250));
        panel.setLayout(null);

        overviewLabel=new JLabel("Overview");
        overviewLabel.setFont(new Font("century gothic", Font.PLAIN, 24));
        overviewLabel.setBounds(275, 5, 300, 40);
        overviewLabel.setForeground(Color.decode("#004d99"));
        panel.add(overviewLabel);

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

        messageLabel = new JLabel("");
        messageLabel.setBounds(275, 70, 500, 20);
        messageLabel.setFont(new Font("century gothic", Font.PLAIN, 16));
        messageLabel.setForeground(Color.red);
        panel.add(messageLabel);

        logoutBtn=new JButton();
        logoutBtn.setText("Log Out");
        logoutBtn.setBounds(670, 510, 100, 30);
        logoutBtn.setFont(new Font("century gothic", Font.PLAIN, 16));
        logoutBtn.setForeground(Color.decode("#003366"));
        logoutBtn.setBackground(Color.decode("#cce6ff"));
        logoutBtn.addMouseListener(this);
        logoutBtn.addActionListener(this);
        panel.add(logoutBtn);

        retriveData();

        table = new JTable(data, column);
        table.setEnabled(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(210, 100, 440, 400);
        panel.add(scrollPane);

        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
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
            if(ObjectRefer.getManage() == null)
                ObjectRefer.setManage(new Manage(username, name, statement, role));
            else
                ObjectRefer.getManage().setVisible(true);
            this.setVisible(false);
        }
         
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==logoutBtn)
        {
            logoutBtn.setForeground(Color.white);
            logoutBtn.setBackground(Color.decode("#ff4d4d"));
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

    void retriveData(){
        int totalMealCount = 0;
        Double totalCostCount = 0.0;
<<<<<<< HEAD

=======
        String month = new SimpleDateFormat("yyyy-MM-%").format(new Date());
>>>>>>> update
        String userQuery = "SELECT  Username from account WHERE Username IN (SELECT Username FROM account WHERE Suser IN (SELECT Suser FROM account WHERE Username='"+username+"'));";
        
        try{
            ResultSet userResultSet =  statement.executeQuery(userQuery);

            int rowCount=0;
            if(userResultSet.last()){
                rowCount = userResultSet.getRow();
                userResultSet.beforeFirst();
            }
            Vector <String> userName = new Vector<String>();
            while(userResultSet.next()){
                userName.add(userResultSet.getString("Username"));
            }


            column = new Vector<String>();
            column.add("Name");
            column.add("TotalMeal");
            column.add("TotalCost");

            row = (Vector<String>[]) new Vector[rowCount+1];
            data = new Vector<Vector<String>>();
            int i;
            for(i=0;i<userName.size();i++){
<<<<<<< HEAD
                String totalMealQuery = "SELECT SUM(TotalMeal) Meal FROM meal WHERE Username ='"+userName.get(i)+"';";
=======
                String totalMealQuery = "SELECT SUM(TotalMeal) Meal FROM meal WHERE Username ='"+userName.get(i)+"' ;";
>>>>>>> update
                String totalCostQuery = "SELECT SUM(Payment)+SUM(MarketCost) Cost FROM balance WHERE Username ='"+userName.get(i)+"';";

                ResultSet totalMealResultSet = statement.executeQuery(totalMealQuery);
                
                
                row[i] = new Vector<String>();
                row[i].add(userName.get(i));

                if(totalMealResultSet.next()){
                    row[i].add(totalMealResultSet.getString("Meal"));
                    totalMealCount += totalMealResultSet.getInt("Meal");
                }

                ResultSet totalCostResultSet = statement.executeQuery(totalCostQuery);
                if(totalCostResultSet.next()){
                    row[i].add(totalCostResultSet.getString("Cost"));
                    totalCostCount += totalCostResultSet.getDouble("Cost");
                }

                data.add(row[i]);
                
            }
            
            row[i] = new Vector<String>();
            row[i].add("Total");
            row[i].add(String.valueOf(totalMealCount));
            row[i].add(String.valueOf(totalCostCount));
            data.add(row[i]);

        }catch(Exception ae){
            System.out.println("retriveData Exception: " + ae.getMessage());
        }
    }
}