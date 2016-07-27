package com.vinhsang.vivmall.data.net;

import android.os.AsyncTask;
import android.util.Log;

import com.vinhsang.vivmall.data.datamanager.ConnectService;


/**
 * Created by Long on 7/18/2016.
 */

public class ApiConnection {
    private static final String TAG = "ApiConnection";
    public static class MyTaskLoadProduct extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String response = "";
            String fromrow = params[0];
            try {


                try {
                    String file = "USER";
                    String code = "getproduct";
                    String[] pfields = {"fromrow"};
                    String[] pvalues = {fromrow};
                    response = ConnectService.ExecGetDataReturnJson(ConnectService.RequestMethod.GET, file, code, pfields, pvalues);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {


            super.onPostExecute(result);
        }
    }

    public static class MyTaskLoadProductByCatalogue extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String response = "";
            String cateid = params[0];
            String fromrow  = params[1];
            try {


                try {
                    String file = "USER";
                    String code = "getproductbycateid";
                    String []pfields = {"cateid","fromrow"};
                    String []pvalues = {cateid,fromrow};
                    response = ConnectService.ExecGetDataReturnJson(ConnectService.RequestMethod.GET, file, code, pfields, pvalues);
                    Log.d(TAG, "response: "+response);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return response;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {


            super.onPostExecute(result);
        }
    }
}
