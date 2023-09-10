package repository;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class MemoryRepository<ID, T> implements Repository<ID, T>{
    public Map<ID, T> entities;

    public MemoryRepository() {
        entities = new HashMap<ID, T>();
    }

    @Override
    public boolean add(ID id, T t) {
        return entities.put(id, t) == null;
    }

    @Override
    public boolean remove(ID id) {
        return entities.remove(id)!=null;
    }

    @Override
    public boolean update(ID id, T t) {
        return entities.replace(id, t)!=null;
    }

    @Override
    public ArrayList<T> get() {return new ArrayList<T>(entities.values());}

    @Override
    public T get(ID id) {
        return entities.get(id);
    }

    @Override
    public Iterator<T> iterator() {
        return entities.values().iterator();
    }

    @Override
    public int size() {
        return entities.size();
    }

    @Override
    public String toString() {
        String s = "";
        for (T t : entities.values()) {
            s += t.toString() + "\n";
        }
        return s;
    }

}