package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "CommonServiceTest-context.xml", "BookServiceImplCacheTest-context.xml" })
public class BookServiceImplCacheTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private CacheManager cacheManager;

	@Before
	public void setUp() {
		cacheManager.getCache("booksCache").clear();
	}

	@Test
	public void testShouldFindAllBooksFirstFromDaoThenFromCache() {
		// when
		Mockito.when(bookDao.findAll()).thenReturn(Arrays.asList(new BookEntity(1L, "Title", "1;Author;Some")));

		List<BookTo> allBooks = bookService.findAllBooks();
		assertEquals(1, allBooks.size());

		allBooks = bookService.findAllBooks();
		assertEquals(1, allBooks.size());
		// then
		Mockito.verify(bookDao, Mockito.times(1)).findAll();
	}
}
