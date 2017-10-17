package com.apress.springrecipes.court.service;

import java.util.Collection;

import com.apress.springrecipes.court.domain.Member;

/**
 * Created by marten on 16-06-14.
 */
public interface MemberService {

    Collection<Member> findAll();
    Member find(long id);
}
