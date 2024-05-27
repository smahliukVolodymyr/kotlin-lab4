import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4bd.R

data class ButtonItem(val text: String, val backgroundColor: Int, val textColor: Int)

class ButtonAdapter(private val buttonTitles: List<ButtonItem>) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.button)

        fun bind(buttonItem: ButtonItem) {
            button.text = buttonItem.text
            button.setBackgroundColor(buttonItem.backgroundColor)
            button.setTextColor(buttonItem.textColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_item, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(buttonTitles[position])
    }

    override fun getItemCount(): Int {
        return buttonTitles.size
    }
}

