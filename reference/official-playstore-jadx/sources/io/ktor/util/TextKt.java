package io.ktor.util;

import kotlin.Metadata;
import kotlin.text.o;
import r7.a;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0001\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001aE\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0005*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00002\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00050\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0011\u0010\t\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\t\u0010\u0002\u001a\u0011\u0010\n\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\n\u0010\u0002\u001a\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u000e\u001a\u0013\u0010\u0011\u001a\u00020\u0010*\u00020\u0000H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"", "escapeHTML", "(Ljava/lang/String;)Ljava/lang/String;", "separator", "Lkotlin/Function0;", "Lx6/x;", "onMissingDelimiter", "chomp", "(Ljava/lang/String;Ljava/lang/String;Lr7/a;)Lx6/x;", "toLowerCasePreservingASCIIRules", "toUpperCasePreservingASCIIRules", "", "ch", "toLowerCasePreservingASCII", "(C)C", "toUpperCasePreservingASCII", "Lio/ktor/util/CaseInsensitiveString;", "caseInsensitive", "(Ljava/lang/String;)Lio/ktor/util/CaseInsensitiveString;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextKt {
    public static final CaseInsensitiveString caseInsensitive(String str) {
        return new CaseInsensitiveString(str);
    }

    public static final x chomp(String str, String str2, a<x> aVar) {
        int iF0 = o.f0(str, str2, 0, false, 6);
        return iF0 == -1 ? (x) aVar.invoke() : new x(str.substring(0, iF0), str.substring(iF0 + 1));
    }

    public static final String escapeHTML(String str) {
        if (str.length() == 0) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str.length());
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if (cCharAt == '\'') {
                sb2.append("&#x27;");
            } else if (cCharAt == '\"') {
                sb2.append("&quot;");
            } else if (cCharAt == '&') {
                sb2.append("&amp;");
            } else if (cCharAt == '<') {
                sb2.append("&lt;");
            } else if (cCharAt == '>') {
                sb2.append("&gt;");
            } else {
                sb2.append(cCharAt);
            }
        }
        return sb2.toString();
    }

    private static final char toLowerCasePreservingASCII(char c10) {
        return ('A' > c10 || c10 >= '[') ? (c10 < 0 || c10 >= 128) ? Character.toLowerCase(c10) : c10 : (char) (c10 + ' ');
    }

    public static final String toLowerCasePreservingASCIIRules(String str) {
        int length = str.length();
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                i10 = -1;
                break;
            }
            char cCharAt = str.charAt(i10);
            if (toLowerCasePreservingASCII(cCharAt) != cCharAt) {
                break;
            }
            i10++;
        }
        if (i10 == -1) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str.length());
        sb2.append((CharSequence) str, 0, i10);
        int iB0 = o.b0(str);
        if (i10 <= iB0) {
            while (true) {
                sb2.append(toLowerCasePreservingASCII(str.charAt(i10)));
                if (i10 == iB0) {
                    break;
                }
                i10++;
            }
        }
        return sb2.toString();
    }

    private static final char toUpperCasePreservingASCII(char c10) {
        return ('a' > c10 || c10 >= '{') ? (c10 < 0 || c10 >= 128) ? Character.toLowerCase(c10) : c10 : (char) (c10 - ' ');
    }

    public static final String toUpperCasePreservingASCIIRules(String str) {
        int length = str.length();
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                i10 = -1;
                break;
            }
            char cCharAt = str.charAt(i10);
            if (toUpperCasePreservingASCII(cCharAt) != cCharAt) {
                break;
            }
            i10++;
        }
        if (i10 == -1) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str.length());
        sb2.append((CharSequence) str, 0, i10);
        int iB0 = o.b0(str);
        if (i10 <= iB0) {
            while (true) {
                sb2.append(toUpperCasePreservingASCII(str.charAt(i10)));
                if (i10 == iB0) {
                    break;
                }
                i10++;
            }
        }
        return sb2.toString();
    }
}
