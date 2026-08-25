package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.internal.zzs;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 extends SessionProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CastOptions f13246a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x0 f13247b;

    public f0(Context context, CastOptions castOptions, x0 x0Var) {
        super(context, castOptions.getSupportedNamespaces().isEmpty() ? CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId()) : CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId(), castOptions.getSupportedNamespaces()));
        this.f13246a = castOptions;
        this.f13247b = x0Var;
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final Session createSession(String str) {
        Context context = getContext();
        String category = getCategory();
        Context context2 = getContext();
        CastOptions castOptions = this.f13246a;
        x0 x0Var = this.f13247b;
        return new CastSession(context, category, str, castOptions, x0Var, new zzs(context2, castOptions, x0Var));
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final boolean isSessionRecoverable() {
        return this.f13246a.getResumeSavedSession();
    }
}
