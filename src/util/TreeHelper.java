package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.SlangWord;
import model.Tree;

public class TreeHelper {
    public static Tree<Character, SlangWord> buildSWTree(ArrayList<SlangWord> listSW) {
        Tree<Character, SlangWord> treeSW = new Tree<Character, SlangWord>(' ');
        for (int i = 0; i < listSW.size(); i++) {
            SlangWord slangWord = listSW.get(i);
            String word = listSW.get(i).getWord();
            treeSW.addLeaf(Utils.stringToCharList(word), slangWord);
        }
        return treeSW;
    }

    public static Tree<Character, SlangWord> loadTreeFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(fileName));
        Tree<Character, SlangWord> tree = (Tree<Character, SlangWord>) ois.readObject();
        ois.close();
        return tree;
    }

    public static void saveTreeToFile(Tree<Character, SlangWord> treeSW, String fileName) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(treeSW);
            oos.close();
        } catch (IOException e) {
            System.out.println("TreeHelper -> saveTreeToFile(): " + e.toString());
        }
        // try {
        //     FileWriter writer = new FileWriter(fileName);
        //     Node<Character, SlangWord> curr = treeSW.getRoot();
        //     while (true) {
        //         if (!curr.isLeaf()) {
        //             for (Node<Character, SlangWord> child : curr.getChildren()) {

        //             }
        //         }
        //     }            
        // } catch (IOException e) {
        //     System.out.println("TreeHelper -> saveTreeToFile(): " + e);
        // }
    }
}
