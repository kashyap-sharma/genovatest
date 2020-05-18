package kashyap.genovatest.ui.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import kashyap.genovatest.Placed;
import kashyap.genovatest.R;
import kashyap.genovatest.cusfo.ProximaNovaButton;
import kashyap.genovatest.cusfo.ProximaNovaEditText;
import kashyap.genovatest.cusfo.ProximaNovaInputLayout;
import kashyap.genovatest.cusfo.ProximaNovaText;
import kashyap.genovatest.cusfo.ValidationUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    String[] titleItem = {"Mr", "Mrs", "Ms"};
    String[] cityItem = {"Delhi", "Bengaluru", "Mumbai", "Dubai", "Abu Dhabi", "Sharjah","Al Ain"};
    String[] countryItem = {"India", "Saudi Arabia", "UAE"};
    String[] codeItem = {"+91", "+971", "+972"};


    public ProximaNovaEditText etTitle;
    public ProximaNovaInputLayout ilTitle;
    public ProximaNovaEditText etFullName;
    public ProximaNovaInputLayout ilFullName;
    public ProximaNovaEditText etAddress;
    public ProximaNovaInputLayout ilAddress;
    public ProximaNovaEditText etPOBox;
    public ProximaNovaInputLayout ilPOBox;
    public ProximaNovaEditText etCity;
    public ProximaNovaInputLayout ilCity;
    public ProximaNovaEditText etCountry;
    public ProximaNovaInputLayout ilCountry;
    public ProximaNovaEditText etCode;
    public ProximaNovaInputLayout ilCode;
    public ProximaNovaEditText etMobile;
    public ProximaNovaInputLayout ilMobile;
    public ProximaNovaButton verify;
    public ProximaNovaButton place;
    private PageViewModel pageViewModel;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        // final TextView textView = root.findViewById(R.id.section_label);
        etTitle =  root.findViewById(R.id.etTitle);
        ilTitle =  root.findViewById(R.id.ilTitle);
        etFullName =  root.findViewById(R.id.etFullName);
        ilFullName =  root.findViewById(R.id.ilFullName);
        etAddress =  root.findViewById(R.id.etAddress);
        ilAddress =  root.findViewById(R.id.ilAddress);
        etPOBox =  root.findViewById(R.id.etPOBox);
        ilPOBox =  root.findViewById(R.id.ilPOBox);
        etCity =  root.findViewById(R.id.etCity);
        ilCity =  root.findViewById(R.id.ilCity);
        etCountry =  root.findViewById(R.id.etCountry);
        ilCountry =  root.findViewById(R.id.ilCountry);
        etCode =  root.findViewById(R.id.etCode);
        ilCode =  root.findViewById(R.id.ilCode);
        etMobile =  root.findViewById(R.id.etMobile);
        ilMobile =  root.findViewById(R.id.ilMobile);
        verify =  root.findViewById(R.id.verify);
        place =  root.findViewById(R.id.place);
        initview();
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //  textView.setText(s);
            }
        });
        return root;
    }

    private void initview() {
        etTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTitle(view, titleItem,"title");
            }
        });
        etCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTitle(view, cityItem, "city");
            }
        });
        etCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTitle(view, countryItem, "country");
            }
        });
        etCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTitle(view, codeItem, "code");
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit() {

        clearErrors();
        if (!ValidationUtils.Companion.isEmptyEditText(etTitle.getText().toString().trim())){
            ilTitle.setError("Please select a title.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etFullName.getText().toString().trim())){
            ilFullName.setError("Please provide a valid name.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etAddress.getText().toString().trim())
                ||!ValidationUtils.Companion.isValidAddress(etAddress.getText().toString().trim())){
            ilAddress.setError("Please provide a valid address.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etPOBox.getText().toString().trim())
                ||!ValidationUtils.Companion.isValidPincode(etPOBox.getText().toString().trim())){
            ilPOBox.setError("Please provide a valid P.O. Box number.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etCity.getText().toString().trim())){
            ilCity.setError("Please select a city.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etCountry.getText().toString().trim())){
            ilCountry.setError("Please select a country.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etCode.getText().toString().trim())){
            ilCode.setError("Please select a code.");
            return;
        }if (!ValidationUtils.Companion.isEmptyEditText(etMobile.getText().toString().trim())
                ||!ValidationUtils.Companion.isValidMobile(etMobile.getText().toString().trim())){
            ilMobile.setError("Please provide a valid contact number.");
            return;
        }
        startActivity(new Intent(requireContext(), Placed.class));
        requireActivity().finish();

    }

    private void clearErrors() {
        ilTitle.setError("");
        ilFullName.setError("");
        ilAddress.setError("");
        ilPOBox.setError("");
        ilCity.setError("");
        ilCountry.setError("");
        ilCode.setError("");
        ilMobile.setError("");
    }

    private void verify() {

        clearErrors();
        if (!ValidationUtils.Companion.isEmptyEditText(etTitle.getText().toString().trim())){
            ilTitle.setError("Please select a title.");
        }if (!ValidationUtils.Companion.isEmptyEditText(etFullName.getText().toString().trim())||etFullName.getText().toString().trim().length()<3){
            ilFullName.setError("Please provide a valid name.");

        }if (!ValidationUtils.Companion.isEmptyEditText(etAddress.getText().toString().trim())
                ||!ValidationUtils.Companion.isValidAddress(etAddress.getText().toString().trim())){
            ilAddress.setError("Please provide a valid address.");

        }if (!ValidationUtils.Companion.isEmptyEditText(etPOBox.getText().toString().trim())
                ||!ValidationUtils.Companion.isValidPincode(etPOBox.getText().toString().trim())){
            ilPOBox.setError("Please provide a valid P.O. Box number.");

        }if (!ValidationUtils.Companion.isEmptyEditText(etCity.getText().toString().trim())){
            ilCity.setError("Please select a city.");

        }if (!ValidationUtils.Companion.isEmptyEditText(etCountry.getText().toString().trim())){
            ilCountry.setError("Please select a country.");

        }if (!ValidationUtils.Companion.isEmptyEditText(etCode.getText().toString().trim())){
            ilCode.setError("Please select a code.");

        }if (!ValidationUtils.Companion.isEmptyEditText(etMobile.getText().toString().trim())
                ||!ValidationUtils.Companion.isValidMobile(etMobile.getText().toString().trim())){
            ilMobile.setError("Please provide a valid contact number.");

        }


    }

    @SuppressLint("SetTextI18n")
    public void showTitle(View view, String[] items, String from) {

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(requireContext());
        bottomSheetDialog.setTitle("Please select a "+from+".");
        LinearLayout linearLayout=new LinearLayout(requireContext());
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(25,25,25,25);
        ProximaNovaText proxima=new ProximaNovaText(requireContext());
        proxima.setTextSize(20.0f);
        proxima.setText("Please select a "+from+".");

        linearLayout.addView(proxima,layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        switch (from){
            case  "title":
                etTitle.setText(items[0]);
                break;
            case  "city":
                etCity.setText(items[0]);
                break;
            case  "country":
                etCountry.setText(items[0]);
                break;
            case  "code":
                etCode.setText(items[0]);
                break;

        }
        for (int i = 0; i < items.length; i++) {
            ProximaNovaText proximaNovaText=new ProximaNovaText(requireContext());
            proximaNovaText.setText(items[i]);
            proximaNovaText.setId(i);
            proximaNovaText.setTextSize(16.0f);
            proximaNovaText.setPadding(5,5,5,5);
            proximaNovaText.setTextColor(Color.parseColor("#000000"));
            linearLayout.addView(proximaNovaText,layoutParams);

            proximaNovaText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (from){
                        case  "title":
                            etTitle.setText(items[view.getId()]);
                            break;
                        case  "city":
                            etCity.setText(items[view.getId()]);
                            break;
                        case  "country":
                            etCountry.setText(items[view.getId()]);
                            break;
                        case  "code":
                            etCode.setText(items[view.getId()]);
                            break;

                    }
                    bottomSheetDialog.cancel();
                }
            });
        }
        bottomSheetDialog.setContentView(linearLayout);

        bottomSheetDialog.show();
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
//        alertDialog.setTitle("Please select a "+from+".");

//        int checkedItem = 0;
//        switch (from){
//            case  "title":
//                etTitle.setText(items[0]);
//                    break;
//            case  "city":
//                etCity.setText(items[0]);
//                break;
//            case  "country":
//                etCountry.setText(items[0]);
//                break;
//            case  "code":
//                etCode.setText(items[0]);
//                break;
//
//        }
//
//        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (from){
//                    case  "title":
//                        etTitle.setText(items[which]);
//                        break;
//                    case  "city":
//                        etCity.setText(items[which]);
//                        break;
//                    case  "country":
//                        etCountry.setText(items[which]);
//                        break;
//                    case  "code":
//                        etCode.setText(items[which]);
//                        break;
//
//                }
//
//            }
//        });
//        AlertDialog alert = alertDialog.create();
//        alert.setCanceledOnTouchOutside(true);
//        alert.show();

    }


}