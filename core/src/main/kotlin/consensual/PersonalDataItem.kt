package consensual

import android.support.annotation.StringRes

/**
 * An individual piece of personal data that is being collected.
 *
 * @param name The user readable name to call this piece of information.
 *        This should be concise and well-understood.
 */
data class PersonalDataItem(@StringRes val name: Int) {
    operator fun plus(data: PersonalDataItem): Set<PersonalDataItem> = setOf(this, data)
}
