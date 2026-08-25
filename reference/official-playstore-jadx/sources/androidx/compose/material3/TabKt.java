package androidx.compose.material3;

import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.tokens.PrimaryNavigationTabTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0088\u0001\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00002\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0002\b\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0080\u0001\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\b2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\b2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001ax\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00030\u0016¢\u0006\u0002\b\b¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a=\u0010 \u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\bH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a9\u0010!\u001a\u00020\u00032\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0002\b\b2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\b!\u0010\"\u001a#\u0010(\u001a\u00020\u0003*\u00020#2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b(\u0010)\u001aK\u00101\u001a\u00020\u0003*\u00020#2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020&H\u0002¢\u0006\u0004\b1\u00102\"\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105\"\u0014\u00106\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00105\"\u0014\u00107\u001a\u00020&8\u0002X\u0082T¢\u0006\u0006\n\u0004\b7\u00108\"\u0014\u00109\u001a\u00020&8\u0002X\u0082T¢\u0006\u0006\n\u0004\b9\u00108\"\u0014\u0010:\u001a\u00020&8\u0002X\u0082T¢\u0006\u0006\n\u0004\b:\u00108\"\u001a\u0010;\u001a\u0002038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b;\u00105\u001a\u0004\b<\u0010=\"\u0014\u0010>\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u00105\"\u0014\u0010?\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u00105\"\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010B\"\u0014\u0010C\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006E²\u0006\f\u0010D\u001a\u00020\u000b8\nX\u008a\u0084\u0002"}, d2 = {"", "selected", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "enabled", "Landroidx/compose/runtime/Composable;", "text", "icon", "Landroidx/compose/ui/graphics/Color;", "selectedContentColor", "unselectedContentColor", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Tab-wqdebIU", "(ZLr7/a;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;JJLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "Tab", "LeadingIconTab-wqdebIU", "(ZLr7/a;Lr7/p;Lr7/p;Landroidx/compose/ui/Modifier;ZJJLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "LeadingIconTab", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/n;", "content", "Tab-bogVsAg", "(ZLr7/a;Landroidx/compose/ui/Modifier;ZJJLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "activeColor", "inactiveColor", "TabTransition-Klgx-Pg", "(JJZLr7/p;Landroidx/compose/runtime/Composer;I)V", "TabTransition", "TabBaselineLayout", "(Lr7/p;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Landroidx/compose/ui/layout/Placeable;", "textOrIconPlaceable", "", "tabHeight", "placeTextOrIcon", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Landroidx/compose/ui/layout/Placeable;I)V", "Landroidx/compose/ui/unit/Density;", "density", "textPlaceable", "iconPlaceable", "tabWidth", "firstBaseline", "lastBaseline", "placeTextAndIcon", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;IIII)V", "Landroidx/compose/ui/unit/Dp;", "SmallTabHeight", "F", "LargeTabHeight", "TabFadeInAnimationDuration", "I", "TabFadeInAnimationDelay", "TabFadeOutAnimationDuration", "HorizontalTextPadding", "getHorizontalTextPadding", "()F", "SingleLineTextBaselineWithIcon", "DoubleLineTextBaselineWithIcon", "Landroidx/compose/ui/unit/TextUnit;", "IconDistanceFromBaseline", "J", "TextDistanceFromLeadingIcon", TtmlNode.ATTR_TTS_COLOR, "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TabKt {
    private static final int TabFadeInAnimationDelay = 100;
    private static final int TabFadeInAnimationDuration = 150;
    private static final int TabFadeOutAnimationDuration = 100;
    private static final float SmallTabHeight = PrimaryNavigationTabTokens.INSTANCE.m2799getContainerHeightD9Ej5fM();
    private static final float LargeTabHeight = Dp.m5678constructorimpl(72);
    private static final float HorizontalTextPadding = Dp.m5678constructorimpl(16);
    private static final float SingleLineTextBaselineWithIcon = Dp.m5678constructorimpl(14);
    private static final float DoubleLineTextBaselineWithIcon = Dp.m5678constructorimpl(6);
    private static final long IconDistanceFromBaseline = TextUnitKt.getSp(20);
    private static final float TextDistanceFromLeadingIcon = Dp.m5678constructorimpl(8);

    /* JADX INFO: renamed from: androidx.compose.material3.TabKt$TabBaselineLayout$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ p<Composer, Integer, t0> $text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, int i10) {
            super(2);
            this.$text = pVar;
            this.$icon = pVar2;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TabKt.TabBaselineLayout(this.$text, this.$icon, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f7  */
    /* JADX INFO: renamed from: LeadingIconTab-wqdebIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2041LeadingIconTabwqdebIU(boolean r20, r7.a<x6.t0> r21, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, androidx.compose.ui.Modifier r24, boolean r25, long r26, long r28, androidx.compose.foundation.interaction.MutableInteractionSource r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TabKt.m2041LeadingIconTabwqdebIU(boolean, r7.a, r7.p, r7.p, androidx.compose.ui.Modifier, boolean, long, long, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010b  */
    /* JADX INFO: renamed from: Tab-bogVsAg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2042TabbogVsAg(boolean r23, r7.a<x6.t0> r24, androidx.compose.ui.Modifier r25, boolean r26, long r27, long r29, androidx.compose.foundation.interaction.MutableInteractionSource r31, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 517
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TabKt.m2042TabbogVsAg(boolean, r7.a, androidx.compose.ui.Modifier, boolean, long, long, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:152:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fd  */
    /* JADX INFO: renamed from: Tab-wqdebIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2043TabwqdebIU(boolean r29, r7.a<x6.t0> r30, androidx.compose.ui.Modifier r31, boolean r32, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r34, long r35, long r37, androidx.compose.foundation.interaction.MutableInteractionSource r39, androidx.compose.runtime.Composer r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TabKt.m2043TabwqdebIU(boolean, r7.a, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, long, long, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TabBaselineLayout(final p<? super Composer, ? super Integer, t0> pVar, final p<? super Composer, ? super Integer, t0> pVar2, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(514131524);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(514131524, i11, -1, "androidx.compose.material3.TabBaselineLayout (Tab.kt:313)");
            }
            composerStartRestartGroup.startReplaceableGroup(150513508);
            int i12 = i11 & 14;
            boolean z = (i12 == 4) | ((i11 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.TabKt$TabBaselineLayout$2$1

                    /* JADX INFO: renamed from: androidx.compose.material3.TabKt$TabBaselineLayout$2$1$1, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
                        final /* synthetic */ Integer $firstBaseline;
                        final /* synthetic */ Placeable $iconPlaceable;
                        final /* synthetic */ Integer $lastBaseline;
                        final /* synthetic */ int $tabHeight;
                        final /* synthetic */ int $tabWidth;
                        final /* synthetic */ Placeable $textPlaceable;
                        final /* synthetic */ MeasureScope $this_Layout;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass1(Placeable placeable, Placeable placeable2, MeasureScope measureScope, int i10, int i11, Integer num, Integer num2) {
                            super(1);
                            this.$textPlaceable = placeable;
                            this.$iconPlaceable = placeable2;
                            this.$this_Layout = measureScope;
                            this.$tabWidth = i10;
                            this.$tabHeight = i11;
                            this.$firstBaseline = num;
                            this.$lastBaseline = num2;
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                            Placeable placeable;
                            Placeable placeable2 = this.$textPlaceable;
                            if (placeable2 != null && (placeable = this.$iconPlaceable) != null) {
                                TabKt.placeTextAndIcon(placementScope, this.$this_Layout, placeable2, placeable, this.$tabWidth, this.$tabHeight, this.$firstBaseline.intValue(), this.$lastBaseline.intValue());
                                return;
                            }
                            if (placeable2 != null) {
                                TabKt.placeTextOrIcon(placementScope, placeable2, this.$tabHeight);
                                return;
                            }
                            Placeable placeable3 = this.$iconPlaceable;
                            if (placeable3 != null) {
                                TabKt.placeTextOrIcon(placementScope, placeable3, this.$tabHeight);
                            }
                        }
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i13);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i13);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        Placeable placeableMo4631measureBRTryo0;
                        Placeable placeableMo4631measureBRTryo02;
                        if (pVar != null) {
                            int size = list.size();
                            for (int i13 = 0; i13 < size; i13++) {
                                Measurable measurable = list.get(i13);
                                if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), "text")) {
                                    placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 11, null));
                                }
                            }
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                        placeableMo4631measureBRTryo0 = null;
                        if (pVar2 != null) {
                            int size2 = list.size();
                            for (int i14 = 0; i14 < size2; i14++) {
                                Measurable measurable2 = list.get(i14);
                                if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), "icon")) {
                                    placeableMo4631measureBRTryo02 = measurable2.mo4631measureBRTryo0(j10);
                                }
                            }
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                        placeableMo4631measureBRTryo02 = null;
                        int iMax = Math.max(placeableMo4631measureBRTryo0 != null ? placeableMo4631measureBRTryo0.getWidth() : 0, placeableMo4631measureBRTryo02 != null ? placeableMo4631measureBRTryo02.getWidth() : 0);
                        int iMax2 = Math.max(measureScope.mo279roundToPx0680j_4((placeableMo4631measureBRTryo0 == null || placeableMo4631measureBRTryo02 == null) ? TabKt.SmallTabHeight : TabKt.LargeTabHeight), measureScope.mo278roundToPxR2X_6o(TabKt.IconDistanceFromBaseline) + (placeableMo4631measureBRTryo02 != null ? placeableMo4631measureBRTryo02.getHeight() : 0) + (placeableMo4631measureBRTryo0 != null ? placeableMo4631measureBRTryo0.getHeight() : 0));
                        return MeasureScope.CC.q(measureScope, iMax, iMax2, null, new AnonymousClass1(placeableMo4631measureBRTryo0, placeableMo4631measureBRTryo02, measureScope, iMax, iMax2, placeableMo4631measureBRTryo0 != null ? Integer.valueOf(placeableMo4631measureBRTryo0.get(AlignmentLineKt.getFirstBaseline())) : null, placeableMo4631measureBRTryo0 != null ? Integer.valueOf(placeableMo4631measureBRTryo0.get(AlignmentLineKt.getLastBaseline())) : null), 4, null);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i13);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i13) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i13);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            composerStartRestartGroup.startReplaceableGroup(720851373);
            if (pVar != null) {
                Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(LayoutIdKt.layoutId(companion, "text"), HorizontalTextPadding, 0.0f, 2, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion2.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU2 = a0.c.u(companion2, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                pVar.invoke(composerStartRestartGroup, Integer.valueOf(i12));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(150513400);
            if (pVar2 != null) {
                Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, "icon");
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD2 = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion2.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierLayoutId);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU3 = a0.c.u(companion2, composerM2991constructorimpl3, measurePolicyD2, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                pVar2.invoke(composerStartRestartGroup, Integer.valueOf((i11 >> 3) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass3(pVar, pVar2, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TabTransition-Klgx-Pg, reason: not valid java name */
    public static final void m2044TabTransitionKlgxPg(long j10, long j11, boolean z, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        boolean z5;
        TwoWayConverter twoWayConverter;
        Composer composerStartRestartGroup = composer.startRestartGroup(735731848);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(j10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j11) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            z5 = z;
            i11 |= composerStartRestartGroup.changed(z5) ? 256 : 128;
        } else {
            z5 = z;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(735731848, i11, -1, "androidx.compose.material3.TabTransition (Tab.kt:278)");
            }
            int i12 = i11 >> 6;
            Transition transitionUpdateTransition = TransitionKt.updateTransition(Boolean.valueOf(z5), (String) null, composerStartRestartGroup, i12 & 14, 2);
            TabKt$TabTransition$color$2 tabKt$TabTransition$color$2 = TabKt$TabTransition$color$2.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-1939694975);
            boolean zBooleanValue = ((Boolean) transitionUpdateTransition.getTargetState()).booleanValue();
            composerStartRestartGroup.startReplaceableGroup(-1997025499);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1997025499, 0, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:296)");
            }
            long j12 = zBooleanValue ? j10 : j11;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceableGroup();
            ColorSpace colorSpaceM3476getColorSpaceimpl = Color.m3476getColorSpaceimpl(j12);
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            boolean zChanged = composerStartRestartGroup.changed(colorSpaceM3476getColorSpaceimpl);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpaceM3476getColorSpaceimpl);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            TwoWayConverter twoWayConverter2 = (TwoWayConverter) objRememberedValue;
            composerStartRestartGroup.startReplaceableGroup(-142660079);
            boolean zBooleanValue2 = ((Boolean) transitionUpdateTransition.getCurrentState()).booleanValue();
            composerStartRestartGroup.startReplaceableGroup(-1997025499);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1997025499, 0, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:296)");
            }
            long j13 = zBooleanValue2 ? j10 : j11;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceableGroup();
            Color colorM3462boximpl = Color.m3462boximpl(j13);
            boolean zBooleanValue3 = ((Boolean) transitionUpdateTransition.getTargetState()).booleanValue();
            composerStartRestartGroup.startReplaceableGroup(-1997025499);
            if (ComposerKt.isTraceInProgress()) {
                twoWayConverter = twoWayConverter2;
                ComposerKt.traceEventStart(-1997025499, 0, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:296)");
            } else {
                twoWayConverter = twoWayConverter2;
            }
            long j14 = zBooleanValue3 ? j10 : j11;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceableGroup();
            State stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transitionUpdateTransition, colorM3462boximpl, Color.m3462boximpl(j14), (FiniteAnimationSpec) tabKt$TabTransition$color$2.invoke((Object) transitionUpdateTransition.getSegment(), (Object) composerStartRestartGroup, (Object) 0), twoWayConverter, "ColorAnimation", composerStartRestartGroup, 32768);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(TabTransition_Klgx_Pg$lambda$5(stateCreateTransitionAnimation))), pVar, composerStartRestartGroup, (i12 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new TabKt$TabTransition$1(j10, j11, z5, pVar, i10));
        }
    }

    private static final long TabTransition_Klgx_Pg$lambda$5(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    public static final float getHorizontalTextPadding() {
        return HorizontalTextPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextAndIcon(Placeable.PlacementScope placementScope, Density density, Placeable placeable, Placeable placeable2, int i10, int i11, int i12, int i13) {
        int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(PrimaryNavigationTabTokens.INSTANCE.m2797getActiveIndicatorHeightD9Ej5fM()) + density.mo279roundToPx0680j_4(i12 == i13 ? SingleLineTextBaselineWithIcon : DoubleLineTextBaselineWithIcon);
        int iMo278roundToPxR2X_6o = (density.mo278roundToPxR2X_6o(IconDistanceFromBaseline) + placeable2.getHeight()) - i12;
        int i14 = (i11 - i13) - iMo279roundToPx0680j_4;
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, androidx.compose.foundation.c.B(placeable, i10, 2), i14, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, androidx.compose.foundation.c.B(placeable2, i10, 2), i14 - iMo278roundToPxR2X_6o, 0.0f, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextOrIcon(Placeable.PlacementScope placementScope, Placeable placeable, int i10) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, androidx.compose.foundation.c.f(placeable, i10, 2), 0.0f, 4, null);
    }
}
