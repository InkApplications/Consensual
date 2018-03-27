package consensual

import android.support.annotation.StringRes

/**
 * A collection of all of the Personal information the user is disclosing
 * to various services within your application.
 *
 * This is the primary object to represent your Privacy Policy to the user.
 *
 * @param disclosures A set of information being collected about the user
 * @param message A message to display when showing the user the report.
 * @param title A title to display above the report.
 */
data class DisclosureReport(
    val disclosures: Set<CollectionPoint>,
    @StringRes val message: Int? = null,
    @StringRes val title: Int? = null
) {
    constructor(
        disclosures: CollectionPoint,
        @StringRes message: Int? = null,
        @StringRes title: Int? = null
    ): this(setOf(disclosures), message, title)

}
