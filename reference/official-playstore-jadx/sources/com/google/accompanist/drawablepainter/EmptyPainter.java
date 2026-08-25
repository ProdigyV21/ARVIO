package com.google.accompanist.drawablepainter;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/google/accompanist/drawablepainter/EmptyPainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "<init>", "()V", "drawablepainter_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class EmptyPainter extends Painter {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final EmptyPainter f12562i = new EmptyPainter();

    private EmptyPainter() {
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public final long mo4010getIntrinsicSizeNHjbRc() {
        return Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final void onDraw(DrawScope drawScope) {
    }
}
