package kr.co.thecheck.domainh2.domain.user.service;

import kr.co.thecheck.domainh2.domain.user.entity.Member;
import kr.co.thecheck.domainh2.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);

    }
}
