package data_base;

import logic.Split;

public interface DataBase {

    DataBase instance = new NonSavableDataBase();

    String addSplit(Split split);

    Split getSplit(String code) throws SplitNotFoundException;

}
