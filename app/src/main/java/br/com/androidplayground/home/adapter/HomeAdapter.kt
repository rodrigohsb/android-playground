package br.com.androidplayground.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.androidplayground.R
import br.com.androidplayground.persistence.model.Client
import kotlinx.android.synthetic.main.layout_user_item.view.*

/**
 * @rodrigohsb
 */
class HomeAdapter(private var listener: OnItemClickListener)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var repositories: MutableList<Client> = ArrayList()

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
        fun onItemClick(position: Int)
    }

    class ViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bind(user: Client, listener: OnItemClickListener) {
            with(user){
                rootView.user_avatar.text = user.prefix
                rootView.company_name.text = user.company.fantasyName
                rootView.owner_name.text = user.name
                rootView.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
        }
    }

    fun addData(list: List<Client>) {
        repositories.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() =
        repositories.clear()
}