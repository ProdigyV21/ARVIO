package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.ui.theme.ColorKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$SettingsScreenKt {

    /* JADX INFO: renamed from: lambda$-1755645267, reason: not valid java name */
    private static r7.q<RowScope, Composer, Integer, x6.t0> f70lambda$1755645267;

    /* JADX INFO: renamed from: lambda$-1825293141, reason: not valid java name */
    private static r7.q<RowScope, Composer, Integer, x6.t0> f71lambda$1825293141;

    /* JADX INFO: renamed from: lambda$-892732435, reason: not valid java name */
    private static r7.q<BoxScope, Composer, Integer, x6.t0> f73lambda$892732435;
    private static r7.q<BoxScope, Composer, Integer, x6.t0> lambda$1133847537;
    private static r7.q<BoxScope, Composer, Integer, x6.t0> lambda$1629219017;
    private static r7.q<BoxScope, Composer, Integer, x6.t0> lambda$637247930;
    private static r7.q<BoxScope, Composer, Integer, x6.t0> lambda$849727446;
    public static final ComposableSingletons$SettingsScreenKt INSTANCE = new ComposableSingletons$SettingsScreenKt();
    private static r7.p<Composer, Integer, x6.t0> lambda$1099712397 = ComposableLambdaKt.composableLambdaInstance(1099712397, false, new a(0));
    private static r7.p<Composer, Integer, x6.t0> lambda$1189184630 = ComposableLambdaKt.composableLambdaInstance(1189184630, false, new a(3));
    private static r7.p<Composer, Integer, x6.t0> lambda$1003862489 = ComposableLambdaKt.composableLambdaInstance(1003862489, false, new a(4));

    /* JADX INFO: renamed from: lambda$-841563598, reason: not valid java name */
    private static r7.p<Composer, Integer, x6.t0> f72lambda$841563598 = ComposableLambdaKt.composableLambdaInstance(-841563598, false, new a(1));
    private static r7.p<Composer, Integer, x6.t0> lambda$1713680901 = ComposableLambdaKt.composableLambdaInstance(1713680901, false, new a(2));

    static {
        final int i10 = 2;
        lambda$637247930 = ComposableLambdaKt.composableLambdaInstance(637247930, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i10) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i11 = 3;
        lambda$1133847537 = ComposableLambdaKt.composableLambdaInstance(1133847537, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i11) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i12 = 4;
        f73lambda$892732435 = ComposableLambdaKt.composableLambdaInstance(-892732435, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i12) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i13 = 5;
        lambda$849727446 = ComposableLambdaKt.composableLambdaInstance(849727446, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i13) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i14 = 6;
        lambda$1629219017 = ComposableLambdaKt.composableLambdaInstance(1629219017, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i14) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i15 = 0;
        f71lambda$1825293141 = ComposableLambdaKt.composableLambdaInstance(-1825293141, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i15) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i16 = 1;
        f70lambda$1755645267 = ComposableLambdaKt.composableLambdaInstance(-1755645267, false, new r7.q() { // from class: com.arflix.tv.ui.screens.settings.b
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i16) {
                    case 0:
                        return ComposableSingletons$SettingsScreenKt.lambda__1825293141$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$SettingsScreenKt.lambda__1755645267$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$SettingsScreenKt.lambda_637247930$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$SettingsScreenKt.lambda_1133847537$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$SettingsScreenKt.lambda__892732435$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 5:
                        return ComposableSingletons$SettingsScreenKt.lambda_849727446$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$SettingsScreenKt.lambda_1629219017$lambda$0((BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1003862489$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1003862489, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$1003862489.<anonymous> (SettingsScreen.kt:6759)");
            }
            TextKt.m6020Text4IGK_g(OkHttpProvider.DEFAULT_USER_AGENT, null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 6, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1099712397$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1099712397, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$1099712397.<anonymous> (SettingsScreen.kt:2785)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.settings_label_device_preset_name, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1133847537$lambda$0(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1133847537, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$1133847537.<anonymous> (SettingsScreen.kt:6710)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.save, composer, 0), SizeKt.fillMaxWidth$default(PaddingKt.m531paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5678constructorimpl(12), 1, null), 0.0f, 1, null), ColorKt.getPink(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 48, 0, 130552);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1189184630$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1189184630, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$1189184630.<anonymous> (SettingsScreen.kt:2799)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.settings_label_regex_pattern, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1629219017$lambda$0(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1629219017, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$1629219017.<anonymous> (SettingsScreen.kt:6892)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.done, composer, 0), PaddingKt.m530paddingVpY3zN4(Modifier.INSTANCE, Dp.m5678constructorimpl(32), Dp.m5678constructorimpl(12)), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 48, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1713680901$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1713680901, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$1713680901.<anonymous> (SettingsScreen.kt:9172)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.mdblist_key_hint, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_637247930$lambda$0(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(637247930, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$637247930.<anonymous> (SettingsScreen.kt:6692)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.cancel, composer, 0), SizeKt.fillMaxWidth$default(PaddingKt.m531paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5678constructorimpl(12), 1, null), 0.0f, 1, null), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 48, 0, 130552);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_849727446$lambda$0(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(849727446, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$849727446.<anonymous> (SettingsScreen.kt:6812)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.save, composer, 0), SizeKt.fillMaxWidth$default(PaddingKt.m531paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5678constructorimpl(12), 1, null), 0.0f, 1, null), ColorKt.getPink(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 48, 0, 130552);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__1755645267$lambda$0(RowScope rowScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1755645267, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$-1755645267.<anonymous> (SettingsScreen.kt:9191)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.cancel, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__1825293141$lambda$0(RowScope rowScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1825293141, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$-1825293141.<anonymous> (SettingsScreen.kt:9187)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.connect, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__841563598$lambda$0(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-841563598, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$-841563598.<anonymous> (SettingsScreen.kt:9164)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.mdblist_connect_title, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__892732435$lambda$0(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-892732435, i10, -1, "com.arflix.tv.ui.screens.settings.ComposableSingletons$SettingsScreenKt.lambda$-892732435.<anonymous> (SettingsScreen.kt:6789)");
            }
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.cancel, composer, 0), SizeKt.fillMaxWidth$default(PaddingKt.m531paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m5678constructorimpl(12), 1, null), 0.0f, 1, null), ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, null, composer, 48, 0, 130552);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-1755645267$app_playRelease, reason: not valid java name */
    public final r7.q<RowScope, Composer, Integer, x6.t0> m6292getLambda$1755645267$app_playRelease() {
        return f70lambda$1755645267;
    }

    /* JADX INFO: renamed from: getLambda$-1825293141$app_playRelease, reason: not valid java name */
    public final r7.q<RowScope, Composer, Integer, x6.t0> m6293getLambda$1825293141$app_playRelease() {
        return f71lambda$1825293141;
    }

    /* JADX INFO: renamed from: getLambda$-841563598$app_playRelease, reason: not valid java name */
    public final r7.p<Composer, Integer, x6.t0> m6294getLambda$841563598$app_playRelease() {
        return f72lambda$841563598;
    }

    /* JADX INFO: renamed from: getLambda$-892732435$app_playRelease, reason: not valid java name */
    public final r7.q<BoxScope, Composer, Integer, x6.t0> m6295getLambda$892732435$app_playRelease() {
        return f73lambda$892732435;
    }

    public final r7.p<Composer, Integer, x6.t0> getLambda$1003862489$app_playRelease() {
        return lambda$1003862489;
    }

    public final r7.p<Composer, Integer, x6.t0> getLambda$1099712397$app_playRelease() {
        return lambda$1099712397;
    }

    public final r7.q<BoxScope, Composer, Integer, x6.t0> getLambda$1133847537$app_playRelease() {
        return lambda$1133847537;
    }

    public final r7.p<Composer, Integer, x6.t0> getLambda$1189184630$app_playRelease() {
        return lambda$1189184630;
    }

    public final r7.q<BoxScope, Composer, Integer, x6.t0> getLambda$1629219017$app_playRelease() {
        return lambda$1629219017;
    }

    public final r7.p<Composer, Integer, x6.t0> getLambda$1713680901$app_playRelease() {
        return lambda$1713680901;
    }

    public final r7.q<BoxScope, Composer, Integer, x6.t0> getLambda$637247930$app_playRelease() {
        return lambda$637247930;
    }

    public final r7.q<BoxScope, Composer, Integer, x6.t0> getLambda$849727446$app_playRelease() {
        return lambda$849727446;
    }
}
