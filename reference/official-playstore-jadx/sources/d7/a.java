package d7;

import d7.j;
import r7.p;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements j.a {
    private final j.b<?> key;

    public a(j.b bVar) {
        this.key = bVar;
    }

    @Override // d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) pVar.invoke(r4, this);
    }

    @Override // d7.j
    public /* bridge */ <E extends j.a> E get(j.b<E> bVar) {
        return (E) i.a(this, bVar);
    }

    @Override // d7.j.a
    public j.b<?> getKey() {
        return this.key;
    }

    @Override // d7.j
    public /* bridge */ j minusKey(j.b<?> bVar) {
        return i.b(this, bVar);
    }

    @Override // d7.j
    public j plus(j jVar) {
        return h.a(this, jVar);
    }
}
