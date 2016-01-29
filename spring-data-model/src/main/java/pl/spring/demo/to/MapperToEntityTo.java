package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MapperToEntityTo {

	public static BookTo mapTo(BookEntity bookEntity) {
		if (bookEntity != null) {
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthors());
		}
		return null;
	}

	public static BookEntity mapEntity(BookTo bookTo) {
		if (bookTo != null) {
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), bookTo.getAuthors());
		}
		return null;
	}

	public static List<BookTo> mapListTO(List<BookEntity> listEntity) {
		if (listEntity != null) {
			List<BookTo> listTo = new ArrayList<>();
			for (BookEntity entity : listEntity) {
				listTo.add(mapTo(entity));
			}
			return listTo;
		}
		return null;
	}

	public static List<BookEntity> mapListEntity(List<BookTo> listTo) {
		if (listTo != null) {
			List<BookEntity> listEntity = new ArrayList<>();
			for (BookTo To : listTo) {
				listEntity.add(mapEntity(To));
			}
			return listEntity;
		}
		return null;
	}

}
