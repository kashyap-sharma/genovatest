package kashyap.genovatest.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kashyap.genovatest.CheckoutA
import kashyap.genovatest.R
import kashyap.genovatest.Static_Catelog
import kashyap.genovatest.adaps.CartAdap
import kashyap.genovatest.adaps.EmptycartAdap
import kashyap.genovatest.cusfo.Constants
import kashyap.genovatest.cusfo.ProximaNovaButton
import kashyap.genovatest.model.HomeModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CartFragment : Fragment() {
    var linearLayoutManager: LinearLayoutManager? = null
    var linearLayoutManager1: LinearLayoutManager? = null
    private var mCartViewModel: CartViewModel? = null
    private var proceed: ProximaNovaButton? = null
    var recyclerView: RecyclerView? = null
    var recyclerView2: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        linearLayoutManager1 = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView2 = root.findViewById(R.id.recyclerView2)
        proceed = root.findViewById(R.id.proceed)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView2?.layoutManager = linearLayoutManager1
        if (Static_Catelog.getStringProperty(requireContext(), "added").equals("no", ignoreCase = true)) {
            root.findViewById<View>(R.id.cartempty).visibility = View.VISIBLE
            root.findViewById<View>(R.id.cartvisible).visibility = View.GONE
        } else {
            root.findViewById<View>(R.id.cartempty).visibility = View.GONE
            root.findViewById<View>(R.id.cartvisible).visibility = View.VISIBLE
        }
        mCartViewModel!!.text.observe(viewLifecycleOwner, Observer {
            //   textView.setText(s);
        })
        proceed?.setOnClickListener(View.OnClickListener { startActivity(Intent(requireContext(), CheckoutA::class.java)) })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch()
        fetchempty()
    }

    private fun loadJSONFromAsset(jsons: String): String? {
        var json: String? = null
        json = try {
            val `is` = requireActivity().assets.open(jsons)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun fetch() {
        try {
            val obj = JSONObject(Objects.requireNonNull(loadJSONFromAsset("filled_cart.json")))
            val status = obj.getString(Constants.STATUS)
            if (status.equals("1", ignoreCase = true)) {
                val jsonArray = obj.getJSONArray("data")
                for (j in 0 until jsonArray.length()) {
                    val jsonArray1 = jsonArray.getJSONObject(j).getJSONArray("items")
                    val list_items = ArrayList<HomeModel>()
                    for (l in 0 until jsonArray1.length()) {
                        //HomeModel(String name, String image, String product_id, String brand, String price, String off, String old_price, String weight, String quantity)
                        list_items.add(HomeModel(jsonArray1.getJSONObject(l).getString("name"),
                                jsonArray1.getJSONObject(l).getString("image"),
                                jsonArray1.getJSONObject(l).getString("product_id"),
                                jsonArray1.getJSONObject(l).getString("brand"),
                                jsonArray1.getJSONObject(l).getString("price"),
                                jsonArray1.getJSONObject(l).getString("off"),
                                jsonArray1.getJSONObject(l).getString("old_price"),
                                jsonArray1.getJSONObject(l).getString("weight"),
                                jsonArray1.getJSONObject(l).getString("quantity")))
                    }
                    showUI(list_items, "fill")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun fetchempty() {
        try {
            val obj = JSONObject(Objects.requireNonNull(loadJSONFromAsset("empty_cart.json")))
            val status = obj.getString(Constants.STATUS)
            if (status.equals("1", ignoreCase = true)) {
                val jsonArray = obj.getJSONArray("data")
                for (j in 0 until jsonArray.length()) {
                    val jsonArray1 = jsonArray.getJSONObject(j).getJSONArray("items")
                    val list_items = ArrayList<HomeModel>()
                    for (l in 0 until jsonArray1.length()) {
                        list_items.add(HomeModel(jsonArray1.getJSONObject(l).getString("name"),
                                jsonArray1.getJSONObject(l).getString("image"),
                                jsonArray1.getJSONObject(l).getString("product_id"),
                                jsonArray1.getJSONObject(l).getString("brand"),
                                jsonArray1.getJSONObject(l).getString("price"),
                                jsonArray1.getJSONObject(l).getString("off"),
                                jsonArray1.getJSONObject(l).getString("old_price")))
                    }
                    showUI(list_items, "empty")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun showUI(list_items: ArrayList<HomeModel>, type: String) {
        if (type.equals("empty", ignoreCase = true)) {
            val adapter = EmptycartAdap(context!!, list_items, requireActivity().supportFragmentManager)
            recyclerView!!.adapter = adapter
        } else {
            val adapter = CartAdap(requireContext(), list_items, requireActivity().supportFragmentManager)
            recyclerView2!!.adapter = adapter
        }
    }
}