package br.com.androidplayground.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.androidplayground.R
import br.com.androidplayground.home.entryModel.ContactEntryModel
import kotlinx.android.synthetic.main.layout_user_item.view.*

/**
 * @rodrigohsb
 */
class HomeAdapter(private var listener: OnItemClickListener)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var repositories: MutableList<ContactEntryModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val root = layoutInflater.inflate(R.layout.layout_user_item, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val repository = repositories[position]
        holder.bind(repository, listener)
    }

    override fun getItemCount(): Int =
            repositories.size

    interface OnItemClickListener {
        fun onItemClick(prefix: String)
    }

    class ViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bind(contact: ContactEntryModel, listener: OnItemClickListener) {
            with(contact){
                rootView.user_avatar.text = contact.prefix
                rootView.company_name.text = contact.companyName
                rootView.owner_name.text = contact.name
                rootView.setOnClickListener({ _ -> listener.onItemClick(prefix) })
            }
        }
    }

    fun addData(list: List<ContactEntryModel>) {
        repositories.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() =
        repositories.clear()
}