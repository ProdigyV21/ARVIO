package org.jsoup.nodes;

import j$.util.function.Consumer$CC;
import java.util.Map;
import java.util.function.Consumer;
import org.jsoup.parser.Tag;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class b implements Consumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f21011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f21012b;

    public /* synthetic */ b(Object obj, int i10) {
        this.f21011a = i10;
        this.f21012b = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.f21011a) {
            case 0:
                Element.appendWholeText((Node) obj, (StringBuilder) this.f21012b);
                break;
            default:
                Tag.lambda$static$7((Map.Entry) this.f21012b, (Tag) obj);
                break;
        }
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        int i10 = this.f21011a;
        return Consumer$CC.$default$andThen(this, consumer);
    }
}
