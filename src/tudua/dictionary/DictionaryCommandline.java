/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudua.dictionary;

/**
 *
 * @author AnhThu_dbez
 */
public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.println("No    |English          |Vietnamese");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            System.out.print( i+1 + "      " + Dictionary.getWordTarget(i));
            for (int j = 0; j < 18 - Dictionary.getWordTarget(i).length(); j++) {
                System.out.print(" ");
            }
            System.out.println(Dictionary.getWordExplain(i));
        }
    }
}
