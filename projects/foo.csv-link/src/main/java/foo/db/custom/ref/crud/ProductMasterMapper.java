package foo.sample.db.custom.ref.crud;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import foo.sample.db.custom.ref.model.SmsProduct;

@Mapper
public interface ProductMasterMapper {

    SmsProduct findOne(@Param("code") String code);
    
}
