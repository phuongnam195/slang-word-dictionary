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
        _treeSWHelper = new TreeHelper<Character>();
        _treeSWdefHelper = new TreeHelper<String>();
        loadTree();
    }

    private Tree<Character, SlangWord> _treeSW; // search: word, result: slang word
    private Tree<String, SlangWord> _treeSWdef; // search: definition, result: slang word
    private TreeHelper<Character> _treeSWHelper;
    private TreeHelper<String> _treeSWdefHelper;

    private static final String TREE_FILE_NAME = "tree_slang_words.dat";
    private static final String TREE_DEF_FILE_NAME = "tree_def_slang_words.dat";
    private static final String ORIGINAL_LIST_FILE_NAME = "original_slang_words.txt";

    public void save() {
        _treeSWHelper.saveTreeToFile(_treeSW, TREE_FILE_NAME);
        _treeSWdefHelper.saveTreeToFile(_treeSWdef, TREE_DEF_FILE_NAME);
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
            System.out.println("AppRepository -> getOriginalList(): " + e);
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
        return _treeSW;
    }

    public Tree<String, SlangWord> getTreeDef() {
        return _treeSWdef;
    }

    public void loadTree() {
        if (_treeSW == null || _treeSWdef == null) {
            try {
                _treeSW = _treeSWHelper.loadTreeFromFile(TREE_FILE_NAME);
                _treeSWdef = _treeSWdefHelper.loadTreeFromFile(TREE_DEF_FILE_NAME);
            } catch (Exception e) {
                ArrayList<SlangWord> list = getOriginalList();
                _treeSW = buildTreeSW(list);
                _treeSWdef = buildTreeSWdef(list);
                save();
            }
        }
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
            ArrayList<String> defs = list.get(i).getDefinitions();
            for (int j = 0; j < defs.size(); j++) {
                String def = defs.get(j);
                ArrayList<String> words = Utils.stringToWordList(def);
                while (words.size() > 0) {
                    treeSW.addLeaf(words, slangWord);
                    words.remove(0);
                }
            }
        }
        return treeSW;
    }
}
