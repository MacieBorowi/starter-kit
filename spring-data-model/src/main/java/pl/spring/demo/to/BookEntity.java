package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookEntity implements IdAware {

	private final static String SEPARATOR_AUTHORS = "$";
	private final static String SEPARATOR_ONE_AUTHOR = ";";

	private Long id;
	private String title;
	private List<AuthorEntity> authors;

	public BookEntity() {
	}

	public BookEntity(Long id, String title, String authors) {
		this.id = id;
		this.title = title;
		this.authors = new ArrayList<AuthorEntity>();
		this.setAuthors(authors);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		String allAuthors = "";
		if (authors != null) {
			Iterator<AuthorEntity> iter = authors.iterator();
			while (iter.hasNext()) {
				AuthorEntity author = iter.next();
				allAuthors += author.getId() + BookEntity.SEPARATOR_ONE_AUTHOR + author.getFirstName()
						+ BookEntity.SEPARATOR_ONE_AUTHOR + author.getLastName();
				if (iter.hasNext()) {
					allAuthors += BookEntity.SEPARATOR_AUTHORS;
				}
			}
		}
		return allAuthors;
	}

	public List<AuthorEntity> getAuthorsList() {
		return this.authors;
	}

	public void setAuthors(String authors) {
		String[] autorsList = authors.split(BookEntity.SEPARATOR_AUTHORS);
		for (String author : autorsList) {
			addAuthor(author);
		}
	}

	public void addAuthor(String author) {
		String[] autorInfo = author.split(BookEntity.SEPARATOR_ONE_AUTHOR);
		if (autorInfo.length == 3) {
			AuthorEntity authorEntity = new AuthorEntity(autorInfo[0], autorInfo[1], autorInfo[2]);
			addIfNotInbase(authorEntity);
		}

	}

	private void addIfNotInbase(AuthorEntity authorEntity) {
		if (!this.authors.contains(authorEntity))
			this.authors.add(authorEntity);
	}
}
