/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudua.dictionary;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author AnhThu_dbez
 */
public class DictionaryCommandLine1 {
    public static void dictionaryBasic() throws FileNotFoundException {
        DictionaryManagement.insertFromCommandline();
        DictionaryCommandline.showAllWords();
    }

    public static void dictionaryAdvanced() throws FileNotFoundException {
        DictionaryManagement.insertFromFile();
        DictionaryCommandline.showAllWords();
        System.out.println("Nhap 'Look up' de tiep tuc tra cuu.");
        Scanner scan = new Scanner(System.in);
        String action = scan.nextLine();

        while (action.equalsIgnoreCase("Look up")) {
            System.out.print("Moi nhap tu can tra: ");
            String word_eng = scan.nextLine();
            if (DictionaryManagement.dictionaryLookup(word_eng).equals("Khong tim thay.")) {
                System.out.println("Khong tim thay");
                System.out.println("Ban co muon tra cuu tiep khong (Yes/No)? ");
                action = scan.nextLine();
                if (action.equalsIgnoreCase("Yes")) {
                    action = "Look up";
                } else return;
            } else {
                System.out.println("Nghia tieng Viet la: " + DictionaryManagement.dictionaryLookup(word_eng));
                System.out.println("Ban co muon tra cuu tiep khong (Yes/No)? ");
                action = scan.nextLine();
                if (action.equalsIgnoreCase("Yes")) {
                    action = "Look up";
                } else return;
            }
        }
    }

    public static void main(String [] args) throws FileNotFoundException {
        //dictionaryBasic();
        //dictionaryAdvanced();
        //DictionaryApplication.runApplication();
        DictionaryApplication.runApplication();
        //new NewJFrame();
    }
}
