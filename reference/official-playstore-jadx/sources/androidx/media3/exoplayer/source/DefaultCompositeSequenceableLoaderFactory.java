package androidx.media3.exoplayer.source;

import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultCompositeSequenceableLoaderFactory implements CompositeSequenceableLoaderFactory {
    @Override // androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory
    public SequenceableLoader create(List<? extends SequenceableLoader> list, List<List<Integer>> list2) {
        return new CompositeSequenceableLoader(list, list2);
    }

    @Override // androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory
    @Deprecated
    public SequenceableLoader createCompositeSequenceableLoader(SequenceableLoader... sequenceableLoaderArr) {
        return new CompositeSequenceableLoader(sequenceableLoaderArr);
    }

    @Override // androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory
    public SequenceableLoader empty() {
        f1 f1Var = h1.f14020l;
        o3 o3Var = o3.f14078o;
        return new CompositeSequenceableLoader(o3Var, o3Var);
    }
}
