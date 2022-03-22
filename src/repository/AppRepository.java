package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;
import model.SlangWord;
import model.Tree;
import util.Utils;

public class AppRepository {
    private ArrayList<SlangWord> _listSW;
    private Tree<Character, SlangWord> _treeSW;

     private static final String FILE_NAME_LIST = "original_slang_words.txt";

    public List<SlangWord> getList() {
        Scanner sc;
        if (_listSW == null) {
            try {
                _listSW = new ArrayList<>();
            File file = new File(FILE_NAME_LIST);
            sc = new Scanner(file);
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                SlangWord slangWord = SlangWord.fromString(line);
                _listSW.add(slangWord);
            }
            sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("AppRepository -> getList(): " + e);
            }
        }
        return _listSW;
    }

    public Tree<Character, SlangWord> getTree() {
        if (_treeSW == null) {
            buildTree();
        }
        return _treeSW;
    }

    public Tree<Character, SlangWord> buildTree() {
        getList();
        _treeSW = new Tree<Character, SlangWord>(' ');
        for (int i = 0; i < _listSW.size(); i++) {
            SlangWord slangWord = _listSW.get(i);
            String word = _listSW.get(i).getWord();
            _treeSW.addLeaf(Utils.stringToCharList(word), slangWord);
        }
        return _treeSW;
    }
}
