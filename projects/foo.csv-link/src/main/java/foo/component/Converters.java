package foo.sample.component;

import static foo.sample.constant.ResultCode.NG_PARSE_ERROR;
import static org.apache.commons.lang3.time.DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import foo.sample.batch.exception.ServiceException;
import foo.sample.constant.base.BaseCodeIf;

/**
 * Jsonと Request/Response間の変換.
 */
public class Converters {

    /**
     * enum型の Requestを Jsonにマッピング.
     */
    @Component
    public class EnumToCodeConverter<E extends Enum<E>> implements Converter<E, String> {
        @Override
        @SuppressWarnings("unchecked")
        public String convert(E enumeration) {
            if (enumeration == null) {
                return null;
            }
            return ((BaseCodeIf<E>) enumeration).getCode();
        }
    }

    /**
     * Jsonの Responseを enum型にマッピング.
     */
    @Component
    public class CodeToEnumConverter<E extends Enum<E>> implements Converter<String, E> {
        @Override
        @SuppressWarnings("unchecked")
        public E convert(String code) {

            return BaseCodeIf.getEnum((Class<? extends BaseCodeIf<E>>) getClass(), code);
        }
    }

    /**
     * Date型の Requestを Jsonにマッピング.
     */
    @Component
    public class DateToStringConverter implements Converter<Date, String> {

        /** 日本の時差（ISO8601形式） */
        public static final String TIME_DIFFERENCE = "+09:00";

        @Override
        public String convert(Date date) {
            if (date == null) {
                return null;
            }
            return DateFormatUtils.format(date, ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern() + TIME_DIFFERENCE,
                    TimeZone.getDefault(), Locale.JAPAN);
        }
    }

    /**
     * Jsonの Responseを Date型にマッピング.
     */
    @Component
    public class StringToDateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String str) {
            if (StringUtils.isBlank(str)) {
                return null;
            }
            try {
                return ISO_8601_EXTENDED_DATETIME_FORMAT.parse(str);

            } catch (ParseException e) {
                throw new ServiceException(NG_PARSE_ERROR, e);
            }
        }
    }

}
