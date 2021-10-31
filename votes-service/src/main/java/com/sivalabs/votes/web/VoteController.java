package com.sivalabs.votes.web;

import com.sivalabs.votes.domain.Vote;
import com.sivalabs.votes.domain.VoteService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.opentracing.Traced;

@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GET
    @Path("votes")
    @Traced
    public List<Vote> getVotes(@QueryParam("bookmarkIds") String bookmarkIdsString) {

        List<Long> bookmarkIds = Arrays.stream(bookmarkIdsString.split(","))
            .mapToLong(Long::valueOf)
            .boxed()
            .collect(Collectors.toList());
        return voteService.getVotes(bookmarkIds);
    }

    @PUT
    @Path("/bookmarks/{bookmarkId}/votes/up")
    @Traced
    public Vote upVote(@PathParam("bookmarkId") Long bookmarkId) {
        return voteService.upVote(bookmarkId);
    }

    @PUT
    @Path("/bookmarks/{bookmarkId}/votes/down")
    @Traced
    public Vote downVote(@PathParam("bookmarkId") Long bookmarkId) {
        return voteService.downVote(bookmarkId);
    }
}
