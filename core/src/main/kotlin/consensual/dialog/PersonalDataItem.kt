package consensual.dialog

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import consensual.PersonalData
import consensual.core.R
import kotlinx.android.synthetic.main.disclosure_personal_data.view.*

internal class PersonalDataItem(private val data: PersonalData): Item<ViewHolder>(data.hashCode().toLong()) {
    override fun getLayout() = R.layout.disclosure_personal_data
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.disclosure_personal_data_description.setText(data.name)
    }
}
