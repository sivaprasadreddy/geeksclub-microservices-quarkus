package com.sivalabs.votes.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.sivalabs.votes.domain.VoteService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(PostgresResource.class)
class VoteControllerIT {
    @Inject
    private VoteService voteService;

    @BeforeEach
    void setUp() {
        voteService.upVote(1L);
        voteService.downVote(2L);
    }

    @Test
    public void shouldGetVotesForBookmarkIds() {
        given()
            .when().get("/api/v1/votes?bookmarkIds=1,2,3")
            .then()
            .statusCode(200)
            .body("size()", is(2));
    }
}
