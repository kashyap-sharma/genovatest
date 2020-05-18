package kashyap.genovatest.ui.gn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kashyap.genovatest.R
import kashyap.genovatest.adaps.MultiViewTypeAdapter
import kashyap.genovatest.cusfo.Constants
import kashyap.genovatest.model.HomeModel
import kashyap.genovatest.model.Model
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class GNFragment : Fragment() {
    private var homeViewModel: GNViewModel? = null
    private var recyclerView: RecyclerView? = null
    var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(GNViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gn, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView?.layoutManager = linearLayoutManager
        homeViewModel!!.text.observe(viewLifecycleOwner, Observer { })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch()
    }

    private fun fetch() {
        try {
            val list = ArrayList<Model>()
            val obj = JSONObject(Objects.requireNonNull(loadJSONFromAsset()))
            val status = obj.getString(Constants.STATUS)
            if (status.equals("1", ignoreCase = true)) {
                val jsonArray = obj.getJSONArray("data")
                val myJsonArrayAsList: MutableList<JSONObject> = ArrayList()
                for (i in 0 until jsonArray.length()) {
                    myJsonArrayAsList.add(jsonArray.getJSONObject(i))
                    myJsonArrayAsList.sortWith(Comparator { jsonObjectA: JSONObject, jsonObjectB: JSONObject ->
                        var compare = 0
                        try {
                            val keyA = jsonObjectA.getString("display_priority").toInt()
                            val keyB = jsonObjectB.getString("display_priority").toInt()
                            compare = Integer.compare(keyA, keyB)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                        compare
                    })
                }
                val myJsonArray = JSONArray()
                for (k in myJsonArrayAsList.indices) {
                    myJsonArray.put(myJsonArrayAsList[k])
                }
                for (j in 0 until myJsonArray.length()) {
                    if (myJsonArray.getJSONObject(j).getString("type").equals("banner", ignoreCase = true)) {
                        val jsonArray1 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        val list_items = ArrayList<HomeModel>()
                        for (l in 0 until jsonArray1.length()) {
                            list_items.add(HomeModel(jsonArray1.getJSONObject(l).getString("name"),
                                    jsonArray1.getJSONObject(l).getString("sub_heading"),
                                    jsonArray1.getJSONObject(l).getString("image"),
                                    jsonArray1.getJSONObject(l).getString("product_id")))
                        }
                        list.add(Model(Model.banner, myJsonArray.getJSONObject(j).getString("title"), list_items))
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equals("ListHorizSquare", ignoreCase = true)) {
                        val grid_items = ArrayList<HomeModel>()
                        val jsonArray2 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        for (m in 0 until jsonArray2.length()) {
                            grid_items.add(HomeModel(jsonArray2.getJSONObject(m).getString("image"),
                                    jsonArray2.getJSONObject(m).getString("category_name"),
                                    jsonArray2.getJSONObject(m).getString("category_id")))
                        }
                        list.add(Model(Model.ListHorizSquare, myJsonArray.getJSONObject(j).getString("title"), grid_items))
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equals("banner1", ignoreCase = true)) {
                        val banner_items = ArrayList<HomeModel>()
                        val jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        for (n in 0 until jsonArray3.length()) {
                            banner_items.add(HomeModel(jsonArray3.getJSONObject(n).getString("name"),
                                    jsonArray3.getJSONObject(n).getString("sub_heading"),
                                    jsonArray3.getJSONObject(n).getString("image"),
                                    jsonArray3.getJSONObject(n).getString("product_id"),
                                    jsonArray3.getJSONObject(n).getString("button_text")))
                        }
                        list.add(Model(Model.banner1, myJsonArray.getJSONObject(j).getString("title"), banner_items))
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equals("GridSquare", ignoreCase = true)) {
                        val squbanner_items = ArrayList<HomeModel>()
                        val jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        for (n in 0 until jsonArray3.length()) {
                            squbanner_items.add(HomeModel(jsonArray3.getJSONObject(n).getString("image"),
                                    jsonArray3.getJSONObject(n).getString("category_name"),
                                    jsonArray3.getJSONObject(n).getString("category_id")))
                        }
                        list.add(Model(Model.GridSquare, myJsonArray.getJSONObject(j).getString("title"), squbanner_items))
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equals("ListHorizBig", ignoreCase = true)) {
                        val listHorizBig_items = ArrayList<HomeModel>()
                        val jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        for (q in 0 until jsonArray3.length()) {
                            listHorizBig_items.add(HomeModel(jsonArray3.getJSONObject(q).getString("name"),
                                    jsonArray3.getJSONObject(q).getString("image"),
                                    jsonArray3.getJSONObject(q).getString("product_id"),
                                    jsonArray3.getJSONObject(q).getString("brand"),
                                    jsonArray3.getJSONObject(q).getString("price"),
                                    jsonArray3.getJSONObject(q).getString("off"),
                                    jsonArray3.getJSONObject(q).getString("old_price")))
                        }
                        list.add(Model(Model.ListHorizBig, myJsonArray.getJSONObject(j).getString("title"), listHorizBig_items))
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equals("ListCarous", ignoreCase = true)) {
                        val listHorizBig_items = ArrayList<HomeModel>()
                        val jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        for (q in 0 until jsonArray3.length()) {
                            listHorizBig_items.add(HomeModel(jsonArray3.getJSONObject(q).getString("name"),
                                    jsonArray3.getJSONObject(q).getString("image"),
                                    jsonArray3.getJSONObject(q).getString("product_id"),
                                    jsonArray3.getJSONObject(q).getString("brand"),
                                    jsonArray3.getJSONObject(q).getString("price"),
                                    jsonArray3.getJSONObject(q).getString("off"),
                                    jsonArray3.getJSONObject(q).getString("old_price")))
                        }
                        list.add(Model(Model.ListCarous, myJsonArray.getJSONObject(j).getString("title"), listHorizBig_items))
                    }
                    if (myJsonArray.getJSONObject(j).getString("type").equals("List2itemgrid", ignoreCase = true)) {
                        val listHorizBig_items = ArrayList<HomeModel>()
                        val jsonArray3 = myJsonArray.getJSONObject(j).getJSONArray("items")
                        for (q in 0 until jsonArray3.length()) {
                            listHorizBig_items.add(HomeModel(jsonArray3.getJSONObject(q).getString("name"),
                                    jsonArray3.getJSONObject(q).getString("image"),
                                    jsonArray3.getJSONObject(q).getString("product_id"),
                                    jsonArray3.getJSONObject(q).getString("brand"),
                                    jsonArray3.getJSONObject(q).getString("price"),
                                    jsonArray3.getJSONObject(q).getString("off"),
                                    jsonArray3.getJSONObject(q).getString("old_price")))
                        }
                        list.add(Model(Model.List2itemgrid, myJsonArray.getJSONObject(j).getString("title"), listHorizBig_items))
                    }
                }
                showUI(list)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val `is` = requireActivity().assets.open("home.json")
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

    private fun showUI(list: ArrayList<Model>) {
        val adapter = MultiViewTypeAdapter(list, context, requireActivity().supportFragmentManager)
        recyclerView!!.adapter = adapter
    }
}