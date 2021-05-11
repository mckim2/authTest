package kr.co.thecheck.domainh2.domain.member.constant;

import kr.co.thecheck.domainh2.infra.constant.EnumType;

/**
 *
 * 회원 롤 타입
 *
 */
public enum RoleType implements EnumType {

    Manager("매니저"),
    Team("팀"),
    HeadQuaters("본부"),
    Center("센터");

    protected String name;

    RoleType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
