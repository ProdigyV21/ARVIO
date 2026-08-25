package androidx.compose.material3;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a¹\u0001\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a/\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0014\b\u0002\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001d0\u0016H\u0007¢\u0006\u0004\b!\u0010\"\u001a0\u0010'\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u000b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010$\u001a\u00020\u001dH\u0003ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a#\u0010*\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010)\u001a\u00020(H\u0003¢\u0006\u0004\b*\u0010+\u001a@\u0010,\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0004\b,\u0010-\u001a\u0013\u0010/\u001a\u00020\u001d*\u00020.H\u0002¢\u0006\u0004\b/\u00100\u001a\u001b\u00103\u001a\u00020\u001d*\u0002012\u0006\u00102\u001a\u00020\u001dH\u0002¢\u0006\u0004\b3\u00104\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00067²\u0006\f\u00105\u001a\u00020(8\nX\u008a\u0084\u0002²\u0006\u0017\u00106\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00108\nX\u008a\u0084\u0002"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/SheetState;", "sheetState", "Landroidx/compose/ui/unit/Dp;", "sheetMaxWidth", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "tonalElevation", "scrimColor", "Landroidx/compose/runtime/Composable;", "dragHandle", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "Landroidx/compose/material3/ModalBottomSheetProperties;", "properties", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/n;", "content", "ModalBottomSheet-dYc4hso", "(Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLr7/p;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/ModalBottomSheetProperties;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "ModalBottomSheet", "", "skipPartiallyExpanded", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "rememberModalBottomSheetState", "(ZLr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", TtmlNode.ATTR_TTS_COLOR, "visible", "Scrim-3J-VO9M", "(JLr7/a;ZLandroidx/compose/runtime/Composer;I)V", "Scrim", "", "fullHeight", "modalBottomSheetAnchors", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;F)Landroidx/compose/ui/Modifier;", "ModalBottomSheetPopup", "(Landroidx/compose/material3/ModalBottomSheetProperties;Lr7/a;Landroidx/compose/foundation/layout/WindowInsets;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroid/view/View;", "isFlagSecureEnabled", "(Landroid/view/View;)Z", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isSecureFlagSetOnParent", "shouldApplySecureFlag", "(Landroidx/compose/ui/window/SecureFlagPolicy;Z)Z", "alpha", "currentContent", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheet_androidKt {

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetPopup$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ ModalBottomSheetProperties $properties;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(ModalBottomSheetProperties modalBottomSheetProperties, r7.a<t0> aVar, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$properties = modalBottomSheetProperties;
            this.$onDismissRequest = aVar;
            this.$windowInsets = windowInsets;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ModalBottomSheet_androidKt.ModalBottomSheetPopup(this.$properties, this.$onDismissRequest, this.$windowInsets, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SecureFlagPolicy.values().length];
            try {
                iArr[SecureFlagPolicy.SecureOff.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SecureFlagPolicy.SecureOn.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SecureFlagPolicy.Inherit.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$modalBottomSheetAnchors$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/unit/IntSize;", "sheetSize", "Lx6/t0;", "invoke-ozmzZPI", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<IntSize, t0> {
        final /* synthetic */ float $fullHeight;
        final /* synthetic */ SheetState $sheetState;

        /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$modalBottomSheetAnchors$1$WhenMappings */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SheetValue.values().length];
                try {
                    iArr[SheetValue.Hidden.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[SheetValue.Expanded.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SheetState sheetState, float f10) {
            super(1);
            this.$sheetState = sheetState;
            this.$fullHeight = f10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m1692invokeozmzZPI(((IntSize) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
        public final void m1692invokeozmzZPI(long j10) {
            SheetValue sheetValue;
            DraggableAnchors<SheetValue> DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new ModalBottomSheet_androidKt$modalBottomSheetAnchors$1$newAnchors$1(this.$fullHeight, j10, this.$sheetState));
            int i10 = WhenMappings.$EnumSwitchMapping$0[this.$sheetState.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
            if (i10 == 1) {
                sheetValue = SheetValue.Hidden;
            } else {
                if (i10 != 2 && i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                sheetValue = SheetValue.PartiallyExpanded;
                if (!DraggableAnchors.hasAnchorFor(sheetValue)) {
                    sheetValue = SheetValue.Expanded;
                    if (!DraggableAnchors.hasAnchorFor(sheetValue)) {
                        sheetValue = SheetValue.Hidden;
                    }
                }
            }
            this.$sheetState.getAnchoredDraggableState$material3_release().updateAnchors(DraggableAnchors, sheetValue);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheet_androidKt$rememberModalBottomSheetState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05271 extends r implements l<SheetValue, Boolean> {
        public static final C05271 INSTANCE = new C05271();

        public C05271() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SheetValue sheetValue) {
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:310:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x010f  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1684ModalBottomSheetdYc4hso(r7.a<x6.t0> r43, androidx.compose.ui.Modifier r44, androidx.compose.material3.SheetState r45, float r46, androidx.compose.ui.graphics.Shape r47, long r48, long r50, float r52, long r53, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r55, androidx.compose.foundation.layout.WindowInsets r56, androidx.compose.material3.ModalBottomSheetProperties r57, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r58, androidx.compose.runtime.Composer r59, int r60, int r61, int r62) {
        /*
            Method dump skipped, instruction units count: 1246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ModalBottomSheet_androidKt.m1684ModalBottomSheetdYc4hso(r7.a, androidx.compose.ui.Modifier, androidx.compose.material3.SheetState, float, androidx.compose.ui.graphics.Shape, long, long, float, long, r7.p, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.ModalBottomSheetProperties, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final void ModalBottomSheetPopup(ModalBottomSheetProperties modalBottomSheetProperties, r7.a<t0> aVar, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(738805080);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modalBottomSheetProperties) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(windowInsets) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(738805080, i11, -1, "androidx.compose.material3.ModalBottomSheetPopup (ModalBottomSheet.android.kt:436)");
            }
            View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            UUID uuid = (UUID) RememberSaveableKt.m3084rememberSaveable(new Object[0], (Saver) null, (String) null, (r7.a) ModalBottomSheet_androidKt$ModalBottomSheetPopup$id$1.INSTANCE, composerStartRestartGroup, 3072, 6);
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(pVar, composerStartRestartGroup, (i11 >> 9) & 14);
            LayoutDirection layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            composerStartRestartGroup.startReplaceableGroup(173201889);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            Object obj = objRememberedValue;
            if (objRememberedValue == companion.getEmpty()) {
                ModalBottomSheetWindow modalBottomSheetWindow = new ModalBottomSheetWindow(modalBottomSheetProperties, aVar, view, uuid);
                modalBottomSheetWindow.setCustomContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-114385661, true, new ModalBottomSheet_androidKt$ModalBottomSheetPopup$modalBottomSheetWindow$1$1$1(windowInsets, stateRememberUpdatedState)));
                composerStartRestartGroup.updateRememberedValue(modalBottomSheetWindow);
                obj = modalBottomSheetWindow;
            }
            ModalBottomSheetWindow modalBottomSheetWindow2 = (ModalBottomSheetWindow) obj;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(173202877);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(modalBottomSheetWindow2) | composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new ModalBottomSheet_androidKt$ModalBottomSheetPopup$1$1(modalBottomSheetWindow2, layoutDirection);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.DisposableEffect(modalBottomSheetWindow2, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(modalBottomSheetProperties, aVar, windowInsets, pVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p<Composer, Integer, t0> ModalBottomSheetPopup$lambda$8(State<? extends p<? super Composer, ? super Integer, t0>> state) {
        return (p) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1685Scrim3JVO9M(long j10, r7.a<t0> aVar, boolean z, Composer composer, int i10) {
        int i11;
        Modifier modifierClearAndSetSemantics;
        Composer composerStartRestartGroup = composer.startRestartGroup(1053897700);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(j10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1053897700, i11, -1, "androidx.compose.material3.Scrim (ModalBottomSheet.android.kt:370)");
            }
            if (j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
                int i12 = i11;
                State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, composerStartRestartGroup, 48, 28);
                composerStartRestartGroup.startReplaceableGroup(-1858718943);
                if (z) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    composerStartRestartGroup.startReplaceableGroup(-1858718859);
                    boolean z5 = (i12 & 112) == 32;
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new ModalBottomSheet_androidKt$Scrim$dismissSheet$1$1(aVar, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceableGroup();
                    modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(SuspendingPointerInputFilterKt.pointerInput(companion, aVar, (p<? super PointerInputScope, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue), ModalBottomSheet_androidKt$Scrim$dismissSheet$2.INSTANCE);
                } else {
                    modifierClearAndSetSemantics = Modifier.INSTANCE;
                }
                composerStartRestartGroup.endReplaceableGroup();
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(modifierClearAndSetSemantics);
                composerStartRestartGroup.startReplaceableGroup(-1858718531);
                boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState) | ((i12 & 14) == 4);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new ModalBottomSheet_androidKt$Scrim$1$1(j10, stateAnimateFloatAsState);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                composerStartRestartGroup.endReplaceableGroup();
                CanvasKt.Canvas(modifierThen, (l) objRememberedValue2, composerStartRestartGroup, 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ModalBottomSheet_androidKt$Scrim$2(j10, aVar, z, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$5(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier modalBottomSheetAnchors(Modifier modifier, SheetState sheetState, float f10) {
        return OnRemeasuredModifierKt.onSizeChanged(modifier, new AnonymousClass1(sheetState, f10));
    }

    public static final SheetState rememberModalBottomSheetState(boolean z, l<? super SheetValue, Boolean> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1261794383);
        if ((i11 & 1) != 0) {
            z = false;
        }
        boolean z5 = z;
        if ((i11 & 2) != 0) {
            lVar = C05271.INSTANCE;
        }
        l<? super SheetValue, Boolean> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1261794383, i10, -1, "androidx.compose.material3.rememberModalBottomSheetState (ModalBottomSheet.android.kt:363)");
        }
        SheetState sheetStateRememberSheetState = SheetDefaultsKt.rememberSheetState(z5, lVar2, SheetValue.Hidden, false, composer, (i10 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK | (i10 & 112), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return sheetStateRememberSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldApplySecureFlag(SecureFlagPolicy secureFlagPolicy, boolean z) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[secureFlagPolicy.ordinal()];
        if (i10 == 1) {
            return false;
        }
        if (i10 == 2) {
            return true;
        }
        if (i10 == 3) {
            return z;
        }
        throw new NoWhenBranchMatchedException();
    }
}
