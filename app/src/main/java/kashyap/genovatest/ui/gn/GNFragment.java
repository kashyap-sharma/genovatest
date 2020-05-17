package kashyap.genovatest.ui.gn;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import kashyap.genovatest.R;
import kashyap.genovatest.adaps.MultiViewTypeAdapter;
import kashyap.genovatest.cusfo.Constants;
import kashyap.genovatest.model.HomeModel;
import kashyap.genovatest.model.Model;

import static kashyap.genovatest.MyApplication.TAG;

public class GNFragment extends Fragment {

    private GNViewModel homeViewModel;
    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(GNViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gn, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetch();
    }

    private void fetch() {
        try {
            final ArrayList<Model> list = new ArrayList<>();
            JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAsset()));
            String status = obj.getString(Constants.STATUS);
            if (status.equalsIgnoreCase("1")) {
                JSONArray jsonArray = obj.getJSONArray("data");
                List<JSONObject> myJsonArrayAsList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    myJsonArrayAsList.add(jsonArray.getJSONObject(i));
                    Collections.sort(myJsonArrayAsList, (jsonObjectA, jsonObjectB) -> {
                        int compare = 0;
                        try {
                            int keyA = Integer.parseInt(jsonObjectA.getString("display_priority"));
                            int keyB = Integer.parseInt(jsonObjectB.getString("display_priority"));
                            compare = Integer.compare(keyA, keyB);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return compare;
                    });

                }
                JSONArray myJsonArray = new JSONArray();
                for (int k = 0; k < myJsonArrayAsList.size(); k++) {
                    myJsonArray.put(myJsonArrayAsList.get(k));
                }
                for (int j = 0; j < myJsonArray.length(); j++) {
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("banner")) {
                        JSONArray jsonArray1 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        ArrayList<HomeModel> list_items = new ArrayList<>();
                        for (int l = 0; l < jsonArray1.length(); l++) {
                            list_items.add(new HomeModel(jsonArray1.getJSONObject(l).getString("name"), jsonArray1.getJSONObject(l).getString("sub_heading"), jsonArray1.getJSONObject(l).getString("image"), jsonArray1.getJSONObject(l).getString("product_id")));
                        }
                        list.add(new Model(Model.banner, myJsonArray.getJSONObject(j).getString("title"), list_items ));
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("ListHorizSquare")) {
                        ArrayList<HomeModel> grid_items = new ArrayList<>();
                        JSONArray jsonArray2 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        for (int m = 0; m < jsonArray2.length(); m++) {
                            grid_items.add(new HomeModel(jsonArray2.getJSONObject(m).getString("category_name"), jsonArray2.getJSONObject(m).getString("image"), jsonArray2.getJSONObject(m).getString("category_id")));
                        }
                        list.add(new Model(Model.ListHorizSquare, myJsonArray.getJSONObject(j).getString("title"), grid_items));
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("banner1")) {
                        ArrayList<HomeModel> banner_items = new ArrayList<>();
                        JSONArray jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        for (int n = 0; n < jsonArray3.length(); n++) {
                            banner_items.add(new HomeModel(jsonArray3.getJSONObject(n).getString("name"), jsonArray3.getJSONObject(n).getString("sub_heading"), jsonArray3.getJSONObject(n).getString("image"), jsonArray3.getJSONObject(n).getString("button_text"), jsonArray3.getJSONObject(n).getString("product_id")));
                        }
                        list.add(new Model(Model.banner1, myJsonArray.getJSONObject(j).getString("title"), banner_items));
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("GridSquare")) {
                        ArrayList<HomeModel> squbanner_items = new ArrayList<>();
                        JSONArray jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        for (int n = 0; n < jsonArray3.length(); n++) {
                            squbanner_items.add(new HomeModel(jsonArray3.getJSONObject(n).getString("category_name"), jsonArray3.getJSONObject(n).getString("image"), jsonArray3.getJSONObject(n).getString("category_id")));
                        }
                        list.add(new Model(Model.GridSquare, myJsonArray.getJSONObject(j).getString("title"), squbanner_items));
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("ListHorizBig")) {
                        ArrayList<HomeModel> listHorizBig_items = new ArrayList<>();
                        JSONArray jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        for (int q = 0; q < jsonArray3.length(); q++) {
                            listHorizBig_items.add(new HomeModel(jsonArray3.getJSONObject(q).getString("name"), jsonArray3.getJSONObject(q).getString("brand"), jsonArray3.getJSONObject(q).getString("image"), jsonArray3.getJSONObject(q).getString("price"), jsonArray3.getJSONObject(q).getString("off"), jsonArray3.getJSONObject(q).getString("old_price"), jsonArray3.getJSONObject(q).getString("product_id")));
                        }
                        list.add(new Model(Model.ListHorizBig, myJsonArray.getJSONObject(j).getString("title"), listHorizBig_items));
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("ListCarous")) {
                        ArrayList<HomeModel> listHorizBig_items = new ArrayList<>();
                        JSONArray jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        for (int q = 0; q < jsonArray3.length(); q++) {
                            listHorizBig_items.add(new HomeModel(jsonArray3.getJSONObject(q).getString("name"), jsonArray3.getJSONObject(q).getString("brand"), jsonArray3.getJSONObject(q).getString("image"), jsonArray3.getJSONObject(q).getString("price"), jsonArray3.getJSONObject(q).getString("off"), jsonArray3.getJSONObject(q).getString("old_price"), jsonArray3.getJSONObject(q).getString("product_id")));
                        }
                        list.add(new Model(Model.ListCarous, myJsonArray.getJSONObject(j).getString("title"), listHorizBig_items));
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equalsIgnoreCase("List2itemgrid")) {
                        ArrayList<HomeModel> listHorizBig_items = new ArrayList<>();
                        JSONArray jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items");
                        for (int q = 0; q < jsonArray3.length(); q++) {
                            listHorizBig_items.add(new HomeModel(jsonArray3.getJSONObject(q).getString("name"), jsonArray3.getJSONObject(q).getString("brand"), jsonArray3.getJSONObject(q).getString("image"), jsonArray3.getJSONObject(q).getString("price"), jsonArray3.getJSONObject(q).getString("off"), jsonArray3.getJSONObject(q).getString("old_price"), jsonArray3.getJSONObject(q).getString("product_id")));
                        }
                        list.add(new Model(Model.List2itemgrid, myJsonArray.getJSONObject(j).getString("title"), listHorizBig_items));
                    }
                }
                showUI(list);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = requireActivity().getAssets().open("home.json");
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
    private void showUI(ArrayList<Model> list) {
        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list, getContext(), requireActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);

    }




}
