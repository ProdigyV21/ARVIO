package androidx.compose.foundation.text;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b`\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0004H&¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0006J\u001a\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u0006J\u000f\u0010\u0010\u001a\u00020\u0004H&¢\u0006\u0004\b\u0010\u0010\tJ\u000f\u0010\u0011\u001a\u00020\u0004H&¢\u0006\u0004\b\u0011\u0010\tø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/TextDragObserver;", "", "Landroidx/compose/ui/geometry/Offset;", "point", "Lx6/t0;", "onDown-k-4lQ0M", "(J)V", "onDown", "onUp", "()V", "startPoint", "onStart-k-4lQ0M", "onStart", "delta", "onDrag-k-4lQ0M", "onDrag", "onStop", "onCancel", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface TextDragObserver {
    void onCancel();

    /* JADX INFO: renamed from: onDown-k-4lQ0M, reason: not valid java name */
    void mo875onDownk4lQ0M(long point);

    /* JADX INFO: renamed from: onDrag-k-4lQ0M, reason: not valid java name */
    void mo876onDragk4lQ0M(long delta);

    /* JADX INFO: renamed from: onStart-k-4lQ0M, reason: not valid java name */
    void mo877onStartk4lQ0M(long startPoint);

    void onStop();

    void onUp();
}
