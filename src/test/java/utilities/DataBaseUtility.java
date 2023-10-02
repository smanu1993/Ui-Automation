package utilities;

import java.sql.*;

public class DataBaseUtility {
   public static Connection con;
    public static Connection getDBConnection(){
        try {
            con = DriverManager.getConnection("db_url", "un", "pwd");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public static void closeDBConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[][] executeSelectQuery(String query){
        String[][] data = null;
        try {
            PreparedStatement statement = con.prepareStatement(query);
           ResultSet rs = statement.executeQuery();
           int col_count = rs.getMetaData().getColumnCount();
           rs.last(); // move to last row
            int row_count = rs.getRow();
            rs.beforeFirst();
            data = new String[row_count][col_count];
            int i= 0;
            while (rs.next()){
                for (int j = 0; j <col_count ; j++) {
                    data[i][j] = rs.getString(j+1);
                }
                i++;
            }
        } catch (SQLException e) {
            data[0][0] ="";
            throw new RuntimeException(e);
        }
        return data ;
    }
}
