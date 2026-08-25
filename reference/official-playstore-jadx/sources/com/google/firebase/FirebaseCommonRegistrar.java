package com.google.firebase;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.work.impl.t;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.a;
import com.google.firebase.components.b;
import com.google.firebase.components.m;
import com.google.firebase.components.s;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import l5.c;
import l5.d;
import l5.f;
import retrofit2.l0;
import t5.e;
import x6.r;
import z4.g;

/* JADX INFO: loaded from: classes4.dex */
public class FirebaseCommonRegistrar implements ComponentRegistrar {
    public static /* synthetic */ String a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return (applicationInfo == null || Build.VERSION.SDK_INT < 24) ? "" : String.valueOf(applicationInfo.minSdkVersion);
    }

    public static String b(String str) {
        return str.replace(' ', '_').replace('/', '_');
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        String string;
        ArrayList arrayList = new ArrayList();
        a aVarB = b.b(e.class);
        aVarB.a(new m(2, 0, t5.a.class));
        aVarB.f14253f = new l0(2);
        arrayList.add(aVarB.b());
        s sVar = new s(c5.a.class, Executor.class);
        a aVar = new a(c.class, new Class[]{l5.e.class, f.class});
        aVar.a(m.b(Context.class));
        aVar.a(m.b(g.class));
        aVar.a(new m(2, 0, d.class));
        aVar.a(new m(1, 1, e.class));
        aVar.a(new m(sVar, 1, 0));
        aVar.f14253f = new io.sentry.cache.a(sVar, 9);
        arrayList.add(aVar.b());
        arrayList.add(t.j("fire-android", String.valueOf(Build.VERSION.SDK_INT)));
        arrayList.add(t.j("fire-core", "20.4.2"));
        arrayList.add(t.j("device-name", b(Build.PRODUCT)));
        arrayList.add(t.j("device-model", b(Build.DEVICE)));
        arrayList.add(t.j("device-brand", b(Build.BRAND)));
        arrayList.add(t.p("android-target-sdk", new l0(18)));
        arrayList.add(t.p("android-min-sdk", new l0(19)));
        arrayList.add(t.p("android-platform", new l0(20)));
        arrayList.add(t.p("android-installer", new l0(21)));
        try {
            string = r.f22598o.toString();
        } catch (NoClassDefFoundError unused) {
            string = null;
        }
        if (string != null) {
            arrayList.add(t.j("kotlin", string));
        }
        return arrayList;
    }
}
