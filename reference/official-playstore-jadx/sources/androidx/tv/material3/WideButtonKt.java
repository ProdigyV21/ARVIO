package androidx.tv.material3;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u001aÉ\u0001\u0010 \u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u0013\b\u0002\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b\n¢\u0006\u0002\b\u001cH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001aì\u0001\u0010 \u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0015\b\u0002\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\n2\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\n2\b\b\u0002\u0010\t\u001a\u00020\b2\u0013\b\u0002\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001aÁ\u0001\u0010)\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010&\u001a\u00020\u00142\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b\n¢\u0006\u0002\b\u001cH\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006,²\u0006\u000e\u0010*\u001a\u00020\u00148\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010+\u001a\u00020\u00148\n@\nX\u008a\u008e\u0002"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "onLongClick", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/runtime/Composable;", "background", "Landroidx/tv/material3/j;", "scale", "Landroidx/tv/material3/g;", "glow", "Landroidx/tv/material3/k;", "shape", "Landroidx/tv/material3/r4;", "contentColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/tv/material3/e;", "border", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lx6/n;", "content", "WideButton-WsuR4So", "(Lr7/a;Landroidx/compose/ui/Modifier;Lr7/a;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/tv/material3/j;Landroidx/tv/material3/g;Landroidx/tv/material3/k;Landroidx/tv/material3/r4;FLandroidx/tv/material3/e;Landroidx/compose/foundation/layout/PaddingValues;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "WideButton", LinkHeader.Parameters.Title, "icon", MediaTrack.ROLE_SUBTITLE, "WideButton-1hbxZUk", "(Lr7/a;Lr7/p;Landroidx/compose/ui/Modifier;Lr7/a;ZLr7/p;Lr7/p;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/tv/material3/j;Landroidx/tv/material3/g;Landroidx/tv/material3/k;Landroidx/tv/material3/r4;FLandroidx/tv/material3/e;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "minHeight", "WideButtonImpl-roJ8YiU", "(Lr7/a;ZLandroidx/tv/material3/j;Landroidx/tv/material3/g;Landroidx/tv/material3/k;Landroidx/tv/material3/r4;FLandroidx/tv/material3/e;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/ui/Modifier;Lr7/a;FLr7/q;Landroidx/compose/runtime/Composer;III)V", "WideButtonImpl", "buttonWidth", "buttonHeight", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WideButtonKt {
    /* JADX INFO: renamed from: WideButton-1hbxZUk, reason: not valid java name */
    public static final void m6023WideButton1hbxZUk(r7.a<x6.t0> aVar, r7.p<? super Composer, ? super Integer, x6.t0> pVar, Modifier modifier, r7.a<x6.t0> aVar2, boolean z, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, MutableInteractionSource mutableInteractionSource, r7.p<? super Composer, ? super Integer, x6.t0> pVar4, j jVar, g gVar, k kVar, r4 r4Var, float f10, e eVar, PaddingValues paddingValues, Composer composer, int i10, int i11, int i12) {
        MutableInteractionSource mutableInteractionSource2;
        int i13;
        boolean z5;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar5;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar6;
        MutableInteractionSource mutableInteractionSource3;
        r4 r4VarM6022contentColorro_MJ88;
        Composer composer2;
        e eVarBorder;
        composer.startReplaceableGroup(751560171);
        Modifier modifier2 = (i12 & 4) != 0 ? Modifier.INSTANCE : modifier;
        r7.a<x6.t0> aVar3 = (i12 & 8) != 0 ? null : aVar2;
        boolean z10 = (i12 & 16) != 0 ? true : z;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar7 = (i12 & 32) != 0 ? null : pVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar8 = (i12 & 64) == 0 ? pVar3 : null;
        if ((i12 & 128) != 0) {
            composer.startReplaceableGroup(7260981);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceableGroup();
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        r7.p<? super Composer, ? super Integer, x6.t0> pVarComposableLambda = (i12 & 256) != 0 ? ComposableLambdaKt.composableLambda(composer, 1539367727, true, new WideButtonKt$WideButton$4(z10, mutableInteractionSource2)) : pVar4;
        j jVarB = (i12 & 512) != 0 ? WideButtonDefaults.b() : jVar;
        g gVarA = (i12 & 1024) != 0 ? WideButtonDefaults.a() : gVar;
        k kVarC = (i12 & 2048) != 0 ? WideButtonDefaults.c() : kVar;
        if ((i12 & 4096) != 0) {
            mutableInteractionSource3 = mutableInteractionSource2;
            z5 = z10;
            pVar5 = pVar8;
            pVar6 = pVar7;
            i13 = 751560171;
            r4VarM6022contentColorro_MJ88 = WideButtonDefaults.f5780a.m6022contentColorro_MJ88(0L, 0L, 0L, 0L, composer, 24576, 15);
        } else {
            i13 = 751560171;
            z5 = z10;
            pVar5 = pVar8;
            pVar6 = pVar7;
            mutableInteractionSource3 = mutableInteractionSource2;
            r4VarM6022contentColorro_MJ88 = r4Var;
        }
        float f11 = (i12 & 8192) != 0 ? j2.e.f19087a : f10;
        if ((i12 & 16384) != 0) {
            composer2 = composer;
            eVarBorder = WideButtonDefaults.f5780a.border(null, null, null, null, null, composer, 196608, 31);
        } else {
            composer2 = composer;
            eVarBorder = eVar;
        }
        PaddingValues paddingValues2 = (32768 & i12) != 0 ? WideButtonDefaults.f5781b : paddingValues;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(i13, i10, i11, "androidx.tv.material3.WideButton (WideButton.kt:166)");
        }
        int i14 = i11 << 9;
        int i15 = i10 >> 3;
        j jVar2 = jVarB;
        boolean z11 = z5;
        m6025WideButtonImplroJ8YiU(aVar, z11, jVar2, gVarA, kVarC, r4VarM6022contentColorro_MJ88, f11, eVarBorder, paddingValues2, mutableInteractionSource3, pVarComposableLambda, modifier2, aVar3, pVar5 == null ? c.f5863b : c.f5864c, ComposableLambdaKt.composableLambda(composer2, -689850138, true, new WideButtonKt$WideButton$5(pVar6, pVar5, pVar)), composer2, (i10 & 14) | ((i10 >> 9) & 112) | ((i10 >> 21) & 896) | (i14 & 7168) | (57344 & i14) | (458752 & i14) | (3670016 & i14) | (29360128 & i14) | (i14 & 234881024) | ((i10 << 6) & 1879048192), ((i10 >> 24) & 14) | 24576 | (i15 & 112) | (i15 & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: renamed from: WideButton-WsuR4So, reason: not valid java name */
    public static final void m6024WideButtonWsuR4So(r7.a<x6.t0> aVar, Modifier modifier, r7.a<x6.t0> aVar2, boolean z, MutableInteractionSource mutableInteractionSource, r7.p<? super Composer, ? super Integer, x6.t0> pVar, j jVar, g gVar, k kVar, r4 r4Var, float f10, e eVar, PaddingValues paddingValues, r7.q<? super RowScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11, int i12) {
        MutableInteractionSource mutableInteractionSource2;
        int i13;
        boolean z5;
        MutableInteractionSource mutableInteractionSource3;
        r4 r4VarM6022contentColorro_MJ88;
        composer.startReplaceableGroup(605975108);
        Modifier modifier2 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        r7.a<x6.t0> aVar3 = (i12 & 4) != 0 ? null : aVar2;
        boolean z10 = (i12 & 8) != 0 ? true : z;
        if ((i12 & 16) != 0) {
            composer.startReplaceableGroup(7158549);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceableGroup();
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        r7.p<? super Composer, ? super Integer, x6.t0> pVarComposableLambda = (i12 & 32) != 0 ? ComposableLambdaKt.composableLambda(composer, 1323022728, true, new WideButtonKt$WideButton$2(z10, mutableInteractionSource2)) : pVar;
        j jVarB = (i12 & 64) != 0 ? WideButtonDefaults.b() : jVar;
        g gVarA = (i12 & 128) != 0 ? WideButtonDefaults.a() : gVar;
        k kVarC = (i12 & 256) != 0 ? WideButtonDefaults.c() : kVar;
        if ((i12 & 512) != 0) {
            z5 = z10;
            mutableInteractionSource3 = mutableInteractionSource2;
            i13 = 605975108;
            r4VarM6022contentColorro_MJ88 = WideButtonDefaults.f5780a.m6022contentColorro_MJ88(0L, 0L, 0L, 0L, composer, 24576, 15);
        } else {
            i13 = 605975108;
            z5 = z10;
            mutableInteractionSource3 = mutableInteractionSource2;
            r4VarM6022contentColorro_MJ88 = r4Var;
        }
        float f11 = (i12 & 1024) != 0 ? j2.e.f19087a : f10;
        e eVarBorder = (i12 & 2048) != 0 ? WideButtonDefaults.f5780a.border(null, null, null, null, null, composer, 196608, 31) : eVar;
        PaddingValues paddingValues2 = (i12 & 4096) != 0 ? WideButtonDefaults.f5781b : paddingValues;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(i13, i10, i11, "androidx.tv.material3.WideButton (WideButton.kt:95)");
        }
        int i14 = i10 >> 12;
        int i15 = (i10 & 14) | ((i10 >> 6) & 112) | (i14 & 896) | (i14 & 7168) | (i14 & 57344) | (i14 & 458752);
        int i16 = i11 << 18;
        m6025WideButtonImplroJ8YiU(aVar, z5, jVarB, gVarA, kVarC, r4VarM6022contentColorro_MJ88, f11, eVarBorder, paddingValues2, mutableInteractionSource3, pVarComposableLambda, modifier2, aVar3, 0.0f, qVar, composer, i15 | (3670016 & i16) | (29360128 & i16) | (i16 & 234881024) | ((i10 << 15) & 1879048192), ((i10 >> 15) & 14) | (i10 & 112) | (i10 & 896) | (57344 & (i11 << 3)), 8192);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:198:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0106  */
    /* JADX INFO: renamed from: WideButtonImpl-roJ8YiU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6025WideButtonImplroJ8YiU(r7.a<x6.t0> r47, boolean r48, androidx.tv.material3.j r49, androidx.tv.material3.g r50, androidx.tv.material3.k r51, androidx.tv.material3.r4 r52, float r53, androidx.tv.material3.e r54, androidx.compose.foundation.layout.PaddingValues r55, androidx.compose.foundation.interaction.MutableInteractionSource r56, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r57, androidx.compose.ui.Modifier r58, r7.a<x6.t0> r59, float r60, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r61, androidx.compose.runtime.Composer r62, int r63, int r64, int r65) {
        /*
            Method dump skipped, instruction units count: 837
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.WideButtonKt.m6025WideButtonImplroJ8YiU(r7.a, boolean, androidx.tv.material3.j, androidx.tv.material3.g, androidx.tv.material3.k, androidx.tv.material3.r4, float, androidx.tv.material3.e, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, r7.p, androidx.compose.ui.Modifier, r7.a, float, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
