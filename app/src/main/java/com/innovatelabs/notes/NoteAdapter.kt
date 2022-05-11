package com.innovatelabs.notes


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val listener:onNoteClicked): RecyclerView.Adapter<noteViewHolder>() {
     private val items = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        val view  = LayoutInflater.from(context).inflate(R.layout.items_view,parent,false)
        val viewHolder = noteViewHolder(view)
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val currentItem = items[position]
        holder.notes.text = currentItem.text
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(updateNotes: List<Note>) {
        items.clear()
        items.addAll(updateNotes)
        notifyDataSetChanged()
    }
}
class noteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val notes : TextView = itemView.findViewById<TextView>(R.id.Notes)
    val deleteButton : ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)
}

interface onNoteClicked {
    fun onItemClicked(item : Note)
}