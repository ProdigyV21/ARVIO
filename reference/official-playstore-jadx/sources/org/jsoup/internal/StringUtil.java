package org.jsoup.internal;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.fragment.app.a2;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes5.dex */
public final class StringUtil {
    private static final int MaxCachedBuilderSize = 8192;
    private static final int MaxIdleBuilders = 8;
    private static final ThreadLocal<Stack<StringBuilder>> threadLocalBuilders;
    static final String[] padding = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};
    private static final Pattern extraDotSegmentsPattern = Pattern.compile("^/((\\.{1,2}/)+)");
    private static final Pattern validUriScheme = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+-.]*:");
    private static final Pattern controlChars = Pattern.compile("[\\x00-\\x1f]*");

    public static class StringJoiner {
        final String separator;

        /* JADX INFO: renamed from: sb, reason: collision with root package name */
        StringBuilder f21008sb = StringUtil.borrowBuilder();
        boolean first = true;

        public StringJoiner(String str) {
            this.separator = str;
        }

        public StringJoiner add(Object obj) {
            Validate.notNull(this.f21008sb);
            if (!this.first) {
                this.f21008sb.append(this.separator);
            }
            this.f21008sb.append(obj);
            this.first = false;
            return this;
        }

        public StringJoiner append(Object obj) {
            Validate.notNull(this.f21008sb);
            this.f21008sb.append(obj);
            return this;
        }

        public String complete() {
            String strReleaseBuilder = StringUtil.releaseBuilder(this.f21008sb);
            this.f21008sb = null;
            return strReleaseBuilder;
        }
    }

    static {
        final a aVar = new a();
        threadLocalBuilders = new ThreadLocal() { // from class: org.jsoup.internal.b
            @Override // java.lang.ThreadLocal
            public final Object initialValue() {
                aVar.getClass();
                return new Stack();
            }
        };
    }

    public static void appendNormalisedWhitespace(StringBuilder sb2, String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        boolean z5 = false;
        boolean z10 = false;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (isActuallyWhitespace(iCodePointAt)) {
                if ((!z || z5) && !z10) {
                    sb2.append(' ');
                    z10 = true;
                }
            } else if (!isInvisibleChar(iCodePointAt)) {
                sb2.appendCodePoint(iCodePointAt);
                z10 = false;
                z5 = true;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
    }

    public static StringBuilder borrowBuilder() {
        Stack<StringBuilder> stack = threadLocalBuilders.get();
        return stack.empty() ? new StringBuilder(8192) : stack.pop();
    }

    public static boolean in(String str, String... strArr) {
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean inSorted(String str, String[] strArr) {
        return Arrays.binarySearch(strArr, str) >= 0;
    }

    public static boolean isActuallyWhitespace(int i10) {
        return i10 == 32 || i10 == 9 || i10 == 10 || i10 == 12 || i10 == 13 || i10 == 160;
    }

    public static boolean isAscii(String str) {
        Validate.notNull(str);
        for (int i10 = 0; i10 < str.length(); i10++) {
            if (str.charAt(i10) > 127) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(String str) {
        if (str != null && str.length() != 0) {
            int length = str.length();
            for (int i10 = 0; i10 < length; i10++) {
                if (!isWhitespace(str.codePointAt(i10))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isInvisibleChar(int i10) {
        return i10 == 8203 || i10 == 173;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            if (!Character.isDigit(str.codePointAt(i10))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWhitespace(int i10) {
        return i10 == 32 || i10 == 9 || i10 == 10 || i10 == 12 || i10 == 13;
    }

    public static String join(Collection<?> collection, String str) {
        return join(collection.iterator(), str);
    }

    public static String normaliseWhitespace(String str) {
        StringBuilder sbBorrowBuilder = borrowBuilder();
        appendNormalisedWhitespace(sbBorrowBuilder, str, false);
        return releaseBuilder(sbBorrowBuilder);
    }

    public static String padding(int i10) {
        return padding(i10, 30);
    }

    public static String releaseBuilder(StringBuilder sb2) {
        Validate.notNull(sb2);
        String string = sb2.toString();
        if (sb2.length() > 8192) {
            sb2 = new StringBuilder(8192);
        } else {
            sb2.delete(0, sb2.length());
        }
        Stack<StringBuilder> stack = threadLocalBuilders.get();
        stack.push(sb2);
        while (stack.size() > 8) {
            stack.pop();
        }
        return string;
    }

    public static URL resolve(URL url, String str) throws MalformedURLException {
        String strStripControlChars = stripControlChars(str);
        if (strStripControlChars.startsWith("?")) {
            strStripControlChars = url.getPath() + strStripControlChars;
        }
        URL url2 = new URL(url, strStripControlChars);
        String strReplaceFirst = extraDotSegmentsPattern.matcher(url2.getFile()).replaceFirst(DomExceptionUtils.SEPARATOR);
        if (url2.getRef() != null) {
            StringBuilder sbP = a2.p(strReplaceFirst, "#");
            sbP.append(url2.getRef());
            strReplaceFirst = sbP.toString();
        }
        return new URL(url2.getProtocol(), url2.getHost(), url2.getPort(), strReplaceFirst);
    }

    public static boolean startsWithNewline(String str) {
        return (str == null || str.length() == 0 || str.charAt(0) != '\n') ? false : true;
    }

    private static String stripControlChars(String str) {
        return controlChars.matcher(str).replaceAll("");
    }

    public static String join(Iterator<?> it, String str) {
        if (!it.hasNext()) {
            return "";
        }
        String string = it.next().toString();
        if (!it.hasNext()) {
            return string;
        }
        StringJoiner stringJoiner = new StringJoiner(str);
        stringJoiner.add(string);
        while (it.hasNext()) {
            stringJoiner.add(it.next());
        }
        return stringJoiner.complete();
    }

    public static String padding(int i10, int i11) {
        Validate.isTrue(i10 >= 0, "width must be >= 0");
        Validate.isTrue(i11 >= -1);
        if (i11 != -1) {
            i10 = Math.min(i10, i11);
        }
        String[] strArr = padding;
        if (i10 < strArr.length) {
            return strArr[i10];
        }
        char[] cArr = new char[i10];
        for (int i12 = 0; i12 < i10; i12++) {
            cArr[i12] = ' ';
        }
        return String.valueOf(cArr);
    }

    public static String join(String[] strArr, String str) {
        return join(Arrays.asList(strArr), str);
    }

    public static String resolve(String str, String str2) {
        String strStripControlChars = stripControlChars(str);
        String strStripControlChars2 = stripControlChars(str2);
        try {
            try {
                return resolve(new URL(strStripControlChars), strStripControlChars2).toExternalForm();
            } catch (MalformedURLException unused) {
                return new URL(strStripControlChars2).toExternalForm();
            }
        } catch (MalformedURLException unused2) {
            return validUriScheme.matcher(strStripControlChars2).find() ? strStripControlChars2 : "";
        }
    }
}
