package io.ktor.http;

import io.ktor.util.StringValues;
import io.ktor.util.StringValuesBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a\u001b\u0010\f\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/util/StringValuesBuilder;", "parameters", "Lio/ktor/http/Parameters;", "decodeParameters", "(Lio/ktor/util/StringValuesBuilder;)Lio/ktor/http/Parameters;", "Lio/ktor/util/StringValues;", "Lio/ktor/http/ParametersBuilder;", "encodeParameters", "(Lio/ktor/util/StringValues;)Lio/ktor/http/ParametersBuilder;", "Lx6/t0;", "appendAllDecoded", "(Lio/ktor/util/StringValuesBuilder;Lio/ktor/util/StringValuesBuilder;)V", "appendAllEncoded", "(Lio/ktor/util/StringValuesBuilder;Lio/ktor/util/StringValues;)V", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UrlDecodedParametersBuilderKt {
    private static final void appendAllDecoded(StringValuesBuilder stringValuesBuilder, StringValuesBuilder stringValuesBuilder2) {
        for (String str : stringValuesBuilder2.names()) {
            List<String> all = stringValuesBuilder2.getAll(str);
            if (all == null) {
                all = z.f19728i;
            }
            String strDecodeURLQueryComponent$default = CodecsKt.decodeURLQueryComponent$default(str, 0, 0, false, null, 15, null);
            ArrayList arrayList = new ArrayList(s.U(all, 10));
            Iterator<T> it = all.iterator();
            while (it.hasNext()) {
                arrayList.add(CodecsKt.decodeURLQueryComponent$default((String) it.next(), 0, 0, true, null, 11, null));
            }
            stringValuesBuilder.appendAll(strDecodeURLQueryComponent$default, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void appendAllEncoded(StringValuesBuilder stringValuesBuilder, StringValues stringValues) {
        for (String str : stringValues.names()) {
            List<String> all = stringValues.getAll(str);
            if (all == null) {
                all = z.f19728i;
            }
            String strEncodeURLParameter$default = CodecsKt.encodeURLParameter$default(str, false, 1, null);
            ArrayList arrayList = new ArrayList(s.U(all, 10));
            Iterator<T> it = all.iterator();
            while (it.hasNext()) {
                arrayList.add(CodecsKt.encodeURLParameterValue((String) it.next()));
            }
            stringValuesBuilder.appendAll(strEncodeURLParameter$default, arrayList);
        }
    }

    public static final Parameters decodeParameters(StringValuesBuilder stringValuesBuilder) {
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        appendAllDecoded(parametersBuilderParametersBuilder$default, stringValuesBuilder);
        return parametersBuilderParametersBuilder$default.build();
    }

    public static final ParametersBuilder encodeParameters(StringValues stringValues) {
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        appendAllEncoded(parametersBuilderParametersBuilder$default, stringValues);
        return parametersBuilderParametersBuilder$default;
    }
}
