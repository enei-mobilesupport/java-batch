package foo.sample.db.auto.ref.crud;

import java.util.List;
import foo.sample.db.auto.ref.model.商品マスタ;
import foo.sample.db.auto.ref.model.商品マスタExample;
import foo.sample.db.auto.ref.model.商品マスタKey;

public interface 商品マスタMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 商品マスタ
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    long countByExample(商品マスタExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 商品マスタ
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    List<商品マスタ> selectByExample(商品マスタExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 商品マスタ
     *
     * @mbg.generated Wed Dec 26 15:29:57 JST 2018
     */
    商品マスタ selectByPrimaryKey(商品マスタKey key);
}