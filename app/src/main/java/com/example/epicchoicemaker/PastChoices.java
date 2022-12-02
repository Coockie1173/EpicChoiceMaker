package com.example.epicchoicemaker;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;

public class PastChoices extends AppCompatActivity {

    DatabaseHelper DHelper;
    private ListView LView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_choices);
        LView =(ListView) findViewById(R.id.listView);
        DHelper = new DatabaseHelper(this);

        PopulateListView();
    }

    private void PopulateListView() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this );
        Cursor data = DHelper.GetData();
        ArrayList<String> ListData = new ArrayList<>();
        while(data.moveToNext())
        {
            String buf = getResources().getString(R.string.Choice) + " ";
            buf += data.getString(1) + " ";
            buf += getResources().getString(R.string.By) + " ";
            buf += data.getString(3) + " ";
            if (pref.getBoolean("SaveDate", true))
            {
                buf += getResources().getString(R.string.From) + " ";
                buf += data.getString(2);
            }
            ListData.add(buf);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListData);
        LView.setAdapter(adapter);
    }

    public void ClearPastChoicesPress(View view)
    {
        DHelper.ClearData();
        finish();
        startActivity(getIntent());
    }
}