package captify.test.java;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;
import java.util.Iterator;

import static captify.test.java.SparseIterators.*;
import static captify.test.java.TestAssignment.*;
import static org.junit.Assert.*;

public class TestAssignmentTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testSampleAfter() throws Exception {
        // sampleAfter(iteratorFromOne, 1, 2) should be same as to Seq[BigInt](2,3,4).toIterator
        Iterator<BigInteger> result = sampleAfter(iteratorFromOne(), 1, 2);
        assertTrue("Should have 1st item", result.hasNext());
        assertEquals("1st item should be equal to 2", BigInteger.valueOf(2), result.next());
        assertTrue("Should have 2nd item", result.hasNext());
        assertEquals("2nd item should be equal to 3", BigInteger.valueOf(3), result.next());
        assertTrue("Should have 3rd item", result.hasNext());
        assertEquals("3rd item should be equal to 3", BigInteger.valueOf(4), result.next());
        assertFalse("Should not have more items", result.hasNext());
    }

    /**
     * To be sure that method working good on big extent
     */
    @Test
    public void testSampleAfterLarge() throws Exception {
        Iterator<BigInteger> result = sampleAfter(iteratorFromOne(), 1000000, 2);
        assertTrue("Should have 1st item", result.hasNext());
        assertEquals("1st item should be equal to 2", BigInteger.valueOf(1000001), result.next());
        assertTrue("Should have 2nd item", result.hasNext());
        assertEquals("2nd item should be equal to 3", BigInteger.valueOf(1000002), result.next());
        assertTrue("Should have 3rd item", result.hasNext());
        assertEquals("3rd item should be equal to 3", BigInteger.valueOf(1000003), result.next());
        assertFalse("Should not have more items", result.hasNext());
    }

    @Test
    public void testSampleAfterNegative1() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        sampleAfter(iteratorFromOne(), -1, 2);
    }

    @Test
    public void testSampleAfterNegative2() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        sampleAfter(iteratorFromOne(), 1, -2);
    }

    @Test
    public void testValueAt() throws Exception {
        BigInteger expectedResult = BigInteger.valueOf(3);
        BigInteger result = valueAt(iteratorFromOne(), 2);
        assertEquals(expectedResult, result);
    }

    /**
     * To be sure that method working good on big extent
     */
    @Test
    public void testValueAtLarge() throws Exception {
        BigInteger expectedResult = BigInteger.valueOf(10000001);
        BigInteger result = valueAt(iteratorFromOne(), 10000000);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValueAtNegative() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        valueAt(iteratorFromOne(), -1);
    }

    @Test
    public void testMergeIterators() throws Exception {

    }

    @Test
    public void testApproximatesFor() throws Exception {

    }

}