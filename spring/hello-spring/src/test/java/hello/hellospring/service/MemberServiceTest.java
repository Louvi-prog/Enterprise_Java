package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void aferEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member=new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicateJoin() {
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}