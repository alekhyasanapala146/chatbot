package com.example.chatbotpoc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatbotpoc.data.viewmodel.LoginVM
import com.example.chatbotpoc.databinding.FragmentLoginFragmentBinding
import com.example.chatbotpoc.db.AppDb

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
    private val noteDatabase by lazy { AppDb.getDatabase(requireContext()).userDao() }
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

            loginVM.mobileNumber = getMobileNumber(binding.mobileNumber.text?.trim().toString())
            loginVM.password = binding.password.text?.trim().toString()
            var valid : Boolean = true

            if (loginVM.validUserInput(loginVM.mobileNumber,loginVM.password)){
                loginVM.insertData(noteDatabase)
                val action = login_fragmentDirections.actionLoginFragmentToProfileFragment2()
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(activity,"Enter valid data", Toast.LENGTH_SHORT).show()
            }

          /*  if (!loginVM.isMobileNumberValid()){
                valid = false
            }
            else if (!loginVM.isPasswordValid()){
                valid = false
            }

            if (valid){
                loginVM.insertData(noteDatabase)
                val action = login_fragmentDirections.actionLoginFragmentToProfileFragment2()
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(activity,"Enter valid data", Toast.LENGTH_SHORT).show()
            }*/

        }

        return root
    }

    fun getMobileNumber(number: String): String {
        return number
    }

    fun sum(number: Int,number2 : Int): Int {
        return number+number2
    }


    fun validUserInput(
        mobileNumber : String,
        password : String
    ) : Boolean {
        // write conditions along with their return statement
        // if username / password / confirm password are empty return false
        if (mobileNumber.isEmpty() || password.isEmpty()){
            return false
        }
        // if digit count of the password is less than 2 return false
        if (password.count { it.isDigit() } < 2){
            return false
        }
        return true
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