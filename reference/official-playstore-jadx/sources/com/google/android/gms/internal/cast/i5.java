package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.zzaa;

/* JADX INFO: loaded from: classes4.dex */
public final class i5 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Logger f13315j = new Logger("ApplicationAnalytics");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4 f13316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c0 f13317b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l5 f13318c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final SharedPreferences f13321f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j5 f13322g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CastSession f13323h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f13324i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final androidx.loader.content.j f13320e = new androidx.loader.content.j(Looper.getMainLooper(), 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g1 f13319d = new g1(this, 1);

    public i5(SharedPreferences sharedPreferences, z4 z4Var, c0 c0Var, Bundle bundle, String str) {
        this.f13321f = sharedPreferences;
        this.f13316a = z4Var;
        this.f13317b = c0Var;
        this.f13318c = new l5(bundle, str);
    }

    public final void a() {
        j5 j5Var = this.f13322g;
        j5Var.getClass();
        SharedPreferences sharedPreferences = this.f13321f;
        if (sharedPreferences == null) {
            return;
        }
        j5.f13341q.d("Save the ApplicationAnalyticsSession to SharedPreferences %s", sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("application_id", j5Var.f13344b);
        editorEdit.putString("receiver_metrics_id", j5Var.f13345c);
        editorEdit.putLong("analytics_session_id", j5Var.f13346d);
        editorEdit.putInt("event_sequence_number", j5Var.f13347e);
        editorEdit.putString("receiver_session_id", j5Var.f13348f);
        editorEdit.putInt("device_capabilities", j5Var.f13349g);
        editorEdit.putString("device_model_name", j5Var.f13350h);
        editorEdit.putString("manufacturer", j5Var.f13351i);
        editorEdit.putString("product_name", j5Var.f13352j);
        editorEdit.putString("build_type", j5Var.k);
        editorEdit.putString("cast_build_version", j5Var.f13353l);
        editorEdit.putString("system_build_number", j5Var.f13354m);
        editorEdit.putInt("device_category", j5Var.f13355n);
        editorEdit.putInt("analytics_session_start_type", j5Var.f13357p);
        editorEdit.putBoolean("is_output_switcher_enabled", j5Var.f13356o);
        editorEdit.apply();
    }

    public final void b(int i10) {
        f13315j.d("log session ended with error = %d", Integer.valueOf(i10));
        e();
        this.f13316a.a(this.f13318c.a(this.f13322g, i10), 228);
        this.f13320e.removeCallbacks(this.f13319d);
        if (this.f13324i) {
            return;
        }
        this.f13322g = null;
    }

    public final void c() {
        androidx.loader.content.j jVar = this.f13320e;
        com.google.android.gms.common.internal.t.i(jVar);
        g1 g1Var = this.f13319d;
        com.google.android.gms.common.internal.t.i(g1Var);
        jVar.postDelayed(g1Var, 300000L);
    }

    public final void d() {
        f13315j.d("Create a new ApplicationAnalyticsSession based on CastSession", new Object[0]);
        j5 j5Var = new j5(this.f13317b);
        j5.f13342r++;
        this.f13322g = j5Var;
        CastSession castSession = this.f13323h;
        j5Var.f13356o = castSession != null && castSession.zzd();
        j5 j5Var2 = this.f13322g;
        com.google.android.gms.common.internal.t.i(j5Var2);
        CastContext sharedInstance = CastContext.getSharedInstance();
        com.google.android.gms.common.internal.t.i(sharedInstance);
        j5Var2.f13344b = sharedInstance.getCastOptions().getReceiverApplicationId();
        CastSession castSession2 = this.f13323h;
        CastDevice castDevice = castSession2 == null ? null : castSession2.getCastDevice();
        if (castDevice != null) {
            f(castDevice);
        }
        j5 j5Var3 = this.f13322g;
        com.google.android.gms.common.internal.t.i(j5Var3);
        CastSession castSession3 = this.f13323h;
        j5Var3.f13357p = castSession3 != null ? castSession3.zzm() : 0;
        com.google.android.gms.common.internal.t.i(this.f13322g);
    }

    public final void e() {
        if (!g()) {
            f13315j.w("The analyticsSession should not be null for logging. Create a dummy one.", new Object[0]);
            d();
            return;
        }
        CastSession castSession = this.f13323h;
        CastDevice castDevice = castSession != null ? castSession.getCastDevice() : null;
        if (castDevice != null && !TextUtils.equals(this.f13322g.f13345c, castDevice.zza())) {
            f(castDevice);
        }
        com.google.android.gms.common.internal.t.i(this.f13322g);
    }

    public final void f(CastDevice castDevice) {
        j5 j5Var = this.f13322g;
        if (j5Var == null) {
            return;
        }
        j5Var.f13345c = castDevice.zza();
        j5Var.f13349g = castDevice.zzc();
        j5Var.f13350h = castDevice.getModelName();
        j5Var.f13355n = castDevice.zzd();
        zzaa zzaaVarZzb = castDevice.zzb();
        if (zzaaVarZzb != null) {
            String strZza = zzaaVarZzb.zza();
            if (strZza != null) {
                j5Var.f13351i = strZza;
            }
            String strZzb = zzaaVarZzb.zzb();
            if (strZzb != null) {
                j5Var.f13352j = strZzb;
            }
            String strZzc = zzaaVarZzb.zzc();
            if (strZzc != null) {
                j5Var.k = strZzc;
            }
            String strZzd = zzaaVarZzb.zzd();
            if (strZzd != null) {
                j5Var.f13353l = strZzd;
            }
            String strZze = zzaaVarZzb.zze();
            if (strZze != null) {
                j5Var.f13354m = strZze;
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final boolean g() {
        String str;
        j5 j5Var = this.f13322g;
        Logger logger = f13315j;
        if (j5Var == null) {
            logger.d("The analytics session is null when matching with application ID.", new Object[0]);
            return false;
        }
        CastContext sharedInstance = CastContext.getSharedInstance();
        com.google.android.gms.common.internal.t.i(sharedInstance);
        String receiverApplicationId = sharedInstance.getCastOptions().getReceiverApplicationId();
        if (receiverApplicationId == null || (str = this.f13322g.f13344b) == null || !TextUtils.equals(str, receiverApplicationId)) {
            logger.d("The analytics session doesn't match the application ID %s", receiverApplicationId);
            return false;
        }
        com.google.android.gms.common.internal.t.i(this.f13322g);
        return true;
    }

    public final boolean h(String str) {
        String str2;
        if (!g()) {
            return false;
        }
        com.google.android.gms.common.internal.t.i(this.f13322g);
        if (str != null && (str2 = this.f13322g.f13348f) != null && TextUtils.equals(str2, str)) {
            return true;
        }
        f13315j.d("The analytics session doesn't match the receiver session ID %s.", str);
        return false;
    }
}
