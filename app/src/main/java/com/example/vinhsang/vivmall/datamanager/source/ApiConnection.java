package com.example.vinhsang.vivmall.datamanager.source;

import android.os.AsyncTask;

import com.example.vinhsang.vivmall.datamanager.ConnectService;

/**
 * Created by Long on 7/18/2016.
 */

public class ApiConnection {
    public static class MyTaskLoadProduct extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String response = "";
            String fromrow  = params[0];
            try {


                try{
                    String file = "USER";
                    String code = "getproduct";
                    String []pfields = {"fromrow"};
                    String []pvalues = {fromrow};
                    response  = ConnectService.ExecGetDataReturnJson(ConnectService.RequestMethod.GET, file, code, pfields, pvalues);

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } catch(Exception e) {
                e.printStackTrace();

            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {




            super.onPostExecute(result);
        }
    }
}
