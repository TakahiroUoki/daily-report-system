package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ClientConverter;
import actions.views.ClientView;
import constants.JpaConst;
import models.Client;
import models.validators.ClientValidator;

/**
 * 顧客テーブルの操作に関わる処理を行うクラス
 */
public class ClientService  extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、ClientViewのリストで返却
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<ClientView> getPerPage(int page){
        List<Client> clients = em.createNamedQuery(JpaConst.Q_EMP_GET_ALL, Client.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return ClientConverter.toViewList(clients);
    }

    /**
     * 顧客テーブルのデータの件数を取得し、返却する
     * @return 顧客テーブルのデータの件数
     */
    public long countAll() {
        long cliCount = (long) em.createNamedQuery(JpaConst.Q_CLI_COUNT, Long.class)
                .getSingleResult();

        return cliCount;
    }

    /**
     * idを条件に取得したデータをClientViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ClientView findOne(int id) {
        Client c = findOneInternal(id);
        return ClientConverter.toView(c);
    }

    /**
     * 画面から入力された顧客の登録内容をもとにデータを1件作成し、顧客テーブルに登録する
     * @param cv 画面から入力された顧客の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(ClientView cv, String pepper) {

        // 登録日時、更新日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        cv.setCreatedAt(now);
        cv.setUpdatedAt(now);

        // 登録内容のバリデーションを行う
        List<String> errors = ClientValidator.validate(cv);

        // バリデーションエラーがなければデータを登録する
        if(errors.size() == 0) {
            create(cv);
        }

        // エラーを返却(エラーがなければ0件の空リスト)
        return errors;
    }

    /**
     * 画面から入力された顧客の更新内容を元にデータを1件作成し、顧客テーブルを更新する
     * @param cv 画面から入力された顧客の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(ClientView cv, String pepper) {

        //idを条件に登録済みの従業員情報を取得する
       ClientView savedCli = findOne(cv.getId());

        savedCli.setName(cv.getName()); //変更後の氏名を設定する
        savedCli.setDepart(cv.getDepart()); // 変更後の部署を設定する
        savedCli.setDivision(cv.getDivision()); // 変更後の課を設定する
        savedCli.setPosition(cv.getPosition()); // 変更後の役職を設定する

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedCli.setUpdatedAt(today);

        //更新内容についてバリデーションを行う
        List<String> errors = ClientValidator.validate(savedCli);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            update(savedCli);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件に顧客データを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの顧客情報を取得する
        ClientView savedCli = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedCli.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedCli.setDeleteFlag(JpaConst.CLI_DEL_TRUE);

        //更新処理を行う
        update(savedCli);

    }

    /**
     * idを条件にデータを1件取得し、Clientのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Client findOneInternal(int id) {
        Client c = em.find(Client.class, id);

        return c;
    }

    /**
     * 顧客データを1件登録する
     * @param cv 顧客データ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void create(ClientView cv) {

        em.getTransaction().begin();
        em.persist(ClientConverter.toModel(cv));
        em.getTransaction().commit();

    }

    /**
     * 顧客データを更新する
     * @param cv 画面から入力された顧客の登録内容
     */
    private void update(ClientView cv) {

        em.getTransaction().begin();
        Client c = findOneInternal(cv.getId());
        ClientConverter.copyViewToModel(c, cv);
        em.getTransaction().commit();

    }

}
