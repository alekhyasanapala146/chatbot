package com.example.chatbotpoc

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotpoc.data.model.MessageModal

class MessageRVAdapter(private val data: ArrayList<MessageModal>, private  val requireActivity: FragmentActivity) : RecyclerView.Adapter<MessageRVAdapter.ViewHolder>() {


    class ViewHolder (ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val user: TextView = itemView.findViewById(R.id.idUser)
        val bot: TextView = itemView.findViewById(R.id.idBot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_msg, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = data[position]
        if (ItemsViewModel.senderName.equals("bot")){
            holder.bot.visibility = View.VISIBLE
            holder.user.visibility = View.GONE
            holder.bot.text  = ItemsViewModel.message
            holder.bot.setTextColor(requireActivity.resources.getColor(R.color.black))
            holder.bot.gravity = Gravity.LEFT
        }
        else{
            holder.user.visibility = View.VISIBLE
            holder.bot.visibility = View.GONE
            holder.user.text  = ItemsViewModel.message
            holder.user.setTextColor(requireActivity.resources.getColor(R.color.text_blue))
            holder.user.gravity = Gravity.RIGHT
        }
    }

    override fun getItemCount(): Int {
       return data.size
    }
}