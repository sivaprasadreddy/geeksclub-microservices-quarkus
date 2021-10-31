package com.sivalabs.bookmarks.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookmarkRepository implements PanacheRepository<Bookmark> {

}
