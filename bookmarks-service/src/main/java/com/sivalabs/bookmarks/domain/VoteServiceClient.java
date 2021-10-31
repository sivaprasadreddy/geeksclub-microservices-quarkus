package com.sivalabs.bookmarks.domain;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/v1")
@RegisterRestClient(configKey="votes-api")
public interface VoteServiceClient {
    @GET
    @Path("/votes")
    List<BookmarkVotes> getVotesByBookmarks(@QueryParam("bookmarkIds") String bookmarkIds);
}
