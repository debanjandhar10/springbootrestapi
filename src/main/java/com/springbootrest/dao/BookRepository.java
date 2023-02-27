package com.springbootrest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootrest.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	public Book findById(int id);
}
