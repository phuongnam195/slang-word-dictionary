package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.SlangWord;
import model.Tree;

public class TreeHelper<T> {
    public Tree<T, SlangWord> loadTreeFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(fileName));
        Tree<T, SlangWord> tree = (Tree<T, SlangWord>) ois.readObject();
        ois.close();
        return tree;
    }

    public void saveTreeToFile(Tree<T, SlangWord> treeSW, String fileName) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(treeSW);
            oos.close();
        } catch (IOException e) {
            System.out.println("TreeHelper -> saveTreeToFile(): " + e.toString());
        }
    }
}
