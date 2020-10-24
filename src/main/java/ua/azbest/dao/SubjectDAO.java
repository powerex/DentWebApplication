package ua.azbest.dao;

import ua.azbest.model.Subject;

import java.io.IOException;
import java.util.List;

public interface SubjectDAO {

    List<Subject> index() throws IOException;
    Subject show(long id);
    void save(Subject subject);
    void update(long id, Subject subject);
    void delete(long id);
}
