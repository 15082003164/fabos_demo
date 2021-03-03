package com.yangy.springboot.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.Iterator;
import java.util.List;

public class CheckResult {
    public CheckResult() {
    }

    public static boolean checkUpdateForInteger(int... nums) {
        int[] var1 = nums;
        int var2 = nums.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            int num = var1[var3];
            if (num != 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkUpdateForInteger(List<Integer> nums) {
        Iterator var1 = nums.iterator();

        int num;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            num = (Integer) var1.next();
        } while (num == 1);

        return false;
    }

    public static boolean checkBoolean(Boolean... boolList) {
        Boolean[] var1 = boolList;
        int var2 = boolList.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            Boolean bool = var1[var3];
            if (!bool) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkBooleanForList(List<Boolean> boolList) {
        Iterator var1 = boolList.iterator();

        Boolean bool;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            bool = (Boolean) var1.next();
        } while (bool);

        return false;
    }
}
