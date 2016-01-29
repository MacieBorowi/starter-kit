package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldMockSaveBook() {
		// given
		BookTo book = new BookTo(null, "title", "1L;author;name");
		BookEntity mockedEntity = new BookEntity(1L, "title", "1L;author;name");
		Mockito.when(bookDao.save(MapperToEntityTo.mapEntity(book))).thenReturn(mockedEntity);
		// when
		BookTo result = bookService.saveBook(book);
		// then
		Mockito.verify(bookDao).save(MapperToEntityTo.mapEntity(book));
		assertEquals(1L, result.getId().longValue());
	}
}
