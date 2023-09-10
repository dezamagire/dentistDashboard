package repository.file;

import repository.MemoryRepository;

public abstract class FileRepository<ID, T> extends MemoryRepository<ID, T> {
    protected String filename;

    public FileRepository(String filename) {
        this.filename = filename;
    }

    public abstract void readFromFile();

    public abstract void writeToFile();

    @Override
    public boolean add(ID id, T t) {
        boolean result = super.add(id, t);
        if (result) {
            writeToFile();
        }
        return result;
    }

    @Override
    public boolean remove(ID id) {
        boolean result = super.remove(id);
        if (result) {
            writeToFile();
        }
        return result;
    }

    @Override
    public boolean update(ID id, T t) {
        boolean result = super.update(id, t);
        if (result) {
            writeToFile();
        }
        return result;
    }
    
}
