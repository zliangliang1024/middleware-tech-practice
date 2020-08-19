package liangliang.study.red.envelops.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 红包生成算法-二倍均值法
 */
public class RedPacketUtil {

    /**
     * 发红包算法，金额参数以分为单位
     *
     * @param totalAmount
     * @param totalPeopleNum
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        // 用于存储每次产生的小红包随机金额List - 金额单位为分
        List<Integer> amountList = new ArrayList<>();
        // 判断总金额和总个数的合法性
        if (totalAmount > 0 && totalPeopleNum > 0) {
            // 记录剩余的总金额 - 初始化时即为指定的总人数
            Integer restAmount = totalAmount;
            // 记录剩余的总人数  - 初始化时即为指定的总人数
            Integer restPeopleNum = totalPeopleNum;
            // 定义产生随机数的实例对象
            Random random = new Random();
            // 不断循环遍历，迭代更新地产生随机金额，直到N-1>0
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                // 随机范围：[1,剩余人均金额的两倍),左闭右开-amount即为产生的随机金额 R - 单位为分
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                // 更新剩余的总金额 M = M - R
                restAmount -= amount;
                // 更新地剩余的总人数
                restPeopleNum--;
                // 将产生的随机金额添加进列表 List 中
                amountList.add(amount);
            }
            // 循环完毕，剩余的金额即为最后一个随机金额，也需要将其添加进列表中
            amountList.add(restAmount);
        }
        // 最终将产生的随机金额返回
        return amountList;
    }
}
