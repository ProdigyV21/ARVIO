package androidx.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class q extends androidx.activity.result.k {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ t f987h;

    public q(t tVar) {
        this.f987h = tVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.activity.result.k
    public final void b(int i10, g.a aVar, Object obj) {
        Bundle bundleExtra;
        int i11;
        t tVar = this.f987h;
        com.google.android.gms.internal.fido.c cVarB = aVar.b(tVar, obj);
        if (cVarB != null) {
            new Handler(Looper.getMainLooper()).post(new p(i10, 0, this, cVarB));
            return;
        }
        Intent intentA = aVar.a(obj);
        if (intentA.getExtras() != null && intentA.getExtras().getClassLoader() == null) {
            intentA.setExtrasClassLoader(tVar.getClassLoader());
        }
        if (intentA.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
            bundleExtra = intentA.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            intentA.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
        } else {
            bundleExtra = null;
        }
        Bundle bundle = bundleExtra;
        if (!"androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentA.getAction())) {
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentA.getAction())) {
                tVar.startActivityForResult(intentA, i10, bundle);
                return;
            }
            androidx.activity.result.n nVar = (androidx.activity.result.n) intentA.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                i11 = i10;
            } catch (IntentSender.SendIntentException e5) {
                e = e5;
                i11 = i10;
            }
            try {
                tVar.startIntentSenderForResult(nVar.f1012i, i11, nVar.f1013l, nVar.f1014m, nVar.f1015n, 0, bundle);
                return;
            } catch (IntentSender.SendIntentException e6) {
                e = e6;
                new Handler(Looper.getMainLooper()).post(new p(i11, 1, this, e));
                return;
            }
        }
        String[] stringArrayExtra = intentA.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        HashSet hashSet = new HashSet();
        for (int i12 = 0; i12 < stringArrayExtra.length; i12++) {
            if (TextUtils.isEmpty(stringArrayExtra[i12])) {
                throw new IllegalArgumentException(a0.c.p(new StringBuilder("Permission request for permissions "), Arrays.toString(stringArrayExtra), " must not contain null or empty values"));
            }
            if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(stringArrayExtra[i12], "android.permission.POST_NOTIFICATIONS")) {
                hashSet.add(Integer.valueOf(i12));
            }
        }
        int size = hashSet.size();
        String[] strArr = size > 0 ? new String[stringArrayExtra.length - size] : stringArrayExtra;
        if (size > 0) {
            if (size == stringArrayExtra.length) {
                return;
            }
            int i13 = 0;
            for (int i14 = 0; i14 < stringArrayExtra.length; i14++) {
                if (!hashSet.contains(Integer.valueOf(i14))) {
                    strArr[i13] = stringArrayExtra[i14];
                    i13++;
                }
            }
        }
        if (tVar instanceof androidx.core.app.g) {
            ((androidx.core.app.g) tVar).validateRequestPermissionsRequestCode(i10);
        }
        androidx.core.app.b.b(tVar, stringArrayExtra, i10);
    }
}
