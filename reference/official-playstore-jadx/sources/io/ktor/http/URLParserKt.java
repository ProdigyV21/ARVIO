package io.ktor.http;

import a0.c;
import androidx.compose.material3.d;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.common.util.concurrent.r0;
import io.ktor.util.CharsetKt;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u001a\u0019\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a3\u0010\u000b\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a+\u0010\r\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a+\u0010\u000f\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u000e\u001a+\u0010\u0012\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u000e\u001a'\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a/\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a#\u0010\u0019\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0019\u0010\u0014\u001a\u0013\u0010\u001b\u001a\u00020\u001a*\u00020\u0015H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\" \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lio/ktor/http/URLBuilder;", "", "urlString", "takeFrom", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "takeFromUnsafe", "", "startIndex", "endIndex", "slashCount", "Lx6/t0;", "parseFile", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;III)V", "parseMailto", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;II)V", "parseQuery", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;II)I", "parseFragment", "fillHost", "findScheme", "(Ljava/lang/String;II)I", "", "char", "count", "(Ljava/lang/String;IIC)I", "indexOfColonInHostPort", "", "isLetter", "(C)Z", "", "ROOT_PATH", "Ljava/util/List;", "getROOT_PATH", "()Ljava/util/List;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLParserKt {
    private static final List<String> ROOT_PATH = Collections.singletonList("");

    /* JADX INFO: renamed from: io.ktor.http.URLParserKt$parseQuery$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", "key", "", "values", "Lx6/t0;", "invoke", "(Ljava/lang/String;Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<String, List<? extends String>, t0> {
        final /* synthetic */ URLBuilder $this_parseQuery;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(URLBuilder uRLBuilder) {
            super(2);
            this.$this_parseQuery = uRLBuilder;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, (List<String>) obj2);
            return t0.f22605a;
        }

        public final void invoke(String str, List<String> list) {
            this.$this_parseQuery.getEncodedParameters().appendAll(str, list);
        }
    }

    private static final int count(String str, int i10, int i11, char c10) {
        int i12 = 0;
        while (true) {
            int i13 = i10 + i12;
            if (i13 >= i11 || str.charAt(i13) != c10) {
                break;
            }
            i12++;
        }
        return i12;
    }

    private static final void fillHost(URLBuilder uRLBuilder, String str, int i10, int i11) {
        Integer numValueOf = Integer.valueOf(indexOfColonInHostPort(str, i10, i11));
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : i11;
        uRLBuilder.setHost(str.substring(i10, iIntValue));
        int i12 = iIntValue + 1;
        if (i12 < i11) {
            uRLBuilder.setPort(Integer.parseInt(str.substring(i12, i11)));
        } else {
            uRLBuilder.setPort(0);
        }
    }

    private static final int findScheme(String str, int i10, int i11) {
        int i12;
        int i13;
        char cCharAt = str.charAt(i10);
        if (('a' > cCharAt || cCharAt >= '{') && ('A' > cCharAt || cCharAt >= '[')) {
            i12 = i10;
            i13 = i12;
        } else {
            i12 = i10;
            i13 = -1;
        }
        while (i12 < i11) {
            char cCharAt2 = str.charAt(i12);
            if (cCharAt2 == ':') {
                if (i13 == -1) {
                    return i12 - i10;
                }
                throw new IllegalArgumentException(c.i(i13, "Illegal character in scheme at position "));
            }
            if (cCharAt2 == '/' || cCharAt2 == '?' || cCharAt2 == '#') {
                break;
            }
            if (i13 == -1 && (('a' > cCharAt2 || cCharAt2 >= '{') && (('A' > cCharAt2 || cCharAt2 >= '[') && (('0' > cCharAt2 || cCharAt2 >= ':') && cCharAt2 != '.' && cCharAt2 != '+' && cCharAt2 != '-')))) {
                i13 = i12;
            }
            i12++;
        }
        return -1;
    }

    public static final List<String> getROOT_PATH() {
        return ROOT_PATH;
    }

    private static final int indexOfColonInHostPort(String str, int i10, int i11) {
        boolean z = false;
        while (i10 < i11) {
            char cCharAt = str.charAt(i10);
            if (cCharAt == '[') {
                z = true;
            } else if (cCharAt == ']') {
                z = false;
            } else if (cCharAt == ':' && !z) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    private static final boolean isLetter(char c10) {
        char lowerCase = Character.toLowerCase(c10);
        return 'a' <= lowerCase && lowerCase < '{';
    }

    private static final void parseFile(URLBuilder uRLBuilder, String str, int i10, int i11, int i12) {
        if (i12 != 2) {
            if (i12 != 3) {
                throw new IllegalArgumentException(d.C("Invalid file url: ", str));
            }
            uRLBuilder.setHost("");
            URLBuilderKt.setEncodedPath(uRLBuilder, DomExceptionUtils.SEPARATOR.concat(str.substring(i10, i11)));
            return;
        }
        int iE0 = o.e0(str, '/', i10, false, 4);
        if (iE0 == -1 || iE0 == i11) {
            uRLBuilder.setHost(str.substring(i10, i11));
        } else {
            uRLBuilder.setHost(str.substring(i10, iE0));
            URLBuilderKt.setEncodedPath(uRLBuilder, str.substring(iE0, i11));
        }
    }

    private static final void parseFragment(URLBuilder uRLBuilder, String str, int i10, int i11) {
        if (i10 >= i11 || str.charAt(i10) != '#') {
            return;
        }
        uRLBuilder.setEncodedFragment(str.substring(i10 + 1, i11));
    }

    private static final void parseMailto(URLBuilder uRLBuilder, String str, int i10, int i11) {
        int iF0 = o.f0(str, "@", i10, false, 4);
        if (iF0 == -1) {
            throw new IllegalArgumentException(c.l("Invalid mailto url: ", str, ", it should contain '@'."));
        }
        uRLBuilder.setUser(CodecsKt.decodeURLPart$default(str.substring(i10, iF0), 0, 0, null, 7, null));
        uRLBuilder.setHost(str.substring(iF0 + 1, i11));
    }

    private static final int parseQuery(URLBuilder uRLBuilder, String str, int i10, int i11) {
        int i12 = i10 + 1;
        if (i12 == i11) {
            uRLBuilder.setTrailingQuery(true);
            return i11;
        }
        int iE0 = o.e0(str, '#', i12, false, 4);
        Integer numValueOf = Integer.valueOf(iE0);
        if (iE0 <= 0) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            i11 = numValueOf.intValue();
        }
        QueryKt.parseQueryString$default(str.substring(i12, i11), 0, 0, false, 6, null).forEach(new AnonymousClass1(uRLBuilder));
        return i11;
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, String str) {
        if (o.h0(str)) {
            return uRLBuilder;
        }
        try {
            return takeFromUnsafe(uRLBuilder, str);
        } catch (Throwable th) {
            throw new URLParserException(str, th);
        }
    }

    public static final URLBuilder takeFromUnsafe(URLBuilder uRLBuilder, String str) {
        int iIntValue;
        int length = str.length();
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                i10 = -1;
                break;
            }
            if (!r0.u(str.charAt(i10))) {
                break;
            }
            i10++;
        }
        int length2 = str.length() - 1;
        if (length2 >= 0) {
            while (true) {
                int i11 = length2 - 1;
                if (!r0.u(str.charAt(length2))) {
                    break;
                }
                if (i11 < 0) {
                    break;
                }
                length2 = i11;
            }
            length2 = -1;
        } else {
            length2 = -1;
        }
        int i12 = length2 + 1;
        int iFindScheme = findScheme(str, i10, i12);
        if (iFindScheme > 0) {
            uRLBuilder.setProtocol(URLProtocol.INSTANCE.createOrDefault(str.substring(i10, i10 + iFindScheme)));
            i10 += iFindScheme + 1;
        }
        int iCount = count(str, i10, i12, '/');
        int query = i10 + iCount;
        if (kotlin.jvm.internal.p.a(uRLBuilder.getProtocol().getName(), "file")) {
            parseFile(uRLBuilder, str, query, i12, iCount);
            return uRLBuilder;
        }
        if (kotlin.jvm.internal.p.a(uRLBuilder.getProtocol().getName(), "mailto")) {
            if (iCount != 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            parseMailto(uRLBuilder, str, query, i12);
            return uRLBuilder;
        }
        if (iCount >= 2) {
            while (true) {
                int iG0 = o.g0(str, CharsetKt.toCharArray("@/\\?#"), query, false);
                Integer numValueOf = Integer.valueOf(iG0);
                if (iG0 <= 0) {
                    numValueOf = null;
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : i12;
                if (iIntValue >= i12 || str.charAt(iIntValue) != '@') {
                    break;
                }
                int iIndexOfColonInHostPort = indexOfColonInHostPort(str, query, iIntValue);
                if (iIndexOfColonInHostPort != -1) {
                    uRLBuilder.setEncodedUser(str.substring(query, iIndexOfColonInHostPort));
                    uRLBuilder.setEncodedPassword(str.substring(iIndexOfColonInHostPort + 1, iIntValue));
                } else {
                    uRLBuilder.setEncodedUser(str.substring(query, iIntValue));
                }
                query = iIntValue + 1;
            }
            fillHost(uRLBuilder, str, query, iIntValue);
            query = iIntValue;
        }
        List<String> list = z.f19728i;
        if (query >= i12) {
            if (str.charAt(length2) == '/') {
                list = ROOT_PATH;
            }
            uRLBuilder.setEncodedPathSegments(list);
            return uRLBuilder;
        }
        uRLBuilder.setEncodedPathSegments(iCount == 0 ? x.i0(1, uRLBuilder.getEncodedPathSegments()) : list);
        int iG02 = o.g0(str, CharsetKt.toCharArray("?#"), query, false);
        Integer numValueOf2 = iG02 > 0 ? Integer.valueOf(iG02) : null;
        int iIntValue2 = numValueOf2 != null ? numValueOf2.intValue() : i12;
        if (iIntValue2 > query) {
            String strSubstring = str.substring(query, iIntValue2);
            List<String> encodedPathSegments = (uRLBuilder.getEncodedPathSegments().size() == 1 && ((CharSequence) x.m0(uRLBuilder.getEncodedPathSegments())).length() == 0) ? list : uRLBuilder.getEncodedPathSegments();
            List<String> listX0 = strSubstring.equals(DomExceptionUtils.SEPARATOR) ? ROOT_PATH : o.x0(strSubstring, new char[]{'/'}, 6);
            if (iCount == 1) {
                list = ROOT_PATH;
            }
            uRLBuilder.setEncodedPathSegments(x.I0(encodedPathSegments, x.I0(list, listX0)));
            query = iIntValue2;
        }
        if (query < i12 && str.charAt(query) == '?') {
            query = parseQuery(uRLBuilder, str, query, i12);
        }
        parseFragment(uRLBuilder, str, query, i12);
        return uRLBuilder;
    }
}
