package com.example.epicchoicemaker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;
    private int AmmItems = 1;
    private ViewHolder adapter;
    public static  List<String> Items;

    public RecyclerViewAdapter(Context context, List<String> InItems)
    {
        mContext = context;
        Items = InItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_choicething, parent, false);
        ViewHolder viewHolder = new ViewHolder((view));
        viewHolder.MyAdapter = this;
        //Items.add("Stuffy Buffy");

        this.adapter = viewHolder;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewholder: called.");

        holder.AddChoiceButton.setOnClickListener(new View.OnClickListener() //actually remove but shh
        {
            @Override
            public void onClick(View view)
            {
                Items.remove(holder.getAdapterPosition());
                holder.MyAdapter.notifyItemRemoved(holder.getAdapterPosition());
            }
        });
        holder.ChoiceTextThing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Items.set(holder.getAdapterPosition(), editable.toString());
            }
        });

        holder.ChoiceTextThing.setText(Items.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        EditText ChoiceTextThing;
        Button AddChoiceButton;
        RelativeLayout parent_layout;
        public RecyclerViewAdapter MyAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ChoiceTextThing = itemView.findViewById(R.id.ChoiceTextThing);
            AddChoiceButton = itemView.findViewById(R.id.AddChoiceButton);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
