package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class MealHistory extends JFrame implements ActionListener, MouseListener{
    JPanel panel;
    JLabel mealLabel , messageLabel;
    JButton logoutBtn, dashboardButton, overviewButton, transactionButton, mealButton, manageButton;
    JTable table;
    JScrollPane scrollPane;
    String username, name;
    Statement statement;
    Dashboard dashboard;
    Vector <String> column, row[];
    Vector <Vector<String>> data;
    int role;

    public MealHistory(String username, String name, Statement statement, int role){
        this.setTitle("Meal History of -" + name);
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

        mealLabel=new JLabel("Meal History");
        mealLabel.setFont(new Font("century gothic", Font.PLAIN, 24));
        mealLabel.setBounds(275, 5, 300, 40);
        mealLabel.setForeground(Color.decode("#004d99"));
        panel.add(mealLabel);

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

        mealButton = new JButton("Meal");
        mealButton.setBounds(0, 65, 200, 30);
        mealButton.setFont(new Font("century gothic", Font.PLAIN, 24));
        mealButton.setForeground(Color.decode("#003366"));
        mealButton.setBackground(Color.decode("#cce6ff"));
        mealButton.addActionListener(this);
        mealButton.addMouseListener(this);
        panel.add(mealButton);

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
        int mealCount = 0;

        String dataQuery = "SELECT  Date, TotalMeal FROM meal WHERE (Date BETWEEN '"+ Activity.fromDate + "' AND '" + Activity.toDate+ "') AND Username='"+username+"';";
        try{
            ResultSet dataResultSet =  statement.executeQuery(dataQuery);
            ResultSetMetaData resultSetMetaData = dataResultSet.getMetaData();

            int rowCount=0;
            if(dataResultSet.last()){
                rowCount = dataResultSet.getRow();
                dataResultSet.beforeFirst();
            }
            column = new Vector<String>();
            row = (Vector<String>[]) new Vector[rowCount+1];
            data = new Vector<Vector<String>>();


            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                column.add(resultSetMetaData.getColumnName(i));
            }
            int index = 0;
            for(index=0;dataResultSet.next();index++){
                row[index] = new Vector<String>();
                for(int i=0;i<resultSetMetaData.getColumnCount();i++){
                    row[index].add(dataResultSet.getString(column.get(i)));
                    if(i == 1)
                        mealCount += Integer.parseInt(dataResultSet.getString(column.get(i)));
                }
                data.add(row[index]);
            }
            row[index] = new Vector<String>();
            row[index].add("Total");
            row[index].add(String.valueOf(mealCount));
            data.add(row[index]);

        }catch(Exception ae){
            System.out.println("retriveData Exception: " + ae.getMessage());
        }
    }
}
