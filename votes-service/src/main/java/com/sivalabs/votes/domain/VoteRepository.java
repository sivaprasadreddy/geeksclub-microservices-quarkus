package com.sivalabs.votes.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VoteRepository implements PanacheRepository<Vote> {

}
