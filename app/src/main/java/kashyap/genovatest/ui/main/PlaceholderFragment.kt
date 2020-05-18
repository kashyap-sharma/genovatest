package kashyap.genovatest.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import kashyap.genovatest.Placed
import kashyap.genovatest.R
import kashyap.genovatest.cusfo.ProximaNovaButton
import kashyap.genovatest.cusfo.ProximaNovaEditText
import kashyap.genovatest.cusfo.ProximaNovaInputLayout
import kashyap.genovatest.cusfo.ProximaNovaText
import kashyap.genovatest.cusfo.ValidationUtils.Companion.isEmptyEditText
import kashyap.genovatest.cusfo.ValidationUtils.Companion.isValidAddress
import kashyap.genovatest.cusfo.ValidationUtils.Companion.isValidMobile
import kashyap.genovatest.cusfo.ValidationUtils.Companion.isValidPincode

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {
    var titleItem = arrayOf("Mr", "Mrs", "Ms")
    var cityItem = arrayOf("Delhi", "Bengaluru", "Mumbai", "Dubai", "Abu Dhabi", "Sharjah", "Al Ain")
    var countryItem = arrayOf("India", "Saudi Arabia", "UAE")
    var codeItem = arrayOf("+91", "+971", "+972")
    var etTitle: ProximaNovaEditText? = null
    var ilTitle: ProximaNovaInputLayout? = null
    var etFullName: ProximaNovaEditText? = null
    var ilFullName: ProximaNovaInputLayout? = null
    var etAddress: ProximaNovaEditText? = null
    var ilAddress: ProximaNovaInputLayout? = null
    var etPOBox: ProximaNovaEditText? = null
    var ilPOBox: ProximaNovaInputLayout? = null
    var etCity: ProximaNovaEditText? = null
    var ilCity: ProximaNovaInputLayout? = null
    var etCountry: ProximaNovaEditText? = null
    var ilCountry: ProximaNovaInputLayout? = null
    var etCode: ProximaNovaEditText? = null
    var ilCode: ProximaNovaInputLayout? = null
    var etMobile: ProximaNovaEditText? = null
    var ilMobile: ProximaNovaInputLayout? = null
    var verify: ProximaNovaButton? = null
    var place: ProximaNovaButton? = null
    private var pageViewModel: PageViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        var index = 1
        if (arguments != null) {
            index = arguments!!.getInt(ARG_SECTION_NUMBER)
        }
        pageViewModel!!.setIndex(index)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        // final TextView textView = root.findViewById(R.id.section_label);
        etTitle = root.findViewById(R.id.etTitle)
        ilTitle = root.findViewById(R.id.ilTitle)
        etFullName = root.findViewById(R.id.etFullName)
        ilFullName = root.findViewById(R.id.ilFullName)
        etAddress = root.findViewById(R.id.etAddress)
        ilAddress = root.findViewById(R.id.ilAddress)
        etPOBox = root.findViewById(R.id.etPOBox)
        ilPOBox = root.findViewById(R.id.ilPOBox)
        etCity = root.findViewById(R.id.etCity)
        ilCity = root.findViewById(R.id.ilCity)
        etCountry = root.findViewById(R.id.etCountry)
        ilCountry = root.findViewById(R.id.ilCountry)
        etCode = root.findViewById(R.id.etCode)
        ilCode = root.findViewById(R.id.ilCode)
        etMobile = root.findViewById(R.id.etMobile)
        ilMobile = root.findViewById(R.id.ilMobile)
        verify = root.findViewById(R.id.verify)
        place = root.findViewById(R.id.place)
        initview()
        pageViewModel!!.text.observe(viewLifecycleOwner, Observer {
            //  textView.setText(s);
        })
        return root
    }

    private fun initview() {
        etTitle!!.setOnClickListener { view -> showTitle(view, titleItem, "title") }
        etCity!!.setOnClickListener { view -> showTitle(view, cityItem, "city") }
        etCountry!!.setOnClickListener { view -> showTitle(view, countryItem, "country") }
        etCode!!.setOnClickListener { view -> showTitle(view, codeItem, "code") }
        verify!!.setOnClickListener { verify() }
        place!!.setOnClickListener { submit() }
    }

    private fun submit() {
        clearErrors()
        if (!isEmptyEditText(etTitle!!.text.toString().trim { it <= ' ' })) {
            ilTitle!!.error = "Please select a title."
            return
        }
        if (!isEmptyEditText(etFullName!!.text.toString().trim { it <= ' ' })) {
            ilFullName!!.error = "Please provide a valid name."
            return
        }
        if (!isEmptyEditText(etAddress!!.text.toString().trim { it <= ' ' })
                || !isValidAddress(etAddress!!.text.toString().trim { it <= ' ' })) {
            ilAddress!!.error = "Please provide a valid address."
            return
        }
        if (!isEmptyEditText(etPOBox!!.text.toString().trim { it <= ' ' })
                || !isValidPincode(etPOBox!!.text.toString().trim { it <= ' ' })) {
            ilPOBox!!.error = "Please provide a valid P.O. Box number."
            return
        }
        if (!isEmptyEditText(etCity!!.text.toString().trim { it <= ' ' })) {
            ilCity!!.error = "Please select a city."
            return
        }
        if (!isEmptyEditText(etCountry!!.text.toString().trim { it <= ' ' })) {
            ilCountry!!.error = "Please select a country."
            return
        }
        if (!isEmptyEditText(etCode!!.text.toString().trim { it <= ' ' })) {
            ilCode!!.error = "Please select a code."
            return
        }
        if (!isEmptyEditText(etMobile!!.text.toString().trim { it <= ' ' })
                || !isValidMobile(etMobile!!.text.toString().trim { it <= ' ' })) {
            ilMobile!!.error = "Please provide a valid contact number."
            return
        }
        startActivity(Intent(requireContext(), Placed::class.java))
        requireActivity().finish()
    }

    private fun clearErrors() {
        ilTitle!!.error = ""
        ilFullName!!.error = ""
        ilAddress!!.error = ""
        ilPOBox!!.error = ""
        ilCity!!.error = ""
        ilCountry!!.error = ""
        ilCode!!.error = ""
        ilMobile!!.error = ""
    }

    private fun verify() {
        clearErrors()
        if (!isEmptyEditText(etTitle!!.text.toString().trim { it <= ' ' })) {
            ilTitle!!.error = "Please select a title."
        }
        if (!isEmptyEditText(etFullName!!.text.toString().trim { it <= ' ' }) || etFullName!!.text.toString().trim { it <= ' ' }.length < 3) {
            ilFullName!!.error = "Please provide a valid name."
        }
        if (!isEmptyEditText(etAddress!!.text.toString().trim { it <= ' ' })
                || !isValidAddress(etAddress!!.text.toString().trim { it <= ' ' })) {
            ilAddress!!.error = "Please provide a valid address."
        }
        if (!isEmptyEditText(etPOBox!!.text.toString().trim { it <= ' ' })
                || !isValidPincode(etPOBox!!.text.toString().trim { it <= ' ' })) {
            ilPOBox!!.error = "Please provide a valid P.O. Box number."
        }
        if (!isEmptyEditText(etCity!!.text.toString().trim { it <= ' ' })) {
            ilCity!!.error = "Please select a city."
        }
        if (!isEmptyEditText(etCountry!!.text.toString().trim { it <= ' ' })) {
            ilCountry!!.error = "Please select a country."
        }
        if (!isEmptyEditText(etCode!!.text.toString().trim { it <= ' ' })) {
            ilCode!!.error = "Please select a code."
        }
        if (!isEmptyEditText(etMobile!!.text.toString().trim { it <= ' ' })
                || !isValidMobile(etMobile!!.text.toString().trim { it <= ' ' })) {
            ilMobile!!.error = "Please provide a valid contact number."
        }
    }

    @SuppressLint("SetTextI18n")
    fun showTitle(view: View?, items: Array<String>, from: String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setTitle("Please select a $from.")
        val linearLayout = LinearLayout(requireContext())
        linearLayout.gravity = Gravity.CENTER_HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(25, 25, 25, 25)
        val proxima = ProximaNovaText(requireContext())
        proxima.textSize = 20.0f
        proxima.text = "Please select a $from."
        linearLayout.addView(proxima, layoutParams)
        linearLayout.orientation = LinearLayout.VERTICAL
        when (from) {
            "title" -> etTitle!!.setText(items[0])
            "city" -> etCity!!.setText(items[0])
            "country" -> etCountry!!.setText(items[0])
            "code" -> etCode!!.setText(items[0])
        }
        for (i in items.indices) {
            val proximaNovaText = ProximaNovaText(requireContext())
            proximaNovaText.text = items[i]
            proximaNovaText.id = i
            proximaNovaText.textSize = 16.0f
            proximaNovaText.setPadding(5, 5, 5, 5)
            proximaNovaText.setTextColor(Color.parseColor("#000000"))
            linearLayout.addView(proximaNovaText, layoutParams)
            proximaNovaText.setOnClickListener { view ->
                when (from) {
                    "title" -> etTitle!!.setText(items[view.id])
                    "city" -> etCity!!.setText(items[view.id])
                    "country" -> etCountry!!.setText(items[view.id])
                    "code" -> etCode!!.setText(items[view.id])
                }
                bottomSheetDialog.cancel()
            }
        }
        bottomSheetDialog.setContentView(linearLayout)
        bottomSheetDialog.show()
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

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(index: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }
}