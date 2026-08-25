package io.ktor.http;

import androidx.compose.material3.d;
import io.ktor.http.ContentDisposition;
import io.ktor.util.date.GMTDate;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x7.i;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\t\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000bJ-\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/http/CookieDateParser;", "", "<init>", "()V", "T", "", "source", ContentDisposition.Parameters.Name, "field", "Lx6/t0;", "checkFieldNotNull", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "", "requirement", "Lkotlin/Function0;", "msg", "checkRequirement", "(Ljava/lang/String;ZLr7/a;)V", "Lio/ktor/util/date/GMTDate;", "parse", "(Ljava/lang/String;)Lio/ktor/util/date/GMTDate;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CookieDateParser {

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Character, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isDelimiter(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<Character, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isNonDelimiter(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<Character, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final Boolean invoke(char c10) {
            return Boolean.valueOf(CookieUtilsKt.isDelimiter(c10));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Character) obj).charValue());
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements a<String> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "day-of-month not in [1,31]";
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements a<String> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "year >= 1601";
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements a<String> {
        public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

        public AnonymousClass6() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "hours > 23";
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass7 extends r implements a<String> {
        public static final AnonymousClass7 INSTANCE = new AnonymousClass7();

        public AnonymousClass7() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "minutes > 59";
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieDateParser$parse$8, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass8 extends r implements a<String> {
        public static final AnonymousClass8 INSTANCE = new AnonymousClass8();

        public AnonymousClass8() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return "seconds > 59";
        }
    }

    private final <T> void checkFieldNotNull(String source, String name, T field) {
        if (field == null) {
            throw new InvalidCookieDateException(source, d.C("Could not find ", name));
        }
    }

    private final void checkRequirement(String source, boolean requirement, a<String> msg) {
        if (!requirement) {
            throw new InvalidCookieDateException(source, (String) msg.invoke());
        }
    }

    public final GMTDate parse(String source) {
        StringLexer stringLexer = new StringLexer(source);
        CookieDateBuilder cookieDateBuilder = new CookieDateBuilder();
        stringLexer.acceptWhile(AnonymousClass1.INSTANCE);
        while (stringLexer.getHasRemaining()) {
            if (stringLexer.test(AnonymousClass2.INSTANCE)) {
                int index = stringLexer.getIndex();
                stringLexer.acceptWhile(CookieDateParser$parse$token$1$1.INSTANCE);
                CookieUtilsKt.handleToken(cookieDateBuilder, stringLexer.getSource().substring(index, stringLexer.getIndex()));
                stringLexer.acceptWhile(AnonymousClass3.INSTANCE);
            }
        }
        Integer year = cookieDateBuilder.getYear();
        i iVar = new i(70, 99, 1);
        if (year == null || !iVar.d(year.intValue())) {
            i iVar2 = new i(0, 69, 1);
            if (year != null && iVar2.d(year.intValue())) {
                cookieDateBuilder.setYear(Integer.valueOf(cookieDateBuilder.getYear().intValue() + 2000));
            }
        } else {
            cookieDateBuilder.setYear(Integer.valueOf(cookieDateBuilder.getYear().intValue() + 1900));
        }
        checkFieldNotNull(source, "day-of-month", cookieDateBuilder.getDayOfMonth());
        checkFieldNotNull(source, "month", cookieDateBuilder.getMonth());
        checkFieldNotNull(source, "year", cookieDateBuilder.getYear());
        checkFieldNotNull(source, "time", cookieDateBuilder.getHours());
        checkFieldNotNull(source, "time", cookieDateBuilder.getMinutes());
        checkFieldNotNull(source, "time", cookieDateBuilder.getSeconds());
        i iVar3 = new i(1, 31, 1);
        Integer dayOfMonth = cookieDateBuilder.getDayOfMonth();
        checkRequirement(source, dayOfMonth != null && iVar3.d(dayOfMonth.intValue()), AnonymousClass4.INSTANCE);
        checkRequirement(source, cookieDateBuilder.getYear().intValue() >= 1601, AnonymousClass5.INSTANCE);
        checkRequirement(source, cookieDateBuilder.getHours().intValue() <= 23, AnonymousClass6.INSTANCE);
        checkRequirement(source, cookieDateBuilder.getMinutes().intValue() <= 59, AnonymousClass7.INSTANCE);
        checkRequirement(source, cookieDateBuilder.getSeconds().intValue() <= 59, AnonymousClass8.INSTANCE);
        return cookieDateBuilder.build();
    }
}
