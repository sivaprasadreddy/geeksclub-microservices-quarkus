package com.sivalabs.bookmarks.web;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkDTO;
import com.sivalabs.bookmarks.domain.BookmarkService;
import com.sivalabs.bookmarks.domain.BookmarkVotes;
import com.sivalabs.bookmarks.domain.VoteServiceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v1/bookmarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final VoteServiceClient voteServiceClient;

    public BookmarkController(BookmarkService bookmarkService,
        @RestClient VoteServiceClient voteServiceClient) {
        this.bookmarkService = bookmarkService;
        this.voteServiceClient = voteServiceClient;
    }

    @GET
    @Traced
    public List<BookmarkDTO> getBookmarks() {
        List<Bookmark> bookmarks = bookmarkService.getBookmarks();
        List<BookmarkVotes> votes = new ArrayList<>(0);
        if(!bookmarks.isEmpty()) {
            String bookmarkIds = bookmarks.stream().map(b -> Long.toString(b.id)).collect(Collectors.joining(","));
            votes = voteServiceClient.getVotesByBookmarks(bookmarkIds);
        }
        Map<Long, BookmarkVotes> voteCountMap = votes.stream().collect(Collectors.toMap(BookmarkVotes::getBookmarkId, v -> v));
        return bookmarks.stream()
            .map(b -> BookmarkDTO.toBookmarkDTO(b, voteCountMap.get(b.id)))
            .collect(Collectors.toList());
    }

    @POST
    public Bookmark createBookmark(Bookmark bookmark) {
        bookmark.id = null;
        return bookmarkService.createBookmark(bookmark);
    }
}
