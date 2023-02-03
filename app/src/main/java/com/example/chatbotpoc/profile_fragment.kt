package com.example.chatbotpoc

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatbotpoc.data.viewmodel.UserVM
import com.example.chatbotpoc.databinding.FragmentProfileFragmentBinding
import com.example.chatbotpoc.db.AppDb

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profile_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class profile_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val noteDatabase by lazy { AppDb.getDatabase(requireContext()).userDao() }
    private var _binding: FragmentProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        _binding!!.chatButton.setOnClickListener {
            val action = profile_fragmentDirections.actionProfileFragmentToChatBotFragment()
            findNavController().navigate(action)
        }
        return root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
           val viewModel = ViewModelProvider(it!!)[UserVM::class.java]
            viewModel.geData(noteDatabase)
            viewModel.mobileNumber.observe(viewLifecycleOwner, Observer { mobileNum->
                binding.nameTv.text = getString(R.string.hello)+" "+mobileNum
                Log.d("Number is ",mobileNum)

            })
        }

    }

    fun onBackPressed() {
        //handle back press event
    }

    override fun onResume() {
        super.onResume()
        this.requireView().isFocusableInTouchMode = true
        this.requireView().requestFocus()
        this.requireView().setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
    }

    companion object {

       // val noteDatabase by lazy { AppDb.getDatabase(requireContext()).userDao() }

       /* // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}