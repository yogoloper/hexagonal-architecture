package tobyspring.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    @Test
    void createMember() {
        var member = new Member("toby@splearn.app", "Toby", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void constructorNullCheck() {
        assertThatThrownBy(() -> new Member(null, "Toby", "secret")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate() {
        var member = new Member("toby@splearn.app", "Toby", "secret");

        member.avtivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {
        var member = new Member("toby@splearn.app", "Toby", "secret");

        member.avtivate();

        assertThatThrownBy(() -> {
            member.avtivate();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        var member = new Member("toby@splearn.app", "Toby", "secret");
        member.avtivate();

        member.deavtivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
        
    }

    @Test
    void deactivateFail() {
        var member = new Member("toby@splearn.app", "Toby", "secret");

        assertThatThrownBy(member::deavtivate).isInstanceOf(IllegalStateException.class);

        member.avtivate();
        member.deavtivate();

        assertThatThrownBy(member::deavtivate).isInstanceOf(IllegalStateException.class);
    }
}