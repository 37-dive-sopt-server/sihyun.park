package or.service;

import or.domain.Member;
import or.repository.MemoryMemberRepository;
import java.util.List;

import java.util.Optional;

public class MemberServiceImpl {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    private static long sequence = 1L;

    public Long join(String name) {
        Member member = new Member(sequence++, name);
        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}