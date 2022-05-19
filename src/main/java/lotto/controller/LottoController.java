package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPrice;
import lotto.domain.LottoUtil;
import lotto.strategy.ManualPickNumberStrategy;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public static void startLotto() {
        int purchaseAmount = InputView.enterNumber();
        int autoLottoCount = LottoPrice.numberOfLottoCanBuy(purchaseAmount);
        LottoManager lottoManager = new LottoManager(autoLottoCount);

        List<Lotto> lottoElements = lottoManager.getLottoElements();

        ResultView.purchaseLottoResult(autoLottoCount, lottoElements);

        int[] numberArray = InputView.enterManualLotto();
        List<LottoNumber> lottoNumbers = LottoUtil.convertToLottoNumber(numberArray);
        Lotto winningLotto = new Lotto(new ManualPickNumberStrategy(lottoNumbers));

        lottoManager.makeWinningLotto(winningLotto);

        ResultView.winningResult(lottoManager.getWinningStatistics());
        ResultView.yieldResult(lottoManager.calculateRateOfReturn(purchaseAmount));
    }
}
