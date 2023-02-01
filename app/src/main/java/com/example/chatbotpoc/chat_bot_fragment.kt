package com.example.chatbotpoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbotpoc.data.model.MessageModal
import com.example.chatbotpoc.data.model.MsgModal
import com.example.chatbotpoc.data.repository.DataRepository
import com.example.chatbotpoc.data.repository.DataRepositoryImpl
import com.example.chatbotpoc.data.viewmodel.ChatBotVM
import com.example.chatbotpoc.databinding.FragmentChatBotFragmentBinding
import com.example.chatbotpoc.retrofit.APIEndPoint
import com.example.chatbotpoc.retrofit.RetrofitHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [chat_bot_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class chat_bot_fragment : Fragment() {

    private var _binding: FragmentChatBotFragmentBinding? = null
    private val binding get() = _binding!!
     //lateinit var viewModel : ChatBotVM
    val viewModel:ChatBotVM by viewModels()

    // TODO: Rename and change types of parameters
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
        _binding = FragmentChatBotFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var data = ArrayList<MessageModal>()
        binding.idRVChats.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = MessageRVAdapter(data,requireActivity())
        binding.idRVChats.adapter = adapter

        binding.idRVChats
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProvider(this)[ChatBotVM::class.java]

        binding.sendButton.setOnClickListener {
            viewModel.userMsg = binding.idEdtMessage.text.toString()

            if (!viewModel.userMsgValid()){

                binding.idEdtMessage.setText("")
                viewModel.messagesList.add(MessageModal(viewModel.userMsg,"user"))
                val adapter = MessageRVAdapter(viewModel.messagesList,requireActivity())
                binding.idRVChats.adapter = adapter
               // getResponse(viewModel.userMsg)
                getBotResponse(viewModel.userMsg)
            }
            else{
                Toast.makeText(requireActivity(),"Please enter valid message ",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel._messagesList.observe(viewLifecycleOwner, Observer { list->
            val adapter = MessageRVAdapter(viewModel.messagesList,requireActivity())
            binding.idRVChats.adapter = adapter
        })

    }

    private fun getBotResponse(userMsg: String) {
       // val quotesApi = RetrofitHelper.getInstance().create(APIEndPoint::class.java)
        url = "http://api.brainshop.ai/get?bid=172027&key=znVdhqRj7BhpQji0&uid=[uid]&msg=$userMsg"
        GlobalScope.launch {
           /* val result = quotesApi.getMessages(url)
            result?.cnt*/
            val res = DataRepositoryImpl(RetrofitHelper.apiService).getMessage(url)
            res?.cnt

            viewModel.messagesList.add(MessageModal(res?.cnt,"bot"))
            viewModel._messagesList.postValue(viewModel.messagesList)
        }
    }

   /* private fun getResponse(userMsg: String) {

        val url =
            "http://api.brainshop.ai/get?bid=172027&key=znVdhqRj7BhpQji0&uid=[uid]&msg=$userMsg"
        val BASE_URL = "http://api.brainshop.ai/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val retrofitAPI: APIEndPoint = retrofit.create(APIEndPoint::class.java)
        val call: Call<MsgModal?>? = retrofitAPI.getMessages(url)
        call?.enqueue(object : Callback<MsgModal?> {
            override fun onResponse(call: Call<MsgModal?>, response: Response<MsgModal?>) {
                if (response.isSuccessful) {
                    viewModel.messagesList.add(MessageModal(response.body()?.cnt,"bot"))
                    viewModel._messagesList.value = viewModel.messagesList
                }
            }

            override fun onFailure(call: Call<MsgModal?>, t: Throwable) {
                Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    companion object {

        lateinit var url:String

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            chat_bot_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}