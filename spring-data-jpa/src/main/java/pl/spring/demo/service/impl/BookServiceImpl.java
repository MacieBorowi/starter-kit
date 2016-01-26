package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	@Override
	@Cacheable("booksCache")
	public List<BookTo> findAllBooks() {
		return bookDao.findAll();
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return bookDao.findBookByTitle(title);
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return bookDao.findBooksByAuthor(author);
	}

	@Override
	public BookTo saveBook(BookTo book) {
		return bookDao.save(book);
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
