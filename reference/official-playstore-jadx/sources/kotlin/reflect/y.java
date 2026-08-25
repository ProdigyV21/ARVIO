package kotlin.reflect;

import a8.x1;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class y implements TypeVariable, x {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f19922i;

    public y(r rVar) {
        this.f19922i = rVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TypeVariable) || !this.f19922i.getName().equals(((TypeVariable) obj).getName())) {
            return false;
        }
        getGenericDeclaration();
        throw null;
    }

    @Override // java.lang.reflect.TypeVariable
    public final Type[] getBounds() {
        List upperBounds = this.f19922i.getUpperBounds();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(upperBounds, 10));
        Iterator it = upperBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(b0.k((q) it.next(), true));
        }
        return (Type[]) arrayList.toArray(new Type[0]);
    }

    @Override // java.lang.reflect.TypeVariable
    public final GenericDeclaration getGenericDeclaration() {
        throw new x1(androidx.compose.material3.d.C("An operation is not implemented: ", "getGenericDeclaration() is not yet supported for type variables created from KType: " + this.f19922i));
    }

    @Override // java.lang.reflect.TypeVariable
    public final String getName() {
        return this.f19922i.getName();
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        return this.f19922i.getName();
    }

    public final int hashCode() {
        this.f19922i.getName();
        getGenericDeclaration();
        throw null;
    }

    public final String toString() {
        return this.f19922i.getName();
    }
}
