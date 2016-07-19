package com.vinhsang.vivmall.presentation.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.vinhsang.vivmall.data.datamanager.ConnectService;
import com.vinhsang.vivmall.domain.ItemProduct;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class Extra {

	public static String ConvertArrayToJson(String[]parray){
		 JSONArray mJSONArray = new JSONArray(Arrays.asList(parray));
		 return mJSONArray.toString();		 
	}
	public static String site="8.8.8.8";
	public static boolean testInet(String site) {
		try {
			try {
				URL url = new URL(site);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();
				if (con.getResponseCode() == 200){
					return  true;
				}
				return  false;
			} catch (Exception exception) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  false;
		}
	}

	public static boolean isOnline(String site) {

		Runtime runtime = Runtime.getRuntime();
		try {

			Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+site);
			int     exitValue = ipProcess.waitFor();
			return (exitValue == 0);

		} catch (IOException e)          { e.printStackTrace(); }
		catch (InterruptedException e) { e.printStackTrace(); }

		return false;
	}
    public static class MyTaskLoadProductbyCt  extends AsyncTask<String, Void, ArrayList<ItemProduct>> {

        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected ArrayList<ItemProduct> doInBackground(String... params) {
            ArrayList<ItemProduct> mlistProduct = new ArrayList<>();
            try {
                String cateid = params[0];
                String fromrow  = params[1];

                try{
                    String file = "USER";
                    String code = "getproductbycateid";
                    String []pfields = {"cateid","fromrow"};
                    String []pvalues = {cateid,fromrow};
                    String response  = ConnectService.ExecGetDataReturnJson(ConnectService.RequestMethod.GET, file, code, pfields, pvalues);
                    if(response!=null && response.length()>0){
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++) {
                            String strobj = jsonArray.getString(i);
                            ItemProduct item = Extra.getItemProductFromJson(strobj);
                            mlistProduct.add(item);
                        }
                    }

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } catch(Exception e) {
                e.printStackTrace();

            }
            return mlistProduct;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemProduct> result) {

            super.onPostExecute(result);
        }
    }

    public static class MyTaskLoadProduct extends AsyncTask<String, Void, ArrayList<ItemProduct>> {


        @Override
        protected ArrayList<ItemProduct> doInBackground(String... params) {
            ArrayList<ItemProduct> mlistProduct = new ArrayList<>();
            try {

                String fromrow  = params[0];

                mlistProduct = Extra.loadProductListAll( fromrow);
            } catch(Exception e) {
                e.printStackTrace();

            }
            return mlistProduct;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemProduct> result) {




            super.onPostExecute(result);
        }
    }
    public static ArrayList<ItemProduct> loadProductListAll(String fromrow){

        ArrayList<ItemProduct> itemProductList = new ArrayList<ItemProduct>();
        try{
            String file = "USER";
            String code = "getproduct";
            String []pfields = {"fromrow"};
            String []pvalues = {fromrow};
            String response  = ConnectService.ExecGetDataReturnJson(ConnectService.RequestMethod.GET, file, code, pfields, pvalues);
            if(response!=null && response.length()>0){
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0; i<jsonArray.length(); i++) {
                    String strobj = jsonArray.getString(i);
                    ItemProduct item = Extra.getItemProductFromJson(strobj);
                    itemProductList.add(item);
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        return itemProductList;
    }
	public static ItemProduct getItemProductFromJson(String strobj) {
		ItemProduct itemProduct=new Gson().fromJson( strobj, ItemProduct.class);
		return itemProduct;
	}
	public static String getJsonfromItemProduct(ItemProduct itemProduct) {
		String strobj = new Gson().toJson(itemProduct);
		return strobj;
	}
//    public static ItemCustomer getCuntomerFromJson(String strobj) {
//        ItemCustomer itemCustomer =new Gson().fromJson(strobj, ItemCustomer.class);
//        return itemCustomer;
//    }
//    public static String getJsonfromCustomer(ItemCustomer itemCustomer) {
//        String strobj = new Gson().toJson(itemCustomer);
//        return strobj;
//    }
    public static String postOrder(String itemCustomer, String list){
        String response = "";
        try{

            response  = ConnectService.ExecShoopingCart(ConnectService.RequestMethod.GET, itemCustomer,list);
            return response;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }


    public static class MyTaskloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 2;
                URL u = new URL(params[0]);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent(),
                        null, bmOptions);

                return bitmap;

            } catch(Exception e) {
                e.printStackTrace();

            }
            return null;
        }
    }
    public static Bitmap getProduct_bitmap(String product_image )  {
        Bitmap product_bitmap = null;
        try {
            product_bitmap = new MyTaskloadImage().execute(product_image).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return product_bitmap;
    }
    //Load product by product_id
    public static class MyTaskLoadProductById extends AsyncTask<String, Void, ArrayList<ItemProduct>> {


        @Override
        protected ArrayList<ItemProduct> doInBackground(String... params) {
            ArrayList<ItemProduct> mlistProduct = new ArrayList<>();
            try {
                String product_id = params[0];
                try{
                    String file = "USER";
                    String code = "getproductbyid";
                    String []pfields = {"product_id"};
                    String []pvalues = {product_id};
                    String response  = ConnectService.ExecGetDataReturnJson(ConnectService.RequestMethod.GET, file, code, pfields, pvalues);
                    if(response!=null && response.length()>0){
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++) {
                            String strobj = jsonArray.getString(i);
                            ItemProduct item = Extra.getItemProductFromJson(strobj);
                            mlistProduct.add(item);
                        }
                    }

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } catch(Exception e) {
                e.printStackTrace();

            }
            return mlistProduct;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemProduct> result) {




            super.onPostExecute(result);
        }
    }
    public static String formatNumber(String strNumber){
        String yourFormattedString = NumberFormat.getNumberInstance(Locale.US).format(Integer.valueOf(strNumber));
        return yourFormattedString;
    }
    public static String formatNumber(double number){

        String yourFormattedString = NumberFormat.getNumberInstance(Locale.US).format(number);
        return yourFormattedString;
    }



}
