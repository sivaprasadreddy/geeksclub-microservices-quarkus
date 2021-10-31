package com.sivalabs.votes.domain;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getVotes(List<Long> bookmarkIds) {
        return this.voteRepository.list("select v from Vote v where v.bookmarkId in ?1", bookmarkIds);
    }

    public Vote upVote(Long bookmarkId) {
        Vote vote = getVote(bookmarkId);
        vote.upVotes = vote.upVotes +1;
        vote.persist();
        return vote;
    }

    public Vote downVote(Long bookmarkId) {
        Vote vote = getVote(bookmarkId);
        vote.downVotes = vote.downVotes +1;
        vote.persist();
        return vote;
    }

    private Vote getVote(Long bookmarkId) {
        Optional<Vote> optionalVote = this.voteRepository.find("bookmarkId", bookmarkId).firstResultOptional();
        Vote vote;
        if(optionalVote.isEmpty()) {
            vote = new Vote();
            vote.bookmarkId = bookmarkId;
        } else {
            vote = optionalVote.get();
        }
        return vote;
    }
}
