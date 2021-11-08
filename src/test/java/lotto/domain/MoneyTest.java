package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("입력 금액 생성 테스트")
    public void money() {
        Money money = new Money(BigDecimal.valueOf(1000));

        Money expected = new Money(BigDecimal.valueOf(1000));

        assertThat(money).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력 금액 테스트(0원)")
    public void money2() {
        Money money = new Money(BigDecimal.valueOf(0));
        LottoMachine machine = new LottoMachine();

        int count = machine.getLottoList(money).size();

        assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("구매 회수 테스트")
    public void money3() {
        Money money = new Money(BigDecimal.valueOf(1000));
        LottoMachine machine = new LottoMachine();

        int purchaseCount = machine.getLottoList(money).size();

        assertThat(purchaseCount).isEqualTo(1);
    }

    @Test
    @DisplayName("입력 금액 나머지 존재")
    public void money4() {
        assertThatThrownBy(() -> {
            LottoMachine machine = new LottoMachine();
            Money money = new Money(BigDecimal.valueOf(1500));

            machine.getLottoList(money);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}