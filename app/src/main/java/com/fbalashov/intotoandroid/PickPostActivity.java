package com.fbalashov.intotoandroid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by admin on 11/4/2015.
 */
public class PickPostActivity extends AppCompatActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pick_post);
    final Spinner dropdown = (Spinner) findViewById(R.id.spinner);
    Button button = (Button) findViewById(R.id.button);
    setupSpinner(dropdown);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Integer selectedItem = (Integer)dropdown.getSelectedItem();
        ViewPostActivity.startActivity(PickPostActivity.this, selectedItem);
      }
    });
  }

  private void setupSpinner(Spinner dropdown) {
    Integer[] items = new Integer[50];
    for(int index = 1; index <= items.length; index++) {
      items[index-1] = index;
    }
    ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
    dropdown.setAdapter(adapter);
  }
}
