package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.utils.hide


class AdviceFragment : Fragment(R.layout.fragment_advice) {
    private lateinit var loading: LottieAnimationView
    private lateinit var llTabrik:LinearLayout
    private lateinit var llOOPS:LinearLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loading = view.findViewById(R.id.loading)
        llTabrik = view.findViewById(R.id.llTabrik)
        llOOPS= view.findViewById(R.id.llOOPS)

        loading.hide()

    }
}