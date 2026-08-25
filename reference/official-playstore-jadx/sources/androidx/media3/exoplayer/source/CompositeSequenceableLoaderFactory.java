package androidx.media3.exoplayer.source;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface CompositeSequenceableLoaderFactory {
    SequenceableLoader create(List<? extends SequenceableLoader> list, List<List<Integer>> list2);

    @Deprecated
    SequenceableLoader createCompositeSequenceableLoader(SequenceableLoader... sequenceableLoaderArr);

    SequenceableLoader empty();
}
