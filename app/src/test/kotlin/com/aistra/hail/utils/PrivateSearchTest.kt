package com.aistra.hail.utils

import org.junit.Assert.*
import org.junit.Test

class PrivateSearchTest {
    @Test
    fun ordinaryQueriesExcludeHiddenApps() {
        listOf(null, "", "app", "/private", "/Private app", " /private app", "app /private ").forEach {
            val search = PrivateSearch(it)
            assertFalse(search.isPrivate)
            assertEquals(it.orEmpty(), search.keyword)
            assertTrue(search.includes(false))
            assertFalse(search.includes(true))
        }
    }

    @Test
    fun privateQueriesOnlyIncludeHiddenApps() {
        val search = PrivateSearch("/private app")
        assertTrue(search.isPrivate)
        assertEquals("app", search.keyword)
        assertTrue(search.includes(true))
        assertFalse(search.includes(false))
    }

    @Test
    fun prefixAloneHasEmptyKeyword() {
        val search = PrivateSearch("/private ")
        assertTrue(search.isPrivate)
        assertEquals("", search.keyword)
        assertTrue(search.includes(true))
        assertFalse(search.includes(false))
    }

    @Test
    fun onlyLeadingPrefixIsRemoved() {
        assertEquals("/private app", PrivateSearch("/private /private app").keyword)
        assertEquals(" app", PrivateSearch("/private  app").keyword)
    }
}
