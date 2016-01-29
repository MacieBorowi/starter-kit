package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.MapperToEntityTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private MapperToEntityTo map;

	@Before
	public void setUp() {
		cacheManager.getCache("booksCache").clear();
	}

	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookTo> allBooks = bookService.findAllBooks();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(8, allBooks.size());
	}

	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test
	public void testShouldFindAllBooksByAuthor() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookEntity bookToSave = new BookEntity();
		bookToSave.setId(22L);
		// when
		bookService.saveBook(map.mapTo(bookToSave));
		// then
		fail("test should throw BookNotNullIdException");
	}

	@Test
	public void testShouldFindTwoBooksByTitle() {
		// given
		final String title = "Sta";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
		assertEquals(2, booksByTitle.size());
	}

	@Test
	public void testShouldFindTwoBooksByAuthorFirstName() {
		// given
		final String author = "eDmu";
		// when
		List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
		assertEquals(3, booksByAuthor.size());
	}

	@Test
	public void testShouldFindBookByAuthorLastName() {
		// given
		final String author = "szekspir";
		// when
		List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
		assertEquals(1, booksByAuthor.size());
	}

	@Test
	public void testShouldFindTwoBooksByAuthorFirstAndLastName() {
		// given
		final String author = "Zbi";
		// when
		List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
		assertEquals(2, booksByAuthor.size());
	}

	@Test
	public void testShouldFindNothingByAuthor() {
		// given
		final String author = "Konstantyn";
		// when
		List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertTrue(booksByAuthor.isEmpty());
	}

}
