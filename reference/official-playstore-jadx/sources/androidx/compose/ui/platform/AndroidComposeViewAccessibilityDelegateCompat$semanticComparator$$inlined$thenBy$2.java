package androidx.compose.ui.platform;

import androidx.compose.ui.semantics.SemanticsNode;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.util.concurrent.r0;
import java.util.Comparator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00002\u000e\u0010\u0002\u001a\n \u0001*\u0004\u0018\u00018\u00008\u00002\u000e\u0010\u0003\u001a\n \u0001*\u0004\u0018\u00018\u00008\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "kotlin.jvm.PlatformType", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2<T> implements Comparator {
    final /* synthetic */ Comparator $this_thenBy;

    public AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2(Comparator comparator) {
        this.$this_thenBy = comparator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T t2, T t10) {
        int iCompare = this.$this_thenBy.compare(t2, t10);
        return iCompare != 0 ? iCompare : r0.e(Integer.valueOf(((SemanticsNode) t2).getId()), Integer.valueOf(((SemanticsNode) t10).getId()));
    }
}
