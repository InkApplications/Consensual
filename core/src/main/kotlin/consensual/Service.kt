package consensual

import android.support.annotation.StringRes
import java.io.Serializable

/**
 * An individual service that is collecting user information.
 *
 * @param type A category this service primarily falls into.
 * @param name User-readable title to call this service.
 */
data class Service(
    val type: Purpose,
    @StringRes val name: Int
): Serializable
