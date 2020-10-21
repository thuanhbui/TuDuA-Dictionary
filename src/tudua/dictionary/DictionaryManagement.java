/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudua.dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author AnhThu_dbez
 */
public class DictionaryManagement {
    public static void insertFromCommandline()
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String target = scan.next();
            String explain = scan.nextLine();
            String type ="";
            String spell = "";
            Word wordy = new Word(target, explain,type, spell);
            Dictionary.insertWord(wordy);
        }
    }

    public static void insertFromFile() throws FileNotFoundException {
        String path = "C:\\Users\\MRIT\\Documents\\NetBeansProjects\\TuDuA Dictionary\\src\\tudua\\dictionary\\vocab.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] arr = line.split("\t");
            if (arr.length >= 4) {
                String word_target = arr[0].trim();
                String word_explain = arr[3].trim();
                String word_type = arr[1].trim();
                String spell = arr[2].trim();
                
                Word s = new Word(word_target, word_explain, word_type, spell);
                
                Dictionary.insertWord(s);
            }
        }
        Dictionary.sortWord();
    }

    public static int lookUp(String s, ArrayList<Word> list, int l, int h) {
        Collections.sort(list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return (o1.getWord_target().compareTo(o2.getWord_target()));
            }
        });
        if (l <= h) {
            int mid = l + (h - l) / 2;
            if (list.get(mid).getWord_target().compareToIgnoreCase(s) == 0) {
                return mid;
            }
            if (list.get(mid).getWord_target().compareToIgnoreCase(s) < 0) {
                //return lookUp(s, list, l, mid - 1);
                return lookUp(s, list, mid + 1, h);
            }
            //return lookUp(s, list, mid + 1, h);
            if (list.get(mid).getWord_target().compareToIgnoreCase(s) > 0) {
                return lookUp(s, list, l, mid - 1);
            }
        }
        return -1;
    }
    
    public static String lookUpWord(String s, ArrayList<Word> list, int l, int h) {
        Collections.sort(list, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return (o1.getWord_target().compareTo(o2.getWord_target()));
            }
        });
        if (l <= h) {
            int mid = l + (h - l) / 2;
            if (list.get(mid).getWord_target().toLowerCase().compareToIgnoreCase(s) == 0) {
                //return list.get(mid).getWord_explain();
                return list.get(mid).getWord_explain();
            }
            if (list.get(mid).getWord_target().toLowerCase().compareToIgnoreCase(s) < 0) {
                //return lookUp(s, list, l, mid - 1);
                //return lookUpWord(s, list, mid + 1, h);
                return lookUpWord(s, list, mid + 1, h);
            }
            //return lookUp(s, list, mid + 1, h);
            if (list.get(mid).getWord_target().toLowerCase().compareToIgnoreCase(s) > 0) {
                //return lookUpWord(s, list, l, mid - 1);
                return lookUpWord(s, list, l, mid - 1);
            }
        }
        return "KHÔNG TÌM THẤY TỪ";
    }
    
    public static ArrayList<Word> dictionaryLookup(String s) {
        s.trim();
        ArrayList<Word> arr = new ArrayList<Word>();
        int n = Dictionary.sizeOfWord();
        
        for (int i = 0; i < n; i++) {
            if (Dictionary.getWord(i).getWord_target().toLowerCase().startsWith(s.toLowerCase())) {
                arr.add(Dictionary.getWord(i));
            }
        }
        return arr;
    }

    public static String dictionaryInsert(String word_tar, String word_ex, String word_type, String spell) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        word_tar.trim();
        int n = Dictionary.sizeOfWord();
        int index = lookUp(word_tar, Dictionary.getArrayWord(), 0, n - 1);
        /*
        
        boolean check = true;
        for (int i=0; i<n; i++) {
            if (Dictionary.getWord(i).getWord_target().compareToIgnoreCase(word_tar) == 0) {
                check = false;
                break;
            }
        }*/
        if (index != -1) {
            //System.out.println("Tu can them da co trong tu dien!");
            return "TỪ CẦN THÊM ĐÃ CÓ TRONG TỪ ĐIỂN!";
        } else {
            
            try {
                File file = new File("C:\\Users\\MRIT\\Documents\\NetBeansProjects\\TuDuA Dictionary\\src\\tudua\\dictionary\\vocab.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fw = new FileWriter(file.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write(word_tar + "\t" + word_type + "\t" + spell + "\t" + word_ex + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            Word s = new Word(word_tar, word_ex, word_type, spell);
            Dictionary.insertWord(s);
            Dictionary.sortWord();
        }
        return "THÊM TỪ THÀNH CÔNG";
    }
    /*
    public static String dictionaryDelete(String delete) throws IOException {
        //Dictionary.deleteWord(s);
        
        Word s = null;
        int n = Dictionary.sizeOfWord();
        int index = lookUp(delete, Dictionary.getArrayWord(), 0, n - 1);
        if (index == -1) {
            //System.out.println("Tu can xoa khong co trong tu dien!");
            return "TỪ CẦN XÓA KHÔNG CÓ TRONG TỪ ĐIỂN";
        } else {
            s = Dictionary.getWord(index);
            Dictionary.deleteWord_(s);
            File file = new File("C:\\Users\\MRIT\\Documents\\NetBeansProjects\\TuDuA Dictionary\\src\\tudua\\dictionary\\vocab.txt");
            OutputStream ops = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ops);
            for (int i=0; i<Dictionary.sizeOfWord(); i++) {
                outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                            + Dictionary.getWord(i).getWord_type() + "\t"
                                            + Dictionary.getWord(i).getSpell() + "\t"
                                            + Dictionary.getWord(i).getWord_explain());
                outputStreamWriter.write("\n");
            }
            System.out.println("Done!");
            //bắt chương trình chờ ghi dữ liệu xong mới kết thúc chương trình
            outputStreamWriter.flush();
        }
        return "XOÁ TỪ THÀNH CÔNG";
    }
    */
    public static String dictionaryDelete(String delete) throws IOException {
        //Dictionary.deleteWord(s);
        
        Word s = null;
        int n = Dictionary.sizeOfWord();
        boolean check = true;
        for (int i=0; i<n; i++) {
            if (Dictionary.getWord(i).getWord_target().compareToIgnoreCase(delete) == 0) {
                s = Dictionary.getWord(i);
                check = false;
            }
        }
        if (check) {
            return "TỪ CẦN XÓA KHÔNG CÓ TRONG TỪ ĐIỂN";
        } else {
            Dictionary.deleteWord_(s);
            File file = new File("C:\\Users\\MRIT\\Documents\\NetBeansProjects\\TuDuA Dictionary\\src\\tudua\\dictionary\\vocab.txt");
            OutputStream ops = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ops);
            for (int i=0; i<Dictionary.sizeOfWord(); i++) {
                outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                            + Dictionary.getWord(i).getWord_type() + "\t"
                                            + Dictionary.getWord(i).getSpell() + "\t"
                                            + Dictionary.getWord(i).getWord_explain());
                outputStreamWriter.write("\n");
            }
            //bắt chương trình chờ ghi dữ liệu xong mới kết thúc chương trình
            outputStreamWriter.flush();
        }
        return "XOÁ TỪ THÀNH CÔNG";
    }
    
    /*
    public static String dictionaryFix(String fix, String word_type, String word_ex, String spell) throws IOException {
        Word s = null;
        int n = Dictionary.sizeOfWord();
        int index = lookUp(fix, Dictionary.getArrayWord(), 0, n - 1);
        if (index == -1) {
            //System.out.println("Từ cần sửa không có trong từ điển !");
            return "TỪ CẦN SỬA KHÔNG CÓ TRONG TỪ ĐIỂN";
        } else {
            s = Dictionary.getWord(index);
            Dictionary.deleteWord_(s);
            
            Word ss = new Word(fix, word_ex, word_type, spell);
            Dictionary.insertWord(ss);
            Dictionary.sortWord();
            File file = new File("C:\\Users\\MRIT\\Documents\\NetBeansProjects\\TuDuA Dictionary\\src\\tudua\\dictionary\\vocab.txt");
            OutputStream ops = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ops);
            for (int i = 0; i < Dictionary.sizeOfWord(); i++) {
                outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                            + Dictionary.getWord(i).getWord_type() + "\t"
                                            + Dictionary.getWord(i).getSpell() + "\t"
                                            + Dictionary.getWord(i).getWord_explain());
                outputStreamWriter.write("\n");
            }
            System.out.println("Done!");
            outputStreamWriter.flush();
        }
        return "SỬA TỪ THÀNH CÔNG";
    }
    */
    public static String dictionaryFix(String fix, String word_type, String word_ex, String spell) throws IOException {
        Word s = null;
        int n = Dictionary.sizeOfWord();
        boolean check = true;
        for (int i=0; i<n; i++) {
            if (Dictionary.getWord(i).getWord_target().compareToIgnoreCase(fix) == 0) {
                s = Dictionary.getWord(i);
                check = false;
            }
        }
        if (check) {
            System.out.println("Từ cần sửa không có trong từ điển !");
            return "TỪ CẦN SỬA KHÔNG CÓ TRONG TỪ ĐIỂN";
        } else {
            Dictionary.deleteWord_(s);
            
            Word ss = new Word(fix, word_ex, word_type, spell);
            Dictionary.insertWord(ss);
            File file = new File("C:\\Users\\MRIT\\Documents\\NetBeansProjects\\TuDuA Dictionary\\src\\tudua\\dictionary\\vocab.txt");
            OutputStream ops = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ops);
            for (int i = 0; i < Dictionary.sizeOfWord(); i++) {
                outputStreamWriter.write(Dictionary.getWord(i).getWord_target() + "\t"
                                            + Dictionary.getWord(i).getWord_type() + "\t"
                                            + Dictionary.getWord(i).getSpell() + "\t"
                                            + Dictionary.getWord(i).getWord_explain());
                outputStreamWriter.write("\n");
            }
            outputStreamWriter.flush();
        }
        return "SỬA TỪ THÀNH CÔNG";
    }

}