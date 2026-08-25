package com.arflix.tv.ui.skin;

import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.graphics.h;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.l;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u001aç\u0001\u0010\u001f\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00180\u001b¢\u0006\u0004\b\u001d\u0010\u001e\u001a¸\u0002\u0010,\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00180\u001b21\u0010)\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00180\"¢\u0006\u0002\b'¢\u0006\u0002\b(H\u0007¢\u0006\u0004\b*\u0010+\u001a\u0019\u00100\u001a\u00020\u00062\b\b\u0002\u0010-\u001a\u00020\u000bH\u0007¢\u0006\u0004\b.\u0010/¨\u00065²\u0006\f\u00101\u001a\u00020\u00018\nX\u008a\u0084\u0002²\u0006\u000e\u0010&\u001a\u00020\u00018\n@\nX\u008a\u008e\u0002²\u0006\f\u00102\u001a\u00020\b8\nX\u008a\u0084\u0002²\u0006\f\u00103\u001a\u00020\b8\nX\u008a\u0084\u0002²\u0006\f\u00104\u001a\u00020\b8\nX\u008a\u0084\u0002²\u0006\u000e\u0010&\u001a\u00020\u00018\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "", "enabled", "enableSystemFocus", "useSystemFocusForVisuals", "isFocusedOverride", "Landroidx/compose/ui/graphics/Shape;", "shape", "", "focusedScale", "pressedScale", "Landroidx/compose/ui/unit/Dp;", "outlineWidth", "glowWidth", "glowAlpha", "Landroidx/compose/ui/graphics/Color;", "outlineColor", "focusedTransformOriginX", "useGradientBorder", "gradientStartColor", "gradientEndColor", "showRestBorder", "animateFocus", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "onLongClick", "Lkotlin/Function1;", "onFocusChanged", "arvioFocusable-gqPShRU", "(Landroidx/compose/ui/Modifier;ZZZZLandroidx/compose/ui/graphics/Shape;FFFFFJFZJJZZLr7/a;Lr7/a;Lr7/l;)Landroidx/compose/ui/Modifier;", "arvioFocusable", "modifier", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Lkotlin/Function2;", "Landroidx/compose/foundation/layout/BoxScope;", "Lx6/y;", ContentDisposition.Parameters.Name, "isFocused", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "ArvioFocusableSurface-aewEPWQ", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JFFFFFJFZJJZZZZZZLr7/a;Lr7/a;Lr7/l;Lr7/r;Landroidx/compose/runtime/Composer;IIII)V", "ArvioFocusableSurface", "cornerRadius", "rememberArvioCardShape-kHDZbjc", "(FLandroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/Shape;", "rememberArvioCardShape", "isPressed", "animatedScale", "animatedAlpha", "restBorderAlpha", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ArvioFocusKt {
    /* JADX WARN: Removed duplicated region for block: B:160:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x057e  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0620  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:408:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: ArvioFocusableSurface-aewEPWQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6499ArvioFocusableSurfaceaewEPWQ(androidx.compose.ui.Modifier r48, final androidx.compose.ui.graphics.Shape r49, long r50, float r52, float r53, float r54, float r55, float r56, long r57, float r59, boolean r60, long r61, long r63, boolean r65, boolean r66, boolean r67, boolean r68, boolean r69, boolean r70, r7.a<x6.t0> r71, r7.a<x6.t0> r72, r7.l<? super java.lang.Boolean, x6.t0> r73, final r7.r<? super androidx.compose.foundation.layout.BoxScope, ? super java.lang.Boolean, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r74, androidx.compose.runtime.Composer r75, final int r76, final int r77, final int r78, final int r79) {
        /*
            Method dump skipped, instruction units count: 1709
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.skin.ArvioFocusKt.m6499ArvioFocusableSurfaceaewEPWQ(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, float, float, float, float, float, long, float, boolean, long, long, boolean, boolean, boolean, boolean, boolean, boolean, r7.a, r7.a, r7.l, r7.r, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioFocusableSurface_aewEPWQ$lambda$0$0(boolean z) {
        return t0.f22605a;
    }

    private static final boolean ArvioFocusableSurface_aewEPWQ$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ArvioFocusableSurface_aewEPWQ$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioFocusableSurface_aewEPWQ$lambda$4$0(l lVar, MutableState mutableState, boolean z) {
        ArvioFocusableSurface_aewEPWQ$lambda$3(mutableState, z);
        lVar.invoke(Boolean.valueOf(z));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArvioFocusableSurface_aewEPWQ$lambda$6(Modifier modifier, Shape shape, long j10, float f10, float f11, float f12, float f13, float f14, long j11, float f15, boolean z, long j12, long j13, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, r7.a aVar, r7.a aVar2, l lVar, r rVar, int i10, int i11, int i12, int i13, Composer composer, int i14) {
        m6499ArvioFocusableSurfaceaewEPWQ(modifier, shape, j10, f10, f11, f12, f13, f14, j11, f15, z, j12, j13, z5, z10, z11, z12, z13, z14, aVar, aVar2, lVar, rVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: arvioFocusable-gqPShRU, reason: not valid java name */
    public static final Modifier m6500arvioFocusablegqPShRU(Modifier modifier, final boolean z, final boolean z5, final boolean z10, final boolean z11, final Shape shape, final float f10, final float f11, final float f12, final float f13, final float f14, final long j10, final float f15, boolean z12, long j11, long j12, final boolean z13, final boolean z14, final r7.a<t0> aVar, final r7.a<t0> aVar2, final l<? super Boolean, t0> lVar) {
        return ComposedModifierKt.composed$default(modifier, null, new q() { // from class: com.arflix.tv.ui.skin.e
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int iIntValue = ((Integer) obj3).intValue();
                return ArvioFocusKt.arvioFocusable_gqPShRU$lambda$1(j10, z11, z10, f11, f10, z14, z13, f15, aVar, aVar2, z, z5, lVar, shape, f12, f13, f14, (Modifier) obj, (Composer) obj2, iIntValue);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioFocusable_gqPShRU$lambda$0(boolean z) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.Modifier arvioFocusable_gqPShRU$lambda$1(long r28, boolean r30, boolean r31, float r32, float r33, boolean r34, boolean r35, float r36, r7.a r37, r7.a r38, boolean r39, boolean r40, r7.l r41, final androidx.compose.ui.graphics.Shape r42, final float r43, final float r44, final float r45, androidx.compose.ui.Modifier r46, androidx.compose.runtime.Composer r47, int r48) {
        /*
            Method dump skipped, instruction units count: 758
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.skin.ArvioFocusKt.arvioFocusable_gqPShRU$lambda$1(long, boolean, boolean, float, float, boolean, boolean, float, r7.a, r7.a, boolean, boolean, r7.l, androidx.compose.ui.graphics.Shape, float, float, float, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
    }

    private static final boolean arvioFocusable_gqPShRU$lambda$1$1(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawResult arvioFocusable_gqPShRU$lambda$1$10$0(Shape shape, float f10, float f11, long j10, float f12, float f13, State state, CacheDrawScope cacheDrawScope) {
        final Outline outlineMo230createOutlinePq9zytI = shape.mo230createOutlinePq9zytI(cacheDrawScope.m3140getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
        final float fG = androidx.compose.ui.unit.a.g(cacheDrawScope, f10 > 0.0f ? f11 : Dp.m5678constructorimpl((float) 0.5d));
        final long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(j10, f10 > 0.0f ? f10 : arvioFocusable_gqPShRU$lambda$1$7(state) * 0.5f, 0.0f, 0.0f, 0.0f, 14, null);
        final float fG2 = androidx.compose.ui.unit.a.g(cacheDrawScope, f12);
        final boolean z = f10 > 0.3f && fG2 > 0.01f && f13 > 0.01f;
        final long jM3471copywmQWz5c$default2 = Color.m3471copywmQWz5c$default(j10, f10 * f13, 0.0f, 0.0f, 0.0f, 14, null);
        return cacheDrawScope.onDrawWithContent(new l() { // from class: com.arflix.tv.ui.skin.b
            @Override // r7.l
            public final Object invoke(Object obj) {
                return ArvioFocusKt.arvioFocusable_gqPShRU$lambda$1$10$0$0(outlineMo230createOutlinePq9zytI, z, jM3471copywmQWz5c$default2, fG, fG2, jM3471copywmQWz5c$default, (ContentDrawScope) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioFocusable_gqPShRU$lambda$1$10$0$0(Outline outline, boolean z, long j10, float f10, float f11, long j11, ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        if (outline instanceof Outline.Rounded) {
            long jM3286getTopLeftCornerRadiuskKHJgLs = ((Outline.Rounded) outline).getRoundRect().m3286getTopLeftCornerRadiuskKHJgLs();
            if (z) {
                androidx.compose.ui.graphics.drawscope.c.M(contentDrawScope, j10, 0L, 0L, jM3286getTopLeftCornerRadiuskKHJgLs, new Stroke(f10 + f11, 0.0f, 0, 0, null, 30, null), 0.0f, null, 0, 230, null);
            }
            androidx.compose.ui.graphics.drawscope.c.M(contentDrawScope, j11, 0L, 0L, jM3286getTopLeftCornerRadiuskKHJgLs, new Stroke(f10, 0.0f, 0, 0, null, 30, null), 0.0f, null, 0, 230, null);
        } else if (outline instanceof Outline.Rectangle) {
            if (z) {
                androidx.compose.ui.graphics.drawscope.c.K(contentDrawScope, j10, 0L, 0L, 0.0f, new Stroke(f10 + f11, 0.0f, 0, 0, null, 30, null), null, 0, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, null);
            }
            androidx.compose.ui.graphics.drawscope.c.K(contentDrawScope, j11, 0L, 0L, 0.0f, new Stroke(f10, 0.0f, 0, 0, null, 30, null), null, 0, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, null);
        } else {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            if (z) {
                Path Path = AndroidPath_androidKt.Path();
                h.g(Path, ((Outline.Generic) outline).getPath(), 0L, 2, null);
                androidx.compose.ui.graphics.drawscope.c.G(contentDrawScope, Path, j10, 0.0f, new Stroke(f10 + f11, 0.0f, 0, 0, null, 30, null), null, 0, 52, null);
            }
            androidx.compose.ui.graphics.drawscope.c.G(contentDrawScope, ((Outline.Generic) outline).getPath(), j11, 0.0f, new Stroke(f10, 0.0f, 0, 0, null, 30, null), null, 0, 52, null);
        }
        return t0.f22605a;
    }

    private static final boolean arvioFocusable_gqPShRU$lambda$1$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void arvioFocusable_gqPShRU$lambda$1$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float arvioFocusable_gqPShRU$lambda$1$5(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float arvioFocusable_gqPShRU$lambda$1$6(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float arvioFocusable_gqPShRU$lambda$1$7(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioFocusable_gqPShRU$lambda$1$8$0(l lVar, MutableState mutableState, FocusState focusState) {
        boolean zIsFocused = focusState.isFocused();
        if (zIsFocused != arvioFocusable_gqPShRU$lambda$1$3(mutableState)) {
            arvioFocusable_gqPShRU$lambda$1$4(mutableState, zIsFocused);
            lVar.invoke(Boolean.valueOf(zIsFocused));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioFocusable_gqPShRU$lambda$1$9$0(float f10, long j10, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(f10);
        graphicsLayerScope.setScaleY(f10);
        graphicsLayerScope.mo3646setTransformOrigin__ExYCQ(j10);
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: rememberArvioCardShape-kHDZbjc, reason: not valid java name */
    public static final Shape m6502rememberArvioCardShapekHDZbjc(float f10, Composer composer, int i10, int i11) {
        boolean z = true;
        if ((i11 & 1) != 0) {
            f10 = ArvioSkin.INSTANCE.getRadius(composer, 6).m6517getMdD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(793993159, i10, -1, "com.arflix.tv.ui.skin.rememberArvioCardShape (ArvioFocus.kt:282)");
        }
        if ((((i10 & 14) ^ 6) <= 4 || !composer.changed(f10)) && (i10 & 6) != 4) {
            z = false;
        }
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(f10);
            composer.updateRememberedValue(objRememberedValue);
        }
        RoundedCornerShape roundedCornerShape = (RoundedCornerShape) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return roundedCornerShape;
    }
}
