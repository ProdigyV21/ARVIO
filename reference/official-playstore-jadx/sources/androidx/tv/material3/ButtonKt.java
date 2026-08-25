package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a¶\u0001\u0010\u001f\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a¶\u0001\u0010!\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010\u001e\u001a¢\u0001\u0010#\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u001c\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0003ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u001e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "onLongClick", "", "enabled", "Landroidx/tv/material3/j;", "scale", "Landroidx/tv/material3/g;", "glow", "Landroidx/tv/material3/k;", "shape", "Landroidx/tv/material3/f;", "colors", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/tv/material3/e;", "border", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "Button-TCVpFMg", "(Lr7/a;Landroidx/compose/ui/Modifier;Lr7/a;ZLandroidx/tv/material3/j;Landroidx/tv/material3/g;Landroidx/tv/material3/k;Landroidx/tv/material3/f;FLandroidx/tv/material3/e;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "Button", "OutlinedButton-TCVpFMg", "OutlinedButton", "ButtonImpl-TCVpFMg", "ButtonImpl", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ButtonKt {
    /* JADX INFO: renamed from: Button-TCVpFMg, reason: not valid java name */
    public static final void m5974ButtonTCVpFMg(r7.a<x6.t0> aVar, Modifier modifier, r7.a<x6.t0> aVar2, boolean z, j jVar, g gVar, k kVar, f fVar, float f10, e eVar, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, r7.q<? super RowScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11, int i12) {
        j jVar2;
        g gVar2;
        MutableInteractionSource mutableInteractionSource2;
        f fVarM5973colorsoq7We08;
        composer.startReplaceableGroup(2019514113);
        Modifier modifier2 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        r7.a<x6.t0> aVar3 = (i12 & 4) != 0 ? null : aVar2;
        boolean z5 = (i12 & 8) != 0 ? true : z;
        if ((i12 & 16) != 0) {
            ButtonDefaults buttonDefaults = ButtonDefaults.f5530a;
            jVar2 = new j(1.1f);
        } else {
            jVar2 = jVar;
        }
        if ((i12 & 32) != 0) {
            ButtonDefaults buttonDefaults2 = ButtonDefaults.f5530a;
            s1 s1Var = s1.f6376c;
            gVar2 = new g(s1Var, s1Var, s1Var);
        } else {
            gVar2 = gVar;
        }
        k kVarA = (i12 & 64) != 0 ? ButtonDefaults.a(null, 31) : kVar;
        if ((i12 & 128) != 0) {
            mutableInteractionSource2 = null;
            fVarM5973colorsoq7We08 = ButtonDefaults.f5530a.m5973colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 100663296, 255);
        } else {
            mutableInteractionSource2 = null;
            fVarM5973colorsoq7We08 = fVar;
        }
        float f11 = (i12 & 256) != 0 ? j2.e.f19087a : f10;
        e eVarBorder = (i12 & 512) != 0 ? ButtonDefaults.f5530a.border(null, null, null, null, null, composer, 196608, 31) : eVar;
        PaddingValues paddingValues2 = (i12 & 1024) != 0 ? ButtonDefaults.f5532c : paddingValues;
        MutableInteractionSource mutableInteractionSource3 = (i12 & 2048) != 0 ? mutableInteractionSource2 : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2019514113, i10, i11, "androidx.tv.material3.Button (Button.kt:89)");
        }
        m5975ButtonImplTCVpFMg(aVar, modifier2, aVar3, z5, jVar2, gVar2, kVarA, fVarM5973colorsoq7We08, f11, eVarBorder, paddingValues2, mutableInteractionSource3, qVar, composer, i10 & 2147483646, i11 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0105  */
    /* JADX INFO: renamed from: ButtonImpl-TCVpFMg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m5975ButtonImplTCVpFMg(r7.a<x6.t0> r33, androidx.compose.ui.Modifier r34, r7.a<x6.t0> r35, boolean r36, androidx.tv.material3.j r37, androidx.tv.material3.g r38, androidx.tv.material3.k r39, androidx.tv.material3.f r40, float r41, androidx.tv.material3.e r42, androidx.compose.foundation.layout.PaddingValues r43, androidx.compose.foundation.interaction.MutableInteractionSource r44, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, androidx.compose.runtime.Composer r46, int r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.ButtonKt.m5975ButtonImplTCVpFMg(r7.a, androidx.compose.ui.Modifier, r7.a, boolean, androidx.tv.material3.j, androidx.tv.material3.g, androidx.tv.material3.k, androidx.tv.material3.f, float, androidx.tv.material3.e, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: OutlinedButton-TCVpFMg, reason: not valid java name */
    public static final void m5976OutlinedButtonTCVpFMg(r7.a<x6.t0> aVar, Modifier modifier, r7.a<x6.t0> aVar2, boolean z, j jVar, g gVar, k kVar, f fVar, float f10, e eVar, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, r7.q<? super RowScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11, int i12) {
        j jVar2;
        g gVar2;
        k kVar2;
        composer.startReplaceableGroup(-1818074365);
        Modifier modifier2 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        r7.a<x6.t0> aVar3 = (i12 & 4) != 0 ? null : aVar2;
        boolean z5 = (i12 & 8) != 0 ? true : z;
        if ((i12 & 16) != 0) {
            OutlinedButtonDefaults outlinedButtonDefaults = OutlinedButtonDefaults.f5694a;
            jVar2 = new j(1.1f);
        } else {
            jVar2 = jVar;
        }
        if ((i12 & 32) != 0) {
            OutlinedButtonDefaults outlinedButtonDefaults2 = OutlinedButtonDefaults.f5694a;
            s1 s1Var = s1.f6376c;
            gVar2 = new g(s1Var, s1Var, s1Var);
        } else {
            gVar2 = gVar;
        }
        if ((i12 & 64) != 0) {
            RoundedCornerShape roundedCornerShape = OutlinedButtonDefaults.f5695b;
            kVar2 = new k(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            kVar2 = kVar;
        }
        f fVarM6001colorsoq7We08 = (i12 & 128) != 0 ? OutlinedButtonDefaults.f5694a.m6001colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 100663296, 255) : fVar;
        float f11 = (i12 & 256) != 0 ? j2.e.f19087a : f10;
        e eVarBorder = (i12 & 512) != 0 ? OutlinedButtonDefaults.f5694a.border(null, null, null, null, null, composer, 196608, 31) : eVar;
        PaddingValues paddingValues2 = (i12 & 1024) != 0 ? OutlinedButtonDefaults.f5696c : paddingValues;
        MutableInteractionSource mutableInteractionSource2 = (i12 & 2048) != 0 ? null : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1818074365, i10, i11, "androidx.tv.material3.OutlinedButton (Button.kt:161)");
        }
        m5975ButtonImplTCVpFMg(aVar, modifier2, aVar3, z5, jVar2, gVar2, kVar2, fVarM6001colorsoq7We08, f11, eVarBorder, paddingValues2, mutableInteractionSource2, qVar, composer, i10 & 2147483646, i11 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }
}
