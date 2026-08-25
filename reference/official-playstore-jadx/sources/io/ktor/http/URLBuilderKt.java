package io.ktor.http;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a)\u0010\u0005\u001a\u00028\u0000\"\f\b\u0000\u0010\u0002*\u00060\u0000j\u0002`\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u000b\u001a\u00020\n*\u00060\u0000j\u0002`\u00012\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a'\u0010\u000e\u001a\u00020\n*\u00060\u0000j\u0002`\u00012\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000e\u0010\f\u001a\u0011\u0010\u000f\u001a\u00020\u0003*\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a)\u0010\u0015\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016\u001a/\u0010\u0015\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0017\"\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0019\u001a%\u0010\u001a\u001a\u00020\n*\u00020\u00032\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0017\"\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u001c\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0004\b\u001c\u0010\u001d\u001a%\u0010\u001c\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0017\"\u00020\u0007¢\u0006\u0004\b\u001c\u0010\u001e\u001a\u0019\u0010\u001f\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0002¢\u0006\u0004\b\u001f\u0010 \u001a\\\u0010'\u001a\u00020\n*\u00020\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0$¢\u0006\u0002\b%¢\u0006\u0004\b'\u0010(\u001a!\u0010\u0015\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0007¢\u0006\u0004\b\u0015\u0010\u001d\u001a'\u0010\u0015\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0017\"\u00020\u0007H\u0007¢\u0006\u0004\b\u0015\u0010\u001e\u001a'\u0010)\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0017\"\u00020\u0007H\u0007¢\u0006\u0004\b)\u0010\u001e\u001a!\u0010)\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0007¢\u0006\u0004\b)\u0010\u001d\"\u0014\u0010*\u001a\u00020\"8\u0006X\u0086T¢\u0006\u0006\n\u0004\b*\u0010+\"\u0018\u0010.\u001a\u00020\u0007*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-\"\u0015\u00100\u001a\u00020\u0007*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b/\u0010-\"(\u0010\r\u001a\u00020\u0007*\u00020\u00032\u0006\u00101\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010-\"\u0004\b3\u00104¨\u00065"}, d2 = {"Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "Lio/ktor/http/URLBuilder;", "out", "appendTo", "(Lio/ktor/http/URLBuilder;Ljava/lang/Appendable;)Ljava/lang/Appendable;", "", "encodedUser", "host", "Lx6/t0;", "appendMailto", "(Ljava/lang/Appendable;Ljava/lang/String;Ljava/lang/String;)V", "encodedPath", "appendFile", "clone", "(Lio/ktor/http/URLBuilder;)Lio/ktor/http/URLBuilder;", "", "segments", "", "encodeSlash", "appendPathSegments", "(Lio/ktor/http/URLBuilder;Ljava/util/List;Z)Lio/ktor/http/URLBuilder;", "", "components", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;Z)Lio/ktor/http/URLBuilder;", "path", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;)V", "appendEncodedPathSegments", "(Lio/ktor/http/URLBuilder;Ljava/util/List;)Lio/ktor/http/URLBuilder;", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "joinPath", "(Ljava/util/List;)Ljava/lang/String;", "scheme", "", "port", "Lkotlin/Function1;", "Lx6/n;", "block", "set", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lr7/l;)V", "pathComponents", "DEFAULT_PORT", "I", "getEncodedUserAndPassword", "(Lio/ktor/http/URLBuilder;)Ljava/lang/String;", "encodedUserAndPassword", "getAuthority", "authority", "value", "getEncodedPath", "setEncodedPath", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;)V", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLBuilderKt {
    public static final int DEFAULT_PORT = 0;

    /* JADX INFO: renamed from: io.ktor.http.URLBuilderKt$set$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/URLBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/URLBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<URLBuilder, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(URLBuilder uRLBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((URLBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final URLBuilder appendEncodedPathSegments(URLBuilder uRLBuilder, List<String> list) {
        boolean z = false;
        boolean z5 = uRLBuilder.getEncodedPathSegments().size() > 1 && ((CharSequence) x.w0(uRLBuilder.getEncodedPathSegments())).length() == 0 && !list.isEmpty();
        if (list.size() > 1 && ((CharSequence) x.m0(list)).length() == 0 && !uRLBuilder.getEncodedPathSegments().isEmpty()) {
            z = true;
        }
        uRLBuilder.setEncodedPathSegments((z5 && z) ? x.I0(x.i0(1, uRLBuilder.getEncodedPathSegments()), x.h0(list, 1)) : z5 ? x.I0(x.i0(1, uRLBuilder.getEncodedPathSegments()), list) : z ? x.I0(uRLBuilder.getEncodedPathSegments(), x.h0(list, 1)) : x.I0(uRLBuilder.getEncodedPathSegments(), list));
        return uRLBuilder;
    }

    private static final void appendFile(Appendable appendable, String str, String str2) throws IOException {
        appendable.append("://");
        appendable.append(str);
        if (!o.A0(str2, '/')) {
            appendable.append('/');
        }
        appendable.append(str2);
    }

    private static final void appendMailto(Appendable appendable, String str, String str2) throws IOException {
        appendable.append(":");
        appendable.append(str);
        appendable.append(str2);
    }

    public static final URLBuilder appendPathSegments(URLBuilder uRLBuilder, String[] strArr, boolean z) {
        return appendPathSegments(uRLBuilder, (List<String>) kotlin.collections.r.o0(strArr), z);
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, List list, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, (List<String>) list, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <A extends Appendable> A appendTo(URLBuilder uRLBuilder, A a10) throws IOException {
        a10.append(uRLBuilder.getProtocol().getName());
        String name = uRLBuilder.getProtocol().getName();
        if (p.a(name, "file")) {
            appendFile(a10, uRLBuilder.getHost(), getEncodedPath(uRLBuilder));
            return a10;
        }
        if (p.a(name, "mailto")) {
            appendMailto(a10, getEncodedUserAndPassword(uRLBuilder), uRLBuilder.getHost());
            return a10;
        }
        a10.append("://");
        a10.append(getAuthority(uRLBuilder));
        URLUtilsKt.appendUrlFullPath(a10, getEncodedPath(uRLBuilder), uRLBuilder.getEncodedParameters(), uRLBuilder.getTrailingQuery());
        if (uRLBuilder.getEncodedFragment().length() > 0) {
            a10.append('#');
            a10.append(uRLBuilder.getEncodedFragment());
        }
        return a10;
    }

    public static final URLBuilder clone(URLBuilder uRLBuilder) {
        return URLUtilsKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null), uRLBuilder);
    }

    public static final String getAuthority(URLBuilder uRLBuilder) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getEncodedUserAndPassword(uRLBuilder));
        sb2.append(uRLBuilder.getHost());
        if (uRLBuilder.getPort() != 0 && uRLBuilder.getPort() != uRLBuilder.getProtocol().getDefaultPort()) {
            sb2.append(":");
            sb2.append(String.valueOf(uRLBuilder.getPort()));
        }
        return sb2.toString();
    }

    public static final String getEncodedPath(URLBuilder uRLBuilder) {
        return joinPath(uRLBuilder.getEncodedPathSegments());
    }

    public static final String getEncodedUserAndPassword(URLBuilder uRLBuilder) {
        StringBuilder sb2 = new StringBuilder();
        URLUtilsKt.appendUserAndPassword(sb2, uRLBuilder.getEncodedUser(), uRLBuilder.getEncodedPassword());
        return sb2.toString();
    }

    private static final String joinPath(List<String> list) {
        return list.isEmpty() ? "" : list.size() == 1 ? ((CharSequence) x.m0(list)).length() == 0 ? DomExceptionUtils.SEPARATOR : (String) x.m0(list) : x.u0(list, DomExceptionUtils.SEPARATOR, null, null, null, 62);
    }

    public static final void path(URLBuilder uRLBuilder, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(CodecsKt.encodeURLPath(str));
        }
        uRLBuilder.setEncodedPathSegments(arrayList);
    }

    @e
    public static final URLBuilder pathComponents(URLBuilder uRLBuilder, String... strArr) {
        return appendPathSegments$default(uRLBuilder, kotlin.collections.r.o0(strArr), false, 2, (Object) null);
    }

    public static final void set(URLBuilder uRLBuilder, String str, String str2, Integer num, String str3, l<? super URLBuilder, t0> lVar) {
        if (str != null) {
            uRLBuilder.setProtocol(URLProtocol.INSTANCE.createOrDefault(str));
        }
        if (str2 != null) {
            uRLBuilder.setHost(str2);
        }
        if (num != null) {
            uRLBuilder.setPort(num.intValue());
        }
        if (str3 != null) {
            setEncodedPath(uRLBuilder, str3);
        }
        lVar.invoke(uRLBuilder);
    }

    public static /* synthetic */ void set$default(URLBuilder uRLBuilder, String str, String str2, Integer num, String str3, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            num = null;
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        if ((i10 & 16) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        set(uRLBuilder, str, str2, num, str3, lVar);
    }

    public static final void setEncodedPath(URLBuilder uRLBuilder, String str) {
        uRLBuilder.setEncodedPathSegments(o.h0(str) ? z.f19728i : str.equals(DomExceptionUtils.SEPARATOR) ? URLParserKt.getROOT_PATH() : new ArrayList(o.x0(str, new char[]{'/'}, 6)));
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, String[] strArr, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, strArr, z);
    }

    @e
    public static final URLBuilder pathComponents(URLBuilder uRLBuilder, List<String> list) {
        return appendPathSegments$default(uRLBuilder, (List) list, false, 2, (Object) null);
    }

    public static final URLBuilder appendPathSegments(URLBuilder uRLBuilder, List<String> list, boolean z) {
        if (!z) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                x.b0(arrayList, o.x0((String) it.next(), new char[]{'/'}, 6));
            }
            list = arrayList;
        }
        ArrayList arrayList2 = new ArrayList(s.U(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(CodecsKt.encodeURLPathPart((String) it2.next()));
        }
        appendEncodedPathSegments(uRLBuilder, arrayList2);
        return uRLBuilder;
    }

    public static final URLBuilder appendEncodedPathSegments(URLBuilder uRLBuilder, String... strArr) {
        return appendEncodedPathSegments(uRLBuilder, (List<String>) kotlin.collections.r.o0(strArr));
    }
}
