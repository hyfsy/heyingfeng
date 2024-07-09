package com.hyf.test.test;
// 可以引⼊的库和版本相关请参考 “环境说明”
// 请勿更改 `ShowMeBug` 类名以防执⾏失败

public class ShowMeBug {

    public static void main(String[] args) {
        System.out.println(new ShowMeBug().solution(10, 3));
    }

    // 请按你的实际需求修改参数 m元  n瓶
    public String solution(int m, int n) {
        // 在这⾥写代码
        String[] waters = {"可乐", "格瓦斯", "脉动"};
        int[] moneys = {2, 3, 5};

        int[][] arrs = new int[3][n];

        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[0].length; j++) {
                arrs[i][j] = (j + 1) * moneys[i];
            }
        }

        int mid = (n - 1) / 2;

        StringBuilder sb = new StringBuilder();

        for (int i = mid; i < arrs[0].length; i++) {
            for (int j = 0; j < arrs.length; j++) {
                int temp = arrs[j][i];
                for (int k = 0; k < arrs.length; k++) {
                    int idx = n - (i + 1) - 1;
                    if (((n - 1) == i && temp == m) || idx >= 0 && temp + arrs[k][idx] == m) {
                        for (int w = 0; w < waters.length; w++) {
                            if (sb.length() != 0) {
                                sb.append(",");
                            }
                            if (j == k && w == j) {
                                sb.append(waters[w]).append(i + 1 + n - (i + 1));
                            }
                            else if (w == j) {
                                sb.append(waters[w]).append(i + 1);
                            }
                            else if (w == k) {
                                sb.append(waters[w]).append(n - (i + 1));
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

        //   n 可乐 格瓦斯 脉动
        // 0 1  2    3      5
        // 1 2  4    6     10
        // 2 3  6    9     15
        // 3 4  8   12     20
        // 4 5 10   15     25

        return sb.toString();
    }
}