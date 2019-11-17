package foo.sample.component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import foo.sample.constant.log.LogParam;

/**
 * LogComponentテスト
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogComponentTest {

    @Before
    public void resetMDC() {
        MDC.clear();
    }

    @Autowired
    private LogComponent logger;

    @Test
    public void test001() {
        logger.debug("message");
    }

    @Test
    public void test002() {
        MDC.put(LogParam.CATV_COMPANY_ID.name(), "CATV1");
        logger.debug("SIM.INFO.9901", "フィリピン沖", "今年最大", "台風");
    }

    @Test
    public void test003() {
        MDC.put(LogParam.OPERATE_ID.name(), "Servive1");
        logger.info("SIM.INFO.9901", "山手線", "電車", "遅延");
    }

    @Test
    public void test004() {
        MDC.put(LogParam.SP_ID.name(), "SPID1");
        logger.warn("SIM.WARN.9901", "中央道", "年末帰省", "渋滞");
    }

    @Test
    public void test005() {
        MDC.put(LogParam.SUBSCRIBER_ID.name(), "契約者");
        logger.error("SIM.ERROR.9901", "上野", "パンダ", "赤ちゃん");
    }

    @Test
    public void test006() {
        logger.fatal("SIM.FATAL.9901", "山手線", "待望", "高輪ゲートウェイ駅");
    }

    @Test
    public void test007() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.info("SIM.INFO.9991", e);
    }

    @Test
    public void test008() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.warn("SIM.WARN.9991", e);
    }

    @Test
    public void test009() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.error("SIM.ERROR.9991", e);
    }

    @Test
    public void test010() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.fatal("SIM.FATAL.9991", e);
    }

    @Test
    public void test012() {
        MDC.put(LogParam.CATV_COMPANY_ID.name(), "CATV1");
        logger.log("SIM.DEBUG.9901", "フィリピン沖", "今年最大", "台風");
    }

    @Test
    public void test013() {
        MDC.put(LogParam.OPERATE_ID.name(), "Servive1");
        logger.log("SIM.INFO.9901", "山手線", "電車", "遅延");
    }

    @Test
    public void test014() {
        MDC.put(LogParam.SP_ID.name(), "SPID1");
        logger.log("SIM.WARN.9901", "山手線", "待望", "高輪ゲートウェイ駅");
    }

    @Test
    public void test015() {
        MDC.put(LogParam.SUBSCRIBER_ID.name(), "契約者");
        logger.log("SIM.ERROR.9901", "上野", "パンダ", "赤ちゃん");
    }

    @Test
    public void test016() {
        MDC.put(LogParam.CATV_COMPANY_ID.name(), "CATV1");
        MDC.put(LogParam.OPERATE_ID.name(), "Servive1");
        MDC.put(LogParam.SP_ID.name(), "SPID1");
        MDC.put(LogParam.SUBSCRIBER_ID.name(), "契約者");
        logger.log("SIM.FATAL.9901", "秋葉原", "AKB", "握手会");
    }

    @Test
    public void test017() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.log("SIM.INFO.9991", e);
    }

    @Test
    public void test018() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.log("SIM.WARN.9991", e);
    }

    @Test
    public void test019() {
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.log("SIM.ERROR.9991", e);
    }

    @Test
    public void test020() {
        MDC.put(LogParam.CATV_COMPANY_ID.name(), "CATV1");
        MDC.put(LogParam.OPERATE_ID.name(), "Servive1");
        MDC.put(LogParam.SP_ID.name(), "SPID1");
        MDC.put(LogParam.SUBSCRIBER_ID.name(), "契約者");
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.log("SIM.FATAL.9991", e);
    }

    @Test
    public void test021() {
        MDC.put(LogParam.CATV_COMPANY_ID.name(), "CATV1");
        MDC.put(LogParam.OPERATE_ID.name(), "Servive1");
        MDC.put(LogParam.SP_ID.name(), "SPID1");
        MDC.put(LogParam.SUBSCRIBER_ID.name(), "契約者");
        Exception e = new Exception("テスト作成例外 " + Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.log("SIM.ALERT.9991", e);
    }

}
