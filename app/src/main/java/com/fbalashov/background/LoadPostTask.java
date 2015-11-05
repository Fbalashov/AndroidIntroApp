package com.fbalashov.background;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 11/4/2015.
 */
public class LoadPostTask extends AsyncTask<Integer, Void, JSONObject> {
  private static final String LOG_TAG = LoadPostTask.class.getSimpleName();
  private TextView titleView;
  private TextView bodyView;

  public LoadPostTask(TextView titleView, TextView bodyView) {
    this.titleView = titleView;
    this.bodyView = bodyView;
  }

  @Override
  protected JSONObject doInBackground(Integer... postId) {
    JSONObject jsonObject = null;
    try {
      URL url = new URL("http://jsonplaceholder.typicode.com/posts/" + postId[0].toString());
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      try {
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        jsonObject = readStream(in);
      } catch (Exception e) {
        Log.e(LOG_TAG, "Exception when connecting: "+e.getMessage());
      } finally{
        urlConnection.disconnect();
      }
    } catch (Exception e) {
      Log.e(LOG_TAG, "Exception when parsing: "+e.getMessage());
    }
    return jsonObject;
  }

  public JSONObject readStream(InputStream inputStream) throws Exception {
    BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    StringBuilder responseStrBuilder = new StringBuilder();

    String inputStr;
    while ((inputStr = streamReader.readLine()) != null) {
      responseStrBuilder.append(inputStr);
    }
    return new JSONObject(responseStrBuilder.toString());
  }

  @Override
  protected void onPostExecute(JSONObject jsonObject) {
    super.onPostExecute(jsonObject);
    if (jsonObject == null) {
      return;
    }

    try {
      String titleString = jsonObject.getString("title");
      String bodyString = jsonObject.getString("body");
      titleView.setText(titleString);
      bodyView.setText(bodyString);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}