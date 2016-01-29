package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.MapperToEntityTo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private MapperToEntityTo map;

	@Override
	@Cacheable("booksCache")
	public List<BookTo> findAllBooks() {
		return map.mapListTO(bookDao.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return map.mapListTO(bookDao.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return map.mapListTO(bookDao.findBooksByAuthor(author));
	}

	@Override
	public BookTo saveBook(BookTo book) {
		return map.mapTo(bookDao.save(map.mapEntity(book)));
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
