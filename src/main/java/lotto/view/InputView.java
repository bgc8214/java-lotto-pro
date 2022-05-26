package lotto.view;

import java.util.List;
import java.util.Scanner;
import lotto.WinningLotto;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoUtil;
import lotto.strategy.ManualPickNumberStrategy;

public class InputView {

    private static final String SPLIT_DELIMITER = "[\\s,]+";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int[] enterManualLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            return LottoUtil.convertLottoStringToIntArray(enterString().split(SPLIT_DELIMITER));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterManualLotto();
        }
    }


    public static WinningLotto enterWinningLotto() {
        try {
            int[] lottoNumber = enterManualLotto();
            List<LottoNumber> lottoNumbers = LottoUtil.convertToLottoNumber(lottoNumber);
            Lotto lotto = new Lotto(new ManualPickNumberStrategy(lottoNumbers));
            LottoNumber bonusLottoNumber = new LottoNumber(enterBonusNumber());
            return new WinningLotto(lotto, bonusLottoNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterWinningLotto();
        }
    }


    public static String enterString() {
        return SCANNER.nextLine();
    }

    public static int enterBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요");
        return enterNumber();
    }


    public static int enterPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요");
        return enterNumber();
    }

    public static int enterManualLottoCount(int numberOfLottoCanBuy) {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
        try {
            int purchaseManualLottoCount = enterNumber();
            LottoUtil.checkManualLottoBuyCount(numberOfLottoCanBuy, purchaseManualLottoCount);
            return purchaseManualLottoCount;
        } catch (IllegalArgumentException e) {
            return enterManualLottoCount(numberOfLottoCanBuy);
        }
    }


    private static int enterNumber() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값을 입력하였습니다");
            SCANNER.reset();
            return enterNumber();
        }
    }

    public static int[][] enterManualLottos(int manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해주세요.");
        int[][] manualLottos = new int[manualLottoCount][LottoUtil.LOTTO_SIZE];
        for (int i = 0; i < manualLottoCount; i++) {
            enterManualLotto(manualLottos, i);
        }
        return manualLottos;
    }

    public static int[] enterManualLotto(int[][] manualLottos, int index) {
        try {
            manualLottos[index] = LottoUtil.convertLottoStringToIntArray(
                enterString().split(SPLIT_DELIMITER));
            LottoUtil.validateDuplication(manualLottos[index]);
            return manualLottos[index];
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterManualLotto(manualLottos, index);
        }
    }
}