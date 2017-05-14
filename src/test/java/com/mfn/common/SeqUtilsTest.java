package com.mfn.common;

import com.mfn.server.MfnServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LPF on 2017/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MfnServer.class, properties = "classpath:application.properties")
public class SeqUtilsTest {
    private static final Logger LOG = LoggerFactory.getLogger(SeqUtilsTest.class);

    @Autowired
    @Qualifier("com.mfn.common.SeqUtils")
    SeqUtils seqUtils;

    @Test
    public void getNextSeq() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 60; ++i) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    String seq = seqUtils.getNextSeq(new Date());
                    LOG.info(seq);
                }
            });
        }

    }

}