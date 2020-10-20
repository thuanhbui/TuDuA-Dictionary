/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudua.dictionary;

import java.io.FileNotFoundException;

/**
 *
 * @author AnhThu_dbez
 */
public class DictionaryApplication {
    public static void runApplication() throws FileNotFoundException {
        //new MyFrame();
        DictionaryManagement.insertFromFile();
        MyFrame.runApp();
    }
}
