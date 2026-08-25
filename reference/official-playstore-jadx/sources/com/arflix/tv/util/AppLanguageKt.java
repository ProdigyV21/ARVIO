package com.arflix.tv.util;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import c1.e;
import com.arflix.tv.ui.screens.player.d0;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0017\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0017\u0010\f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\f\u0010\u000b\"\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00000\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00000\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"", "languageTag", "Ljava/util/Locale;", "appLocale", "(Ljava/lang/String;)Ljava/util/Locale;", "Landroid/content/Context;", "context", "localizedAppContext", "(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Context;", "text", "tr", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "trUpper", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalAppLanguage", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalAppLanguage", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lc1/e;", "LAST_APP_LANGUAGE_KEY", "Lc1/e;", "getLAST_APP_LANGUAGE_KEY", "()Lc1/e;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AppLanguageKt {
    private static final ProvidableCompositionLocal<String> LocalAppLanguage = CompositionLocalKt.staticCompositionLocalOf(new d0(19));
    private static final e LAST_APP_LANGUAGE_KEY = new e("last_app_language");

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LocalAppLanguage$lambda$0() {
        return "en-US";
    }

    public static final Locale appLocale(String str) {
        Locale localeForLanguageTag = Locale.forLanguageTag(str.replace('_', '-'));
        if (o.h0(localeForLanguageTag.getLanguage())) {
            localeForLanguageTag = null;
        }
        return localeForLanguageTag == null ? Locale.US : localeForLanguageTag;
    }

    public static final e getLAST_APP_LANGUAGE_KEY() {
        return LAST_APP_LANGUAGE_KEY;
    }

    public static final ProvidableCompositionLocal<String> getLocalAppLanguage() {
        return LocalAppLanguage;
    }

    public static final Context localizedAppContext(Context context, String str) {
        Locale localeAppLocale = appLocale(str);
        Locale.setDefault(localeAppLocale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocales(new LocaleList(localeAppLocale));
        } else {
            configuration.setLocale(localeAppLocale);
        }
        return context.createConfigurationContext(configuration);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0583  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x0595  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x05e1  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x05fd  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x063d  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0673  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x06df  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0700  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0710  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x0720  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0730  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x074f  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0751  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0a5f  */
    /* JADX WARN: Removed duplicated region for block: B:588:0x0ac8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String tr(java.lang.String r3, androidx.compose.runtime.Composer r4, int r5) {
        /*
            Method dump skipped, instruction units count: 3462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AppLanguageKt.tr(java.lang.String, androidx.compose.runtime.Composer, int):java.lang.String");
    }

    public static final String trUpper(String str, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1101440852, i10, -1, "com.arflix.tv.util.trUpper (AppLanguage.kt:230)");
        }
        String upperCase = tr(str, composer, i10 & 14).toUpperCase(appLocale((String) composer.consume(LocalAppLanguage)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return upperCase;
    }
}
