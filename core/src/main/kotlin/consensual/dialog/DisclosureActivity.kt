package consensual.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import consensual.*
import consensual.core.R
import kotlinx.android.synthetic.main.disclosure_dialog.*

private const val REPORT_DATA = "disclosure_report_data"

/** Request code for the Disclosure Activity intent. */
const val DISCLOSURE_REQUEST = 62049

/** Result code returned when the user accepts the policy. */
const val DISCLOSURE_ACCEPTED = 21926

/** Result code returned when the user cancels out of the policy. */
const val DISCLOSURE_CANCELED = 9544

/**
 * An Activity that will display the Privacy Policy Disclosure.
 *
 * Use [Activity.showDisclosure] to start this activity properly.
 *
 * This Activity is started for a result code [DISCLOSURE_REQUEST]
 * it returns:
 * [DISCLOSURE_ACCEPTED] when the user presses accept
 * [DISCLOSURE_CANCELED] when the user cancels the dialog
 *
 * This class is left open for extension in order to hook into the
 * accept/cancel events if more complex handling is needed.
 */
open class DisclosureActivity: AppCompatActivity() {
    private var disclosureReport: DisclosureReport? = null
    private val disclosureAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disclosure_dialog)

        disclosure_notices.layoutManager = LinearLayoutManager(this)
        disclosure_notices.isNestedScrollingEnabled = false
        disclosure_notices.isFocusable = false
        disclosure_notices.adapter = disclosureAdapter

        disclosure_accept.setOnClickListener { acceptTerms() }
        disclosure_cancel.setOnClickListener { cancel() }

        disclosureReport = intent.extras?.getSerializable(REPORT_DATA) as? DisclosureReport?
        disclosureAdapter.addAll(disclosureReport?.toItems() ?: emptyList())
    }

    open fun acceptTerms() {
        setResult(DISCLOSURE_ACCEPTED)
        finish()
    }

    open fun cancel() {
        setResult(DISCLOSURE_CANCELED)
        finish()
    }
}

/**
 * Start a [DisclosureActivity] for a Report.
 *
 * @param report The Report to be displayed to the user.
 */
fun Activity.showDisclosure(report: DisclosureReport) {
    Intent(this, DisclosureActivity::class.java)
        .apply { putExtra(REPORT_DATA, report) }
        .also { startActivityForResult(it, DISCLOSURE_REQUEST) }
}
