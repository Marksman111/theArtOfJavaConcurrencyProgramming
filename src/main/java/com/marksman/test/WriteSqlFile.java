package com.marksman.test;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weilb
 * @date 2018/7/8
 * @description
 */
public class WriteSqlFile {

    public static void main(String[] args){
        //String str = "abcdefghijklmnopqrstuvwxyz";
        //WriteStringSqlFile(str);
    }

    /**
      * @description
      * @params [resultSet]
      * @return void
      * @author weilb
      * @date 2018/7/8
      */
    public static void WriteStringSqlFile(String string){
        String path = "F:\\testOracle\\";
        String fileName = 1+".sql";
        File file = new File(path+fileName);

        FileOutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(file);
            outputStream.write(string.getBytes());
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
      * @description
      * @params [resultSet]
      * @return void
      * @author weilb
      * @date 2018/7/8
      */
    public static void WriteSqlFile(ResultSet resultSet,int columnCount){
        String sqlString = "INSERT INTO 'MDM_RISK_FACTOR_BASIC_INFO'('ID', 'RISK_FACTOR', 'TERM', 'DELTA', 'DOMESTIC_CURRENCY'," +
                           "'FOREIGN_CURRENCY', 'MARKET_TYPE', 'SRC_SYSTEM_CODE', 'SRC_CURVE_CODE', 'SRC_TERM_CODE', " +
                           "'SRC_RISK_FACTOR_CODE', 'STATUS', 'VALID_DATE', 'INVALID_DATE', 'CREATE_BY', 'CREATE_DATE'," +
                           "'UPDATE_BY', 'UPDATE_DATE', 'REMARKS', 'DEL_FLAG') VALUES (";
        String path = "F:\\testOracle\\";
        String fileName = 1+".sql";
        File file = new File(path+fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            while (resultSet.next()) {
                /*INSERT INTO "MDM_RISK_FACTOR_BASIC_INFO"("ID", "RISK_FACTOR", "TERM", "DELTA", "DOMESTIC_CURRENCY",
                        "FOREIGN_CURRENCY", "MARKET_TYPE", "SRC_SYSTEM_CODE", "SRC_CURVE_CODE", "SRC_TERM_CODE",
                        "SRC_RISK_FACTOR_CODE", "STATUS", "VALID_DATE", "INVALID_DATE", "CREATE_BY", "CREATE_DATE",
                        "UPDATE_BY", "UPDATE_DATE", "REMARKS", "DEL_FLAG") VALUES ('216334', 'USDAM6L8Y=SMKR', '8Y',
                        NULL, 'USD', NULL, 'ALL_IN_COST', 'REUTERS', '1', '1', '1', '1', '2016-10-16', '3000-01-01',
                        '1', TO_TIMESTAMP('2018-07-08 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), '1',
                        TO_TIMESTAMP('2018-07-08 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), NULL, '0');*/
                String resultString;
                for (int i=1;i<columnCount;i++){
                    resultString = resultSet.getString(i);
                    if (resultString!=null&&resultString!=""){
                        sqlString += "'"+resultString+"',";
                    }else {
                        sqlString += "'"+"NULL"+"',";
                    }
                }
                sqlString += sqlString +"'"+ resultSet.getString(columnCount) +"');\n";
                System.out.println("resultSet总列数：" + columnCount);
                outputStream.write(sqlString.getBytes("UTF-8"));
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outputStream !=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
