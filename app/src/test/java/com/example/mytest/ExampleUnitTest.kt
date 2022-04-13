package com.example.mytest

import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.lang.Exception


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var ob1: String? = null
    var ob2: String? = null
    var list : ArrayList<Int>?=null

    @Before
    fun setUp() {
        ob1 = "hiiii"
        ob2 = "hi"
    }


    @Before
    fun setssUp() {
        list = ArrayList()
        list?.add(3)
        list?.add(1)
        list?.add(4)
        list?.add(1)
        list?.add(5)
        list?.add(9)
    }

    @After
    fun tearDown() {
        list?.clear()
    }

    @Test
    fun shouldBeOkToAlterTestData() {
       //list?.remove(4) // Remove first element of list.
        assertEquals(5, list?.size) // Size is down to five
    }

    @Test
    fun checkNull() {
        val testString: String? = null
        //Assert.assertNull("west", testString)
        Assert.assertNull("west", testString)
    }
    @Test
    fun testEx() {
        assertEquals("hi", ob1, ob2)
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}