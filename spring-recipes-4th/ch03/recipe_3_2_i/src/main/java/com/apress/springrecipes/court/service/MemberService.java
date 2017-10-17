package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Member;

import java.util.List;

public interface MemberService {

    void add(Member member);

    void remove(String memberName);

    List<Member> list();
}
