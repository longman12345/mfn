package com.mfn.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by LPF on 2017/5/13.
 */
@Component("com.mfn.common.SeqUtils")
public class SeqUtils {
    private static final Logger LOG = LoggerFactory.getLogger(SeqUtils.class);

    private static String machineNo;

    @Value("${machine.no}")
    private void setMachineNo(String machineNo) {
        SeqUtils.machineNo = machineNo;
    }

    private static final long MaxSeq = 100000000L;
    private static final AtomicLong seqValue = new AtomicLong(0);
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    public String getNextSeq(Date date) {
        String dateStr = format.format(date);
        StringBuilder seq = new StringBuilder();
        String nextSeq =  seq.append(dateStr).append(getFixedStr(machineNo, 3)).append(getFixedStr(getSeqValue(), 9)).toString();
        return nextSeq;
    }

    private String getSeqValue() {
        if (seqValue.get() < MaxSeq) {
            return String.valueOf(seqValue.addAndGet(1));
        }else {
            throw new RuntimeException("seqValue is overflow");
        }
    }

    private String getFixedStr(String input, int size) {
        if (input.length() > size) {
            throw new RuntimeException("getFixedStr(" + input + ", " + size + ") length is overflow");
        }
        return String.format("%0" + size + "d", Integer.parseInt(input));
    }
}
