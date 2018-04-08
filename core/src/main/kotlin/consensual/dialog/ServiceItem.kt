package consensual.dialog

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import consensual.Service
import consensual.core.R
import kotlinx.android.synthetic.main.disclosure_service.view.*

internal class ServiceItem(private val service: Service): Item<ViewHolder>(service.hashCode().toLong()) {
    override fun getLayout() = R.layout.disclosure_service
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.disclosure_service_name.setText(service.name)
    }
}
