package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.unit.Velocity;
import d7.d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\"\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "", "Landroidx/compose/ui/geometry/Offset;", "available", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "onPreScroll-OzD1aCk", "(JI)J", "onPreScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostScroll", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLd7/d;)Ljava/lang/Object;", "onPreFling", "onPostFling-RZ2iAVY", "(JJLd7/d;)Ljava/lang/Object;", "onPostFling", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface NestedScrollConnection {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
        public static Object m4409onPostFlingRZ2iAVY(NestedScrollConnection nestedScrollConnection, long j10, long j11, d<? super Velocity> dVar) {
            return a.a(nestedScrollConnection, j10, j11, dVar);
        }

        @Deprecated
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
        public static long m4410onPostScrollDzOQY0M(NestedScrollConnection nestedScrollConnection, long j10, long j11, int i10) {
            return a.b(nestedScrollConnection, j10, j11, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
        public static Object m4411onPreFlingQWom1Mo(NestedScrollConnection nestedScrollConnection, long j10, d<? super Velocity> dVar) {
            return a.c(nestedScrollConnection, j10, dVar);
        }

        @Deprecated
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
        public static long m4412onPreScrollOzD1aCk(NestedScrollConnection nestedScrollConnection, long j10, int i10) {
            return a.d(nestedScrollConnection, j10, i10);
        }
    }

    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    Object mo368onPostFlingRZ2iAVY(long j10, long j11, d<? super Velocity> dVar);

    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    long mo369onPostScrollDzOQY0M(long consumed, long available, int source);

    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    Object mo370onPreFlingQWom1Mo(long j10, d<? super Velocity> dVar);

    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    long mo371onPreScrollOzD1aCk(long available, int source);
}
