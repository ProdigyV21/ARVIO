package io.ktor.http;

import androidx.compose.foundation.c;
import io.ktor.http.Parameters;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.text.a;
import kotlin.text.o;
import r7.l;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\u001a)\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0001j\u0002`\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a%\u0010\u000b\u001a\u00020\u0000*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n0\t¢\u0006\u0004\b\u000b\u0010\f\u001a1\u0010\u0011\u001a\u00020\u0010*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n0\t2\n\u0010\u000f\u001a\u00060\rj\u0002`\u000e¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0011\u0010\u000b\u001a\u00020\u0000*\u00020\u0006¢\u0006\u0004\b\u000b\u0010\u0013\u001a\u001d\u0010\u0011\u001a\u00020\u0010*\u00020\u00062\n\u0010\u000f\u001a\u00060\rj\u0002`\u000e¢\u0006\u0004\b\u0011\u0010\u0014\u001a\u001f\u0010\u0011\u001a\u00020\u0010*\u00020\u00152\n\u0010\u000f\u001a\u00060\rj\u0002`\u000eH\u0000¢\u0006\u0004\b\u0011\u0010\u0016\u001a7\u0010\u0011\u001a\u00020\u0010*\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000\t0\u00180\u00172\n\u0010\u000f\u001a\u00060\rj\u0002`\u000eH\u0000¢\u0006\u0004\b\u0011\u0010\u0019¨\u0006\u001a"}, d2 = {"", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "defaultEncoding", "", "limit", "Lio/ktor/http/Parameters;", "parseUrlEncodedParameters", "(Ljava/lang/String;Ljava/nio/charset/Charset;I)Lio/ktor/http/Parameters;", "", "Lx6/x;", "formUrlEncode", "(Ljava/util/List;)Ljava/lang/String;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "Lx6/t0;", "formUrlEncodeTo", "(Ljava/util/List;Ljava/lang/Appendable;)V", "(Lio/ktor/http/Parameters;)Ljava/lang/String;", "(Lio/ktor/http/Parameters;Ljava/lang/Appendable;)V", "Lio/ktor/http/ParametersBuilder;", "(Lio/ktor/http/ParametersBuilder;Ljava/lang/Appendable;)V", "", "", "(Ljava/util/Set;Ljava/lang/Appendable;)V", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpUrlEncodedKt {

    /* JADX INFO: renamed from: io.ktor.http.HttpUrlEncodedKt$formUrlEncodeTo$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lx6/x;", "", "it", "", "invoke", "(Lx6/x;)Ljava/lang/CharSequence;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<x, CharSequence> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final CharSequence invoke(x xVar) throws Throwable {
            String strEncodeURLParameter = CodecsKt.encodeURLParameter((String) xVar.f22608i, true);
            Object obj = xVar.f22609l;
            return obj == null ? strEncodeURLParameter : c.m('=', strEncodeURLParameter, CodecsKt.encodeURLParameterValue(String.valueOf(obj)));
        }
    }

    public static final String formUrlEncode(List<x> list) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        formUrlEncodeTo(list, sb2);
        return sb2.toString();
    }

    public static final void formUrlEncodeTo(List<x> list, Appendable appendable) throws IOException {
        kotlin.collections.x.t0(list, appendable, "&", null, null, AnonymousClass1.INSTANCE, 60);
    }

    public static final Parameters parseUrlEncodedParameters(String str, Charset charset, int i10) {
        Object next;
        String name;
        List<String> listY0 = o.y0(str, new String[]{"&"}, i10, 2);
        ArrayList<x> arrayList = new ArrayList(s.U(listY0, 10));
        for (String str2 : listY0) {
            arrayList.add(new x(o.H0(str2, "="), o.D0(str2, "=", "")));
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((x) next).f22608i, "_charset_")) {
                break;
            }
        }
        x xVar = (x) next;
        if (xVar == null || (name = (String) xVar.f22609l) == null) {
            name = CharsetJVMKt.getName(charset);
        }
        Charset charsetForName = Charset.forName(name);
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        for (x xVar2 : arrayList) {
            parametersBuilderParametersBuilder$default.append(CodecsKt.decodeURLQueryComponent$default((String) xVar2.f22608i, 0, 0, false, charsetForName, 7, null), CodecsKt.decodeURLQueryComponent$default((String) xVar2.f22609l, 0, 0, false, charsetForName, 7, null));
        }
        return parametersBuilderParametersBuilder$default.build();
    }

    public static /* synthetic */ Parameters parseUrlEncodedParameters$default(String str, Charset charset, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            charset = a.f19924a;
        }
        if ((i11 & 2) != 0) {
            i10 = 1000;
        }
        return parseUrlEncodedParameters(str, charset, i10);
    }

    public static final String formUrlEncode(Parameters parameters) {
        Set<Map.Entry<String, List<String>>> setEntries = parameters.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList2 = new ArrayList(s.U(iterable, 10));
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new x(entry.getKey(), (String) it2.next()));
            }
            kotlin.collections.x.b0(arrayList, arrayList2);
        }
        return formUrlEncode(arrayList);
    }

    public static final void formUrlEncodeTo(Parameters parameters, Appendable appendable) throws IOException {
        formUrlEncodeTo(parameters.entries(), appendable);
    }

    public static final void formUrlEncodeTo(ParametersBuilder parametersBuilder, Appendable appendable) throws IOException {
        formUrlEncodeTo(parametersBuilder.entries(), appendable);
    }

    public static final void formUrlEncodeTo(Set<? extends Map.Entry<String, ? extends List<String>>> set, Appendable appendable) throws IOException {
        List listSingletonList;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.isEmpty()) {
                listSingletonList = Collections.singletonList(new x(str, null));
            } else {
                ArrayList arrayList2 = new ArrayList(s.U(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new x(str, (String) it2.next()));
                }
                listSingletonList = arrayList2;
            }
            kotlin.collections.x.b0(arrayList, listSingletonList);
        }
        formUrlEncodeTo(arrayList, appendable);
    }
}
