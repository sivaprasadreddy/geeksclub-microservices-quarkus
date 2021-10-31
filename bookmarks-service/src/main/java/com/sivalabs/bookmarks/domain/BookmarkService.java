package com.sivalabs.bookmarks.domain;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public List<Bookmark> getBookmarks() {
        return this.bookmarkRepository.listAll();
    }


    public Bookmark createBookmark(Bookmark bookmark) {
        this.bookmarkRepository.persist(bookmark);
        return bookmark;
    }
}
