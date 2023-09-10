package domain;

public interface Identifiable<ID>{

    public void setId(ID id);

    public ID getId();

    @Override
    public boolean equals(Object o);
}