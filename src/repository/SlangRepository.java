package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.SlangWord;
import model.Tree;
import util.TreeHelper;
import util.Utils;

public class SlangRepository {
    private static SlangRepository instance;

    public static SlangRepository getInstance() {
        if (instance == null) {
            instance = new SlangRepository();
        }
        return instance;
    }

    private SlangRepository() {
        getTree();
    }

    private Tree<Character, SlangWord> _treeSW; // search: word, result: slang word
    private Tree<String, SlangWord> _treeSWdef; // search: definition, result: slang word
    private TreeHelper<Character> treeSWHelper;
    private TreeHelper<String> treeSWdefHelper;

    private static final String TREE_FILE_NAME = "tree_slang_words.txt";
    private static final String TREE_DEF_FILE_NAME = "tree_def_slang_words.txt";
    private static final String ORIGINAL_LIST_FILE_NAME = "original_slang_words.txt";

    public void save() {
        treeSWHelper.saveTreeToFile(_treeSW, TREE_FILE_NAME);
        treeSWdefHelper.saveTreeToFile(_treeSWdef, TREE_DEF_FILE_NAME);
    }

    public ArrayList<SlangWord> getOriginalList() {
        Scanner sc;
        ArrayList<SlangWord> originalList = new ArrayList<>();
        try {
            File file = new File(ORIGINAL_LIST_FILE_NAME);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                SlangWord slangWord = SlangWord.fromString(line);
                originalList.add(slangWord);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("AppRepository -> getList(): " + e);
        }
        return originalList;
    }

    public boolean add(SlangWord newSW) {
        _treeSW.addLeaf(Utils.stringToCharList(newSW.getWord()), newSW);
        return true;
    }

    public boolean update(SlangWord newSW) {
        return _treeSW.updateLeaf(Utils.stringToCharList(newSW.getWord()), newSW);
    }

    public boolean remove(String word) {
        return _treeSW.removeLeaf(Utils.stringToCharList(word));
    }

    public SlangWord getRandomWord() {
        return _treeSW.getRandom();
    }

    public Tree<Character, SlangWord> getTree() {
        if (_treeSW == null) {
            try {
                _treeSW = treeSWHelper.loadTreeFromFile(TREE_FILE_NAME);
                _treeSWdef = treeSWdefHelper.loadTreeFromFile(TREE_DEF_FILE_NAME);
            } catch (Exception e) {
                _treeSW = buildTreeSW(getOriginalList());
                _treeSWdef = buildTreeSWdef(getOriginalList());
                save();
            }
        }
        return _treeSW;
    }

    public void reset() {
        _treeSW = buildTreeSW(getOriginalList());
        _treeSWdef = buildTreeSWdef(getOriginalList());
    }

    private Tree<Character, SlangWord> buildTreeSW(ArrayList<SlangWord> list) {
        Tree<Character, SlangWord> treeSW = new Tree<Character, SlangWord>('.');
        for (int i = 0; i < list.size(); i++) {
            SlangWord slangWord = list.get(i);
            String word = list.get(i).getWord();
            treeSW.addLeaf(Utils.stringToCharList(word), slangWord);
        }
        return treeSW;
    }

    private Tree<String, SlangWord> buildTreeSWdef(ArrayList<SlangWord> list) {
        Tree<String, SlangWord> treeSW = new Tree<String, SlangWord>("root");
        for (int i = 0; i < list.size(); i++) {
            SlangWord slangWord = list.get(i);
            String word = list.get(i).getWord();
            treeSW.addLeaf(Utils.stringToWordList(word), slangWord);
        }
        return treeSW;
    }
}