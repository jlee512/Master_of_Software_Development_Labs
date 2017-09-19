package Cybersecurity_Part1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.io.File;

/**
 * Created by Julian on 19/09/2017.
 */
public class RSA_KeyPairs_Part02 {

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new File("cybersecurity_experiment.csv"));
            stringBuilder.append("Number of Keys,");
            stringBuilder.append("Time Difference (ms),");
            stringBuilder.append("Time Average (ms)\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("1024 byte length keys: ");
        System.out.println("[Difference(ms), Avg(ms)], 10 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(10, 1024, stringBuilder)));
        System.out.println("20 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(20, 1024, stringBuilder)));
        System.out.println("50 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(50, 1024, stringBuilder)));
        System.out.println("100 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(100, 1024, stringBuilder)));
        System.out.println("200 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(200, 1024, stringBuilder)));
        System.out.println("500 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(500, 1024, stringBuilder)));
        System.out.println("1000 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(1000, 1024, stringBuilder)));

        System.out.println();

        //2048 byte key length
        System.out.println("2048 byte length keys: ");
        System.out.println("[Difference(ms), Avg(ms)], 10 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(10, 1024, stringBuilder)));
        System.out.println("20 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(20, 2048, stringBuilder)));
        System.out.println("50 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(50, 2048, stringBuilder)));
        System.out.println("100 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(100, 2048,stringBuilder)));
        System.out.println("200 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(200, 2048, stringBuilder)));
        System.out.println("500 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(500, 2048, stringBuilder)));
        System.out.println("1000 keys: " + Arrays.toString(RSA_KeyPairs_Part02.generateIndividualKey(1000, 2048, stringBuilder)));

        writer.write(stringBuilder.toString());
        writer.close();
        System.out.println("Results written to csv");
    }

    public static double[] generateIndividualKey(int numberOfKeys, int keyLength, StringBuilder stringBuilder) {

        double[] result = new double[2];
        long start_time = System.currentTimeMillis();
        for (int i = 0; i < numberOfKeys; i++) {

            KeyPair keyPair = null;

            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(keyLength);
                keyPair = keyPairGenerator.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        long end_time = System.currentTimeMillis();

        double time_diff = (double) (end_time - start_time);
        double time_avg = time_diff / ((double) numberOfKeys);

        stringBuilder.append(numberOfKeys + ",");
        stringBuilder.append(time_diff + ",");
        stringBuilder.append(time_avg + "\n");

        result[0] = time_diff;
        result[1] = time_avg;

        return result;
    }
}
