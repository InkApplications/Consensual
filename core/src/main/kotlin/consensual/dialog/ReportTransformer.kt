package consensual.dialog

import com.xwray.groupie.Group
import com.xwray.groupie.Section
import consensual.CollectionPoint
import consensual.DisclosureReport

/**
 * Create a Groupie group from a disclosure report.
 */
internal fun DisclosureReport?.toItems(): List<Group> = this?.disclosures
    ?.groupBy { it.service.type }
    ?.map { (purpose, collectionPoints) -> Section(PurposeGroupItem(purpose)) to collectionPoints }
    ?.onEach { (purposeSection, collectionPoints) ->
        collectionPoints.map(CollectionPoint::service)
            .onEach { service ->
                purposeSection.add(ServiceItem(service))
                purposeSection.addAll(collectionPoints.find { it.service == service }.let { it!!.data.map(::PersonalDataItem) })
            }
    }
    ?.map { (section, _) -> section }
    ?: emptyList()
