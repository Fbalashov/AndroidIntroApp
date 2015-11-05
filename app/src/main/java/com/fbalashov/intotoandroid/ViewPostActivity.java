package com.fbalashov.intotoandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fbalashov.background.LoadPostTask;

public class ViewPostActivity extends AppCompatActivity {
  private static String postIntentIndex = "POST_INTENT_INDEX";

  private TextView titleText;
  private TextView contextText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_post);
    titleText = (TextView) findViewById(R.id.post_title);
    contextText = (TextView) findViewById(R.id.post_content);

    Intent startIntent = getIntent();
    int postIndex = startIntent.getIntExtra(postIntentIndex, 1);

    LoadPostTask loadPostTask = new LoadPostTask(titleText, contextText);
    loadPostTask.execute(postIndex);
  }

  public static void startActivity(Context context, Integer postIndex) {
    Intent intent = new Intent(context, ViewPostActivity.class);
    intent.putExtra(postIntentIndex, (int)postIndex);
    context.startActivity(intent);
  }
}
