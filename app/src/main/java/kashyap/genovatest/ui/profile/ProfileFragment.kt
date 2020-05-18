package kashyap.genovatest.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kashyap.genovatest.R

class ProfileFragment : Fragment() {
    private var dashboardViewModel: ProfileViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        //   final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel!!.text.observe(viewLifecycleOwner, Observer {
            // textView.setText(s);
        })
        return root
    }
}