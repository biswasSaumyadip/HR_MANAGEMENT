package com.human_resource.hr_management.v1.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> list();

    void create(T t);

    Optional<T> getBy(String uuid);

    void update(T t, String uuid);

    void delete(String uuid);
}
