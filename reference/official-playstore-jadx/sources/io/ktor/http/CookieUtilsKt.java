package io.ktor.http;

import io.ktor.util.date.Month;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.u;
import r7.a;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0003\u001a\u0013\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0013\u0010\u0006\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0003\u001a\u0013\u0010\u0007\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\u0003\u001a%\u0010\u000b\u001a\u00020\t*\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a7\u0010\u0011\u001a\u00020\t*\u00020\r2\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u000eH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a+\u0010\u0015\u001a\u00020\t*\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a+\u0010\u0017\u001a\u00020\t*\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0016\u001a+\u0010\u0018\u001a\u00020\t*\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0016\u001a\u001b\u0010\u001b\u001a\u00020\t*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"", "", "isDelimiter", "(C)Z", "isNonDelimiter", "isOctet", "isNonDigit", "isDigit", "Lkotlin/Function0;", "Lx6/t0;", "block", "otherwise", "(ZLr7/a;)V", "", "Lkotlin/Function3;", "", "success", "tryParseTime", "(Ljava/lang/String;Lr7/q;)V", "Lkotlin/Function1;", "Lio/ktor/util/date/Month;", "tryParseMonth", "(Ljava/lang/String;Lr7/l;)V", "tryParseDayOfMonth", "tryParseYear", "Lio/ktor/http/CookieDateBuilder;", "token", "handleToken", "(Lio/ktor/http/CookieDateBuilder;Ljava/lang/String;)V", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CookieUtilsKt {

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseDayOfMonth$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass1 extends r implements l<Character, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isNonDigit(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseDayOfMonth$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass2 extends r implements l<Character, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isOctet(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C18101 extends r implements l<Character, Boolean> {
        public static final C18101 INSTANCE = new C18101();

        public C18101() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(c10 == ':');
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass3 extends r implements l<Character, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(c10 == ':');
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass5 extends r implements l<Character, Boolean> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isNonDigit(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseTime$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass6 extends r implements l<Character, Boolean> {
        public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

        public AnonymousClass6() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isOctet(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseYear$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C18111 extends r implements l<Character, Boolean> {
        public static final C18111 INSTANCE = new C18111();

        public C18111() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isNonDigit(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieUtilsKt$tryParseYear$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C18122 extends r implements l<Character, Boolean> {
        public static final C18122 INSTANCE = new C18122();

        public C18122() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isOctet(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    public static final void handleToken(CookieDateBuilder cookieDateBuilder, String str) {
        if (cookieDateBuilder.getHours() == null || cookieDateBuilder.getMinutes() == null || cookieDateBuilder.getSeconds() == null) {
            StringLexer stringLexer = new StringLexer(str);
            int index = stringLexer.getIndex();
            if (stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$1.INSTANCE)) {
                stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$3.INSTANCE);
                int i10 = Integer.parseInt(stringLexer.getSource().substring(index, stringLexer.getIndex()));
                if (stringLexer.accept(C18101.INSTANCE)) {
                    int index2 = stringLexer.getIndex();
                    if (stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$1.INSTANCE)) {
                        stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$3.INSTANCE);
                        int i11 = Integer.parseInt(stringLexer.getSource().substring(index2, stringLexer.getIndex()));
                        if (stringLexer.accept(AnonymousClass3.INSTANCE)) {
                            int index3 = stringLexer.getIndex();
                            if (stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$1.INSTANCE)) {
                                stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$3.INSTANCE);
                                int i12 = Integer.parseInt(stringLexer.getSource().substring(index3, stringLexer.getIndex()));
                                if (stringLexer.accept(AnonymousClass5.INSTANCE)) {
                                    stringLexer.acceptWhile(AnonymousClass6.INSTANCE);
                                }
                                cookieDateBuilder.setHours(Integer.valueOf(i10));
                                cookieDateBuilder.setMinutes(Integer.valueOf(i11));
                                cookieDateBuilder.setSeconds(Integer.valueOf(i12));
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (cookieDateBuilder.getDayOfMonth() == null) {
            StringLexer stringLexer2 = new StringLexer(str);
            int index4 = stringLexer2.getIndex();
            if (stringLexer2.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$1.INSTANCE)) {
                stringLexer2.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$3.INSTANCE);
                int i13 = Integer.parseInt(stringLexer2.getSource().substring(index4, stringLexer2.getIndex()));
                if (stringLexer2.accept(AnonymousClass1.INSTANCE)) {
                    stringLexer2.acceptWhile(AnonymousClass2.INSTANCE);
                }
                cookieDateBuilder.setDayOfMonth(Integer.valueOf(i13));
                return;
            }
        }
        if (cookieDateBuilder.getMonth() == null && str.length() >= 3) {
            for (Month month : Month.values()) {
                if (u.P(str, month.getValue(), true)) {
                    cookieDateBuilder.setMonth(month);
                    return;
                }
            }
        }
        if (cookieDateBuilder.getYear() == null) {
            StringLexer stringLexer3 = new StringLexer(str);
            int index5 = stringLexer3.getIndex();
            for (int i14 = 0; i14 < 2; i14++) {
                if (!stringLexer3.accept(CookieUtilsKt$tryParseYear$year$1$1$1.INSTANCE)) {
                    return;
                }
            }
            for (int i15 = 0; i15 < 2; i15++) {
                stringLexer3.accept(CookieUtilsKt$tryParseYear$year$1$2$1.INSTANCE);
            }
            int i16 = Integer.parseInt(stringLexer3.getSource().substring(index5, stringLexer3.getIndex()));
            if (stringLexer3.accept(C18111.INSTANCE)) {
                stringLexer3.acceptWhile(C18122.INSTANCE);
            }
            cookieDateBuilder.setYear(Integer.valueOf(i16));
        }
    }

    public static final boolean isDelimiter(char c10) {
        if (c10 == '\t') {
            return true;
        }
        if (' ' <= c10 && c10 < '0') {
            return true;
        }
        if (';' <= c10 && c10 < 'A') {
            return true;
        }
        if ('[' > c10 || c10 >= 'a') {
            return '{' <= c10 && c10 < 127;
        }
        return true;
    }

    public static final boolean isDigit(char c10) {
        return '0' <= c10 && c10 < ':';
    }

    public static final boolean isNonDelimiter(char c10) {
        if (c10 >= 0 && c10 < '\t') {
            return true;
        }
        if ('\n' <= c10 && c10 < ' ') {
            return true;
        }
        if (('0' <= c10 && c10 < ':') || c10 == ':') {
            return true;
        }
        if ('a' <= c10 && c10 < '{') {
            return true;
        }
        if ('A' > c10 || c10 >= '[') {
            return 127 <= c10 && c10 < 256;
        }
        return true;
    }

    public static final boolean isNonDigit(char c10) {
        if (c10 < 0 || c10 >= '0') {
            return 'J' <= c10 && c10 < 256;
        }
        return true;
    }

    public static final boolean isOctet(char c10) {
        return c10 >= 0 && c10 < 256;
    }

    public static final void otherwise(boolean z, a<t0> aVar) {
        if (z) {
            return;
        }
        aVar.invoke();
    }

    public static final void tryParseDayOfMonth(String str, l<? super Integer, t0> lVar) {
        StringLexer stringLexer = new StringLexer(str);
        int index = stringLexer.getIndex();
        if (stringLexer.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$1.INSTANCE)) {
            stringLexer.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$3.INSTANCE);
            int i10 = Integer.parseInt(stringLexer.getSource().substring(index, stringLexer.getIndex()));
            if (stringLexer.accept(AnonymousClass1.INSTANCE)) {
                stringLexer.acceptWhile(AnonymousClass2.INSTANCE);
            }
            lVar.invoke(Integer.valueOf(i10));
        }
    }

    public static final void tryParseMonth(String str, l<? super Month, t0> lVar) {
        if (str.length() < 3) {
            return;
        }
        for (Month month : Month.values()) {
            if (u.P(str, month.getValue(), true)) {
                lVar.invoke(month);
                return;
            }
        }
    }

    public static final void tryParseTime(String str, q<? super Integer, ? super Integer, ? super Integer, t0> qVar) {
        StringLexer stringLexer = new StringLexer(str);
        int index = stringLexer.getIndex();
        if (stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$1.INSTANCE)) {
            stringLexer.accept(CookieUtilsKt$tryParseTime$hour$1$3.INSTANCE);
            int i10 = Integer.parseInt(stringLexer.getSource().substring(index, stringLexer.getIndex()));
            if (stringLexer.accept(C18101.INSTANCE)) {
                int index2 = stringLexer.getIndex();
                if (stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$1.INSTANCE)) {
                    stringLexer.accept(CookieUtilsKt$tryParseTime$minute$1$3.INSTANCE);
                    int i11 = Integer.parseInt(stringLexer.getSource().substring(index2, stringLexer.getIndex()));
                    if (stringLexer.accept(AnonymousClass3.INSTANCE)) {
                        int index3 = stringLexer.getIndex();
                        if (stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$1.INSTANCE)) {
                            stringLexer.accept(CookieUtilsKt$tryParseTime$second$1$3.INSTANCE);
                            int i12 = Integer.parseInt(stringLexer.getSource().substring(index3, stringLexer.getIndex()));
                            if (stringLexer.accept(AnonymousClass5.INSTANCE)) {
                                stringLexer.acceptWhile(AnonymousClass6.INSTANCE);
                            }
                            qVar.invoke(Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12));
                        }
                    }
                }
            }
        }
    }

    public static final void tryParseYear(String str, l<? super Integer, t0> lVar) {
        StringLexer stringLexer = new StringLexer(str);
        int index = stringLexer.getIndex();
        for (int i10 = 0; i10 < 2; i10++) {
            if (!stringLexer.accept(CookieUtilsKt$tryParseYear$year$1$1$1.INSTANCE)) {
                return;
            }
        }
        for (int i11 = 0; i11 < 2; i11++) {
            stringLexer.accept(CookieUtilsKt$tryParseYear$year$1$2$1.INSTANCE);
        }
        int i12 = Integer.parseInt(stringLexer.getSource().substring(index, stringLexer.getIndex()));
        if (stringLexer.accept(C18111.INSTANCE)) {
            stringLexer.acceptWhile(C18122.INSTANCE);
        }
        lVar.invoke(Integer.valueOf(i12));
    }
}
