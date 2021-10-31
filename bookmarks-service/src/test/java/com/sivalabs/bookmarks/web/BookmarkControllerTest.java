package com.sivalabs.bookmarks.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class BookmarkControllerTest {
    @InjectMock
    private BookmarkService bookmarkService;

    @Test
    public void shouldGetBookmarks() {
        Mockito.when(bookmarkService.getBookmarks()).thenReturn(List.of(new Bookmark()));
        given()
            .when().get("/api/v1/bookmarks")
            .then()
            .statusCode(200)
            .body("size()", is(1));
    }
}
