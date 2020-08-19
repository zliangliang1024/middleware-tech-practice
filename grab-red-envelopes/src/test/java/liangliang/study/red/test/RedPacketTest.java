package liangliang.study.red.test;

import liangliang.study.red.envelops.RedEnvelopsApp;
import liangliang.study.red.envelops.util.RedPacketUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedEnvelopsApp.class})
public class RedPacketTest {

    // 定义日志
    private static final Logger logger = LoggerFactory.getLogger(RedPacketTest.class);

    /**
     * 二倍均值法自测方法
     */
    @Test
    public void one() {
        // 二倍均值法单位为分，这里假设为 1000 分，即10元
        Integer amount = 1000;
        // 总人数及为红包总个数，在这里假设为10个
        Integer total = 10;
        // 调用二倍均值法工具类中产生随机金额列表的方法得到小红包随机金额列表
        List<Integer> list = RedPacketUtil.divideRedPackage(amount, total);
        logger.info("总金额: {} 分,总个数：{} 个", amount, total);
        // 用于统计生成的随机金额之和是否等于总金额
        Integer sum = 0;
        // 遍历输出每个随机金额
        for (Integer i : list) {
            // 输出随机金额时包括单位为分和单位为元的信息
            logger.info("随机金额为 {} 分，即 {} 元", i, new BigDecimal(i.toString()).divide(new BigDecimal(100)));
            sum += i;
        }
        logger.info("所有随机金额叠加之和={}", sum);

    }


}
