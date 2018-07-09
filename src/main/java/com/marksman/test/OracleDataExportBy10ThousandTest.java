package com.marksman.test;

import java.io.FileOutputStream;
import java.sql.*;

/**
 * @author weilb
 * @date 2018/7/8
 * @description  将数据库中的数据按一万条数据导出一个sql文件
 */
public class OracleDataExportBy10ThousandTest {

    public static void main(String[] args){
        //String sql = "select count(*) from tradevl.DRM_CURRENCY_PAIR";
        String sql = "select t.*,ROWNUM from tradevl.MDM_RISK_FACTOR_BASIC_INFO t where ROWNUM<3";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            WriteSqlFile.WriteSqlFile(resultSet,columnCount);

            /*while(resultSet.next()) {
                //System.out.println("货币对表 DRM_CURRENCY_PAIR 中有" + resultSet.getInt(1) + "条数据!");
                System.out.println("风险因子基本信息 MDM_RISK_FACTOR_BASIC_INFO 中有" + resultSet.getInt(1) + "条数据!");
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
      * @description
      * @params []
      * @return java.sql.Connection
      * @author weilb
      * @date 2018/7/8
      */
    public static Connection getConnection(){
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:TRADEVL";
        String username = "TRADEVL";
        String password = "123456";

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


}
