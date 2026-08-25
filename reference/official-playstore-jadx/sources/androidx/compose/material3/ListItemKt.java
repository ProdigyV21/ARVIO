package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.ListItemType;
import androidx.compose.material3.tokens.ListTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.i;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a©\u0001\u0010\u0011\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0015\b\u0002\u0010\u0006\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001av\u0010\u0017\u001a\u00020\u00012\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0013\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u0002H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a`\u0010)\u001a\u00020&*\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$H\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a`\u0010.\u001a\u00020&*\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\u0006\u0010+\u001a\u00020*2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$H\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001am\u00104\u001a\u000203*\u00020\u00192\u0006\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020&2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u0002012\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b4\u00105\u001a5\u0010=\u001a\u00020\u00012\u0006\u00107\u001a\u0002062\u0006\u00109\u001a\u0002082\u0011\u0010:\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u0003ø\u0001\u0000¢\u0006\u0004\b;\u0010<\" \u0010>\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b>\u0010?\u0012\u0004\bB\u0010C\u001a\u0004\b@\u0010A\" \u0010D\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bD\u0010?\u0012\u0004\bF\u0010C\u001a\u0004\bE\u0010A\" \u0010G\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bG\u0010?\u0012\u0004\bI\u0010C\u001a\u0004\bH\u0010A\" \u0010J\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bJ\u0010?\u0012\u0004\bL\u0010C\u001a\u0004\bK\u0010A\" \u0010M\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bM\u0010?\u0012\u0004\bO\u0010C\u001a\u0004\bN\u0010A\" \u0010P\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bP\u0010?\u0012\u0004\bR\u0010C\u001a\u0004\bQ\u0010A\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006S"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "headlineContent", "Landroidx/compose/ui/Modifier;", "modifier", "overlineContent", "supportingContent", "leadingContent", "trailingContent", "Landroidx/compose/material3/ListItemColors;", "colors", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "shadowElevation", "ListItem-HXNGIdc", "(Lr7/p;Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/material3/ListItemColors;FFLandroidx/compose/runtime/Composer;II)V", "ListItem", "leading", "trailing", "headline", "overline", "supporting", "ListItemLayout", "(Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Placeable;", "leadingPlaceable", "trailingPlaceable", "headlinePlaceable", "overlinePlaceable", "supportingPlaceable", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "Landroidx/compose/ui/unit/Constraints;", "constraints", "", "calculateWidth-xygx4p4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/foundation/layout/PaddingValues;J)I", "calculateWidth", "Landroidx/compose/material3/ListItemType;", "listItemType", "calculateHeight-N4Jib3Y", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;ILandroidx/compose/foundation/layout/PaddingValues;J)I", "calculateHeight", "width", "height", "", "isThreeLine", "Landroidx/compose/ui/layout/MeasureResult;", "place", "(Landroidx/compose/ui/layout/MeasureScope;IILandroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;ZLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/foundation/layout/PaddingValues;)Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "textToken", "content", "ProvideTextStyleFromToken-3J-VO9M", "(JLandroidx/compose/material3/tokens/TypographyKeyTokens;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "ProvideTextStyleFromToken", "ListItemVerticalPadding", "F", "getListItemVerticalPadding", "()F", "getListItemVerticalPadding$annotations", "()V", "ListItemThreeLineVerticalPadding", "getListItemThreeLineVerticalPadding", "getListItemThreeLineVerticalPadding$annotations", "ListItemStartPadding", "getListItemStartPadding", "getListItemStartPadding$annotations", "ListItemEndPadding", "getListItemEndPadding", "getListItemEndPadding$annotations", "LeadingContentEndPadding", "getLeadingContentEndPadding", "getLeadingContentEndPadding$annotations", "TrailingContentStartPadding", "getTrailingContentStartPadding", "getTrailingContentStartPadding$annotations", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListItemKt {
    private static final float LeadingContentEndPadding;
    private static final float ListItemEndPadding;
    private static final float ListItemStartPadding;
    private static final float TrailingContentStartPadding;
    private static final float ListItemVerticalPadding = Dp.m5678constructorimpl(8);
    private static final float ListItemThreeLineVerticalPadding = Dp.m5678constructorimpl(12);

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemKt$ListItemLayout$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $headline;
        final /* synthetic */ p<Composer, Integer, t0> $leading;
        final /* synthetic */ p<Composer, Integer, t0> $overline;
        final /* synthetic */ p<Composer, Integer, t0> $supporting;
        final /* synthetic */ p<Composer, Integer, t0> $trailing;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, int i10) {
            super(2);
            this.$leading = pVar;
            this.$trailing = pVar2;
            this.$headline = pVar3;
            this.$overline = pVar4;
            this.$supporting = pVar5;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ListItemKt.ListItemLayout(this.$leading, this.$trailing, this.$headline, this.$overline, this.$supporting, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ListItemKt$place$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ Placeable $headlinePlaceable;
        final /* synthetic */ int $height;
        final /* synthetic */ boolean $isThreeLine;
        final /* synthetic */ LayoutDirection $layoutDirection;
        final /* synthetic */ Placeable $leadingPlaceable;
        final /* synthetic */ Placeable $overlinePlaceable;
        final /* synthetic */ PaddingValues $paddingValues;
        final /* synthetic */ Placeable $supportingPlaceable;
        final /* synthetic */ MeasureScope $this_place;
        final /* synthetic */ Placeable $trailingPlaceable;
        final /* synthetic */ int $width;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MeasureScope measureScope, PaddingValues paddingValues, LayoutDirection layoutDirection, Placeable placeable, Placeable placeable2, boolean z, Placeable placeable3, Placeable placeable4, Placeable placeable5, int i10, int i11) {
            super(1);
            this.$this_place = measureScope;
            this.$paddingValues = paddingValues;
            this.$layoutDirection = layoutDirection;
            this.$leadingPlaceable = placeable;
            this.$trailingPlaceable = placeable2;
            this.$isThreeLine = z;
            this.$headlinePlaceable = placeable3;
            this.$overlinePlaceable = placeable4;
            this.$supportingPlaceable = placeable5;
            this.$height = i10;
            this.$width = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            int iMo279roundToPx0680j_4 = this.$this_place.mo279roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$paddingValues, this.$layoutDirection));
            int iMo279roundToPx0680j_42 = this.$this_place.mo279roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$paddingValues, this.$layoutDirection));
            int iMo279roundToPx0680j_43 = this.$this_place.mo279roundToPx0680j_4(this.$paddingValues.getTop());
            Placeable placeable = this.$leadingPlaceable;
            if (placeable != null) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iMo279roundToPx0680j_4, this.$isThreeLine ? iMo279roundToPx0680j_43 : Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), this.$height), 0.0f, 4, null);
            }
            Placeable placeable2 = this.$trailingPlaceable;
            if (placeable2 != null) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, (this.$width - iMo279roundToPx0680j_42) - placeable2.getWidth(), this.$isThreeLine ? iMo279roundToPx0680j_43 : Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), this.$height), 0.0f, 4, null);
            }
            int iWidthOrZero = TextFieldImplKt.widthOrZero(this.$leadingPlaceable) + iMo279roundToPx0680j_4;
            if (!this.$isThreeLine) {
                iMo279roundToPx0680j_43 = Alignment.INSTANCE.getCenterVertically().align(TextFieldImplKt.heightOrZero(this.$supportingPlaceable) + TextFieldImplKt.heightOrZero(this.$overlinePlaceable) + TextFieldImplKt.heightOrZero(this.$headlinePlaceable), this.$height);
            }
            int i10 = iMo279roundToPx0680j_43;
            Placeable placeable3 = this.$overlinePlaceable;
            if (placeable3 != null) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, iWidthOrZero, i10, 0.0f, 4, null);
            }
            int iHeightOrZero = i10 + TextFieldImplKt.heightOrZero(this.$overlinePlaceable);
            Placeable placeable4 = this.$headlinePlaceable;
            if (placeable4 != null) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, iWidthOrZero, iHeightOrZero, 0.0f, 4, null);
            }
            int iHeightOrZero2 = iHeightOrZero + TextFieldImplKt.heightOrZero(this.$headlinePlaceable);
            Placeable placeable5 = this.$supportingPlaceable;
            if (placeable5 != null) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, iWidthOrZero, iHeightOrZero2, 0.0f, 4, null);
            }
        }
    }

    static {
        float f10 = 16;
        ListItemStartPadding = Dp.m5678constructorimpl(f10);
        ListItemEndPadding = Dp.m5678constructorimpl(f10);
        LeadingContentEndPadding = Dp.m5678constructorimpl(f10);
        TrailingContentStartPadding = Dp.m5678constructorimpl(f10);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00f9  */
    /* JADX INFO: renamed from: ListItem-HXNGIdc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1651ListItemHXNGIdc(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, androidx.compose.ui.Modifier r37, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r38, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r39, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, androidx.compose.material3.ListItemColors r42, float r43, float r44, androidx.compose.runtime.Composer r45, int r46, int r47) {
        /*
            Method dump skipped, instruction units count: 674
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ListItemKt.m1651ListItemHXNGIdc(r7.p, androidx.compose.ui.Modifier, r7.p, r7.p, r7.p, r7.p, androidx.compose.material3.ListItemColors, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ListItemLayout(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(2052297037);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar3) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar4) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar5) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2052297037, i11, -1, "androidx.compose.material3.ListItemLayout (ListItem.kt:167)");
            }
            final LayoutDirection layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            List listE = t7.a.E(pVar3, pVar4 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1423getLambda1$material3_release() : pVar4, pVar5 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1424getLambda2$material3_release() : pVar5, pVar == null ? ComposableSingletons$ListItemKt.INSTANCE.m1425getLambda3$material3_release() : pVar, pVar2 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1426getLambda4$material3_release() : pVar2);
            composerStartRestartGroup.startReplaceableGroup(1361340338);
            boolean zChanged = composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MultiContentMeasurePolicy() { // from class: androidx.compose.material3.ListItemKt$ListItemLayout$1$1
                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return i.a(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return i.b(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
                    public final MeasureResult mo1658measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j10) {
                        List<? extends Measurable> list2 = list.get(0);
                        List<? extends Measurable> list3 = list.get(1);
                        List<? extends Measurable> list4 = list.get(2);
                        List<? extends Measurable> list5 = list.get(3);
                        List<? extends Measurable> list6 = list.get(4);
                        long jM5662offsetNN6EwU = ConstraintsKt.m5662offsetNN6EwU(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null), -measureScope.mo279roundToPx0680j_4(Dp.m5678constructorimpl(ListItemKt.getListItemEndPadding() + ListItemKt.getListItemStartPadding())), -measureScope.mo279roundToPx0680j_4(Dp.m5678constructorimpl(ListItemKt.getListItemVerticalPadding() * 2)));
                        Measurable measurable = (Measurable) x.o0(list5);
                        Placeable placeableMo4631measureBRTryo0 = measurable != null ? measurable.mo4631measureBRTryo0(jM5662offsetNN6EwU) : null;
                        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo0);
                        Measurable measurable2 = (Measurable) x.o0(list6);
                        Placeable placeableMo4631measureBRTryo02 = measurable2 != null ? measurable2.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(jM5662offsetNN6EwU, -iWidthOrZero, 0, 2, null)) : null;
                        int iWidthOrZero2 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo02) + iWidthOrZero;
                        Measurable measurable3 = (Measurable) x.o0(list2);
                        Placeable placeableMo4631measureBRTryo03 = measurable3 != null ? measurable3.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(jM5662offsetNN6EwU, -iWidthOrZero2, 0, 2, null)) : null;
                        int iHeightOrZero = TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo03);
                        Measurable measurable4 = (Measurable) x.o0(list4);
                        Placeable placeableMo4631measureBRTryo04 = measurable4 != null ? measurable4.mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(jM5662offsetNN6EwU, -iWidthOrZero2, -iHeightOrZero)) : null;
                        int iHeightOrZero2 = TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo04) + iHeightOrZero;
                        boolean z = (placeableMo4631measureBRTryo04 == null || placeableMo4631measureBRTryo04.get(AlignmentLineKt.getFirstBaseline()) == placeableMo4631measureBRTryo04.get(AlignmentLineKt.getLastBaseline())) ? false : true;
                        Measurable measurable5 = (Measurable) x.o0(list3);
                        Placeable placeableMo4631measureBRTryo05 = measurable5 != null ? measurable5.mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(jM5662offsetNN6EwU, -iWidthOrZero2, -iHeightOrZero2)) : null;
                        ListItemType.Companion companion = ListItemType.INSTANCE;
                        int iM1668getListItemTypeZLSjz4$material3_release = companion.m1668getListItemTypeZLSjz4$material3_release(placeableMo4631measureBRTryo05 != null, placeableMo4631measureBRTryo04 != null, z);
                        boolean zM1663equalsimpl0 = ListItemType.m1663equalsimpl0(iM1668getListItemTypeZLSjz4$material3_release, companion.m1670getThreeLineAlXitO8());
                        PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(ListItemKt.getListItemStartPadding(), zM1663equalsimpl0 ? ListItemKt.getListItemThreeLineVerticalPadding() : ListItemKt.getListItemVerticalPadding(), ListItemKt.getListItemEndPadding(), zM1663equalsimpl0 ? ListItemKt.getListItemThreeLineVerticalPadding() : ListItemKt.getListItemVerticalPadding());
                        Placeable placeable = placeableMo4631measureBRTryo02;
                        Placeable placeable2 = placeableMo4631measureBRTryo0;
                        Placeable placeable3 = placeableMo4631measureBRTryo04;
                        Placeable placeable4 = placeableMo4631measureBRTryo05;
                        return ListItemKt.place(measureScope, ListItemKt.m1657calculateWidthxygx4p4(measureScope, placeable2, placeable, placeableMo4631measureBRTryo03, placeable4, placeable3, layoutDirection, paddingValuesM525PaddingValuesa9UjIt4, j10), ListItemKt.m1656calculateHeightN4Jib3Y(measureScope, placeable2, placeable, placeableMo4631measureBRTryo03, placeable4, placeable3, iM1668getListItemTypeZLSjz4$material3_release, paddingValuesM525PaddingValuesa9UjIt4, j10), placeable2, placeable, placeableMo4631measureBRTryo03, placeable4, placeable3, zM1663equalsimpl0, layoutDirection, paddingValuesM525PaddingValuesa9UjIt4);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return i.c(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return i.d(this, intrinsicMeasureScope, list, i12);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(1399185516);
            Modifier.Companion companion = Modifier.INSTANCE;
            p<Composer, Integer, t0> pVarCombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listE);
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            boolean zChanged2 = composerStartRestartGroup.changed(multiContentMeasurePolicy);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue2;
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
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
            if (a0.c.B(pVarCombineAsVirtualLayouts, composerStartRestartGroup, 0)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(pVar, pVar2, pVar3, pVar4, pVar5, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ProvideTextStyleFromToken-3J-VO9M, reason: not valid java name */
    public static final void m1652ProvideTextStyleFromToken3JVO9M(long j10, TypographyKeyTokens typographyKeyTokens, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        long j11;
        p<? super Composer, ? super Integer, t0> pVar2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1133967795);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(j10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(typographyKeyTokens) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            j11 = j10;
            pVar2 = pVar;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1133967795, i11, -1, "androidx.compose.material3.ProvideTextStyleFromToken (ListItem.kt:518)");
            }
            j11 = j10;
            ProvideContentColorTextStyleKt.m1782ProvideContentColorTextStyle3JVO9M(j11, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), typographyKeyTokens), pVar, composerStartRestartGroup, i11 & 910);
            pVar2 = pVar;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ListItemKt$ProvideTextStyleFromToken$1(j11, typographyKeyTokens, pVar2, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-N4Jib3Y, reason: not valid java name */
    public static final int m1656calculateHeightN4Jib3Y(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, int i10, PaddingValues paddingValues, long j10) {
        ListItemType.Companion companion = ListItemType.INSTANCE;
        int iMax = Math.max(Math.max(Constraints.m5647getMinHeightimpl(j10), measureScope.mo279roundToPx0680j_4(ListItemType.m1663equalsimpl0(i10, companion.m1669getOneLineAlXitO8()) ? ListTokens.INSTANCE.m2645getListItemOneLineContainerHeightD9Ej5fM() : ListItemType.m1663equalsimpl0(i10, companion.m1671getTwoLineAlXitO8()) ? ListTokens.INSTANCE.m2649getListItemTwoLineContainerHeightD9Ej5fM() : ListTokens.INSTANCE.m2647getListItemThreeLineContainerHeightD9Ej5fM())), Math.max(TextFieldImplKt.heightOrZero(placeable), Math.max(TextFieldImplKt.heightOrZero(placeable5) + TextFieldImplKt.heightOrZero(placeable4) + TextFieldImplKt.heightOrZero(placeable3), TextFieldImplKt.heightOrZero(placeable2))) + measureScope.mo279roundToPx0680j_4(Dp.m5678constructorimpl(paddingValues.getBottom() + paddingValues.getTop())));
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        return iMax > iM5645getMaxHeightimpl ? iM5645getMaxHeightimpl : iMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-xygx4p4, reason: not valid java name */
    public static final int m1657calculateWidthxygx4p4(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, LayoutDirection layoutDirection, PaddingValues paddingValues, long j10) {
        if (Constraints.m5642getHasBoundedWidthimpl(j10)) {
            return Constraints.m5646getMaxWidthimpl(j10);
        }
        int iMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(Dp.m5678constructorimpl(paddingValues.mo481calculateRightPaddingu2uoSUM(layoutDirection) + paddingValues.mo480calculateLeftPaddingu2uoSUM(layoutDirection)));
        return TextFieldImplKt.widthOrZero(placeable2) + TextFieldImplKt.widthOrZero(placeable) + iMo279roundToPx0680j_4 + Math.max(TextFieldImplKt.widthOrZero(placeable3), Math.max(TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeable5)));
    }

    public static final float getLeadingContentEndPadding() {
        return LeadingContentEndPadding;
    }

    public static /* synthetic */ void getLeadingContentEndPadding$annotations() {
    }

    public static final float getListItemEndPadding() {
        return ListItemEndPadding;
    }

    public static /* synthetic */ void getListItemEndPadding$annotations() {
    }

    public static final float getListItemStartPadding() {
        return ListItemStartPadding;
    }

    public static /* synthetic */ void getListItemStartPadding$annotations() {
    }

    public static final float getListItemThreeLineVerticalPadding() {
        return ListItemThreeLineVerticalPadding;
    }

    public static /* synthetic */ void getListItemThreeLineVerticalPadding$annotations() {
    }

    public static final float getListItemVerticalPadding() {
        return ListItemVerticalPadding;
    }

    public static /* synthetic */ void getListItemVerticalPadding$annotations() {
    }

    public static final float getTrailingContentStartPadding() {
        return TrailingContentStartPadding;
    }

    public static /* synthetic */ void getTrailingContentStartPadding$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult place(MeasureScope measureScope, int i10, int i11, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, boolean z, LayoutDirection layoutDirection, PaddingValues paddingValues) {
        return MeasureScope.CC.q(measureScope, i10, i11, null, new AnonymousClass1(measureScope, paddingValues, layoutDirection, placeable, placeable2, z, placeable3, placeable4, placeable5, i11, i10), 4, null);
    }
}
