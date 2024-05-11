package com.tvcrud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tvcrud.MainActivity;
import com.tvcrud.R;
import com.tvcrud.adapter.CatAdapter;
import com.tvcrud.adapter.SpinnerAdapter;
import com.tvcrud.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener{
    private CatAdapter adapter;
    private Spinner spinner;
    private EditText eName, ePrice, eInfor;
    private Button btAdd, btupdate;
    private RecyclerView recyclerView;

    private int pcurr;
    private int[] imgs = {R.drawable.img, R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
            R.drawable.img_4, R.drawable.img_5, };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new CatAdapter((MainActivity) getActivity());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try{
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(ePrice.getText().toString());
                    Cat cat = new Cat(img, eName.getText().toString(), price, eInfor.getText().toString());
                    adapter.add(cat);

                } catch (NumberFormatException e){

                }
            }
        });

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try{
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(ePrice.getText().toString());
                    Cat cat = new Cat(img, eName.getText().toString(), price, eInfor.getText().toString());
                    adapter.update(pcurr,cat);
                    btupdate.setVisibility(View.INVISIBLE);

                } catch (NumberFormatException e){

                }
                btAdd.setVisibility(View.VISIBLE);

            }
        });

    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapterSp  = new SpinnerAdapter(getContext(),imgs);
        spinner.setAdapter(adapterSp);
        eName = view.findViewById(R.id.eName);
        ePrice = view.findViewById(R.id.ePrice);
        eInfor = view.findViewById(R.id.eDesc);
        btAdd = view.findViewById(R.id.btAdd);
        btupdate = view.findViewById(R.id.btUpdate);
        recyclerView = view.findViewById(R.id.reView);
        btupdate.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onItemCreateClick(View view, int position) {
        btAdd.setVisibility(View.INVISIBLE);
        btupdate.setVisibility(view.VISIBLE);
        pcurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for(int i = 0; i < imgs.length; i++){
            if (img == imgs[i]){
                p = i;
                break;
            }
            spinner.setSelection(p);
            eName.setText(cat.getName());
            ePrice.setText(cat.getPrice()+"");
            eInfor.setText(cat.getInfor());

        }
    }
}
