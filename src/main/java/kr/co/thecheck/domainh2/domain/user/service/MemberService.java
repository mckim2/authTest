package kr.co.thecheck.domainh2.domain.user.service;

import kr.co.thecheck.domainh2.domain.user.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findByUsername(String username);

}
