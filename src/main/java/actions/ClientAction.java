package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ClientView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.ClientService;

/**
 * 顧客に関わる処理を行うActionクラス
 *
 */
public class ClientAction extends ActionBase {

    private ClientService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new ClientService();

        // メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        // 指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<ClientView> clients = service.getPerPage(page);

        // 全ての顧客データの件数を取得
        long clientCount = service.countAll();

        putRequestScope(AttributeConst.CLIENTS, clients); // 取得した顧客データ
        putRequestScope(AttributeConst.CLI_COUNT, clientCount); // 全ての顧客データの件数
        putRequestScope(AttributeConst.PAGE, page); // ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); // 1ページに表示するレコードの数

        // セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        // 一覧画面を表示
        forward(ForwardConst.FW_EMP_INDEX);


    }

}
