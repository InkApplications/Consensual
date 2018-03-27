package consensual.common

import consensual.CollectionPoint
import consensual.common.PersonalDataItems.DEMOGRAPHICS
import consensual.common.PersonalDataItems.FINANCIAL
import consensual.common.PersonalDataItems.IDENTITY
import consensual.common.PersonalDataItems.USAGE
import consensual.common.Services.CRASHLYTICS
import consensual.common.Services.FIREBASE_ANALYTICS
import consensual.common.Services.GOOGLE_ANALYTICS
import consensual.common.Services.STRIPE

object CollectionPoints {
    val FIREBASE_ANALYTICS_MINIMUM = CollectionPoint(
        service = FIREBASE_ANALYTICS,
        data = IDENTITY + DEMOGRAPHICS + USAGE
    )
    val GOOGLE_ANALYTICS_MINIMUM = CollectionPoint(
        service = GOOGLE_ANALYTICS,
        data = IDENTITY + USAGE
    )
    val GOOGLE_ANALYTICS_DEMOGRAPHICS = CollectionPoint(
        service = GOOGLE_ANALYTICS,
        data = IDENTITY + USAGE + DEMOGRAPHICS
    )
    val CRASHLYTICS_MINIMUM = CollectionPoint(
        service = CRASHLYTICS,
        data = IDENTITY + USAGE
    )
    val STRIPE_MINIMUM = CollectionPoint(
        service = STRIPE,
        data = IDENTITY + FINANCIAL
    )
}
