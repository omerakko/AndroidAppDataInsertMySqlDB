package com.example.hp.sqlinsert;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
//String DB_URL="jdbc:mysql://192.168.1.34/test";
String DB_URL="jdbc:mysql://172.19.3.199/test";
String USER="omer";
String PASS="xxl";
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);


    }
    public void btn (View view){
        Send send=new Send();

        send.execute("");
    }


    private class Send extends AsyncTask<String,String,String>
    {




        String text="omer";
        String msg="no";
        String retrieved;
        ResultSet rs;
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {

            textView.setText("please wait inserting data ");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn==null) {
                  msg="connection wrong";
                }
                else
                {
                    String query1="SELECT * from test1 ";
                   // String query="INSERT INTO test1 (omer) VALUES('"+text+"')" ;
                    Statement stm=conn.createStatement();

                   // stm.executeUpdate(query);
                    rs=stm.executeQuery(query1);
                    msg="reading succesfful!!!";
                    while(rs.next()){
                    System.out.println(rs.getString(1)+"AKKO");}
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (ClassNotFoundException e) {
                msg="conncection goes wrong";
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return msg;
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(msg);
            textView.setText(msg);
        }
    }
    }

