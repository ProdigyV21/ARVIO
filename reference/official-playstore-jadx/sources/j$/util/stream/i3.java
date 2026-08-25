package j$.util.stream;

import j$.util.Spliterator;
import java.util.Deque;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class i3 extends j3 {
    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        g2 g2VarA;
        if (!c()) {
            return false;
        }
        boolean zTryAdvance = this.f18744d.tryAdvance(consumer);
        if (!zTryAdvance) {
            if (this.f18743c == null && (g2VarA = j3.a(this.f18745e)) != null) {
                Spliterator spliterator = g2VarA.spliterator();
                this.f18744d = spliterator;
                return spliterator.tryAdvance(consumer);
            }
            this.f18741a = null;
        }
        return zTryAdvance;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        if (this.f18741a == null) {
            return;
        }
        if (this.f18744d == null) {
            Spliterator spliterator = this.f18743c;
            if (spliterator == null) {
                Deque dequeB = b();
                while (true) {
                    g2 g2VarA = j3.a(dequeB);
                    if (g2VarA != null) {
                        g2VarA.forEach(consumer);
                    } else {
                        this.f18741a = null;
                        return;
                    }
                }
            } else {
                spliterator.forEachRemaining(consumer);
            }
        } else {
            while (tryAdvance(consumer)) {
            }
        }
    }
}
