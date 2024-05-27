import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val column = position % 2 // Визначення стовпця, у якому знаходиться елемент

        // Додавання відступів до елементів
        if (column == 0) {
            outRect.right = space / 2
        } else {
            outRect.left = space / 2
        }
        outRect.top = space
    }
}
