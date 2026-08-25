package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class w0 extends g.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2854a;

    public /* synthetic */ w0(int i10) {
        this.f2854a = i10;
    }

    @Override // g.a
    public final Intent a(Object obj) {
        Bundle bundleExtra;
        switch (this.f2854a) {
            case 0:
                androidx.activity.result.n nVar = (androidx.activity.result.n) obj;
                Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
                Intent intent2 = nVar.f1013l;
                if (intent2 != null && (bundleExtra = intent2.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                    intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                    intent2.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                    if (intent2.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                        nVar = new androidx.activity.result.n(nVar.f1012i, null, nVar.f1014m, nVar.f1015n);
                    }
                }
                intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", nVar);
                if (b1.G(2)) {
                    Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
                }
                return intent;
            case 1:
                return new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType((String) obj);
            case 2:
                return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", (String[]) obj);
            default:
                return (Intent) obj;
        }
    }

    @Override // g.a
    public com.google.android.gms.internal.fido.c b(Context context, Object obj) {
        switch (this.f2854a) {
            case 1:
                return null;
            case 2:
                String[] strArr = (String[]) obj;
                if (strArr.length == 0) {
                    return new com.google.android.gms.internal.fido.c(kotlin.collections.a0.f19683i);
                }
                for (String str : strArr) {
                    if (qb.d.f(context, str) != 0) {
                        return null;
                    }
                }
                int iQ0 = kotlin.collections.i0.q0(strArr.length);
                if (iQ0 < 16) {
                    iQ0 = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
                for (String str2 : strArr) {
                    linkedHashMap.put(str2, Boolean.TRUE);
                }
                return new com.google.android.gms.internal.fido.c(linkedHashMap);
            default:
                return super.b(context, obj);
        }
    }

    @Override // g.a
    public final Object c(Intent intent, int i10) {
        switch (this.f2854a) {
            case 0:
                return new androidx.activity.result.a(intent, i10);
            case 1:
                if (i10 != -1) {
                    intent = null;
                }
                if (intent != null) {
                    return intent.getData();
                }
                return null;
            case 2:
                if (i10 == -1 && intent != null) {
                    String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                    if (intArrayExtra != null && stringArrayExtra != null) {
                        ArrayList arrayList = new ArrayList(intArrayExtra.length);
                        for (int i11 : intArrayExtra) {
                            arrayList.add(Boolean.valueOf(i11 == 0));
                        }
                        return kotlin.collections.h0.A0(kotlin.collections.x.i1(kotlin.collections.r.X(stringArrayExtra), arrayList));
                    }
                }
                return kotlin.collections.a0.f19683i;
            default:
                return new androidx.activity.result.a(intent, i10);
        }
    }
}
