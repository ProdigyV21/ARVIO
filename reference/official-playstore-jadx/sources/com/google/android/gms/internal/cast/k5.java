package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class k5 implements ta, gb, d4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13389i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final k5 f13376l = new k5(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k5 f13377m = new k5(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k5 f13378n = new k5(2);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final k5 f13379o = new k5(3);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final k5 f13380p = new k5(4);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final k5 f13381q = new k5(5);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final k5 f13382r = new k5(6);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final k5 f13383s = new k5(7);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final k5 f13384t = new k5(8);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final k5 f13385u = new k5(9);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final k5 f13386v = new k5(10);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final k5 f13387w = new k5(11);
    public static final k5 x = new k5(12);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final k5 f13388y = new k5(13);
    public static final k5 z = new k5(14);
    public static final k5 A = new k5(15);
    public static final k5 B = new k5(16);
    public static final k5 C = new k5(17);
    public static final k5 D = new k5(18);
    public static final k5 E = new k5(19);
    public static final k5 F = new k5(20);
    public static final k5 G = new k5(21);
    public static final k5 H = new k5(22);
    public static final k5 I = new k5(23);
    public static final /* synthetic */ k5 J = new k5(26);
    public static final /* synthetic */ k5 K = new k5(29);

    public /* synthetic */ k5(int i10) {
        this.f13389i = i10;
    }

    @Override // com.google.android.gms.internal.cast.gb
    public boolean a(Class cls) {
        switch (this.f13389i) {
            case 23:
                return pa.class.isAssignableFrom(cls);
            default:
                return false;
        }
    }

    @Override // com.google.android.gms.internal.cast.gb
    public fb b(Class cls) {
        switch (this.f13389i) {
            case 23:
                if (!pa.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
                }
                try {
                    return (fb) pa.h(cls.asSubclass(pa.class)).k(3, null);
                } catch (Exception e5) {
                    throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e5);
                }
            default:
                throw new IllegalStateException("This should never be called.");
        }
    }

    @Override // com.google.android.gms.internal.cast.d4
    public /* synthetic */ Object zza() {
        switch (this.f13389i) {
            case 26:
                throw new IllegalStateException();
            default:
                Logger logger = b.f13170v;
                CastContext sharedInstance = CastContext.getSharedInstance();
                com.google.android.gms.common.internal.t.i(sharedInstance);
                return sharedInstance.getCastOptions().getReceiverApplicationId();
        }
    }
}
