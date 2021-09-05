package mock;

import adeo.leroymerlin.cdp.model.Member;

import java.util.HashSet;
import java.util.Set;

public class MockMembers {

    public static Set<Member> createMockedMembers() {
        Set<Member> members = new HashSet<>();

        Member member = new Member();
        member.setName("Member");

        members.add(member);

        return members;
    }

}
