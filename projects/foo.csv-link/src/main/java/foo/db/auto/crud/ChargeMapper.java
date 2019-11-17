package foo.sample.db.auto.crud;

import java.util.List;
import foo.sample.db.auto.model.Charge;
import foo.sample.db.auto.model.ChargeExample;
import foo.sample.db.auto.model.ChargeKey;

public interface ChargeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CHARGE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    long countByExample(ChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CHARGE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int insert(Charge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CHARGE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int insertSelective(Charge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CHARGE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    List<Charge> selectByExample(ChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CHARGE
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    Charge selectByPrimaryKey(ChargeKey key);
}