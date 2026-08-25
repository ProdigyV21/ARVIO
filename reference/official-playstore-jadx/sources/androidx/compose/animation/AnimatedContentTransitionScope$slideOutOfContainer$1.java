package androidx.compose.animation;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "S", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnimatedContentTransitionScope$slideOutOfContainer$1 extends r implements l<Integer, Integer> {
    public static final AnimatedContentTransitionScope$slideOutOfContainer$1 INSTANCE = new AnimatedContentTransitionScope$slideOutOfContainer$1();

    public AnimatedContentTransitionScope$slideOutOfContainer$1() {
        super(1);
    }

    public final Integer invoke(int i10) {
        return Integer.valueOf(i10);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
