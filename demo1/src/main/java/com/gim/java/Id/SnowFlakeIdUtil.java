package com.gim.java.Id;

/**
 * @author Gim
 */
public class SnowFlakeIdUtil {

    private final static SnowFlakeIdUtil snowflakeIdGenerator = new SnowFlakeIdUtil(4, 1);
    /**
     * 初始时间戳(纪年)，可用雪花算法服务上线时间戳的值
     * // 1649059688068：2022-04-04 16:08:08
     */
    private static final long INIT_EPOCH = 1649059688068L;

    /**
     * 记录最后使用的毫秒时间戳，主要用于判断是否同一毫秒，以及用于服务器时钟回拨判断
     */
    private long lastTimeMillis = -1L;

    /**
     * dataCenterId占用的位数
     */
    private static final long DATA_CENTER_ID_BITS = 5L;
    /**
     * dataCenterId占用5个比特位，最大值31
     * 0000000000000000000000000000000000000000000000000000000000011111
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * dataCenterId
     */
    private long dataCenterId;

    /**
     * workId占用的位数
     */
    private static final long WORKER_ID_BITS = 5L;
    /**
     * workId占用5个比特位，最大值31
     * 0000000000000000000000000000000000000000000000000000000000011111
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    /**
     * workId
     */
    private long workerId;

    /**
     * 最后12位，代表每毫秒内可产生最大序列号，即 2^12 - 1 = 4095
     */
    private static final long SEQUENCE_BITS = 12L;
    /**
     * 掩码（最低12位为1，高位都为0），主要用于与自增后的序列号进行位与，如果值为0，则代表自增后的序列号超过了4095
     * 0000000000000000000000000000000000000000000000000000111111111111
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    /**
     * 同一毫秒内的最新序号，最大值可为 2^12 - 1 = 4095
     */
    private long sequence;

    /**
     * workId位需要左移的位数 12
     */
    private static final long WORK_ID_SHIFT = SEQUENCE_BITS;
    /**
     * dataCenterId位需要左移的位数 12+5
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    /**
     * 时间戳需要左移的位数 12+5+5
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    public SnowFlakeIdUtil(long dataCenterId, long workerId) {

        // 检查dataCenterId的合法值
        if (dataCenterId < 0 || dataCenterId > MAX_DATA_CENTER_ID) {
            throw new IllegalArgumentException(
                    String.format("dataCenterId值必须大于0并且小于%d", MAX_DATA_CENTER_ID));
        }

        // 检查workId的合法值
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException(String.format("workId值必须大于0并且小于%d", MAX_WORKER_ID));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 通过雪花算法生成下一个id，注意这里使用synchronized同步
     *
     * @return 唯一id
     */
    public synchronized long nextId() {

        long currentTimeMillis = System.currentTimeMillis();

        // 当前时间小于上一次生成id使用的时间，可能出现服务器时钟回拨问题
        if (currentTimeMillis < lastTimeMillis) {
            throw new RuntimeException(
                    String.format("可能出现服务器时钟回拨问题，请检查服务器时间。当前服务器时间戳：%d，上一次使用时间戳：%d", currentTimeMillis,
                            lastTimeMillis));
        }
        // 还是在同一毫秒内，则将序列号递增1，序列号最大值为4095
        if (currentTimeMillis == lastTimeMillis) {

            // 序列号的最大值是4095，使用掩码（最低12位为1，高位都为0）进行位与运行后如果值为0，则自增后的序列号超过了4095
            // 那么就使用新的时间戳
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                currentTimeMillis = tilNextMillis(lastTimeMillis);
            }

        } else {
            // 不在同一毫秒内，则序列号重新从0开始，序列号最大值为4095
            sequence = 0;
        }

        // 记录最后一次使用的毫秒时间戳
        lastTimeMillis = currentTimeMillis;

        // 核心算法，将不同部分的数值移动到指定的位置，然后进行或运行
        return ((currentTimeMillis - INIT_EPOCH) << TIMESTAMP_SHIFT) | (dataCenterId
                << DATA_CENTER_ID_SHIFT) | (workerId << WORK_ID_SHIFT) | sequence;
    }

    /**
     * 获取指定时间戳的接下来的时间戳，也可以说是下一毫秒
     *
     * @param lastTimeMillis 指定毫秒时间戳
     * @return 时间戳
     */
    private long tilNextMillis(long lastTimeMillis) {
        long currentTimeMillis = System.currentTimeMillis();
        while (currentTimeMillis <= lastTimeMillis) {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis;
    }

    public static Long getNextId(){
        return snowflakeIdGenerator.nextId();
    }



    public static void main(String[] args) {

        final Long nextId = getNextId();
        System.out.println(nextId);
    }
}

