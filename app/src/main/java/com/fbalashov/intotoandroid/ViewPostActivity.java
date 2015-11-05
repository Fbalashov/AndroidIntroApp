package com.fbalashov.intotoandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.fbalashov.background.LoadPostTask;

public class ViewPostActivity extends ActionBarActivity {
  private TextView titleText;
  private TextView contextText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_post);
    titleText = (TextView) findViewById(R.id.post_title);
    contextText = (TextView) findViewById(R.id.post_content);
    LoadPostTask loadPostTask = new LoadPostTask(titleText, contextText);
    loadPostTask.execute(1);
  }
}
