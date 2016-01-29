package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.AuthorEntity;
import pl.spring.demo.to.BookEntity;

public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> foundBooks = new ArrayList<BookEntity>();
		for (BookEntity book : ALL_BOOKS) {
			if (book.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
				foundBooks.add(book);
			}
		}
		return foundBooks;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> foundBooks = new ArrayList<BookEntity>();
		for (BookEntity book : ALL_BOOKS) {
			for (AuthorEntity authorEntity : book.getAuthorsList()) {
				if (authorEntity.getLastName().toLowerCase().startsWith(author.toLowerCase())
						|| authorEntity.getFirstName().toLowerCase().startsWith(author.toLowerCase())) {
					if (!foundBooks.contains(book)) {
						foundBooks.add(book);
					}
				}
			}
		}
		return foundBooks;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		// if (book.getId() == null) {
		// book.setId(sequence.nextValue(ALL_BOOKS));
		// }
		ALL_BOOKS.add(book);
		return book;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "1;Wiliam;Szekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "2;Hanna;Ożogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "3;Jan;Edmurowicz"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "4;Edmund;Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "5;Zbigniew;Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "6;Aleksander;Fredro"));
		ALL_BOOKS.add(new BookEntity(7L, "Stan", "7;Zbigniew;Borowik"));
		ALL_BOOKS.add(new BookEntity(8L, "Starosta", "8;Edmund;Skarbek"));
	}
}
