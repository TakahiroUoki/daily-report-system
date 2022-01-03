package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 顧客データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CLI)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CLI_GET_ALL,
            query = JpaConst.Q_CLI_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_CLI_COUNT,
            query = JpaConst.Q_CLI_COUNT_DEF)
})
@Getter // 全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter // 全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor // 引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor // 全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Client {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CLI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 氏名
     */
    @Column(name = JpaConst.CLI_COL_NAME, nullable = false)
    private String name;

    /**
     * 部署
     */
    @Column(name = JpaConst.CLI_COL_DEPART, nullable = false)
    private String depart;

    /**
     * 課
     */
    @Column(name = JpaConst.CLI_COL_DIVISION, nullable = false)
    private String division;

    /**
     * 役職
     */
    @Column(name = JpaConst.CLI_COL_POSITION, nullable = false)
    private String position;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.CLI_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.CLI_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除された顧客かどうか(現役:0、削除済み:1)
     */
    @Column(name = JpaConst.CLI_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;


}
