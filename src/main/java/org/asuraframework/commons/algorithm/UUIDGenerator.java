/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.algorithm;

import java.net.InetAddress;

/**
 * <p>
 * UUID
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @Date 2018/4/2 下午9:31
 * @since 1.0
 */
public class UUIDGenerator {

    private static final int IP;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private final static String SEP = "";
    private static short COUNTER = (short) 0;

    static {
        int ipadd;
        try {
            ipadd = iptoInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private UUIDGenerator() {
    }

    private static int iptoInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    public static String hexUUID() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        return uuidGenerator.generate();
    }

    /**
     * Unique across JVMs on this machine (unless they load this class
     * in the same quater second - very unlikely)
     */
    protected int getJVM() {
        return JVM;
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there
     * are > Short.MAX_VALUE instances created in a millisecond)
     */
    protected short getCount() {
        synchronized (UUIDGenerator.class) {
            if (COUNTER < 0) {
                COUNTER = 0;
            }
            return COUNTER++;
        }
    }

    /**
     * Unique in a local network
     */
    protected int getIP() {
        return IP;
    }

    /**
     * Unique down to millisecond
     */
    protected short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    protected int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    protected String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    private String generate() {
        return new StringBuffer(36)
                .append(format(getIP())).append(SEP)
                .append(format(getJVM())).append(SEP)
                .append(format(getHiTime())).append(SEP)
                .append(format(getLoTime())).append(SEP)
                .append(format(getCount()))
                .toString();
    }

}
