package androidx.compose.material3;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.ts.PsExtractor;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0091\u0002\u0010!\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\r¢\u0006\u0002\b\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u0087\u0002\u0010$\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\r¢\u0006\u0002\b\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aÈ\u0001\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0014\u001a\u00020%2\b\b\u0002\u0010\u001b\u001a\u00020\u001aH\u0003¢\u0006\u0004\b&\u0010'\"\u001a\u0010(\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\f\n\u0004\b(\u0010)\u0012\u0004\b*\u0010+\"\u001a\u0010,\u001a\u00020\u00158\u0000X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010)\u001a\u0004\b-\u0010.\"\u0014\u00100\u001a\u00020/8\u0002X\u0082T¢\u0006\u0006\n\u0004\b0\u0010)\"\u001a\u00101\u001a\u00020\u00158\u0000X\u0080\u0004¢\u0006\f\n\u0004\b1\u0010)\u001a\u0004\b2\u0010.\"\u0014\u00103\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u0010)\"\u001a\u00104\u001a\u00020\u00158\u0000X\u0080\u0004¢\u0006\f\n\u0004\b4\u0010)\u001a\u0004\b5\u0010.\"\u0014\u00106\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u0010)\"\u0014\u00108\u001a\u0002078\u0002X\u0082T¢\u0006\u0006\n\u0004\b8\u00109\"\u0014\u0010:\u001a\u0002078\u0002X\u0082T¢\u0006\u0006\n\u0004\b:\u00109\"\u0014\u0010;\u001a\u0002078\u0002X\u0082T¢\u0006\u0006\n\u0004\b;\u00109\"\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>\"\u0014\u0010?\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010>\"\u001a\u0010A\u001a\b\u0012\u0004\u0012\u00020/0@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010B\"\u001a\u0010C\u001a\b\u0012\u0004\u0012\u00020/0@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010B\"\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020D0@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010B\"\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020D0@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010B\"\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010I\"\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010L\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006O²\u0006\f\u0010M\u001a\u00020\u00068\nX\u008a\u0084\u0002²\u0006\f\u0010N\u001a\u00020\u00068\nX\u008a\u0084\u0002"}, d2 = {"", "query", "Lkotlin/Function1;", "Lx6/t0;", "onQueryChange", "onSearch", "", "active", "onActiveChange", "Landroidx/compose/ui/Modifier;", "modifier", "enabled", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/material3/SearchBarColors;", "colors", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "shadowElevation", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/n;", "content", "SearchBar-WuY5d9Q", "(Ljava/lang/String;Lr7/l;Lr7/l;ZLr7/l;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "SearchBar", "DockedSearchBar-eWTbjVg", "(Ljava/lang/String;Lr7/l;Lr7/l;ZLr7/l;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "DockedSearchBar", "Landroidx/compose/material3/TextFieldColors;", "SearchBarInputField", "(Ljava/lang/String;Lr7/l;Lr7/l;ZLr7/l;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Lr7/p;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "SearchBarCornerRadius", "F", "getSearchBarCornerRadius$annotations", "()V", "DockedActiveTableMinHeight", "getDockedActiveTableMinHeight", "()F", "", "DockedActiveTableMaxHeightScreenRatio", "SearchBarMinWidth", "getSearchBarMinWidth", "SearchBarMaxWidth", "SearchBarVerticalPadding", "getSearchBarVerticalPadding", "SearchBarIconOffsetX", "", "AnimationEnterDurationMillis", "I", "AnimationExitDurationMillis", "AnimationDelayMillis", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationEnterEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationExitEasing", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "AnimationEnterFloatSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "AnimationExitFloatSpec", "Landroidx/compose/ui/unit/IntSize;", "AnimationEnterSizeSpec", "AnimationExitSizeSpec", "Landroidx/compose/animation/EnterTransition;", "DockedEnterTransition", "Landroidx/compose/animation/EnterTransition;", "Landroidx/compose/animation/ExitTransition;", "DockedExitTransition", "Landroidx/compose/animation/ExitTransition;", "useFullScreenShape", "showResults", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SearchBar_androidKt {
    private static final int AnimationDelayMillis = 100;
    private static final int AnimationEnterDurationMillis = 600;
    private static final CubicBezierEasing AnimationEnterEasing;
    private static final FiniteAnimationSpec<Float> AnimationEnterFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationEnterSizeSpec;
    private static final int AnimationExitDurationMillis = 350;
    private static final CubicBezierEasing AnimationExitEasing;
    private static final FiniteAnimationSpec<Float> AnimationExitFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationExitSizeSpec;
    private static final float DockedActiveTableMaxHeightScreenRatio = 0.6666667f;
    private static final EnterTransition DockedEnterTransition;
    private static final ExitTransition DockedExitTransition;
    private static final float SearchBarCornerRadius = Dp.m5678constructorimpl(SearchBarDefaults.INSTANCE.m1811getInputFieldHeightD9Ej5fM() / 2);
    private static final float DockedActiveTableMinHeight = Dp.m5678constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
    private static final float SearchBarMinWidth = Dp.m5678constructorimpl(360);
    private static final float SearchBarMaxWidth = Dp.m5678constructorimpl(720);
    private static final float SearchBarVerticalPadding = Dp.m5678constructorimpl(8);
    private static final float SearchBarIconOffsetX = Dp.m5678constructorimpl(4);

    /* JADX INFO: renamed from: androidx.compose.material3.SearchBar_androidKt$SearchBarInputField$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ String $query;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(String str, boolean z, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, TextFieldColors textFieldColors) {
            super(3);
            this.$query = str;
            this.$enabled = z;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = pVar;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$colors = textFieldColors;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((p<? super Composer, ? super Integer, t0>) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = i10 | (composer.changedInstance(pVar) ? 4 : 2);
            } else {
                i11 = i10;
            }
            if ((i11 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-951844929, i11, -1, "androidx.compose.material3.SearchBarInputField.<anonymous> (SearchBar.android.kt:478)");
            }
            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
            int i12 = i11;
            String str = this.$query;
            boolean z = this.$enabled;
            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            p<Composer, Integer, t0> pVar2 = this.$placeholder;
            p<Composer, Integer, t0> pVar3 = this.$leadingIcon;
            ComposableLambda composableLambda = pVar3 != null ? ComposableLambdaKt.composableLambda(composer, -1030845367, true, new SearchBar_androidKt$SearchBarInputField$5$1$1(pVar3)) : null;
            p<Composer, Integer, t0> pVar4 = this.$trailingIcon;
            textFieldDefaults.DecorationBox(str, pVar, z, true, none, mutableInteractionSource, false, null, pVar2, composableLambda, pVar4 != null ? ComposableLambdaKt.composableLambda(composer, -1558904811, true, new SearchBar_androidKt$SearchBarInputField$5$2$1(pVar4)) : null, null, null, null, SearchBarDefaults.INSTANCE.getInputFieldShape(composer, 6), this.$colors, TextFieldDefaults.m2114contentPaddingWithoutLabela9UjIt4$default(textFieldDefaults, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableSingletons$SearchBar_androidKt.INSTANCE.m1433getLambda1$material3_release(), composer, ((i12 << 3) & 112) | 27648, 113246208, 14528);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SearchBar_androidKt$SearchBarInputField$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $active;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Boolean, t0> $onActiveChange;
        final /* synthetic */ l<String, t0> $onQueryChange;
        final /* synthetic */ l<String, t0> $onSearch;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ String $query;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(String str, l<? super String, t0> lVar, l<? super String, t0> lVar2, boolean z, l<? super Boolean, t0> lVar3, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$query = str;
            this.$onQueryChange = lVar;
            this.$onSearch = lVar2;
            this.$active = z;
            this.$onActiveChange = lVar3;
            this.$modifier = modifier;
            this.$enabled = z5;
            this.$placeholder = pVar;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$colors = textFieldColors;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SearchBar_androidKt.SearchBarInputField(this.$query, this.$onQueryChange, this.$onSearch, this.$active, this.$onActiveChange, this.$modifier, this.$enabled, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$colors, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    static {
        CubicBezierEasing easingEmphasizedDecelerateCubicBezier = MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier();
        AnimationEnterEasing = easingEmphasizedDecelerateCubicBezier;
        CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationExitEasing = cubicBezierEasing;
        TweenSpec tweenSpecTween = AnimationSpecKt.tween(600, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterFloatSpec = tweenSpecTween;
        TweenSpec tweenSpecTween2 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitFloatSpec = tweenSpecTween2;
        TweenSpec tweenSpecTween3 = AnimationSpecKt.tween(600, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterSizeSpec = tweenSpecTween3;
        TweenSpec tweenSpecTween4 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitSizeSpec = tweenSpecTween4;
        DockedEnterTransition = EnterExitTransitionKt.fadeIn$default(tweenSpecTween, 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(tweenSpecTween3, null, false, null, 14, null));
        DockedExitTransition = EnterExitTransitionKt.fadeOut$default(tweenSpecTween2, 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(tweenSpecTween4, null, false, null, 14, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:258:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0125  */
    /* JADX INFO: renamed from: DockedSearchBar-eWTbjVg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1816DockedSearchBareWTbjVg(java.lang.String r34, r7.l<? super java.lang.String, x6.t0> r35, r7.l<? super java.lang.String, x6.t0> r36, boolean r37, r7.l<? super java.lang.Boolean, x6.t0> r38, androidx.compose.ui.Modifier r39, boolean r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r42, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r43, androidx.compose.ui.graphics.Shape r44, androidx.compose.material3.SearchBarColors r45, float r46, float r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, androidx.compose.runtime.Composer r50, int r51, int r52, int r53) {
        /*
            Method dump skipped, instruction units count: 1068
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m1816DockedSearchBareWTbjVg(java.lang.String, r7.l, r7.l, boolean, r7.l, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04da  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:332:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0123  */
    /* JADX INFO: renamed from: SearchBar-WuY5d9Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1817SearchBarWuY5d9Q(java.lang.String r36, r7.l<? super java.lang.String, x6.t0> r37, r7.l<? super java.lang.String, x6.t0> r38, boolean r39, r7.l<? super java.lang.Boolean, x6.t0> r40, androidx.compose.ui.Modifier r41, boolean r42, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, androidx.compose.ui.graphics.Shape r46, androidx.compose.material3.SearchBarColors r47, float r48, float r49, androidx.compose.foundation.layout.WindowInsets r50, androidx.compose.foundation.interaction.MutableInteractionSource r51, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r52, androidx.compose.runtime.Composer r53, int r54, int r55, int r56) {
        /*
            Method dump skipped, instruction units count: 1510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m1817SearchBarWuY5d9Q(java.lang.String, r7.l, r7.l, boolean, r7.l, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:107:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x042f  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:221:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchBarInputField(java.lang.String r82, r7.l<? super java.lang.String, x6.t0> r83, r7.l<? super java.lang.String, x6.t0> r84, boolean r85, r7.l<? super java.lang.Boolean, x6.t0> r86, androidx.compose.ui.Modifier r87, boolean r88, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r89, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r90, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r91, androidx.compose.material3.TextFieldColors r92, androidx.compose.foundation.interaction.MutableInteractionSource r93, androidx.compose.runtime.Composer r94, int r95, int r96, int r97) {
        /*
            Method dump skipped, instruction units count: 1268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.SearchBarInputField(java.lang.String, r7.l, r7.l, boolean, r7.l, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, r7.p, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final boolean SearchBar_WuY5d9Q$lambda$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public static final float getDockedActiveTableMinHeight() {
        return DockedActiveTableMinHeight;
    }

    private static /* synthetic */ void getSearchBarCornerRadius$annotations() {
    }

    public static final float getSearchBarMinWidth() {
        return SearchBarMinWidth;
    }

    public static final float getSearchBarVerticalPadding() {
        return SearchBarVerticalPadding;
    }
}
