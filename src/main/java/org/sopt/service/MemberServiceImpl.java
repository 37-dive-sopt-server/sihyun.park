package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemoryMemberRepository memberRepository;

    public MemberServiceImpl(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private static long sequence = 1L;

    public boolean validEmail(String email) {
        List<Member> allMembers = memberRepository.findAll();
        boolean exists = allMembers
                .stream()
                .anyMatch(member -> member.getEmail().equals(email));
        return exists;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public Long join(String name, String birth, String email, Gender gender) {
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }

        if (validEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        int userAge = getAge(birth);
        if (userAge < 20) {
            throw new IllegalArgumentException("20세 미만은 회원가입이 불가합니다.");
        }

        Member member = new Member(sequence++, name, birth, email, gender);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public int getAge(String birth) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDay = LocalDate.parse(birth, formatter);
        int age = Period.between(birthDay, now).getYears();
        return age;
    }
}