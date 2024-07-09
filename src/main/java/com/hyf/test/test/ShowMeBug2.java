package com.hyf.test.test;
// 可以引⼊的库和版本相关请参考 “环境说明”
// 请勿更改 `ShowMeBug` 类名以防执⾏失败

public class ShowMeBug2 {

    public static void main(String[] args) {
        System.out.println(new ShowMeBug2().solution(10, 5));
    }

    // 请按你的实际需求修改参数 m元  n瓶
    public String solution(int m, int n) {
        // 在这⾥写代码
        String[] waters = new String[]{"可乐", "格瓦斯", "脉动"};
        int[] moneys = {2, 3, 5};

        int[][] arrs = new int[3][n];

        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[0].length; j++) {
                arrs[i][j] = j * moneys[i];
            }
        }

        int mid = (n - 1) / 2;

        StringBuilder sb = new StringBuilder();

        for (int i = mid; i < arrs[0].length; i++) {
            for (int j = 0; j < arrs.length; j++) {
                int temp = arrs[j][i];
                for (int k = 0; k < arrs.length; k++) {
                    int idx = (n - 1) - i;
                    if (temp + arrs[k][idx] == n) {
                        for (int w = 0; w < waters.length; w++) {
                            if (sb.length() != 0) {
                                sb.append(",");
                            }
                            if (w == j) {
                                sb.append(waters[w]).append(i + 1);
                            }
                            else if (w == k) {
                                sb.append(waters[w]).append((n - 1) - i + 1);
                            }
                            else {
                                sb.append(waters[w]).append(0);
                            }
                        }
                        break;
                    }
                }
            }
        }

        //   可乐 格瓦斯 脉动
        // 0  2    3      5
        // 1  4    6     10
        // 2  6    9     15
        // 3  8   12     20
        // 4 10   15     25

        return sb.toString();
    }
}