package repository;

import domain.Identifiable;

public class EntityMemRepo<I extends Identifiable<String>> extends MemoryRepository<String, I> {}