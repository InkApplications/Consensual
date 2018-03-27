package consensual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CollectionPointTest {
    @Test fun add() {
        val a = CollectionPoint(Service(Purpose(0, 1), 2), PersonalDataItem(3))
        val b = CollectionPoint(Service(Purpose(4, 4), 6), PersonalDataItem(7))
        val combined = a + b

        assertNotEquals(a, b)
        assertEquals(2, combined.size)
        assertTrue(a in combined)
        assertTrue(b in combined)
    }
}
