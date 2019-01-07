package src;
public class ObjectRefer{
    static Dashboard dashboard = null;
    static TransactionHistory transactionHistory = null;
    static MealHistory mealHistory = null;
    static Manage manage = null;
    static Profile profile = null;
    static Overview overview = null;
    /**
     * @return the transactionHistory
     */
    public static TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }
    /**
     * @param transactionHistory the transactionHistory to set
     */
    public static void setTransactionHistory(TransactionHistory transactionHistory) {
        ObjectRefer.transactionHistory = transactionHistory;
    }
    /**
     * @return the dashboard
     */
    public static Dashboard getDashboard() {
        return dashboard;
    }
    /**
     * @param dashboard the dashboard to set
     */
    public static void setDashboard(Dashboard dashboard) {
        ObjectRefer.dashboard = dashboard;
    }
    /**
     * @param overview the overview to set
     */
    public static void setOverview(Overview overview) {
        ObjectRefer.overview = overview;
    }
    /**
     * @return the overview
     */
    public static Overview getOverview() {
        return overview;
    }
    /**
     * @return the mealHistory
     */
    public static MealHistory getMealHistory() {
        return mealHistory;
    }
    /**
     * @param mealHistory the mealHistory to set
     */
    public static void setMealHistory(MealHistory mealHistory) {
        ObjectRefer.mealHistory = mealHistory;
    }
    /**
     * @param manage the manage to set
     */
    public static void setManage(Manage manage) {
        ObjectRefer.manage = manage;
    }
    /**
     * @return the manage
     */
    public static Manage getManage() {
        return manage;
    }
    /**
     * @return the profile
     */
    public static Profile getProfile() {
        return profile;
    }
    /**
     * @param profile the profile to set
     */
    public static void setProfile(Profile profile) {
        ObjectRefer.profile = profile;
    }

    public static void setNull(){
        dashboard = null;
        transactionHistory = null;
        mealHistory = null;
        manage = null;
        profile = null;
    }
}