package foo.sample.db.custom.ref.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsProduct {

    // SMSで定義している商品コード
    private String productCode;
    // SMSで定義している商品名
    private String productName;

}
