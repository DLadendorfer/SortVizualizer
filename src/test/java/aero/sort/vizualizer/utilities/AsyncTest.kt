package aero.sort.vizualizer.utilities

import aero.sort.vizualizer.utilities.Async.safeCancel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import java.time.Instant
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicReference

/**
 * Tests [Async].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
internal class AsyncTest {

    @Mock
    lateinit var throwingCancelFuture: Future<Void>


    @Test
    fun `Tests if invoke is actually invoking the runnable asynchronously`() {
        val latch = CountDownLatch(2)

        Async.invoke {
            Thread.sleep(3L)
            latch.countDown()
        }

        Thread.sleep(1L)
        latch.countDown()

        assertEquals(1, latch.count) {
            "The latch count should be at one"
        }
        Thread.sleep(4L)
        assertEquals(0, latch.count) {
            "The latch count should be at zero"
        }
    }


    @Test
    fun `Tests if submit is actually invoking the runnable asynchronously and returns a cancellable future`() {
        val latch = CountDownLatch(3)

        val future = Async.submit {
            Thread.sleep(3L)
            latch.countDown()
            Thread.sleep(20L)
            latch.countDown()
        }
        assertEquals(3, latch.count) {
            "The latch count should be at 3"
        }
        Thread.sleep(10L)
        assertEquals(2, latch.count) {
            "The latch count should be at 2"
        }
        future.cancel(true)
        Thread.sleep(20L)
        assertEquals(2, latch.count) {
            "The latch count should be at 2"
        }
        assertTrue(future.isCancelled) {
            "The future should be cancelled by now"
        }
    }

    @Test
    fun `Tests if safeCancel is null safe`() {
        assertDoesNotThrow {
            safeCancel(null)
        }
    }

    @Test
    fun `Tests if safeCancel is actually cancelling a future`() {
        val latch = CountDownLatch(1)
        assertDoesNotThrow {
            safeCancel(Async.submit {
                Thread.sleep(10L)
                latch.countDown()
            })
        }
        assertEquals(1, latch.count) {
            "Latch should not have been counted down"
        }
    }

    @Test
    fun `Tests if cancel exception is not rethrown`() {
        `when`(throwingCancelFuture.cancel(true)).thenThrow(RuntimeException())
        assertDoesNotThrow(
            { "No exception should be thrown" },
            { safeCancel(throwingCancelFuture) }
        )
    }

    @Test
    fun `Tests if sleep actually sleeps`() {
        val start = Instant.now().toEpochMilli()
        Async.sleep(10L)
        val end = Instant.now().toEpochMilli()

        assertTrue(end - start >= 10L) {
            "10ms should have passed by now"
        }
    }

    @Test
    fun `Tests if sleep interrupt exceptions are rethrown as runtime exceptions`() {
        val reference = AtomicReference<Thread>()
        Async.invoke {
            reference.set(Thread.currentThread())
            val e = assertThrows<RuntimeException> {
                Async.sleep(1000L)
            }

            assertInstanceOf(InterruptedException::class.java, e.cause) {
                "Inner exception should be of type Interrupted Exception"
            }
        }
        Async.sleep(2L)
        reference.get().interrupt()
    }
}