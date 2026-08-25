package io.ktor.http;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.util.concurrent.r0;
import io.ktor.http.Parameters;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\r\n\u0002\b\u0005\u001a3\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\f\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\f\u0010\r\u001a;\u0010\u0011\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a'\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0019\u0010\u0018¨\u0006\u001a"}, d2 = {"", "query", "", "startIndex", "limit", "", "decode", "Lio/ktor/http/Parameters;", "parseQueryString", "(Ljava/lang/String;IIZ)Lio/ktor/http/Parameters;", "Lio/ktor/http/ParametersBuilder;", "Lx6/t0;", "parse", "(Lio/ktor/http/ParametersBuilder;Ljava/lang/String;IIZ)V", "nameIndex", "equalIndex", "endIndex", "appendParam", "(Lio/ktor/http/ParametersBuilder;Ljava/lang/String;IIIZ)V", TtmlNode.START, TtmlNode.END, "", "text", "trimEnd", "(IILjava/lang/CharSequence;)I", "trimStart", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QueryKt {
    private static final void appendParam(ParametersBuilder parametersBuilder, String str, int i10, int i11, int i12, boolean z) {
        if (i11 == -1) {
            int iTrimStart = trimStart(i10, i12, str);
            int iTrimEnd = trimEnd(iTrimStart, i12, str);
            if (iTrimEnd > iTrimStart) {
                parametersBuilder.appendAll(z ? CodecsKt.decodeURLQueryComponent$default(str, iTrimStart, iTrimEnd, false, null, 12, null) : str.substring(iTrimStart, iTrimEnd), z.f19728i);
                return;
            }
            return;
        }
        int iTrimStart2 = trimStart(i10, i11, str);
        int iTrimEnd2 = trimEnd(iTrimStart2, i11, str);
        if (iTrimEnd2 > iTrimStart2) {
            String strDecodeURLQueryComponent$default = z ? CodecsKt.decodeURLQueryComponent$default(str, iTrimStart2, iTrimEnd2, false, null, 12, null) : str.substring(iTrimStart2, iTrimEnd2);
            int iTrimStart3 = trimStart(i11 + 1, i12, str);
            int iTrimEnd3 = trimEnd(iTrimStart3, i12, str);
            parametersBuilder.append(strDecodeURLQueryComponent$default, z ? CodecsKt.decodeURLQueryComponent$default(str, iTrimStart3, iTrimEnd3, true, null, 8, null) : str.substring(iTrimStart3, iTrimEnd3));
        }
    }

    private static final void parse(ParametersBuilder parametersBuilder, String str, int i10, int i11, boolean z) {
        int i12;
        int i13;
        int iB0 = o.b0(str);
        int i14 = 0;
        if (i10 <= iB0) {
            int i15 = -1;
            int i16 = i10;
            int i17 = i16;
            while (i14 != i11) {
                char cCharAt = str.charAt(i17);
                if (cCharAt == '&') {
                    appendParam(parametersBuilder, str, i16, i15, i17, z);
                    i14++;
                    i15 = -1;
                    i16 = i17 + 1;
                } else if (cCharAt == '=' && i15 == -1) {
                    i15 = i17;
                }
                if (i17 != iB0) {
                    i17++;
                } else {
                    i13 = i16;
                    i12 = i15;
                }
            }
            return;
        }
        i12 = -1;
        i13 = i10;
        if (i14 == i11) {
            return;
        }
        appendParam(parametersBuilder, str, i13, i12, str.length(), z);
    }

    public static final Parameters parseQueryString(String str, int i10, int i11, boolean z) {
        if (i10 > o.b0(str)) {
            return Parameters.INSTANCE.getEmpty();
        }
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parse(parametersBuilderParametersBuilder$default, str, i10, i11, z);
        return parametersBuilderParametersBuilder$default.build();
    }

    public static /* synthetic */ Parameters parseQueryString$default(String str, int i10, int i11, boolean z, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = 1000;
        }
        if ((i12 & 8) != 0) {
            z = true;
        }
        return parseQueryString(str, i10, i11, z);
    }

    private static final int trimEnd(int i10, int i11, CharSequence charSequence) {
        while (i11 > i10 && r0.u(charSequence.charAt(i11 - 1))) {
            i11--;
        }
        return i11;
    }

    private static final int trimStart(int i10, int i11, CharSequence charSequence) {
        while (i10 < i11 && r0.u(charSequence.charAt(i10))) {
            i10++;
        }
        return i10;
    }
}
