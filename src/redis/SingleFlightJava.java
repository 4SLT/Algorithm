package redis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 控制多个线程访问同一个Key时，只有一个线程执行实际操作，其他线程获取实际操作线程返回的结果
 * 多用于防止缓存穿透的情况
 *
 * Singleflight 主要通过维护一个内部的映射表（map）来跟踪每个操作（通常由一个唯一的标识符定义，比如函数参数）的状态。
 * 当多个 thread 尝试通过 Singleflight 执行相同的函数（基于相同的键）时，只有第一个 thread 会实际执行该函数，其余等待的 thread 将会阻塞，直到该操作完成。
 * 完成后，所有等待的 thread 都会接收到相同的结果（不论是成功还是失败），从而避免了重复执行相同的操作。
 */
public class SingleFlightJava {

    private final ConcurrentHashMap<String, CountDownLatch> latchMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtomicReference<Object>> resultMap = new ConcurrentHashMap<>();

    public <T> T doFunction(String key, java.util.function.Supplier<T> function) throws InterruptedException {
        // 检查是否已有其他线程正在执行相同的操作
        CountDownLatch latch = latchMap.computeIfAbsent(key, k -> new CountDownLatch(1));
        // 每个线程都执行一次countDown，但只有第一个线程会真正减少计数
        latch.countDown();

        // 等待前一个操作完成，或如果当前线程是第一个到达的，则直接执行
        latch.await();

        // 双重检查，获取或计算结果
        AtomicReference<Object> ref = resultMap.get(key);
        if (ref == null) {
            T result = function.get();
            ref = new AtomicReference<>(result);
            resultMap.put(key, ref);
        }

        return (T) ref.get();
    }

    public static void main(String[] args) throws InterruptedException {
        SingleFlightJava singleFlight = new SingleFlightJava();

        // 模拟两个线程尝试执行相同的函数调用
        Thread thread1 = new Thread(() -> {
            try {
                String result = singleFlight.doFunction("expensiveCall", () -> {
                    try {
                        Thread.sleep(2000); // 模拟耗时操作
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "线程一获取到的结果";
                });
                System.out.println("Thread 1 got: " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                String result = singleFlight.doFunction("expensiveCall", () -> {
                    // 确保只调用一次
                    throw new UnsupportedOperationException("不应该触发!");
                });
                System.out.println("Thread 2 got: " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();
    }
}