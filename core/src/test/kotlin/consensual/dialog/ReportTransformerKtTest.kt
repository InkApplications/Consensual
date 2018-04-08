package consensual.dialog

import com.xwray.groupie.Group
import com.xwray.groupie.Section
import consensual.*
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.KClass

class ReportTransformerKtTest {
    @Test fun nullable() {
        val report: DisclosureReport? = null
        val result = report.toItems()

        assertEquals(emptyList<Group>(), result)
    }

    @Test fun minimal() {
        val report: DisclosureReport? = DisclosureReport(
            disclosures = CollectionPoint(
                service = Service(
                    type = Purpose(1, 2),
                    name = 3
                ),
                data = PersonalData(4)
            )
        )
        val result = report.toItems()

        assertEquals(1, result.size)
        assertTrue("Root level should be a Section", result[0] is Section)

        val section = result[0] as Section
        assertEquals(3, section.groupCount)
        section.assertContainsTypes(
            PurposeGroupItem::class,
            ServiceItem::class,
            PersonalDataItem::class
        )
    }

    @Test fun multi() {
        val sharedPurpose = Purpose(6, 7)
        val report: DisclosureReport? = DisclosureReport(
            disclosures = setOf(
                CollectionPoint(
                    service = Service(
                        type = Purpose(1, 2),
                        name = 3
                    ),
                    data = setOf(
                        PersonalData(4),
                        PersonalData(5)
                    )
                ),
                CollectionPoint(
                    service = Service(
                        type = sharedPurpose,
                        name = 8
                    ),
                    data = setOf(
                        PersonalData(9),
                        PersonalData(10)
                    )
                ),
                CollectionPoint(
                    service = Service(
                        type = sharedPurpose,
                        name = 11
                    ),
                    data = setOf(
                        PersonalData(12),
                        PersonalData(13)
                    )
                )
            )
        )
        val result = report.toItems()

        assertEquals(2, result.size)
        assertTrue("Root levels should be a sections", result[0] is Section)
        assertTrue("Root levels should be a sections", result[1] is Section)

        val section = result[0] as Section
        assertEquals(4, section.groupCount)
        section.assertContainsTypes(
            PurposeGroupItem::class,
            ServiceItem::class,
            PersonalDataItem::class,
            PersonalDataItem::class
        )

        val section2 = result[1] as Section
        assertEquals(7, section2.groupCount)
        section2.assertContainsTypes(
            PurposeGroupItem::class,
            ServiceItem::class,
            PersonalDataItem::class,
            PersonalDataItem::class,
            ServiceItem::class,
            PersonalDataItem::class,
            PersonalDataItem::class
        )
    }

    private fun Section.assertContainsTypes(vararg types: KClass<out Group>) {
        types.forEachIndexed { index, type ->
            assertEquals(type, getGroup(index)::class)
        }
    }
}
