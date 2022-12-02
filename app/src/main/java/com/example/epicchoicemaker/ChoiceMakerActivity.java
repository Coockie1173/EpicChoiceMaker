package com.example.epicchoicemaker;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epicchoicemaker.databinding.ActivityChoiceMakerBinding;

import java.util.ArrayList;
import java.util.Random;

public class ChoiceMakerActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityChoiceMakerBinding binding;
    ArrayList<String> Items;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChoiceMakerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        dbHelper = new DatabaseHelper(this);

        InitRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_choice_maker);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void InitRecyclerView()
    {
        RecyclerView recv = findViewById(R.id.RecyclerView);
        Items = new ArrayList<>();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Items);
        recv.setAdapter(adapter);
        recv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void OnAddClick(View view)
    {
        RecyclerView recv = findViewById(R.id.RecyclerView);
        RecyclerViewAdapter.Items.add("");
        recv.getAdapter().notifyItemChanged(RecyclerViewAdapter.Items.size() - 1);
    }

    public void OnMakeChoice(View view)
    {
        if(RecyclerViewAdapter.Items.size() > 1)
        {
            int rnd = new Random().nextInt(RecyclerViewAdapter.Items.size());
            int Ofsetter = 0;
            RecyclerView recv = findViewById(R.id.RecyclerView);
        /*for(int i = 0; i < RecyclerViewAdapter.Items.size(); i++)
        {
            if(i != rnd)
            {
                RecyclerViewAdapter.Items.remove(Ofsetter);
                recv.getAdapter().notifyItemRemoved(Ofsetter);
            }
            else
            {
                Ofsetter++;
            }
        }*/
            String Temp = RecyclerViewAdapter.Items.get(rnd);
            int size = RecyclerViewAdapter.Items.size();
            for(int i = 0; i < size; i++)
            {
                RecyclerViewAdapter.Items.remove(0);
            }
            recv.getAdapter().notifyItemRangeRemoved(0, size);
            RecyclerViewAdapter.Items.add(Temp);
            recv.getAdapter().notifyItemInserted(0);
            this.AddData(Temp);
        }
    }

    public void AddData(String NewEntry)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this );
        String buf = pref.getString("DisplayName","Stefaan");
        Boolean MoreBuf = pref.getBoolean("SaveDate", true);
        Log.i("Choicemaker", buf);
        boolean Inserted = dbHelper.addData(NewEntry, buf);

        if(!Inserted)
        {
            //error
            Log.d("Choicemaker", "AddData: Error during insertion in database.");
        }
    }
}