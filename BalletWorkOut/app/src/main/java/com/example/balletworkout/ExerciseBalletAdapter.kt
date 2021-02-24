

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.balletworkout.ExerciseModel
import com.example.balletworkout.R
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseBalletAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExerciseBalletAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_exercise_status,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model: ExerciseModel = items[position]

        holder.tvItem.text = model.getId().toString()

        // Updating the background and text color according to the flags what is in the list.
        // A link to set text color programmatically and same way we can set the drawable background also instead of color.
        // https://stackoverflow.com/questions/8472349/how-to-set-text-color-to-a-text-view-programmatically
        if (model.getIsSelected()) {
            holder.tvItem.background =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.circular_thin_color_border
                )
            holder.tvItem.setTextColor(Color.parseColor("#212121")) // Parse the color string, and return the corresponding color-int.
        } else if (model.getIsCompleted()) {
            holder.tvItem.background =
                ContextCompat.getDrawable(context, R.drawable.circular_accent_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.tvItem.background =
                ContextCompat.getDrawable(context, R.drawable.circular_color_gray_background)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val tvItem = view.tvItem!!
    }
}