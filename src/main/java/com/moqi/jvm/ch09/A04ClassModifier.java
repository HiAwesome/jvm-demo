package com.moqi.jvm.ch09;

/**
 * ClassModifier 的实现
 * 修改 Class 文件，暂时只提供修改常量池常量的功能
 *
 * @author moqi On 10/19/20 09:34
 */

public class A04ClassModifier {

    /**
     * Class 文件中常量池的起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info 常量的 tag 标志
     */
    private static final int CONSTANT_Utf8_info = 1;

    /**
     * 常量池中 11 种常量所占的长度，CONSTANT_Utf8_info 型常量除外，因为它不是定长的
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public A04ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 获取常量池中常量的数量
     *
     * @return 常量池数量
     */
    public int getConstantPoolCount() {
        return A03ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }

    /**
     * 修改常量池中 CONSTANT_Utf8_info 常量的内容
     *
     * @param oldStr 修改前的字符串
     * @param newStr 修改后的字符串
     * @return 修改结果
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {

        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;

        for (int i = 0; i < cpc; i++) {
            int tag = A03ByteUtils.bytes2Int(classByte, offset, u1);

            if (tag == CONSTANT_Utf8_info) {
                int len = A03ByteUtils.bytes2Int(classByte, offset + u1, u2);
                offset += (u1 + u2);
                String str = A03ByteUtils.bytes2String(classByte, offset, len);

                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = A03ByteUtils.string2Bytes(newStr);
                    byte[] strLen = A03ByteUtils.int2Bytes(newStr.length(), u2);
                    classByte = A03ByteUtils.bytesReplace(classByte, offset - u2, u2, strLen);
                    classByte = A03ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    offset += len;
                }

            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }

        }

        return classByte;
    }

}
