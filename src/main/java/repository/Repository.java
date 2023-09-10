package repository;

import java.util.Iterator;
import java.util.ArrayList;

public interface Repository<ID, T>{
    // appointment repository
    boolean add(ID id, T t);
    boolean remove(ID id);
    boolean update(ID id, T t);
    ArrayList<T> get();
    T get(ID id);
    Iterator<T> iterator();
    int size();
}
