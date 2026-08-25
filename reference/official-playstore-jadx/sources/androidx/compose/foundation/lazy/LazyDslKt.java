package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.s;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a«\u0001\u0010\u0003\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022%\b\n\u0010\t\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00042%\b\n\u0010\n\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000423\b\u0004\u0010\u0010\u001a-\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0086\b¢\u0006\u0004\b\u0003\u0010\u0011\u001a\u0084\u0001\u0010\u0003\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022%\b\n\u0010\t\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000423\b\u0004\u0010\u0010\u001a-\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0087\b¢\u0006\u0004\b\u0003\u0010\u0012\u001aê\u0001\u0010\u0016\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022:\b\n\u0010\t\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000b2:\b\u0006\u0010\n\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2H\b\u0004\u0010\u0010\u001aB\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0086\b¢\u0006\u0004\b\u0016\u0010\u0017\u001a®\u0001\u0010\u0016\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022:\b\n\u0010\t\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000b2H\b\u0004\u0010\u0010\u001aB\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0087\b¢\u0006\u0004\b\u0016\u0010\u0018\u001a«\u0001\u0010\u0003\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192%\b\n\u0010\t\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00042%\b\n\u0010\n\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000423\b\u0004\u0010\u0010\u001a-\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0086\b¢\u0006\u0004\b\u0003\u0010\u001a\u001a\u0084\u0001\u0010\u0003\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192%\b\n\u0010\t\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000423\b\u0004\u0010\u0010\u001a-\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0087\b¢\u0006\u0004\b\u0003\u0010\u001b\u001aê\u0001\u0010\u0016\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192:\b\n\u0010\t\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000b2:\b\u0006\u0010\n\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2H\b\u0004\u0010\u0010\u001aB\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0086\b¢\u0006\u0004\b\u0016\u0010\u001c\u001a®\u0001\u0010\u0016\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192:\b\n\u0010\t\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000b2H\b\u0004\u0010\u0010\u001aB\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0087\b¢\u0006\u0004\b\u0016\u0010\u001d\u001ax\u0010.\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020$2\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b.\u0010/\u001ax\u00104\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u00101\u001a\u0002002\b\b\u0002\u00103\u001a\u0002022\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020$2\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b4\u00105\u001an\u00104\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u00101\u001a\u0002002\b\b\u0002\u00103\u001a\u0002022\b\b\u0002\u0010+\u001a\u00020*2\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b4\u00106\u001an\u0010.\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020&2\b\b\u0002\u0010)\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020*2\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b.\u00107¨\u00068"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyListScope;", "", "items", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "item", "", "key", "contentType", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "itemContent", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lr7/l;Lr7/l;Lr7/r;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lr7/l;Lr7/r;)V", "", "index", "Lkotlin/Function3;", "itemsIndexed", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lr7/p;Lr7/p;Lr7/s;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lr7/p;Lr7/s;)V", "", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lr7/l;Lr7/l;Lr7/r;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lr7/l;Lr7/r;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lr7/p;Lr7/p;Lr7/s;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lr7/p;Lr7/s;)V", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/lazy/LazyListState;", "state", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "", "reverseLayout", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/foundation/gestures/FlingBehavior;", "flingBehavior", "userScrollEnabled", "content", "LazyRow", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLr7/l;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "LazyColumn", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLr7/l;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyDslKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$LazyColumn$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<LazyListScope, t0> $content;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ FlingBehavior $flingBehavior;
        final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $reverseLayout;
        final /* synthetic */ LazyListState $state;
        final /* synthetic */ boolean $userScrollEnabled;
        final /* synthetic */ Arrangement.Vertical $verticalArrangement;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z5, l<? super LazyListScope, t0> lVar, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$state = lazyListState;
            this.$contentPadding = paddingValues;
            this.$reverseLayout = z;
            this.$verticalArrangement = vertical;
            this.$horizontalAlignment = horizontal;
            this.$flingBehavior = flingBehavior;
            this.$userScrollEnabled = z5;
            this.$content = lVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyDslKt.LazyColumn(this.$modifier, this.$state, this.$contentPadding, this.$reverseLayout, this.$verticalArrangement, this.$horizontalAlignment, this.$flingBehavior, this.$userScrollEnabled, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$LazyColumn$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<LazyListScope, t0> $content;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ FlingBehavior $flingBehavior;
        final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $reverseLayout;
        final /* synthetic */ LazyListState $state;
        final /* synthetic */ Arrangement.Vertical $verticalArrangement;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, l<? super LazyListScope, t0> lVar, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$state = lazyListState;
            this.$contentPadding = paddingValues;
            this.$reverseLayout = z;
            this.$verticalArrangement = vertical;
            this.$horizontalAlignment = horizontal;
            this.$flingBehavior = flingBehavior;
            this.$content = lVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyDslKt.LazyColumn(this.$modifier, this.$state, this.$contentPadding, this.$reverseLayout, this.$verticalArrangement, this.$horizontalAlignment, this.$flingBehavior, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$LazyRow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03431 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<LazyListScope, t0> $content;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ FlingBehavior $flingBehavior;
        final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $reverseLayout;
        final /* synthetic */ LazyListState $state;
        final /* synthetic */ boolean $userScrollEnabled;
        final /* synthetic */ Alignment.Vertical $verticalAlignment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03431(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z5, l<? super LazyListScope, t0> lVar, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$state = lazyListState;
            this.$contentPadding = paddingValues;
            this.$reverseLayout = z;
            this.$horizontalArrangement = horizontal;
            this.$verticalAlignment = vertical;
            this.$flingBehavior = flingBehavior;
            this.$userScrollEnabled = z5;
            this.$content = lVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyDslKt.LazyRow(this.$modifier, this.$state, this.$contentPadding, this.$reverseLayout, this.$horizontalArrangement, this.$verticalAlignment, this.$flingBehavior, this.$userScrollEnabled, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$LazyRow$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03442 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<LazyListScope, t0> $content;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ FlingBehavior $flingBehavior;
        final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $reverseLayout;
        final /* synthetic */ LazyListState $state;
        final /* synthetic */ Alignment.Vertical $verticalAlignment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03442(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, l<? super LazyListScope, t0> lVar, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$state = lazyListState;
            this.$contentPadding = paddingValues;
            this.$reverseLayout = z;
            this.$horizontalArrangement = horizontal;
            this.$verticalAlignment = vertical;
            this.$flingBehavior = flingBehavior;
            this.$content = lVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyDslKt.LazyRow(this.$modifier, this.$state, this.$contentPadding, this.$reverseLayout, this.$horizontalArrangement, this.$verticalAlignment, this.$flingBehavior, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03451 extends r implements l {
        public static final C03451 INSTANCE = new C03451();

        public C03451() {
            super(1);
        }

        @Override // r7.l
        public final Void invoke(Object obj) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03462 extends r implements l<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ l<T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03462(l<? super T, ? extends Object> lVar, List<? extends T> list) {
            super(1);
            this.$key = lVar;
            this.$items = list;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$key.invoke(this.$items.get(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass3 extends r implements l<Integer, Object> {
        final /* synthetic */ l<T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(l<? super T, ? extends Object> lVar, List<? extends T> list) {
            super(1);
            this.$contentType = lVar;
            this.$items = list;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$contentType.invoke(this.$items.get(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ r7.r<LazyItemScope, T, Composer, Integer, t0> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(r7.r<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, t0> rVar, List<? extends T> list) {
            super(4);
            this.$itemContent = rVar;
            this.$items = list;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
            int i12;
            if ((i11 & 14) == 0) {
                i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
            } else {
                i12 = i11;
            }
            if ((i11 & 112) == 0) {
                i12 |= composer.changed(i10) ? 32 : 16;
            }
            if ((i12 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-632812321, i12, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:148)");
            }
            this.$itemContent.invoke(lazyItemScope, this.$items.get(i10), composer, Integer.valueOf(i12 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass5 extends r implements l {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        @Override // r7.l
        public final Void invoke(Object obj) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass6 extends r implements l<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ l<T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(l<? super T, ? extends Object> lVar, T[] tArr) {
            super(1);
            this.$key = lVar;
            this.$items = tArr;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$key.invoke(this.$items[i10]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass7 extends r implements l<Integer, Object> {
        final /* synthetic */ l<T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass7(l<? super T, ? extends Object> lVar, T[] tArr) {
            super(1);
            this.$contentType = lVar;
            this.$items = tArr;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$contentType.invoke(this.$items[i10]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$items$8, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass8 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ r7.r<LazyItemScope, T, Composer, Integer, t0> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(r7.r<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, t0> rVar, T[] tArr) {
            super(4);
            this.$itemContent = rVar;
            this.$items = tArr;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
            int i12;
            if ((i11 & 14) == 0) {
                i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
            } else {
                i12 = i11;
            }
            if ((i11 & 112) == 0) {
                i12 |= composer.changed(i10) ? 32 : 16;
            }
            if ((i12 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1043393750, i12, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:218)");
            }
            this.$itemContent.invoke(lazyItemScope, this.$items[i10], composer, Integer.valueOf(i12 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "<anonymous parameter 0>", "", "<anonymous parameter 1>", "invoke", "(ILjava/lang/Object;)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03471 extends r implements p {
        public static final C03471 INSTANCE = new C03471();

        public C03471() {
            super(2);
        }

        public final Void invoke(int i10, Object obj) {
            return null;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), obj2);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03482 extends r implements l<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ p<Integer, T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03482(p<? super Integer, ? super T, ? extends Object> pVar, List<? extends T> list) {
            super(1);
            this.$key = pVar;
            this.$items = list;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$key.invoke(Integer.valueOf(i10), this.$items.get(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03493 extends r implements l<Integer, Object> {
        final /* synthetic */ p<Integer, T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03493(p<? super Integer, ? super T, ? extends Object> pVar, List<? extends T> list) {
            super(1);
            this.$contentType = pVar;
            this.$items = list;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$contentType.invoke(Integer.valueOf(i10), this.$items.get(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03504 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ s<LazyItemScope, Integer, T, Composer, Integer, t0> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03504(s<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, t0> sVar, List<? extends T> list) {
            super(4);
            this.$itemContent = sVar;
            this.$items = list;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
            int i12;
            if ((i11 & 14) == 0) {
                i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
            } else {
                i12 = i11;
            }
            if ((i11 & 112) == 0) {
                i12 |= composer.changed(i10) ? 32 : 16;
            }
            if ((i12 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1091073711, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
            }
            this.$itemContent.invoke(lazyItemScope, Integer.valueOf(i10), this.$items.get(i10), composer, Integer.valueOf(i12 & 126));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "<anonymous parameter 0>", "", "<anonymous parameter 1>", "invoke", "(ILjava/lang/Object;)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03515 extends r implements p {
        public static final C03515 INSTANCE = new C03515();

        public C03515() {
            super(2);
        }

        public final Void invoke(int i10, Object obj) {
            return null;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), obj2);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$6, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03526 extends r implements l<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ p<Integer, T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03526(p<? super Integer, ? super T, ? extends Object> pVar, T[] tArr) {
            super(1);
            this.$key = pVar;
            this.$items = tArr;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$key.invoke(Integer.valueOf(i10), this.$items[i10]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$7, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C03537 extends r implements l<Integer, Object> {
        final /* synthetic */ p<Integer, T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03537(p<? super Integer, ? super T, ? extends Object> pVar, T[] tArr) {
            super(1);
            this.$contentType = pVar;
            this.$items = tArr;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$contentType.invoke(Integer.valueOf(i10), this.$items[i10]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$8, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03548 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ s<LazyItemScope, Integer, T, Composer, Integer, t0> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03548(s<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, t0> sVar, T[] tArr) {
            super(4);
            this.$itemContent = sVar;
            this.$items = tArr;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
            int i12;
            if ((i11 & 14) == 0) {
                i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
            } else {
                i12 = i11;
            }
            if ((i11 & 112) == 0) {
                i12 |= composer.changed(i10) ? 32 : 16;
            }
            if ((i12 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1600639390, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:253)");
            }
            this.$itemContent.invoke(lazyItemScope, Integer.valueOf(i10), this.$items[i10], composer, Integer.valueOf(i12 & 126));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyColumn(androidx.compose.ui.Modifier r29, androidx.compose.foundation.lazy.LazyListState r30, androidx.compose.foundation.layout.PaddingValues r31, boolean r32, androidx.compose.foundation.layout.Arrangement.Vertical r33, androidx.compose.ui.Alignment.Horizontal r34, androidx.compose.foundation.gestures.FlingBehavior r35, boolean r36, r7.l<? super androidx.compose.foundation.lazy.LazyListScope, x6.t0> r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyDslKt.LazyColumn(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.LazyListState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.gestures.FlingBehavior, boolean, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyRow(androidx.compose.ui.Modifier r29, androidx.compose.foundation.lazy.LazyListState r30, androidx.compose.foundation.layout.PaddingValues r31, boolean r32, androidx.compose.foundation.layout.Arrangement.Horizontal r33, androidx.compose.ui.Alignment.Vertical r34, androidx.compose.foundation.gestures.FlingBehavior r35, boolean r36, r7.l<? super androidx.compose.foundation.lazy.LazyListScope, x6.t0> r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 583
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyDslKt.LazyRow(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.LazyListState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.FlingBehavior, boolean, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final <T> void items(LazyListScope lazyListScope, List<? extends T> list, l<? super T, ? extends Object> lVar, l<? super T, ? extends Object> lVar2, r7.r<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, t0> rVar) {
        lazyListScope.items(list.size(), lVar != null ? new C03462(lVar, list) : null, new AnonymousClass3(lVar2, list), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new AnonymousClass4(rVar, list)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, List list, l lVar, l lVar2, r7.r rVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        if ((i10 & 4) != 0) {
            lVar2 = C03451.INSTANCE;
        }
        lazyListScope.items(list.size(), lVar != null ? new C03462(lVar, list) : null, new AnonymousClass3(lVar2, list), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new AnonymousClass4(rVar, list)));
    }

    public static final <T> void itemsIndexed(LazyListScope lazyListScope, List<? extends T> list, p<? super Integer, ? super T, ? extends Object> pVar, p<? super Integer, ? super T, ? extends Object> pVar2, s<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, t0> sVar) {
        lazyListScope.items(list.size(), pVar != null ? new C03482(pVar, list) : null, new C03493(pVar2, list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new C03504(sVar, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, List list, p pVar, p pVar2, s sVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        if ((i10 & 4) != 0) {
            pVar2 = C03471.INSTANCE;
        }
        lazyListScope.items(list.size(), pVar != null ? new C03482(pVar, list) : null, new C03493(pVar2, list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new C03504(sVar, list)));
    }

    public static final <T> void items(LazyListScope lazyListScope, T[] tArr, l<? super T, ? extends Object> lVar, l<? super T, ? extends Object> lVar2, r7.r<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, t0> rVar) {
        lazyListScope.items(tArr.length, lVar != null ? new AnonymousClass6(lVar, tArr) : null, new AnonymousClass7(lVar2, tArr), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new AnonymousClass8(rVar, tArr)));
    }

    public static final <T> void itemsIndexed(LazyListScope lazyListScope, T[] tArr, p<? super Integer, ? super T, ? extends Object> pVar, p<? super Integer, ? super T, ? extends Object> pVar2, s<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, t0> sVar) {
        lazyListScope.items(tArr.length, pVar != null ? new C03526(pVar, tArr) : null, new C03537(pVar2, tArr), ComposableLambdaKt.composableLambdaInstance(1600639390, true, new C03548(sVar, tArr)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, Object[] objArr, l lVar, l lVar2, r7.r rVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        if ((i10 & 4) != 0) {
            lVar2 = AnonymousClass5.INSTANCE;
        }
        lazyListScope.items(objArr.length, lVar != null ? new AnonymousClass6(lVar, objArr) : null, new AnonymousClass7(lVar2, objArr), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new AnonymousClass8(rVar, objArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, Object[] objArr, p pVar, p pVar2, s sVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        if ((i10 & 4) != 0) {
            pVar2 = C03515.INSTANCE;
        }
        lazyListScope.items(objArr.length, pVar != null ? new C03526(pVar, objArr) : null, new C03537(pVar2, objArr), ComposableLambdaKt.composableLambdaInstance(1600639390, true, new C03548(sVar, objArr)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, List list, l lVar, r7.r rVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        lazyListScope.items(list.size(), lVar != null ? new C03462(lVar, list) : null, new AnonymousClass3(C03451.INSTANCE, list), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new AnonymousClass4(rVar, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, List list, p pVar, s sVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        lazyListScope.items(list.size(), pVar != null ? new C03482(pVar, list) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$1(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new C03504(sVar, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, Object[] objArr, p pVar, s sVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            pVar = null;
        }
        lazyListScope.items(objArr.length, pVar != null ? new C03526(pVar, objArr) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$2(objArr), ComposableLambdaKt.composableLambdaInstance(1600639390, true, new C03548(sVar, objArr)));
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0122  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void LazyColumn(androidx.compose.ui.Modifier r23, androidx.compose.foundation.lazy.LazyListState r24, androidx.compose.foundation.layout.PaddingValues r25, boolean r26, androidx.compose.foundation.layout.Arrangement.Vertical r27, androidx.compose.ui.Alignment.Horizontal r28, androidx.compose.foundation.gestures.FlingBehavior r29, r7.l r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyDslKt.LazyColumn(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.LazyListState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.gestures.FlingBehavior, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0122  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void LazyRow(androidx.compose.ui.Modifier r23, androidx.compose.foundation.lazy.LazyListState r24, androidx.compose.foundation.layout.PaddingValues r25, boolean r26, androidx.compose.foundation.layout.Arrangement.Horizontal r27, androidx.compose.ui.Alignment.Vertical r28, androidx.compose.foundation.gestures.FlingBehavior r29, r7.l r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyDslKt.LazyRow(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.LazyListState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.FlingBehavior, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, Object[] objArr, l lVar, r7.r rVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = null;
        }
        lazyListScope.items(objArr.length, lVar != null ? new AnonymousClass6(lVar, objArr) : null, new AnonymousClass7(AnonymousClass5.INSTANCE, objArr), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new AnonymousClass8(rVar, objArr)));
    }
}
