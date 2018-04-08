package consensual

import java.io.Serializable

/**
 * Pairs a service with the information that service collects.
 *
 * @param service The service that is collecting information about the user.
 * @param data The personal data that the service is collecting.
 */
@Suppress("unused")
data class CollectionPoint(
    val service: Service,
    val data: Set<PersonalData>
): Serializable {
    constructor(
        service: Service,
        data: PersonalData
    ): this(service, setOf(data))

    /**
     * Combine multiple collection points together into a set.
     */
    operator fun plus(collectionPoint: CollectionPoint) = setOf(this, collectionPoint)
}
