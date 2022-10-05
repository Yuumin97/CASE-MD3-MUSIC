package rikkei.academy.service.singer;

import rikkei.academy.model.singer.Singer;

import java.util.List;

public interface ISingerService {
    List<Singer> findByName(String name);
    List<Singer> findAll();
    void save(Singer singer);
    Singer findById(int id);
    void deleteById(int id);
}

