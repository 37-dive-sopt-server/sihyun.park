package or.service;

import or.domain.Member;
import or.repository.MemoryMemberRepository;
import java.util.List;

import java.util.Optional;

public class MemberServiceImpl {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    private static long sequence = 1L;

    public boolean validEmail(String email){
        List<Member> allMembers = memberRepository.findAll();
        boolean exists = allMembers
                .stream()
                .anyMatch(member -> member.getEmail().equals(email));
        return exists;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public Long join(String name, String birth, String email, Member.Gender gender) {
        validEmail(email);
        Member member = new Member(sequence++, name,birth,email,gender);
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