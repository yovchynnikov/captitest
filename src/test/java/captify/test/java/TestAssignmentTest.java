package captify.test.java;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;

import static captify.test.java.SparseIterators.*;
import static captify.test.java.TestAssignment.*;
import static java.lang.Math.signum;
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
        // return Map(2 -> Future(0.5), 3 -> Future(0.33), 4 -> Future(0.25)) (values given are approximates)
        Integer[] expectedOrder = {2, 3, 4};
        Double[] expectedValues = {0.5, 0.33, 0.25};

        Map<Integer, Future<Double>> result = approximatesFor(2, 4, 1000);

        // check keys for count, values and order
        assertArrayEquals(expectedOrder, result.keySet().toArray(new Integer[result.size()]));

        // check future -> isGet
        for (int i = 2; i <= 4; i++) {
            assertTrue("Future should be done", result.get(i).isDone());
            Double value = result.get(i).get();
            // check with slight approximation
            assertTrue(expectedValues[i - 2] - signum(value) < 0.1);
        }
    }

    @Test
    public void testApproximatesForWrongParameter() throws Exception {
        Map<Integer, Future<Double>> result = approximatesFor(1, 4, 1000);
        expectedException.expectCause(IsInstanceOf.instanceOf(IllegalArgumentException.class));
        result.get(1).get();
    }

    @Test
    public void testApproximatesForZero() throws Exception {
        Map<Integer, Future<Double>> result = approximatesFor(0, 4, 1000);
        expectedException.expectCause(IsInstanceOf.instanceOf(ArithmeticException.class));
        result.get(0).get();
    }

}