package androidx.compose.ui.platform;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0086\u0002¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/platform/ValueElementSequence;", "Lga/m;", "Landroidx/compose/ui/platform/ValueElement;", "<init>", "()V", "", "iterator", "()Ljava/util/Iterator;", "", ContentDisposition.Parameters.Name, "", "value", "Lx6/t0;", "set", "(Ljava/lang/String;Ljava/lang/Object;)V", "", "elements", "Ljava/util/List;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ValueElementSequence implements ga.m<ValueElement> {
    public static final int $stable = 8;
    private final List<ValueElement> elements = new ArrayList();

    @Override // ga.m
    public Iterator<ValueElement> iterator() {
        return this.elements.iterator();
    }

    public final void set(String name, Object value) {
        this.elements.add(new ValueElement(name, value));
    }
}
