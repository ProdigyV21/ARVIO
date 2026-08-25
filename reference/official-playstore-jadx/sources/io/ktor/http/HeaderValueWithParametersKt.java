package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.util.StringValuesBuilder;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0003\u001a!\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0011\u0010\b\u001a\u00020\u0001*\u00020\u0001¢\u0006\u0004\b\b\u0010\t\u001a \u0010\r\u001a\u00020\u0005*\u00020\u00012\n\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0082\b¢\u0006\u0004\b\r\u0010\u000e\u001a\u0013\u0010\u0010\u001a\u00020\u000f*\u00020\u0001H\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0013\u0010\u0012\u001a\u00020\u000f*\u00020\u0001H\u0002¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u0011\u0010\u0013\u001a\u00020\u0001*\u00020\u0001¢\u0006\u0004\b\u0013\u0010\t\u001a\u001f\u0010\u0014\u001a\u00020\u0005*\u00020\u00012\n\u0010\f\u001a\u00060\nj\u0002`\u000bH\u0002¢\u0006\u0004\b\u0014\u0010\u000e\"\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lio/ktor/util/StringValuesBuilder;", "", ContentDisposition.Parameters.Name, "Lio/ktor/http/HeaderValueWithParameters;", "value", "Lx6/t0;", "append", "(Lio/ktor/util/StringValuesBuilder;Ljava/lang/String;Lio/ktor/http/HeaderValueWithParameters;)V", "escapeIfNeeded", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "out", "escapeIfNeededTo", "(Ljava/lang/String;Ljava/lang/StringBuilder;)V", "", "needQuotes", "(Ljava/lang/String;)Z", "isQuoted", "quote", "quoteTo", "", "", "HeaderFieldValueSeparators", "Ljava/util/Set;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeaderValueWithParametersKt {
    private static final Set<Character> HeaderFieldValueSeparators = r.p0(new Character[]{'(', ')', '<', '>', '@', ',', ';', ':', '\\', '\"', '/', '[', ']', '?', '=', '{', '}', ' ', '\t', '\n', '\r'});

    public static final void append(StringValuesBuilder stringValuesBuilder, String str, HeaderValueWithParameters headerValueWithParameters) {
        stringValuesBuilder.append(str, headerValueWithParameters.toString());
    }

    public static final String escapeIfNeeded(String str) {
        return needQuotes(str) ? quote(str) : str;
    }

    private static final void escapeIfNeededTo(String str, StringBuilder sb2) {
        if (needQuotes(str)) {
            sb2.append(quote(str));
        } else {
            sb2.append(str);
        }
    }

    private static final boolean isQuoted(String str) {
        if (str.length() >= 2 && o.Z(str) == '\"' && o.i0(str) == '\"') {
            int i10 = 1;
            do {
                int iE0 = o.e0(str, '\"', i10, false, 4);
                if (iE0 == o.b0(str)) {
                    break;
                }
                int i11 = 0;
                for (int i12 = iE0 - 1; str.charAt(i12) == '\\'; i12--) {
                    i11++;
                }
                if (i11 % 2 != 0) {
                    i10 = iE0 + 1;
                }
            } while (i10 < str.length());
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean needQuotes(String str) {
        if (str.length() == 0) {
            return true;
        }
        if (isQuoted(str)) {
            return false;
        }
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            if (HeaderFieldValueSeparators.contains(Character.valueOf(str.charAt(i10)))) {
                return true;
            }
        }
        return false;
    }

    public static final String quote(String str) {
        StringBuilder sb2 = new StringBuilder();
        quoteTo(str, sb2);
        return sb2.toString();
    }

    private static final void quoteTo(String str, StringBuilder sb2) {
        sb2.append("\"");
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if (cCharAt == '\\') {
                sb2.append("\\\\");
            } else if (cCharAt == '\n') {
                sb2.append("\\n");
            } else if (cCharAt == '\r') {
                sb2.append("\\r");
            } else if (cCharAt == '\t') {
                sb2.append("\\t");
            } else if (cCharAt == '\"') {
                sb2.append("\\\"");
            } else {
                sb2.append(cCharAt);
            }
        }
        sb2.append("\"");
    }
}
