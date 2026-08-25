package androidx.navigation;

import android.net.Uri;
import androidx.fragment.app.a2;
import io.sentry.y6;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class w extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4789i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ y f4790l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(y yVar, int i10) {
        super(0);
        this.f4789i = i10;
        this.f4790l = yVar;
    }

    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() {
        List list;
        switch (this.f4789i) {
            case 0:
                x6.x xVar = (x6.x) this.f4790l.f4812h.getValue();
                return (xVar == null || (list = (List) xVar.f22608i) == null) ? new ArrayList() : list;
            case 1:
                String str = this.f4790l.f4805a;
                if (Uri.parse(str).getFragment() == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                String fragment = Uri.parse(str).getFragment();
                StringBuilder sb2 = new StringBuilder();
                y.a(fragment, arrayList, sb2);
                return new x6.x(arrayList, sb2.toString());
            case 2:
                String str2 = (String) this.f4790l.f4814j.getValue();
                if (str2 != null) {
                    return Pattern.compile(str2, 2);
                }
                return null;
            case 3:
                x6.x xVar2 = (x6.x) this.f4790l.f4812h.getValue();
                if (xVar2 != null) {
                    return (String) xVar2.f22609l;
                }
                return null;
            case 4:
                String str3 = this.f4790l.f4805a;
                return Boolean.valueOf((str3 == null || Uri.parse(str3).getQuery() == null) ? false : true);
            case 5:
                return null;
            case 6:
                String str4 = this.f4790l.f4807c;
                if (str4 != null) {
                    return Pattern.compile(str4, 2);
                }
                return null;
            default:
                y yVar = this.f4790l;
                String str5 = yVar.f4805a;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (((Boolean) yVar.f4809e.getValue()).booleanValue()) {
                    Uri uri = Uri.parse(str5);
                    for (String str6 : uri.getQueryParameterNames()) {
                        StringBuilder sb3 = new StringBuilder();
                        List<String> queryParameters = uri.getQueryParameters(str6);
                        if (queryParameters.size() > 1) {
                            throw new IllegalArgumentException(a2.m("Query parameter ", str6, " must only be present once in ", str5, ". To support repeated query parameters, use an array type for your argument and the pattern provided in your URI will be used to parse each query parameter instance.").toString());
                        }
                        String str7 = (String) kotlin.collections.x.o0(queryParameters);
                        if (str7 == null) {
                            yVar.f4811g = true;
                            str7 = str6;
                        }
                        Matcher matcher = y.f4804n.matcher(str7);
                        v vVar = new v();
                        int iEnd = 0;
                        while (matcher.find()) {
                            vVar.f4787b.add(matcher.group(1));
                            sb3.append(Pattern.quote(str7.substring(iEnd, matcher.start())));
                            sb3.append("(.+?)?");
                            iEnd = matcher.end();
                        }
                        if (iEnd < str7.length()) {
                            sb3.append(Pattern.quote(str7.substring(iEnd)));
                        }
                        vVar.f4786a = kotlin.text.u.O(sb3.toString(), y6.DEFAULT_PROPAGATION_TARGETS, "\\E.*\\Q", false);
                        linkedHashMap.put(str6, vVar);
                    }
                }
                return linkedHashMap;
        }
    }
}
