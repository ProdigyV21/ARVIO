package com.google.firebase.installations;

import android.content.Context;
import android.os.SystemClock;
import androidx.core.app.FrameMetricsAggregator;
import com.google.firebase.components.u;
import com.google.firebase.datatransport.TransportRegistrar;
import gb.v;
import io.github.jan.supabase.postgrest.GetColumnNameKt;
import io.github.jan.supabase.postgrest.PropertyConversionMethod;
import io.sentry.android.core.q0;
import io.sentry.util.j;
import java.net.ProtocolFamily;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import kotlin.reflect.o;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements com.google.firebase.components.f, v, PropertyConversionMethod, io.sentry.transport.f, j, io.sentry.android.core.util.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14383i;

    public /* synthetic */ d(int i10) {
        this.f14383i = i10;
    }

    public static /* bridge */ /* synthetic */ Class b() {
        return ProtocolFamily.class;
    }

    public static /* bridge */ /* synthetic */ Path e(Object obj) {
        return (Path) obj;
    }

    public static /* bridge */ /* synthetic */ WatchKey h(Object obj) {
        return (WatchKey) obj;
    }

    @Override // io.sentry.android.core.util.a
    public Object a(Context context) {
        switch (this.f14383i) {
            case 28:
                return q0.b(context);
            default:
                try {
                    return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                } catch (Throwable unused) {
                    return null;
                }
        }
    }

    @Override // com.google.firebase.components.f
    public Object f(u uVar) {
        switch (this.f14383i) {
            case 0:
                return FirebaseInstallationsRegistrar.lambda$getComponents$0(uVar);
            default:
                return TransportRegistrar.lambda$getComponents$0(uVar);
        }
    }

    @Override // io.sentry.util.j
    public Object g() {
        return new FrameMetricsAggregator();
    }

    @Override // io.sentry.transport.f
    public long getCurrentTimeMillis() {
        return SystemClock.uptimeMillis();
    }

    @Override // io.github.jan.supabase.postgrest.PropertyConversionMethod
    public String invoke(o oVar) {
        switch (this.f14383i) {
            case 3:
                return GetColumnNameKt.getSerialName(oVar);
            case 4:
                return PropertyConversionMethod.Companion.CAMEL_CASE_TO_SNAKE_CASE$lambda$1(oVar);
            default:
                return oVar.getName();
        }
    }
}
