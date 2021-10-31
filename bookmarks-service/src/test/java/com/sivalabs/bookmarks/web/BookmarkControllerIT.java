package com.sivalabs.bookmarks.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(PostgresResource.class)
public class BookmarkControllerIT {
    @Inject
    BookmarkService bookmarkService;

    @BeforeEach
    void setUp() {
        Bookmark bookmark1 = new Bookmark(null, "Bookmark1", "https://bookmark1.com");
        Bookmark bookmark2 = new Bookmark(null, "Bookmark2", "https://bookmark2.com");
        bookmarkService.createBookmark(bookmark1);
        bookmarkService.createBookmark(bookmark2);
    }

    @Test
    public void shouldGetBookmarks() {
        given()
            .when().get("/api/v1/bookmarks")
            .then()
            .statusCode(200)
            .body("size()", is(2));
    }
}
