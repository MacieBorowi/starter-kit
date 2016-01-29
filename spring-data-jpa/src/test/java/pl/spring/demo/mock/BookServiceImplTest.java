package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.MapperToEntityTo;

public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	@Autowired
	private MapperToEntityTo map;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookEntity book = new BookEntity(null, "title", "1L;author;name");
		Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", "1L;author;name"));
		// when
		BookTo result = bookService.saveBook(map.mapTo(book));
		// then
		Mockito.verify(bookDao).save(book);
		assertEquals(1L, result.getId().longValue());
	}
}
