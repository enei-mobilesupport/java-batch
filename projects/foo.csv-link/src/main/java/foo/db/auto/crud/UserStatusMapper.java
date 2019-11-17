package foo.sample.db.auto.crud;

import java.util.List;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusExample;
import foo.sample.db.auto.model.UserStatusKey;
import org.apache.ibatis.annotations.Param;

public interface UserStatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    long countByExample(UserStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int deleteByExample(UserStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int deleteByPrimaryKey(UserStatusKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int insert(UserStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int insertSelective(UserStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    List<UserStatus> selectByExample(UserStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    UserStatus selectByPrimaryKey(UserStatusKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int updateByExampleSelective(@Param("record") UserStatus record, @Param("example") UserStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int updateByExample(@Param("record") UserStatus record, @Param("example") UserStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int updateByPrimaryKeySelective(UserStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_STATUS
     *
     * @mbg.generated Wed Dec 26 15:27:34 JST 2018
     */
    int updateByPrimaryKey(UserStatus record);
}