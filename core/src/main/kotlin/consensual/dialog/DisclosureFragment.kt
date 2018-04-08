package consensual.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import consensual.*
import consensual.core.R
import kotlinx.android.synthetic.main.disclosure_dialog.*

private const val REPORT_DATA = "disclosure_fragment_report_data"

@SuppressLint("ValidFragment")
class DisclosureFragment(): DialogFragment() {
    private var disclosureReport: DisclosureReport? = null
    private val disclosureAdapter = GroupAdapter<ViewHolder>()

    constructor(disclosureReport: DisclosureReport): this() {
        this.disclosureReport = disclosureReport
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.disclosure_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disclosure_dialog_notices.layoutManager = LinearLayoutManager(context)
        disclosure_dialog_notices.isNestedScrollingEnabled = false
        disclosure_dialog_notices.isFocusable = false
        disclosure_dialog_notices.adapter = disclosureAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(REPORT_DATA, disclosureReport)
        super.onSaveInstanceState(outState)
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState ?: return
        disclosureReport = savedInstanceState.getSerializable(REPORT_DATA) as DisclosureReport?
    }

    override fun onStart() {
        super.onStart()
        disclosureAdapter.addAll(disclosureReport?.toItems() ?: emptyList())
    }
}
