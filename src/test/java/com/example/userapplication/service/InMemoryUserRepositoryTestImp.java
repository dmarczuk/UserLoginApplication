package com.example.userapplication.service;

import com.example.userapplication.model.MyUser;
import com.example.userapplication.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InMemoryUserRepositoryTestImp implements UserRepository {
    Map<String, MyUser> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public MyUser save(MyUser user) {
        inMemoryDatabase.put(user.getName(), user);
        return user;
    }

    @Override
    public Optional<MyUser> findByName(String name) {
        return Optional.ofNullable(inMemoryDatabase.get(name));
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends MyUser> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends MyUser> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<MyUser> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public MyUser getOne(String s) {
        return null;
    }

    @Override
    public MyUser getById(String s) {
        return null;
    }

    @Override
    public MyUser getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends MyUser> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MyUser> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends MyUser> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends MyUser> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MyUser> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MyUser> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MyUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends MyUser> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MyUser> findById(String s) {
        return Optional.ofNullable(inMemoryDatabase.get(s));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<MyUser> findAll() {
        return null;
    }

    @Override
    public List<MyUser> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(MyUser entity) {
        inMemoryDatabase.remove(entity.getName());
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends MyUser> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<MyUser> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<MyUser> findAll(Pageable pageable) {
        return null;
    }



//    @Override
//    public boolean find(User user) {
//        return false;
//    }
//
//    @Override
//    public boolean remove(User user) {
//        return false;
//    }


}
