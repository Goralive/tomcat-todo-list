package service;

import entity.Task;

import java.io.IOException;
import java.util.Collection;

public interface CrudService<T, D> {

    T save(Task request) throws IOException;

    Collection<T> fetchAll() throws IOException;

    void delete(D id) throws IOException;
}
 