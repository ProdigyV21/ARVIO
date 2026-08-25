package androidx.compose.foundation.text.selection;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1 extends r implements l<CacheDrawScope, DrawResult> {
    final /* synthetic */ long $handleColor;
    final /* synthetic */ r7.a<Boolean> $iconVisible;
    final /* synthetic */ boolean $isLeft;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<ContentDrawScope, t0> {
        final /* synthetic */ ColorFilter $colorFilter;
        final /* synthetic */ ImageBitmap $handleImage;
        final /* synthetic */ r7.a<Boolean> $iconVisible;
        final /* synthetic */ boolean $isLeft;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(r7.a<Boolean> aVar, boolean z, ImageBitmap imageBitmap, ColorFilter colorFilter) {
            super(1);
            this.$iconVisible = aVar;
            this.$isLeft = z;
            this.$handleImage = imageBitmap;
            this.$colorFilter = colorFilter;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ContentDrawScope) obj);
            return t0.f22605a;
        }

        public final void invoke(ContentDrawScope contentDrawScope) {
            contentDrawScope.drawContent();
            if (((Boolean) this.$iconVisible.invoke()).booleanValue()) {
                if (!this.$isLeft) {
                    c.A(contentDrawScope, this.$handleImage, 0L, 0.0f, null, this.$colorFilter, 0, 46, null);
                    return;
                }
                ImageBitmap imageBitmap = this.$handleImage;
                ColorFilter colorFilter = this.$colorFilter;
                long jMo3915getCenterF1C5BW0 = contentDrawScope.mo3915getCenterF1C5BW0();
                DrawContext drawContext = contentDrawScope.getDrawContext();
                long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
                drawContext.getCanvas().save();
                drawContext.getTransform().mo3929scale0AR0LA0(-1.0f, 1.0f, jMo3915getCenterF1C5BW0);
                c.A(contentDrawScope, imageBitmap, 0L, 0.0f, null, colorFilter, 0, 46, null);
                drawContext.getCanvas().restore();
                drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidSelectionHandles_androidKt$drawSelectionHandle$1$1$1(long j10, r7.a<Boolean> aVar, boolean z) {
        super(1);
        this.$handleColor = j10;
        this.$iconVisible = aVar;
        this.$isLeft = z;
    }

    @Override // r7.l
    public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
        return cacheDrawScope.onDrawWithContent(new AnonymousClass1(this.$iconVisible, this.$isLeft, AndroidSelectionHandles_androidKt.createHandleImage(cacheDrawScope, Size.m3305getWidthimpl(cacheDrawScope.m3140getSizeNHjbRc()) / 2.0f), ColorFilter.Companion.m3513tintxETnrds$default(ColorFilter.INSTANCE, this.$handleColor, 0, 2, null)));
    }
}
