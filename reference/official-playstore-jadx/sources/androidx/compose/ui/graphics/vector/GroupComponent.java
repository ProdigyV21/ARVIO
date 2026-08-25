package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.d;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u001f\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u0007*\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0019\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0001H\u0002¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0007H\u0002¢\u0006\u0004\b#\u0010\u0003J\u000f\u0010$\u001a\u00020\u0007H\u0002¢\u0006\u0004\b$\u0010\u0003J\u000f\u0010%\u001a\u00020\u0007H\u0002¢\u0006\u0004\b%\u0010\u0003R\u001e\u0010'\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b'\u0010(R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R$\u0010.\u001a\u00020,2\u0006\u0010-\u001a\u00020,8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b.\u00100R*\u00101\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001b8\u0006@BX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R6\u00108\u001a\b\u0012\u0004\u0012\u000206052\f\u00107\u001a\b\u0012\u0004\u0012\u000206058\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b8\u0010+\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010/R\u0018\u0010?\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R0\u0010B\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007\u0018\u00010A8\u0010@\u0010X\u0090\u000e¢\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR \u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010CR*\u0010I\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u00148\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bK\u0010\u0016\"\u0004\bL\u0010MR*\u0010O\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR*\u0010U\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bU\u0010P\u001a\u0004\bV\u0010R\"\u0004\bW\u0010TR*\u0010X\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bX\u0010P\u001a\u0004\bY\u0010R\"\u0004\bZ\u0010TR*\u0010[\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010P\u001a\u0004\b\\\u0010R\"\u0004\b]\u0010TR*\u0010^\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b^\u0010P\u001a\u0004\b_\u0010R\"\u0004\b`\u0010TR*\u0010a\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\ba\u0010P\u001a\u0004\bb\u0010R\"\u0004\bc\u0010TR*\u0010d\u001a\u00020N2\u0006\u00107\u001a\u00020N8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bd\u0010P\u001a\u0004\be\u0010R\"\u0004\bf\u0010TR\u0016\u0010g\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010/R\u0011\u0010j\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\bh\u0010iR\u0014\u0010l\u001a\u00020,8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bk\u00100\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006m"}, d2 = {"Landroidx/compose/ui/graphics/vector/GroupComponent;", "Landroidx/compose/ui/graphics/vector/VNode;", "<init>", "()V", "", "index", "instance", "Lx6/t0;", "insertAt", "(ILandroidx/compose/ui/graphics/vector/VNode;)V", "from", "to", "count", "move", "(III)V", "remove", "(II)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/ui/graphics/Brush;", "brush", "markTintForBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "markTintForColor-8_81llA", "(J)V", "markTintForColor", "node", "markTintForVNode", "(Landroidx/compose/ui/graphics/vector/VNode;)V", "markNotTintable", "updateClipPath", "updateMatrix", "Landroidx/compose/ui/graphics/Matrix;", "groupMatrix", "[F", "", "children", "Ljava/util/List;", "", "<set-?>", "isTintable", "Z", "()Z", "tintColor", "J", "getTintColor-0d7_KjU", "()J", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "value", "clipPathData", "getClipPathData", "()Ljava/util/List;", "setClipPathData", "(Ljava/util/List;)V", "isClipPathDirty", "Landroidx/compose/ui/graphics/Path;", "clipPath", "Landroidx/compose/ui/graphics/Path;", "Lkotlin/Function1;", "invalidateListener", "Lr7/l;", "getInvalidateListener$ui_release", "()Lr7/l;", "setInvalidateListener$ui_release", "(Lr7/l;)V", "wrappedListener", ContentDisposition.Parameters.Name, "Ljava/lang/String;", "getName", "setName", "(Ljava/lang/String;)V", "", "rotation", "F", "getRotation", "()F", "setRotation", "(F)V", "pivotX", "getPivotX", "setPivotX", "pivotY", "getPivotY", "setPivotY", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "isMatrixDirty", "getNumChildren", "()I", "numChildren", "getWillClipPath", "willClipPath", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GroupComponent extends VNode {
    public static final int $stable = 8;
    private final List<VNode> children;
    private Path clipPath;
    private List<? extends PathNode> clipPathData;
    private float[] groupMatrix;
    private l<? super VNode, t0> invalidateListener;
    private boolean isClipPathDirty;
    private boolean isMatrixDirty;
    private boolean isTintable;
    private String name;
    private float pivotX;
    private float pivotY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private long tintColor;
    private float translationX;
    private float translationY;
    private final l<VNode, t0> wrappedListener;

    public GroupComponent() {
        super(null);
        this.children = new ArrayList();
        this.isTintable = true;
        this.tintColor = Color.INSTANCE.m3508getUnspecified0d7_KjU();
        this.clipPathData = VectorKt.getEmptyPath();
        this.isClipPathDirty = true;
        this.wrappedListener = new GroupComponent$wrappedListener$1(this);
        this.name = "";
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.isMatrixDirty = true;
    }

    private final boolean getWillClipPath() {
        return !this.clipPathData.isEmpty();
    }

    private final void markNotTintable() {
        this.isTintable = false;
        this.tintColor = Color.INSTANCE.m3508getUnspecified0d7_KjU();
    }

    private final void markTintForBrush(Brush brush) {
        if (this.isTintable && brush != null) {
            if (brush instanceof SolidColor) {
                m4018markTintForColor8_81llA(((SolidColor) brush).getValue());
            } else {
                markNotTintable();
            }
        }
    }

    /* JADX INFO: renamed from: markTintForColor-8_81llA, reason: not valid java name */
    private final void m4018markTintForColor8_81llA(long color) {
        if (this.isTintable) {
            Color.Companion companion = Color.INSTANCE;
            if (color != companion.m3508getUnspecified0d7_KjU()) {
                if (this.tintColor == companion.m3508getUnspecified0d7_KjU()) {
                    this.tintColor = color;
                } else {
                    if (VectorKt.m4042rgbEqualOWjLjI(this.tintColor, color)) {
                        return;
                    }
                    markNotTintable();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markTintForVNode(VNode node) {
        if (node instanceof PathComponent) {
            PathComponent pathComponent = (PathComponent) node;
            markTintForBrush(pathComponent.getFill());
            markTintForBrush(pathComponent.getStroke());
        } else if (node instanceof GroupComponent) {
            GroupComponent groupComponent = (GroupComponent) node;
            if (groupComponent.isTintable && this.isTintable) {
                m4018markTintForColor8_81llA(groupComponent.tintColor);
            } else {
                markNotTintable();
            }
        }
    }

    private final void updateClipPath() {
        if (getWillClipPath()) {
            Path Path = this.clipPath;
            if (Path == null) {
                Path = AndroidPath_androidKt.Path();
                this.clipPath = Path;
            }
            PathParserKt.toPath(this.clipPathData, Path);
        }
    }

    private final void updateMatrix() {
        float[] fArrM3684constructorimpl$default = this.groupMatrix;
        if (fArrM3684constructorimpl$default == null) {
            fArrM3684constructorimpl$default = Matrix.m3684constructorimpl$default(null, 1, null);
            this.groupMatrix = fArrM3684constructorimpl$default;
        } else {
            Matrix.m3693resetimpl(fArrM3684constructorimpl$default);
        }
        float[] fArr = fArrM3684constructorimpl$default;
        Matrix.m3704translateimpl$default(fArr, this.pivotX + this.translationX, this.pivotY + this.translationY, 0.0f, 4, null);
        Matrix.m3696rotateZimpl(fArr, this.rotation);
        Matrix.m3697scaleimpl(fArr, this.scaleX, this.scaleY, 1.0f);
        Matrix.m3704translateimpl$default(fArr, -this.pivotX, -this.pivotY, 0.0f, 4, null);
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public void draw(DrawScope drawScope) {
        if (this.isMatrixDirty) {
            updateMatrix();
            this.isMatrixDirty = false;
        }
        if (this.isClipPathDirty) {
            updateClipPath();
            this.isClipPathDirty = false;
        }
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        DrawTransform transform = drawContext.getTransform();
        float[] fArr = this.groupMatrix;
        if (fArr != null) {
            transform.mo3930transform58bKbWc(Matrix.m3682boximpl(fArr).m3705unboximpl());
        }
        Path path = this.clipPath;
        if (getWillClipPath() && path != null) {
            d.c(transform, path, 0, 2, null);
        }
        List<VNode> list = this.children;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).draw(drawScope);
        }
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    public final List<PathNode> getClipPathData() {
        return this.clipPathData;
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public l<VNode, t0> getInvalidateListener$ui_release() {
        return this.invalidateListener;
    }

    public final String getName() {
        return this.name;
    }

    public final int getNumChildren() {
        return this.children.size();
    }

    public final float getPivotX() {
        return this.pivotX;
    }

    public final float getPivotY() {
        return this.pivotY;
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final float getScaleY() {
        return this.scaleY;
    }

    /* JADX INFO: renamed from: getTintColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTintColor() {
        return this.tintColor;
    }

    public final float getTranslationX() {
        return this.translationX;
    }

    public final float getTranslationY() {
        return this.translationY;
    }

    public final void insertAt(int index, VNode instance) {
        if (index < getNumChildren()) {
            this.children.set(index, instance);
        } else {
            this.children.add(instance);
        }
        markTintForVNode(instance);
        instance.setInvalidateListener$ui_release(this.wrappedListener);
        invalidate();
    }

    /* JADX INFO: renamed from: isTintable, reason: from getter */
    public final boolean getIsTintable() {
        return this.isTintable;
    }

    public final void move(int from, int to, int count) {
        int i10 = 0;
        if (from > to) {
            while (i10 < count) {
                VNode vNode = this.children.get(from);
                this.children.remove(from);
                this.children.add(to, vNode);
                to++;
                i10++;
            }
        } else {
            while (i10 < count) {
                VNode vNode2 = this.children.get(from);
                this.children.remove(from);
                this.children.add(to - 1, vNode2);
                i10++;
            }
        }
        invalidate();
    }

    public final void remove(int index, int count) {
        for (int i10 = 0; i10 < count; i10++) {
            if (index < this.children.size()) {
                this.children.get(index).setInvalidateListener$ui_release(null);
                this.children.remove(index);
            }
        }
        invalidate();
    }

    public final void setClipPathData(List<? extends PathNode> list) {
        this.clipPathData = list;
        this.isClipPathDirty = true;
        invalidate();
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public void setInvalidateListener$ui_release(l<? super VNode, t0> lVar) {
        this.invalidateListener = lVar;
    }

    public final void setName(String str) {
        this.name = str;
        invalidate();
    }

    public final void setPivotX(float f10) {
        this.pivotX = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public final void setPivotY(float f10) {
        this.pivotY = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public final void setRotation(float f10) {
        this.rotation = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public final void setScaleX(float f10) {
        this.scaleX = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public final void setScaleY(float f10) {
        this.scaleY = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public final void setTranslationX(float f10) {
        this.translationX = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public final void setTranslationY(float f10) {
        this.translationY = f10;
        this.isMatrixDirty = true;
        invalidate();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("VGroup: ");
        sb2.append(this.name);
        List<VNode> list = this.children;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            VNode vNode = list.get(i10);
            sb2.append("\t");
            sb2.append(vNode.toString());
            sb2.append("\n");
        }
        return sb2.toString();
    }
}
