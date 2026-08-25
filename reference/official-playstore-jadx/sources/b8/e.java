package b8;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u0000*\f\b\u0000\u0010\u0002 \u0001*\u0004\u0018\u00010\u00012\u00020\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lb8/e;", "Ljava/lang/reflect/Member;", "M", "", "", "args", "Lx6/t0;", "checkArguments", "([Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface e<M extends Member> {
    List a();

    Member b();

    Object call(Object[] objArr);

    Type getReturnType();
}
