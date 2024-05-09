import java.util.BitSet;

public class BloomFilter {
    // 布隆过滤器默认大小
    private static final int DEFAULT_SIZE = 2 << 24;
    private static final int[] seeds = new int[]{7, 11, 13, 31, 37, 61}; // 选择的哈希种子，可以根据实际情况调整
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    // 添加元素
    public void add(Object value) {
        for (SimpleHash f : func) {
            bitSet.set(f.hash(value), true);
        }
    }

    // 判断元素是否存在，可能存在误报
    public boolean contains(Object value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bitSet.get(f.hash(value));
        }
        return ret;
    }

    // 内部类，简单哈希函数
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        // 使用哈希函数计算索引位置
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }

    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter();

        // 添加元素
        filter.add("test");
        filter.add("example");

        // 查询元素是否存在
        System.out.println(filter.contains("test")); // 应返回true
        System.out.println(filter.contains("absent")); // 可能误报为true
    }
}