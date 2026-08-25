package io.sentry;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/* JADX INFO: loaded from: classes4.dex */
public final class r7 implements Queue, Collection, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h f17788i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.util.b f17789l = new io.sentry.util.b();

    public r7(h hVar) {
        this.f17788i = hVar;
    }

    @Override // java.util.Queue, java.util.Collection
    public final boolean add(Object obj) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zAdd = this.f17788i.add(obj);
            aVarA.close();
            return zAdd;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zAddAll = this.f17788i.addAll(collection);
            aVarA.close();
            return zAddAll;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final void clear() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            this.f17788i.clear();
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zContains = this.f17788i.contains(obj);
            aVarA.close();
            return zContains;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zContainsAll = this.f17788i.containsAll(collection);
            aVarA.close();
            return zContainsAll;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Queue
    public final Object element() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            Object objElement = this.f17788i.element();
            aVarA.close();
            return objElement;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zEquals = this.f17788i.equals(obj);
            aVarA.close();
            return zEquals;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final int hashCode() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            int iHashCode = this.f17788i.hashCode();
            aVarA.close();
            return iHashCode;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zIsEmpty = this.f17788i.isEmpty();
            aVarA.close();
            return zIsEmpty;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.f17788i.iterator();
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zOffer = this.f17788i.offer(obj);
            aVarA.close();
            return zOffer;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Queue
    public final Object peek() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            Object objPeek = this.f17788i.peek();
            aVarA.close();
            return objPeek;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Queue
    public final Object poll() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            Object objPoll = this.f17788i.poll();
            aVarA.close();
            return objPoll;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Queue
    public final Object remove() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            Object objRemove = this.f17788i.remove();
            aVarA.close();
            return objRemove;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zRemoveAll = this.f17788i.removeAll(collection);
            aVarA.close();
            return zRemoveAll;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zRetainAll = this.f17788i.retainAll(collection);
            aVarA.close();
            return zRetainAll;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final int size() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            int size = this.f17788i.size();
            aVarA.close();
            return size;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            Object[] array = this.f17788i.toArray();
            aVarA.close();
            return array;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final String toString() {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            String string = this.f17788i.toString();
            aVarA.close();
            return string;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            boolean zRemove = this.f17788i.remove(obj);
            aVarA.close();
            return zRemove;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        io.sentry.util.a aVarA = this.f17789l.a();
        try {
            Object[] array = this.f17788i.toArray(objArr);
            aVarA.close();
            return array;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
