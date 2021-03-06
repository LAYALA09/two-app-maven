package ar.com.ada.second.tdvr.model.mapper.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import java.time.Year;

@Converter(autoApply=true)
public class YearAttributeConverter implements AttributeConverter<Year,Short> {
}
