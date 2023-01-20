package com.example.chatbotpoc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.chatbotpoc.data.viewmodel.LoginVM
import com.example.chatbotpoc.databinding.FragmentLoginFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [login_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class login_fragment : Fragment() {

    private var _binding:FragmentLoginFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

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


        val loginVM = ViewModelProvider(this).get(LoginVM::class.java)

        _binding = FragmentLoginFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //val view: View = inflater.inflate(R.layout.fragment_login_fragment, container, false)
        _binding!!.submit.setOnClickListener {

            loginVM.mobileNumber.value = binding.mobileNumber.text?.trim().toString()
            loginVM.password.value = binding.password.text?.trim().toString()
            var valid : Boolean = true

            if (!loginVM.isMobileNumberValid()){
                valid = false
            }
            else if (!loginVM.isPasswordValid()){
                valid = false
            }

            if (valid){
                val action = login_fragmentDirections.actionLoginFragmentToProfileFragment2()
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(activity,"Enter valid data", Toast.LENGTH_SHORT).show()
            }

        }

        return root
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val navigateButton: Button = view.findViewById(R.id.navigate_button)

        navigateButton.setOnClickListener {
            navController.navigate(R.id.action_login_fragment_to_profile_fragment2)
        }
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment login_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            login_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}