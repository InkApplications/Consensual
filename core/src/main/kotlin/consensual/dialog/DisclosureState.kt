package consensual.dialog

import android.content.Context
import android.content.SharedPreferences
import consensual.DisclosureReport

private const val PREFERENCES = "consensual"
private const val PREFERENCES_STATE_KEY = "disclosure_accepted_state"
private const val STATE_NONE = -1

/**
 * Record the disclosure report as Accepted.
 *
 * @receiver The Context to be used for saving/retrieving state.
 * @param report The report to be marked as accepted by the user.
 */
internal fun Context.setDisclosureAccepted(report: DisclosureReport) {
    getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        .run(SharedPreferences::edit)
        .run { putInt(PREFERENCES_STATE_KEY, report.hashCode()) }
        .run(SharedPreferences.Editor::apply)
}

/**
 * Check if a given report should be displayed to the user for acceptance.
 *
 * This should be invoked *before* starting the Disclosure Activity.
 * If the report changes, this will flag the disclosure to be displayed again.
 *
 * @receiver The Context to be used for saving/retrieving state.
 * @param report The Privacy Policy report to check if has been accepted by the user.
 * @return Whether the user should be displayed a disclosure message about the privacy report.
 */
fun Context.shouldShowDisclosure(report: DisclosureReport): Boolean {
    val currentState = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        .run { getInt(PREFERENCES_STATE_KEY, STATE_NONE) }

    return report.hashCode() != currentState
}
