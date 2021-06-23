package ross;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.zip.CheckedOutputStream;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int length = in.nextInt();
//            int min = in.nextInt();
//            int max = in.nextInt();
//            int stoneCount = in.nextInt();
//            Set<Integer> stones = new HashSet<>(stoneCount);
//            for (int i = 0; i < stoneCount; i++) {
//                stones.add(in.nextInt());
//            }
//            t1(length, min, max, stones);
//        }
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] split = s.split(",");
            int i = t2(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            System.out.println(300 - i);
        }
    }

    public static int t2(int price, int preDays, int actDays) {
        int count = 0;
        if (price >= 100) {
            if (preDays >= actDays) {
                if (preDays <= 15) {
                    count += 5 * preDays;
                } else {
                    count += 5 * 15 + (preDays - 15) * 3;
                }
            } else {
                if (actDays <= 15) {
                    count += 5 * actDays;
                } else {
                    count += 5 * 15 + (actDays - 15) * 3;
                }
            }
            count = Math.min(count, price);
        } else if (price >= 50) {
            if (preDays >= actDays) {
                if (preDays <= 15) {
                    count += 3 * preDays;
                } else {
                    count += 3 * 15 + (preDays - 15) * 2;
                }
            } else {
                if (actDays <= 15) {
                    count += 3 * actDays;
                } else {
                    count += 3 * 15 + (actDays - 15) * 2;
                }
            }
            count = Math.min(count, price);
        } else {
            if (preDays >= actDays) {
                count += preDays;
            } else {
                count += actDays;
            }
            count = Math.min(count, price);
        }
        if (actDays > preDays) {
            count += (actDays - preDays);
        }
        return count;
    }

    public static void t1(int length, int min, int max, Set<Integer> stones) {
        int[] dp = new int[length + max];
        for (int i = 0; i < min; i++) {
            dp[i] = 10;
        }
        for (int i = min; i <= max || i <= 2 * min; i++) {
            dp[i] = stones.contains(i) ? 1 : 0;
        }
        for (int i = max < 2 * min ? max + 1 : 2 * min + 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = min; j <= max; j++) {
                if (i - j >= 0) {
                    int isStone = stones.contains(i) ? 1 : 0;
                    dp[i] = Math.min(dp[i], dp[i - j] + isStone);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++) {
            res = Math.min(res, dp[dp.length - 1 - i]);
        }
        System.out.println(res);
    }
}
