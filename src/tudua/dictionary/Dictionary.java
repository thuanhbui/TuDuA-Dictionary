/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudua.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author AnhThu_dbez
 */
public class Dictionary {
    static ArrayList<Word> words = new ArrayList<Word>();

    public static void insertWord(Word wordy) {
        words.add(wordy);
    }
    
    public static int sizeOfWord() {
        return words.size();
    }
    
    public static Word getWord(int index) {
        return words.get(index);
    }
    
    public static void deleteWord_(Word s) {
        words.remove(s);
    }
    
    public static void sortWord() {
        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return (o1.getWord_target().compareTo(o2.getWord_target()));
            }
        });
    }
    
    public static ArrayList<Word> getArrayWord() {
        return words;
    }

    public static String getWordTarget(int index) {
        return words.get(index).getWord_target();
    }

    public static String getWordExplain(int index) {
        return words.get(index).getWord_explain();
    }
    
    public static String[] toArray() {
        String[] res = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            res[i] = words.get(i).getWord_target();
        }
        return res;
    }
}
