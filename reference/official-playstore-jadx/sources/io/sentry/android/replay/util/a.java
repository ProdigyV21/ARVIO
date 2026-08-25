package io.sentry.android.replay.util;

import androidx.compose.ui.text.TextLayoutResult;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TextLayoutResult f17036i;

    public a(TextLayoutResult textLayoutResult) {
        this.f17036i = textLayoutResult;
    }

    @Override // io.sentry.android.replay.util.k
    public final int getLineBottom(int i10) {
        return t7.a.M(this.f17036i.getLineBottom(i10));
    }

    @Override // io.sentry.android.replay.util.k
    public final int getLineCount() {
        return this.f17036i.getLineCount();
    }

    @Override // io.sentry.android.replay.util.k
    public final float getLineLeft(int i10) {
        TextLayoutResult textLayoutResult = this.f17036i;
        if (textLayoutResult.getMultiParagraph().getWidth() > ((int) (textLayoutResult.getSize() >> 32))) {
            return 0.0f;
        }
        return textLayoutResult.getLineLeft(i10);
    }

    @Override // io.sentry.android.replay.util.k
    public final float getLineRight(int i10) {
        TextLayoutResult textLayoutResult = this.f17036i;
        return textLayoutResult.getMultiParagraph().getWidth() > ((float) ((int) (textLayoutResult.getSize() >> 32))) ? textLayoutResult.getMultiParagraph().getLineWidth(i10) : textLayoutResult.getLineRight(i10);
    }

    @Override // io.sentry.android.replay.util.k
    public final int getLineTop(int i10) {
        return t7.a.M(this.f17036i.getLineTop(i10));
    }

    @Override // io.sentry.android.replay.util.k
    public final Integer i() {
        return null;
    }
}
