package consensual.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import consensual.*
import consensual.core.R
import kotlinx.android.synthetic.main.disclosure_dialog.*

private const val LOG_TAG = "Consensual"
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
    private val disclosureAdapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val report = intent.extras?.getSerializable(REPORT_DATA) as? DisclosureReport?

        if (report == null) {
            Log.e(LOG_TAG, "Failed to read report data. Aborting Activity.")
            finish()
            return
        }

        if (!shouldShowDisclosure(report)) {
            Log.w(LOG_TAG, "Disclosure already accepted. Call `Activity.shouldShowDisclosure` to prevent launching this activity unnecessarily.")
            setResult(DISCLOSURE_ACCEPTED)
            finish()
            return
        }

        setContentView(R.layout.disclosure_dialog)

        disclosure_notices.layoutManager = LinearLayoutManager(this)
        disclosure_notices.isNestedScrollingEnabled = false
        disclosure_notices.isFocusable = false
        disclosure_notices.adapter = disclosureAdapter

        disclosure_accept.setOnClickListener { acceptTerms(report) }
        disclosure_cancel.setOnClickListener { cancel(report) }

        disclosureAdapter.addAll(report.toItems())
    }

    open fun acceptTerms(report: DisclosureReport) {
        setDisclosureAccepted(report)
        Log.i(LOG_TAG, "Privacy Disclosure accepted by user.")
        setResult(DISCLOSURE_ACCEPTED)
        finish()
    }

    open fun cancel(report: DisclosureReport) {
        Log.i(LOG_TAG, "Privacy Disclosure cancelled by user.")
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
