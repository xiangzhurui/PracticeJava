package repository;

import model.Book;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



public class Sample implements ElasticsearchRepository<Book, String> {

    @Override
    public <S extends Book> S index(S entity) {
        return null;
    }

    @Override
    public Iterable<Book> search(QueryBuilder query) {
        return null;
    }

    @Override
    public Page<Book> search(QueryBuilder query, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Book> search(SearchQuery searchQuery) {
        return null;
    }

    @Override
    public Page<Book> searchSimilar(Book entity, String[] fields, Pageable pageable) {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    public Class<Book> getEntityClass() {
        return null;
    }

    @Override
    public Iterable<Book> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Book> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Book> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    public Book findOne(String s) {
        return null;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Iterable<Book> findAll(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public void delete(Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
