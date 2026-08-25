package com.arflix.tv.ui.screens.crash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.activity.t;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.MainActivity;
import com.arflix.tv.ui.theme.ThemeKt;
import e.f;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/arflix/tv/ui/screens/crash/CrashReportActivity;", "Landroidx/activity/t;", "<init>", "()V", "Landroid/os/Bundle;", "savedInstanceState", "Lx6/t0;", "onCreate", "(Landroid/os/Bundle;)V", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CrashReportActivity extends t {
    public static final String DISCORD_BUG_CHANNEL_URL = "https://discord.gg/UavuEYMfQ4";
    public static final String EXTRA_CRASH_ID = "extra_crash_id";
    public static final String EXTRA_CRASH_MSG = "extra_crash_msg";
    public static final String EXTRA_CRASH_TIME = "extra_crash_time";
    public static final int $stable = 8;

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$0(String str, String str2, long j10, String str3, CrashReportActivity crashReportActivity, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(738518729, i10, -1, "com.arflix.tv.ui.screens.crash.CrashReportActivity.onCreate.<anonymous> (CrashReportActivity.kt:52)");
            }
            ThemeKt.ArflixTvTheme(false, null, ComposableLambdaKt.rememberComposableLambda(-2028142659, true, new a(str, str2, j10, str3, crashReportActivity, 1), composer, 54), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$0$0(String str, String str2, long j10, String str3, final CrashReportActivity crashReportActivity, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2028142659, i10, -1, "com.arflix.tv.ui.screens.crash.CrashReportActivity.onCreate.<anonymous>.<anonymous> (CrashReportActivity.kt:53)");
            }
            boolean zChangedInstance = composer.changedInstance(crashReportActivity);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final int i11 = 0;
                objRememberedValue = new r7.a(crashReportActivity) { // from class: com.arflix.tv.ui.screens.crash.b

                    /* JADX INFO: renamed from: l, reason: collision with root package name */
                    public final /* synthetic */ CrashReportActivity f8691l;

                    {
                        this.f8691l = crashReportActivity;
                    }

                    @Override // r7.a
                    public final Object invoke() {
                        switch (i11) {
                            case 0:
                                return CrashReportActivity.onCreate$lambda$0$0$0$0(this.f8691l);
                            default:
                                return CrashReportActivity.onCreate$lambda$0$0$1$0(this.f8691l);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            r7.a aVar = (r7.a) objRememberedValue;
            boolean zChangedInstance2 = composer.changedInstance(crashReportActivity);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                final int i12 = 1;
                objRememberedValue2 = new r7.a(crashReportActivity) { // from class: com.arflix.tv.ui.screens.crash.b

                    /* JADX INFO: renamed from: l, reason: collision with root package name */
                    public final /* synthetic */ CrashReportActivity f8691l;

                    {
                        this.f8691l = crashReportActivity;
                    }

                    @Override // r7.a
                    public final Object invoke() {
                        switch (i12) {
                            case 0:
                                return CrashReportActivity.onCreate$lambda$0$0$0$0(this.f8691l);
                            default:
                                return CrashReportActivity.onCreate$lambda$0$0$1$0(this.f8691l);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            CrashReportActivityKt.CrashReportScreen(str, str2, j10, str3, aVar, (r7.a) objRememberedValue2, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$0$0$0$0(CrashReportActivity crashReportActivity) {
        Intent intent = new Intent(crashReportActivity, (Class<?>) MainActivity.class);
        intent.addFlags(268468224);
        crashReportActivity.startActivity(intent);
        crashReportActivity.finish();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 onCreate$lambda$0$0$1$0(CrashReportActivity crashReportActivity) {
        crashReportActivity.finishAffinity();
        return t0.f22605a;
    }

    @Override // androidx.activity.t, androidx.core.app.m, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("arvio_crash_store", 0);
        String stringExtra = getIntent().getStringExtra(EXTRA_CRASH_ID);
        String str = (stringExtra == null && (stringExtra = sharedPreferences.getString("last_crash_id", "N/A")) == null) ? "N/A" : stringExtra;
        String stringExtra2 = getIntent().getStringExtra(EXTRA_CRASH_MSG);
        String str2 = (stringExtra2 == null && (stringExtra2 = sharedPreferences.getString("last_crash_msg", "Unexpected error")) == null) ? "Unexpected error" : stringExtra2;
        long longExtra = getIntent().getLongExtra(EXTRA_CRASH_TIME, sharedPreferences.getLong("last_crash_time", System.currentTimeMillis()));
        String string = sharedPreferences.getString("last_crash_version", "1.0");
        f.a(this, ComposableLambdaKt.composableLambdaInstance(738518729, true, new a(str, str2, longExtra, string == null ? "1.0" : string, this, 0)));
    }
}
