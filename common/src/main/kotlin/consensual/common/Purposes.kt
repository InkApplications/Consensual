package consensual.common

import consensual.Purpose
import consensual.common.R

object Purposes {
    val ANALYTICS = Purpose(R.drawable.ic_assessment_black_40dp, R.string.service_type_analytics)
    val PAYMENT = Purpose(R.drawable.ic_assessment_black_40dp, R.string.service_type_payment)
    val CRASH_REPORT = Purpose(R.drawable.ic_developer_mode_black_40dp, R.string.service_type_crash_report)
}
