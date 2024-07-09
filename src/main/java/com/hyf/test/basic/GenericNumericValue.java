package com.hyf.test.basic;

/**
 * @author baB_hyf
 * @date 2022/07/17
 */
public final class GenericNumericValue implements Comparable<GenericNumericValue> {
    private byte value;
    public GenericNumericValue(byte value) {
        this.value = value;
    }
    public byte getValue() {
        return value;
    }
    public int compareTo(GenericNumericValue that) {
        return this.value - that.value;
    }
}
