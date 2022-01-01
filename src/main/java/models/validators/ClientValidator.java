package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ClientView;
import constants.MessageConst;
import services.ClientService;

/**
 * 顧客インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class ClientValidator {

    /**
     * 顧客インスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param cv ClientServiceのインスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ClientService service, ClientView cv){
        List<String> errors = new ArrayList<String>();

        // 氏名のチェック
        String nameError = validateName(cv.getName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        // 部署のチェック
        String departError = validateDepart(cv.getDepart());
        if(!departError.equals("")) {
            errors.add(departError);
        }

        return errors;
    }

    /**
     * 氏名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 氏名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {

        if(name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        // 入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 部署に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param depart 部署
     * @return エラーメッセージ
     */
    private static String validateDepart(String depart) {

        if(depart == null || depart.equals("")) {
            return MessageConst.E_NODEPART.getMessage();
        }

        // 入力値がある場合は空文字を返却
        return "";
    }

}