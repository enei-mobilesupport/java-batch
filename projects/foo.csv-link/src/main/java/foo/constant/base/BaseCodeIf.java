package foo.sample.constant.base;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * コードのEnumの共通処理.
 */
public interface BaseCodeIf<E extends Enum<E>> {

    /**
     * コード値を返却.
     */
    @JsonValue
    String getCode();

    /**
     * 表示の順番を返却.
     * 
     * @return 表示順.
     */
    default int getOrder() {
        if (StringUtils.isNumeric(getCode())) {
            return Integer.parseInt(getCode());
        } else {
            return 0;
        }
    }

    /**
     * Enumに変換.
     * 
     * @return Enum
     */
    @SuppressWarnings("unchecked")
    default E toEnum() {
        return (E) this;
    }

    /**
     * コード値が同一かどうかをチェック.
     * 
     * @param code コード値
     * @return true: 同一
     */
    default boolean equalsByCode(String code) {
        return getCode().equalsIgnoreCase(code);
    }

    /**
     * 指定されたBaseCodeIfを実装したEnumを表示順にソートしたリストを返却.
     * 
     * @param clazz フルクラス名
     * @return EnumのList
     */
    public static <E extends Enum<E>> List<E> getOrderedList(Class<? extends BaseCodeIf<E>> clazz) {

        return Arrays.stream(clazz.getEnumConstants())
                .sorted(Comparator.comparing(BaseCodeIf::getOrder))
                .map(BaseCodeIf::toEnum)
                .collect(Collectors.toList());
    }

    /**
     * 指定されたBaseCodeIfを実装したEnumの、指定されたコード値の列挙子を返却.
     * 
     * @param clazz フルクラス名
     * @param code  コード値
     * @return Enum
     */
    public static <E extends Enum<E>> E getEnum(Class<? extends BaseCodeIf<E>> clazz, String code) {

        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.equalsByCode(code))
                .map(BaseCodeIf::toEnum)
                .findFirst()
                .orElse(null);
    }

    /**
     * 指定されたBaseCodeIfを実装したEnumの、指定されたコード値の列挙子のOptionalを返却.
     * 
     * @param clazz フルクラス名
     * @param code  コード値
     * @return EnumのOptional
     */
    public static <E extends Enum<E>> Optional<E> getOptional(Class<? extends BaseCodeIf<E>> clazz, String code) {
        return Optional.ofNullable(BaseCodeIf.getEnum(clazz, code));
    }

    /**
     * 指定されたBaseCodeIfのコード値をキー、コード値に該当するBaseCodeIfを値に持つMapを返却.
     * 
     * @param clazz フルクラス名
     * @return EnumのMap
     */
    public static <E extends Enum<E>> Map<String, E> getMap(Class<? extends BaseCodeIf<E>> clazz) {

        return Arrays.stream(clazz.getEnumConstants())
                .collect(Collectors.toMap(BaseCodeIf::getCode, BaseCodeIf::toEnum));
    }

    /**
     * 指定されたBaseCodeIfに、指定されたコード値を持つ列挙子が存在するかチェック.
     * 
     * @param clazz フルクラス名
     * @param code  コード値
     * @return true: コード値を持つ列挙子が存在
     */
    public static <E extends Enum<E>> boolean hasCode(Class<? extends BaseCodeIf<E>> clazz, String code) {

        return Arrays.stream(clazz.getEnumConstants())
                .anyMatch(e -> e.equalsByCode(code));
    }
}
