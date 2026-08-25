package io.ktor.http;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.util.concurrent.r0;
import f4.f;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\b\u0010\u0005\u001a%\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00002\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\b\u0010\u000b\u001a)\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0002*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\r0\f¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0011*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a#\u0010\u0018\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001aE\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u001c\u0010\u001c\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u001aj\b\u0012\u0004\u0012\u00020\u0003`\u001b0\u00122\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001d\u0010\u001e\u001a=\u0010 \u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u001c\u0010\u001f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e0\u001aj\b\u0012\u0004\u0012\u00020\u000e`\u001b0\u0012H\u0002¢\u0006\u0004\b \u0010!\u001a+\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00000\r2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b#\u0010$\u001a+\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00000\r2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b%\u0010$\u001a\u001b\u0010&\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b&\u0010'¨\u0006("}, d2 = {"", "header", "", "Lio/ktor/http/HeaderValue;", "parseAndSortHeader", "(Ljava/lang/String;)Ljava/util/List;", "parseAndSortContentTypeHeader", "text", "parseHeaderValue", "", "parametersOnly", "(Ljava/lang/String;Z)Ljava/util/List;", "", "Lx6/x;", "Lio/ktor/http/HeaderValueParam;", "toHeaderParamsList", "(Ljava/lang/Iterable;)Ljava/util/List;", "T", "Lx6/s;", "valueOrEmpty", "(Lx6/s;)Ljava/util/List;", "", TtmlNode.START, TtmlNode.END, "subtrim", "(Ljava/lang/String;II)Ljava/lang/String;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "items", "parseHeaderValueItem", "(Ljava/lang/String;ILx6/s;Z)I", "parameters", "parseHeaderValueParameter", "(Ljava/lang/String;ILx6/s;)I", "value", "parseHeaderValueParameterValue", "(Ljava/lang/String;I)Lx6/x;", "parseHeaderValueParameterValueQuoted", "nextIsSemicolonOrEnd", "(Ljava/lang/String;I)Z", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpHeaderValueParserKt {
    private static final boolean nextIsSemicolonOrEnd(String str, int i10) {
        int i11 = i10 + 1;
        while (i11 < str.length() && str.charAt(i11) == ' ') {
            i11++;
        }
        return i11 == str.length() || str.charAt(i11) == ';';
    }

    public static final List<HeaderValue> parseAndSortContentTypeHeader(String str) {
        List<HeaderValue> headerValue = parseHeaderValue(str);
        final Comparator comparator = new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Double.valueOf(((HeaderValue) t10).getQuality()), Double.valueOf(((HeaderValue) t2).getQuality()));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) throws BadContentTypeFormatException {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                ContentType.Companion companion = ContentType.INSTANCE;
                ContentType contentType = companion.parse(((HeaderValue) t2).getValue());
                int i10 = p.a(contentType.getContentType(), "*") ? 2 : 0;
                if (p.a(contentType.getContentSubtype(), "*")) {
                    i10++;
                }
                Integer numValueOf = Integer.valueOf(i10);
                ContentType contentType2 = companion.parse(((HeaderValue) t10).getValue());
                int i11 = p.a(contentType2.getContentType(), "*") ? 2 : 0;
                if (p.a(contentType2.getContentSubtype(), "*")) {
                    i11++;
                }
                return r0.e(numValueOf, Integer.valueOf(i11));
            }
        };
        return x.W0(headerValue, new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator2.compare(t2, t10);
                return iCompare != 0 ? iCompare : r0.e(Integer.valueOf(((HeaderValue) t10).getParams().size()), Integer.valueOf(((HeaderValue) t2).getParams().size()));
            }
        });
    }

    public static final List<HeaderValue> parseAndSortHeader(String str) {
        return x.W0(parseHeaderValue(str), new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortHeader$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Double.valueOf(((HeaderValue) t10).getQuality()), Double.valueOf(((HeaderValue) t2).getQuality()));
            }
        });
    }

    public static final List<HeaderValue> parseHeaderValue(String str) {
        return parseHeaderValue(str, false);
    }

    private static final int parseHeaderValueItem(String str, int i10, s<? extends ArrayList<HeaderValue>> sVar, boolean z) {
        s sVarO = f.o(3, HttpHeaderValueParserKt$parseHeaderValueItem$parameters$1.INSTANCE);
        Integer numValueOf = z ? Integer.valueOf(i10) : null;
        int headerValueParameter = i10;
        while (headerValueParameter <= o.b0(str)) {
            char cCharAt = str.charAt(headerValueParameter);
            if (cCharAt == ',') {
                ((ArrayList) sVar.getValue()).add(new HeaderValue(subtrim(str, i10, numValueOf != null ? numValueOf.intValue() : headerValueParameter), valueOrEmpty(sVarO)));
                return headerValueParameter + 1;
            }
            if (cCharAt == ';') {
                if (numValueOf == null) {
                    numValueOf = Integer.valueOf(headerValueParameter);
                }
                headerValueParameter = parseHeaderValueParameter(str, headerValueParameter + 1, sVarO);
            } else {
                headerValueParameter = z ? parseHeaderValueParameter(str, headerValueParameter, sVarO) : headerValueParameter + 1;
            }
        }
        ((ArrayList) sVar.getValue()).add(new HeaderValue(subtrim(str, i10, numValueOf != null ? numValueOf.intValue() : headerValueParameter), valueOrEmpty(sVarO)));
        return headerValueParameter;
    }

    private static final int parseHeaderValueParameter(String str, int i10, s<? extends ArrayList<HeaderValueParam>> sVar) {
        int i11 = i10;
        while (i11 <= o.b0(str)) {
            char cCharAt = str.charAt(i11);
            if (cCharAt == '=') {
                x6.x headerValueParameterValue = parseHeaderValueParameterValue(str, i11 + 1);
                int iIntValue = ((Number) headerValueParameterValue.f22608i).intValue();
                parseHeaderValueParameter$addParam(sVar, str, i10, i11, (String) headerValueParameterValue.f22609l);
                return iIntValue;
            }
            if (cCharAt == ';' || cCharAt == ',') {
                parseHeaderValueParameter$addParam(sVar, str, i10, i11, "");
                return i11;
            }
            i11++;
        }
        parseHeaderValueParameter$addParam(sVar, str, i10, i11, "");
        return i11;
    }

    private static final void parseHeaderValueParameter$addParam(s<? extends ArrayList<HeaderValueParam>> sVar, String str, int i10, int i11, String str2) {
        String strSubtrim = subtrim(str, i10, i11);
        if (strSubtrim.length() == 0) {
            return;
        }
        ((ArrayList) sVar.getValue()).add(new HeaderValueParam(strSubtrim, str2));
    }

    private static final x6.x parseHeaderValueParameterValue(String str, int i10) {
        if (str.length() == i10) {
            return new x6.x(Integer.valueOf(i10), "");
        }
        if (str.charAt(i10) == '\"') {
            return parseHeaderValueParameterValueQuoted(str, i10 + 1);
        }
        int i11 = i10;
        while (i11 <= o.b0(str)) {
            char cCharAt = str.charAt(i11);
            if (cCharAt == ';' || cCharAt == ',') {
                return new x6.x(Integer.valueOf(i11), subtrim(str, i10, i11));
            }
            i11++;
        }
        return new x6.x(Integer.valueOf(i11), subtrim(str, i10, i11));
    }

    private static final x6.x parseHeaderValueParameterValueQuoted(String str, int i10) {
        StringBuilder sb2 = new StringBuilder();
        while (i10 <= o.b0(str)) {
            char cCharAt = str.charAt(i10);
            if (cCharAt == '\"' && nextIsSemicolonOrEnd(str, i10)) {
                return new x6.x(Integer.valueOf(i10 + 1), sb2.toString());
            }
            if (cCharAt != '\\' || i10 >= o.b0(str) - 2) {
                sb2.append(cCharAt);
                i10++;
            } else {
                sb2.append(str.charAt(i10 + 1));
                i10 += 2;
            }
        }
        return new x6.x(Integer.valueOf(i10), "\"".concat(sb2.toString()));
    }

    private static final String subtrim(String str, int i10, int i11) {
        return o.L0(str.substring(i10, i11)).toString();
    }

    public static final List<HeaderValueParam> toHeaderParamsList(Iterable<x6.x> iterable) {
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        for (x6.x xVar : iterable) {
            arrayList.add(new HeaderValueParam((String) xVar.f22608i, (String) xVar.f22609l));
        }
        return arrayList;
    }

    private static final <T> List<T> valueOrEmpty(s<? extends List<? extends T>> sVar) {
        return sVar.isInitialized() ? (List) sVar.getValue() : z.f19728i;
    }

    public static final List<HeaderValue> parseHeaderValue(String str, boolean z) {
        if (str == null) {
            return z.f19728i;
        }
        s sVarO = f.o(3, HttpHeaderValueParserKt$parseHeaderValue$items$1.INSTANCE);
        int headerValueItem = 0;
        while (headerValueItem <= o.b0(str)) {
            headerValueItem = parseHeaderValueItem(str, headerValueItem, sVarO, z);
        }
        return valueOrEmpty(sVarO);
    }
}
