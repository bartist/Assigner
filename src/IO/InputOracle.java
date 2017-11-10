package IO;
import data.Entry;

import java.util.Arrays;

/**
 * Created by Bart on 8-11-2017.
 */
public class InputOracle {
    private final static String[] QUESTIONS = "2 Fun/Geeky things about me:\n   ,I study/My job is:\n   ,My hobbies are:\n   ,My Hogwarts House:\n    ,My biggest accomplishment of this year:\n   ,Favorite series:\n   ,Favorite Pet:\n   ,What I celebrate around this time of the year:\n   ,The last thing I am comfortable with sharing:\n   ".split(",");

    public static Entry createEntry(String[] data) {
        Entry e = new Entry(QUESTIONS);

        e.setName(data[1]);
        e.setMail(data[2]);
        e.setAddress(data[3]);
        e.setCountry(data[4]);

        e.setOutofCountry(data[5].equals("Yes"));
        e.setReturn(data[6].equals("Yes"));

        e.setOptional(Arrays.copyOfRange(data,7,data.length));
        return e;
    }
}
