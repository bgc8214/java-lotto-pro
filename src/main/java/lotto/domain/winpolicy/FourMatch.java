package lotto.domain.winpolicy;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class FourMatch extends Policy {
  public FourMatch() {
    super(50_000);
  }

  @Override
  public Boolean isMatch(Lotto latestWinLotto, Lotto lotto, LottoNumber bonusNumber) {
    if (latestWinLotto.matchCountOf(lotto).equals(4)) {
      return true;
    }

    return false;
  }
}
