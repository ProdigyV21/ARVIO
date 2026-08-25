package io.ktor.util.converters;

import io.ktor.http.LinkHeader;
import io.ktor.util.reflect.TypeInfo;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u0004\u0018\u00010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0001H&¨\u0006\n"}, d2 = {"Lio/ktor/util/converters/ConversionService;", "", "fromValues", "values", "", "", LinkHeader.Parameters.Type, "Lio/ktor/util/reflect/TypeInfo;", "toValues", "value", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ConversionService {
    Object fromValues(List<String> values, TypeInfo type);

    List<String> toValues(Object value);
}
