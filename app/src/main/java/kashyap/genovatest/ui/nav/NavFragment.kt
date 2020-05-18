package kashyap.genovatest.ui.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kashyap.genovatest.R

class NavFragment : Fragment() {
    private var dashboardViewModel: NavViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel = ViewModelProviders.of(this).get(NavViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_seacrh, container, false)
        val textView = root.findViewById<TextView>(R.id.text_dashboard)
        dashboardViewModel!!.text.observe(viewLifecycleOwner, Observer { s -> textView.text = s })
        return root
    }
}