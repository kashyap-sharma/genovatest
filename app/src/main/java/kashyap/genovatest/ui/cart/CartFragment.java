package kashyap.genovatest.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import kashyap.genovatest.CheckoutA;
import kashyap.genovatest.R;
import kashyap.genovatest.Static_Catelog;
import kashyap.genovatest.adaps.CartAdap;
import kashyap.genovatest.adaps.EmptycartAdap;
import kashyap.genovatest.adaps.MultiViewTypeAdapter;
import kashyap.genovatest.cusfo.Constants;
import kashyap.genovatest.cusfo.ProximaNovaButton;
import kashyap.genovatest.model.HomeModel;
import kashyap.genovatest.model.Model;

public class CartFragment extends Fragment {
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager1;
    private CartViewModel mCartViewModel;
    private ProximaNovaButton proceed;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mCartViewModel =
                ViewModelProviders.of(this).get(CartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        linearLayoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView2 = root.findViewById(R.id.recyclerView2);
        proceed = root.findViewById(R.id.proceed);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager1);
        if (Static_Catelog.getStringProperty(requireContext(),"added").equalsIgnoreCase("no")){
            root.findViewById(R.id.cartempty).setVisibility(View.VISIBLE);
            root.findViewById(R.id.cartvisible).setVisibility(View.GONE);
        }else{
            root.findViewById(R.id.cartempty).setVisibility(View.GONE);
            root.findViewById(R.id.cartvisible).setVisibility(View.VISIBLE);
        }
        mCartViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
             //   textView.setText(s);
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), CheckoutA.class));
            }
        });
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            fetch();
            fetchempty();

    }



    private String loadJSONFromAsset(String jsons) {
        String json = null;
        try {
            InputStream is = requireActivity().getAssets().open(jsons);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void fetch() {
        try {

            JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAsset("filled_cart.json")));
            String status = obj.getString(Constants.STATUS);
            if (status.equalsIgnoreCase("1")) {
                JSONArray jsonArray = obj.getJSONArray("data");
                for (int j = 0; j < jsonArray.length(); j++) {
                        JSONArray jsonArray1 = jsonArray.getJSONObject(j).getJSONArray("items");
                        ArrayList<HomeModel> list_items = new ArrayList<>();
                        for (int l = 0; l < jsonArray1.length(); l++) {
                            //HomeModel(String name, String image, String product_id, String brand, String price, String off, String old_price, String weight, String quantity)
                            list_items.add(new HomeModel(jsonArray1.getJSONObject(l).getString("name"),
                                    jsonArray1.getJSONObject(l).getString("image"),
                                    jsonArray1.getJSONObject(l).getString("product_id"),
                                    jsonArray1.getJSONObject(l).getString("brand"),
                                    jsonArray1.getJSONObject(l).getString("price"),
                                    jsonArray1.getJSONObject(l).getString("off"),
                                    jsonArray1.getJSONObject(l).getString("old_price"),
                                    jsonArray1.getJSONObject(l).getString("weight"),
                                    jsonArray1.getJSONObject(l).getString("quantity")));
                        }
                    showUI(list_items,"fill");
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void fetchempty() {
        try {

            JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAsset("empty_cart.json")));
            String status = obj.getString(Constants.STATUS);
            if (status.equalsIgnoreCase("1")) {
                JSONArray jsonArray = obj.getJSONArray("data");
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONArray jsonArray1 = jsonArray.getJSONObject(j).getJSONArray("items");
                    ArrayList<HomeModel> list_items = new ArrayList<>();
                    for (int l = 0; l < jsonArray1.length(); l++) {
                        list_items.add(new HomeModel(jsonArray1.getJSONObject(l).getString("name"),
                                jsonArray1.getJSONObject(l).getString("image"),
                                jsonArray1.getJSONObject(l).getString("product_id"),
                                jsonArray1.getJSONObject(l).getString("brand"),
                                jsonArray1.getJSONObject(l).getString("price"),
                                jsonArray1.getJSONObject(l).getString("off"),
                                jsonArray1.getJSONObject(l).getString("old_price")));
                    }
                    showUI(list_items,"empty");
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showUI(ArrayList<HomeModel> list_items, String type) {
        if (type.equalsIgnoreCase("empty")) {
            EmptycartAdap adapter = new EmptycartAdap( getContext(),list_items, requireActivity().getSupportFragmentManager());
            recyclerView.setAdapter(adapter);
        }else {
            CartAdap adapter = new CartAdap(requireContext(),list_items, requireActivity().getSupportFragmentManager());
            recyclerView2.setAdapter(adapter);
        }
    }

}
