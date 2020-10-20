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
public class Word {
    private String word_target;
    private String word_explain;
    private String word_type;
    private String spell;

    Word(String target, String explain, String type, String spell) {
        this.word_target = target;
        this.word_explain = explain;
        this.word_type = type;
        this.spell = spell;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
    
    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }
    
    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }
    
    public String getWord_type() {
        return word_type;
    }

    public String getSpell() {
        return spell;
    }

}
