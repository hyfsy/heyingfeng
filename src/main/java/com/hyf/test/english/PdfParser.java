package com.hyf.test.english;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author baB_hyf
 * @date 2023/06/02
 */
public class PdfParser {

    private static final List<String> ignored = new ArrayList<>();

    static {
        ignored.add("Classic Vocabulary List");
        ignored.add("Title:");
        ignored.add("- ");
        ignored.add("No.");
    }

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\baB_hyf\\Desktop\\4级.pdf";
        parse(filePath);
    }

    public static void parse(String filePath) throws Exception {

        String contents = readContent(filePath);
        List<String> words = parseWords(contents);

        addExtra(words);
        words = new ArrayList<>(new HashSet<>(words));
        System.out.println(String.join(",", words));
        System.out.println(words.size());

        // words.forEach(System.out::println);
        // seaport,electronics,breadth,sufficiently,lieutenant,intentional,England,childish,attentive,Marxism,Portugal,tramp,unsatisfactory,Brazilian,odour,daring,waggon,radioactivity,joyful,inquire,virtually,proportional,gunpowder,Japanese,beggar,honeymoon,earphone,bough,interpretation,sticky,exclusively,whitewash,displease,brittle,inefficient,roller,Germany,victorious,harbour,workman,purely,statistical,hillside,manure,widen,purity,radish,hasten,utility,grammatical,respectively,manly,resignation,aero,Swiss,quarterly,tyre,manufacturer,accidental,automobile,seaman,imprison,brim,liar,elimination,contradiction,repent,springtime,Arabian,rusty,microcompute,mechanics,noticeable,zealous,pudding,n,buckle,slit,r,circumference,aluminium,robber,paintbrush,dissatisfy,pluck,father-in-law,Russian,thickness,practically,needless,vibration,gaseous,dramatization,amongst,commander,Portuguese,beginner,twelfth,mister,discharge,Brazil,puberty,pit,bristle,landmark,heating,jew,vine,living-room,wavelength,brook,midday,loft,world-wide,indirect,bitterly,blueberry,photographic,murderer,vex,insufficient,photoshop,establishment,jail,towards,gardener,brood,ampere,solely,millimetre,hateful,transformatio,materialism,youthful,bosom,favourable,reflexion,shady,stiffen,transformer,throng,brute,favour,primarily,indefinite,outskirt,atmospheric,twentieth,previously,structural,Negro,capitalism,inventor,hare,shriek,Polish,refreshment,sulphur,steamer,lime,rotation,telegram,concentration,rotary,Soviet,Fahrenheit,ultimately,whilst,weld,Mediterranea,concerning,stony,landing,undertaking,corresponding,deepen,dishonour,wireless,specially,delegation,turnip

    }

    private static void addExtra(List<String> words) {
        words.add("sufficiently");
        words.add("inefficient");
        words.add("insufficient");
        words.add("indefinite");
        words.add("reflexion");
    }

    private static String readContent(String filePath) throws Exception {
        StringBuilder contents = new StringBuilder();
        PdfReader reader = new PdfReader(filePath);
        for (int i = 0; i < reader.getNumberOfPages(); i++) {
            String pageStr = PdfTextExtractor.getTextFromPage(reader, i + 1);
            contents.append(pageStr);
        }
        reader.close();
        return contents.toString();
    }

    private static List<String> parseWords(String contents) {
        List<String> rowList = Arrays.stream(contents.split("\n")).collect(Collectors.toList());
        List<String> words = new ArrayList<>();
        for (String row : rowList) {

            if (needIgnore(row)) {
                continue;
            }

            String[] rowCeil = row.split("\n");

            for (String ceil : rowCeil) {
                String[] wordChunk = ceil.split(" ");
                for (String wordCandidate : wordChunk) {
                    if (isLetter(wordCandidate)) {
                        if (wordCandidate.length() > 1) {
                            words.add(wordCandidate);
                        }
                    } else {
                        if (isLetterStart(wordCandidate) && !wordCandidate.endsWith(".")) {
                            String normalizedWordCandidate = normalize(wordCandidate);
                            if (isLetter(normalizedWordCandidate)) {
                                if (normalizedWordCandidate.length() > 1) {
                                    words.add(normalizedWordCandidate);
                                }
                            }
                            else {
                                System.out.println(wordCandidate);
                                System.out.println(Arrays.toString(wordCandidate.getBytes(StandardCharsets.UTF_8)));
                            }
                        }
                    }
                }
            }
        }

        return words;
    }

    private static boolean needIgnore(String row) {
        for (String s : ignored) {
            if (row.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private static final Pattern letterP      = Pattern.compile("^[a-zA-Z-]*");
    private static final Pattern letterStartP = Pattern.compile("^[a-zA-Z].*");

    private static boolean isLetter(String row) {
        if (row == null || row.length() <= 0) {
            return false;
        }

        return letterP.matcher(row).matches();
    }

    private static boolean isLetterStart(String row) {
        if (row == null || row.length() <= 0) {
            return false;
        }

        return letterStartP.matcher(row).matches();
    }

    private static String normalize(String word) {

        // -17, -84 -> f = 102
        // -127     -> i = 105
        // -126     -> l = 108

        byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
        byte[] newBytes = new byte[bytes.length];
        int newBytesIdx = 0;
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (b < 0) {
                if (b == -17 && (i + 1) < bytes.length && bytes[i + 1] == -84) {
                    b = 102;
                    i++;
                }
                else if (b == -127) {
                    b = 105;
                }
                else if (b == -126) {
                    b = 108;
                }
            }
            newBytes[newBytesIdx++] = b;
        }

        return new String(newBytes, 0, newBytesIdx, StandardCharsets.UTF_8);
    }
}
