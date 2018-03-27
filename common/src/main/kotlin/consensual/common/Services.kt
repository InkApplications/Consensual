package consensual.common

import consensual.Service
import consensual.common.Purposes.ANALYTICS
import consensual.common.Purposes.CRASH_REPORT
import consensual.common.Purposes.PAYMENT
import consensual.common.R

object Services {
    val GOOGLE_ANALYTICS = Service(ANALYTICS, R.string.service_google_analytics)
    val FIREBASE_ANALYTICS = Service(ANALYTICS, R.string.service_firebase_analytics)
    val CRASHLYTICS = Service(CRASH_REPORT, R.string.service_crashlytics)
    val STRIPE = Service(PAYMENT, R.string.service_stripe)
}
