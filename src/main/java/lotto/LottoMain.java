package lotto;

import lotto.domain.*;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoMain {
    private static final LottoService lottoService = new LottoService();

    public static void main(String[] args) {
        int purchaseMoney = InputView.printInputMoney();
        int countOfLotto = Seller.returnLotto(purchaseMoney);
        OutputView.printLottoCount(countOfLotto);

        Lottos lottos = lottoService.createLotto(countOfLotto);
        OutputView.printLotto(lottos.toString());

        List<Integer> winningLottoNumbers = InputView.printInputWinningLotto();
        int bonusBallNumber = InputView.printInputBonusNumber();
        lottoService.makeWinningLotto(winningLottoNumbers, bonusBallNumber);

        Map<Rank, Integer> lottoResult = lottoService.result(lottos);
        OutputView.printResult(lottoResult, purchaseMoney);
    }

}
