package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.x;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00028\u0000¢\u0006\u0004\b\u000e\u0010\rR$\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000fj\b\u0012\u0004\u0012\u00028\u0000`\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/server/routing/Stack;", "E", "", "<init>", "()V", "", "empty", "()Z", "element", "Lx6/t0;", "push", "(Ljava/lang/Object;)V", "pop", "()Ljava/lang/Object;", "peek", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "tower", "Ljava/util/ArrayList;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class Stack<E> {
    private final ArrayList<E> tower = new ArrayList<>();

    public final boolean empty() {
        return this.tower.isEmpty();
    }

    public final E peek() {
        if (this.tower.isEmpty()) {
            throw new NoSuchElementException("Unable to peek an element into empty stack");
        }
        return (E) x.w0(this.tower);
    }

    public final E pop() {
        if (this.tower.isEmpty()) {
            throw new NoSuchElementException("Unable to pop an element from empty stack");
        }
        ArrayList<E> arrayList = this.tower;
        return arrayList.remove(a.w(arrayList));
    }

    public final void push(E element) {
        this.tower.add(element);
    }
}
