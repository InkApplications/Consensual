package consensual.dialog

import android.content.Context
import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import consensual.*
import org.junit.Test

import org.junit.Assert.*

class DisclosureStateTest {
    @Test fun unchangedDisclosure() {
        val report = DisclosureReport(
            disclosures = CollectionPoint(
                service = Service(
                    type = Purpose(1, 2),
                    name = 3
                ),
                data = PersonalData(4)
            )
        )

        val fakePreferences = mock<SharedPreferences> {
            on { getInt("disclosure_accepted_state", -1) } doReturn report.hashCode()
        }
        val fakeContext = mock<Context> {
            on { getSharedPreferences(any(), any()) } doReturn fakePreferences
        }
        val result = fakeContext.shouldShowDisclosure(report)

        assertFalse("Unchanged report should not be shown", result)
    }

    @Test fun changedDisclosure() {
        val report = DisclosureReport(
            disclosures = CollectionPoint(
                service = Service(
                    type = Purpose(1, 2),
                    name = 3
                ),
                data = PersonalData(4)
            )
        )

        val fakePreferences = mock<SharedPreferences> {
            on { getInt("disclosure_accepted_state", -1) } doReturn 123456
        }
        val fakeContext = mock<Context> {
            on { getSharedPreferences(any(), any()) } doReturn fakePreferences
        }
        val result = fakeContext.shouldShowDisclosure(report)

        assertTrue("Changed report should be shown", result)
    }

    @Test fun defaultState() {
        val report = DisclosureReport(
            disclosures = CollectionPoint(
                service = Service(
                    type = Purpose(1, 2),
                    name = 3
                ),
                data = PersonalData(4)
            )
        )

        val fakePreferences = mock<SharedPreferences> {
            on { getInt("disclosure_accepted_state", -1) } doReturn -1
        }
        val fakeContext = mock<Context> {
            on { getSharedPreferences(any(), any()) } doReturn fakePreferences
        }
        val result = fakeContext.shouldShowDisclosure(report)

        assertTrue("Report should be shown by default", result)
    }
}
