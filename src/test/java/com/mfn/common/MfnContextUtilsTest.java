package com.mfn.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by LPF on 2017/5/14.
 */

public class MfnContextUtilsTest {
    private static final Logger LOG = LoggerFactory.getLogger(MfnContextUtilsTest.class);

    @Test
    public void setValue() throws Exception {

    }

    @Test
    public void getValue() throws Exception {

    }

    @Test
    public void getSeq() throws Exception {
        System.out.println("begin");
        MfnContextUtils.setSeq("123");
        System.out.println(MfnContextUtils.getSeq());
        System.out.println("end");
    }

    @Test
    public void setSeq() throws Exception {

    }

}