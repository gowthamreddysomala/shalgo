package com.shamiralgo.shalgo.controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shamiralgo.shalgo.entity.Point;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class SecretFinder {
    public static void main(String[] args) throws Exception {
        String file1 = "src/main/resources/testcase1.json";
        String file2 = "src/main/resources/testcase2.json";
        System.out.println("Secret 1: " + findSecret(file1));
        System.out.println("Secret 2: " + findSecret(file2));
    }
    public static BigInteger findSecret(String filename) throws Exception {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new FileReader(filename), JsonObject.class);
        JsonObject keysObject = jsonObject.getAsJsonObject("keys");
        int n = keysObject.get("n").getAsInt();
        int k = keysObject.get("k").getAsInt();
        List<Point> points = new ArrayList<>();
        for (Map.Entry<String, com.google.gson.JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            if (key.equals("keys")) continue;
            JsonObject shareObj = entry.getValue().getAsJsonObject();
            String baseStr = shareObj.get("base").getAsString();
            String valueStr = shareObj.get("value").getAsString();
            int x = Integer.parseInt(key);
            int base = Integer.parseInt(baseStr);
            BigInteger y = decodeValue(valueStr, base);
            points.add(new Point(x, y));
        }
        points.sort((p1, p2) -> Integer.compare(p1.getX(), p2.getX()));
        List<Point> selectedPoints = points.subList(0, k);
        return lagrangeInterpolationAtZero(selectedPoints);
    }
    private static BigInteger decodeValue(String val, int base) {
        val = val.toLowerCase();
        BigInteger result = BigInteger.ZERO;
        BigInteger bigBase = BigInteger.valueOf(base);
        for (char ch : val.toCharArray()) {
            int digit;
            if (ch >= '0' && ch <= '9') {
                digit = ch - '0';
            } else if (ch >= 'a' && ch <= 'z') {
                digit = ch - 'a' + 10;
            } else {
                throw new IllegalArgumentException("Invalid character in value string");
            }
            if (digit >= base) {
                throw new IllegalArgumentException("Digit " + digit + " out of base range " + base);
            }
            result = result.multiply(bigBase).add(BigInteger.valueOf(digit));
        }
        return result;
    }
    private static BigInteger lagrangeInterpolationAtZero(List<Point> points) {
        int k = points.size();
        BigInteger secret = BigInteger.ZERO;
        for (int j = 0; j < k; j++) {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            int xj = points.get(j).getX();
            for (int m = 0; m < k; m++) {
                if (m == j) continue;
                int xm = points.get(m).getX();
                numerator = numerator.multiply(BigInteger.valueOf(-xm));
                denominator = denominator.multiply(BigInteger.valueOf(xj - xm));
            }
            BigInteger yj = points.get(j).getY();
            BigInteger term = yj.multiply(numerator).divide(denominator);
            secret = secret.add(term);
        }
        return secret;
    }
}