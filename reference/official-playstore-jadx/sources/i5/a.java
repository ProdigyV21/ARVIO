package i5;

import com.google.firebase.encoders.EncodingException;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16215a;

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        switch (this.f16215a) {
            case 0:
                throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
            case 1:
                Map.Entry entry = (Map.Entry) obj;
                com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
                eVar.f(j5.f.f19215g, entry.getKey());
                eVar.f(j5.f.f19216h, entry.getValue());
                return;
            default:
                throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
        }
    }
}
