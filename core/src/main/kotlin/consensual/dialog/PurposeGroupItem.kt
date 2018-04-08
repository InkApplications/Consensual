package consensual.dialog

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import consensual.Purpose
import consensual.core.R
import kotlinx.android.synthetic.main.disclosure_purpose_group.view.*

internal class PurposeGroupItem(private val purpose: Purpose): Item<ViewHolder>(purpose.hashCode().toLong()) {
    override fun getLayout() = R.layout.disclosure_purpose_group
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.disclosure_purpose_group_icon.setImageResource(purpose.icon)
        viewHolder.itemView.disclosure_purpose_group_title.setText(purpose.title)
    }
}
