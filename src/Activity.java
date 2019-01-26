package src;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
public class Activity{
    static int year;
    static int month;
    static String fromDate;
    static String toDate;

    static Statement statement;
    static  ResultSet resultSet;

    /**
     * Generate Current Month, Year
     */
    static void getDate(){
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        fromDate = ""+  year + "-" + month + "-1" ;
        toDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * Retrieve individual user balance
     */
    static double getBalance(String username, Statement statement){
        Double personMeal = 0.0;
        Double totalMeal = 0.0;
        Double cost = 0.0;
        Double totalBalance = 0.0;
        Activity.statement = statement;

        String mealQuery = "SELECT SUM(TotalMeal) TotalMeal FROM meal WHERE (Date BETWEEN '"+ Activity.fromDate + "' AND '" + Activity.toDate+ "') AND Username='"+username+"';";
        String costQuery = "SELECT SUM(Payment)+SUM(MarketCost) Cost FROM balance WHERE (Date BETWEEN '"+ Activity.fromDate + "' AND '" + Activity.toDate+ "') AND Username='"+username+"';";
        String totalMealQuery = "SELECT SUM(TotalMeal) TotalMeal FROM meal WHERE (Date BETWEEN '"+ Activity.fromDate + "' AND '" + Activity.toDate+ "') AND Username IN (SELECT Username FROM account WHERE Suser IN (SELECT Suser FROM account WHERE Username='"+username+"'));";
        String totalCostQuery = "SELECT SUM(Payment)+SUM(MarketCost) Cost FROM balance WHERE (Date BETWEEN '"+ Activity.fromDate + "' AND '" + Activity.toDate+ "') AND Username IN (SELECT Username FROM account WHERE Suser IN (SELECT Suser FROM account WHERE Username='"+username+"'));";
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

        return 0.0;
    }

    static int countRow(ResultSet resultSet){
        int rowCount=0;
        try{
            if(resultSet.last()){
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
        }catch(Exception ae){
            System.out.println("countRow Exception: " + ae.getMessage());
        }
        return rowCount;
    }


}