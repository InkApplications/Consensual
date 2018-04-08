package consensual

import android.support.annotation.StringRes
import java.io.Serializable

/**
 * An individual piece of personal data that is being collected.
 *
 * @param name The user readable name to call this piece of information.
 *        This should be concise and well-understood.
 */
data class PersonalData(@StringRes val name: Int): Serializable {
    operator fun plus(data: PersonalData): Set<PersonalData> = setOf(this, data)
}
