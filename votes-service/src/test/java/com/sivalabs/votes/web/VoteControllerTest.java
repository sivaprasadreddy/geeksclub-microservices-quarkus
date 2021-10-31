package com.sivalabs.votes.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;

import com.sivalabs.votes.domain.Vote;
import com.sivalabs.votes.domain.VoteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class VoteControllerTest {
    @InjectMock
    private VoteService voteService;

    @Test
    public void shouldGetVotesForBookmarkIds() {
        Mockito.when(voteService.getVotes(any())).thenReturn(List.of(new Vote()));
        given()
            .when().get("/api/v1/votes?bookmarkIds=1,2,3")
            .then()
            .statusCode(200)
            .body("size()", is(1));
    }
}
