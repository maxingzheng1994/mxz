package com.mxz.common.utils.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * redis 漏斗 限流
 *
 * @author mxz on 2020/09/18 17:54
 */
public class FunnelRateLimiter {

    static class Funnel {
        int capacity;
        float leakingRate;
        int leftQuota;
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs; // 距离上次漏水过了多久
            int deltaQuota = (int) (deltaTs * leakingRate); // 应该漏多少
            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            if (deltaQuota < 1) { // 腾出空间太小，最小单位是 1
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, Funnel> funnels = new HashMap<>();

    /**
     *
     * @param userId 用户id
     * @param actionKey 用户行为
     * @param capacity 容量
     * @param leakingRate 漏水速率
     * @return
     */
    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1); // 需要 1 个 quota
    }
}
