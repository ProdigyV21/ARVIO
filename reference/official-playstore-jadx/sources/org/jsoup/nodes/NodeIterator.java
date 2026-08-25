package org.jsoup.nodes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes5.dex */
public class NodeIterator<T extends Node> implements Iterator<T> {
    private Node current;
    private Node currentParent;
    private T next;
    private Node previous;
    private Node root;
    private final Class<T> type;

    public NodeIterator(Node node, Class<T> cls) {
        Validate.notNull(node);
        Validate.notNull(cls);
        this.type = cls;
        restart(node);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private T findNextNode() {
        Node nodeParent = (T) this.current;
        do {
            if (nodeParent.childNodeSize() > 0) {
                nodeParent = (T) nodeParent.childNode(0);
            } else if (this.root.equals(nodeParent)) {
                nodeParent = (T) null;
            } else if (nodeParent.nextSibling() != null) {
                nodeParent = (T) nodeParent.nextSibling();
            } else {
                do {
                    nodeParent = nodeParent.parent();
                    if (nodeParent == null || this.root.equals(nodeParent)) {
                        return null;
                    }
                } while (nodeParent.nextSibling() == null);
                nodeParent = (T) nodeParent.nextSibling();
            }
            if (nodeParent == null) {
                return null;
            }
        } while (!this.type.isInstance(nodeParent));
        return (T) nodeParent;
    }

    public static NodeIterator<Node> from(Node node) {
        return new NodeIterator<>(node, Node.class);
    }

    private void maybeFindNext() {
        if (this.next != null) {
            return;
        }
        if (this.currentParent != null && !this.current.hasParent()) {
            this.current = this.previous;
        }
        this.next = (T) findNextNode();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        maybeFindNext();
        return this.next != null;
    }

    @Override // java.util.Iterator
    public void remove() {
        this.current.remove();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void restart(Node node) {
        if (this.type.isInstance(node)) {
            this.next = node;
        }
        this.current = node;
        this.previous = node;
        this.root = node;
        this.currentParent = node.parent();
    }

    @Override // java.util.Iterator
    public T next() {
        maybeFindNext();
        T t2 = this.next;
        if (t2 == null) {
            throw new NoSuchElementException();
        }
        this.previous = this.current;
        this.current = t2;
        this.currentParent = t2.parent();
        this.next = null;
        return t2;
    }
}
