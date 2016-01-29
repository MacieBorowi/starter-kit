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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!getAuthors().equals(other.getAuthors()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
