package androidx.compose.ui.tooling.preview.datasource;

import androidx.compose.ui.tooling.preview.PreviewParameterProvider;
import ga.m;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\nR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/tooling/preview/datasource/LoremIpsum;", "Landroidx/compose/ui/tooling/preview/PreviewParameterProvider;", "", "", "words", "<init>", "(I)V", "()V", "generateLoremIpsum", "(I)Ljava/lang/String;", "I", "Lga/m;", "getValues", "()Lga/m;", "values", "ui-tooling-preview_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class LoremIpsum implements PreviewParameterProvider<String> {
    public static final int $stable = 0;
    private final int words;

    /* JADX INFO: renamed from: androidx.compose.ui.tooling.preview.datasource.LoremIpsum$generateLoremIpsum$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        final /* synthetic */ int $loremIpsumMaxSize;
        final /* synthetic */ i0 $wordsUsed;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(i0 i0Var, int i10) {
            super(0);
            this.$wordsUsed = i0Var;
            this.$loremIpsumMaxSize = i10;
        }

        @Override // r7.a
        public final String invoke() {
            List list = LoremIpsum_androidKt.LOREM_IPSUM_SOURCE;
            i0 i0Var = this.$wordsUsed;
            int i10 = i0Var.f19744i;
            i0Var.f19744i = i10 + 1;
            return (String) list.get(i10 % this.$loremIpsumMaxSize);
        }
    }

    public LoremIpsum(int i10) {
        this.words = i10;
    }

    private final String generateLoremIpsum(int words) {
        return ga.r.O(ga.r.S(ga.r.N(new AnonymousClass1(new i0(), LoremIpsum_androidKt.LOREM_IPSUM_SOURCE.size())), words), " ", null, null, 62);
    }

    @Override // androidx.compose.ui.tooling.preview.PreviewParameterProvider
    public final /* synthetic */ int getCount() {
        return d0.a.a(this);
    }

    @Override // androidx.compose.ui.tooling.preview.PreviewParameterProvider
    public m<String> getValues() {
        return kotlin.collections.r.G(new String[]{generateLoremIpsum(this.words)});
    }

    public LoremIpsum() {
        this(500);
    }
}
