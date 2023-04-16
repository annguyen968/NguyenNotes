package com.example.nguyennotes

import com.google.firebase.database.FirebaseDatabase
import junit.framework.TestCase.*
import org.junit.After
import org.junit.Before
import org.junit.Test


class TestcaseFirebase {
    private lateinit var database: FirebaseDatabase

    @Before
    fun setUp() {
        database = FirebaseDatabase.getInstance()
    }

    @After
    fun clearData() {
        // Clean up any data written to the Realtime Database during the test
        database.reference.child("test").setValue(null)
    }

    @Test
    fun testWriteToDatabase() {
        // Write a test value to the Realtime Database
        val testValue = "Hello, Firebase!"
        database.reference.child("test").setValue(testValue)
            .addOnCompleteListener { task ->
                // Verify that the write was successful
                assertTrue(task.isSuccessful)
                assertNotNull(task.result)
            }
    }

    @Test
    fun testGetDataFromDatabase() {
        // Write a test value to the Realtime Database
        val testValue = "Hello, Firebase!"
        database.reference.child("test").setValue(testValue)

        // Read the value from the Realtime Database
        var result: String? = null
        database.reference.child("test").get()
            .addOnCompleteListener { task ->
                // Verify that the read was successful
                assertTrue(task.isSuccessful)
                assertNotNull(task.result)
                result = task.result?.value as String?
                // Verify that the value read from the Realtime Database is the same as the value
                assertEquals(testValue, result)
            }
    }

}