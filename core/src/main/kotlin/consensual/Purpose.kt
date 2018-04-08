package consensual

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import java.io.Serializable

/**
 * A categorical reason for a service to be collecting information
 *
 * @param icon A 40dp image to represent the group
 * @param title User-readable string to call the collection of services
 */
data class Purpose(
    @DrawableRes val icon: Int,
    @StringRes val title: Int
): Serializable
