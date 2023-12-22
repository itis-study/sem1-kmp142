package org.example.UtilityClasses;

import org.example.DAO.TextDAO;
import org.example.Entities.Category;
import org.example.Entities.Text;

import java.util.ArrayList;
import java.util.Collections;

public class GetRandomText {
    public static Text getText() {
        ArrayList<Text> texts = (ArrayList<Text>) TextDAO.dao.getAll();
        Collections.shuffle(texts);
        if (texts.isEmpty()) {
            return new Text("", Category.RUSSIAN);
        }
        return texts.get(1);
    }
}
