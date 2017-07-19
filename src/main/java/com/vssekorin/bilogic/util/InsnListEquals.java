/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.util;

import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.ListIterator;

/**
 * Equivalence of two InsnList.
 * Technical code.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class InsnListEquals {

    /**
     * The first list.
     */
    private final InsnList firstList;

    /**
     * The second list.
     */
    private final InsnList secondList;

    /**
     * Check that first list equals second.
     *
     * @return Result
     */
    public boolean value() {
        val firstIter = this.firstList.iterator();
        val secondIter = this.secondList.iterator();
        final boolean result;
        if (this.firstList.size() == this.secondList.size()) {
            result = elementEquals(firstIter, secondIter, true);
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Check that next elements is equal.
     *
     * @param firstIter The first iterator
     * @param secondIter The second iterator
     * @param value Last value
     * @return Result
     */
    private boolean elementEquals(
        final ListIterator<AbstractInsnNode> firstIter,
        final ListIterator<AbstractInsnNode> secondIter,
        final boolean value
    ) {
        final boolean result;
        if (firstIter.hasNext() && value) {
            val first = firstIter.next();
            val second = secondIter.next();
            val opcodeEq = first.getOpcode() == second.getOpcode();
            val typeEq = first.getType() == second.getType();
            result = elementEquals(firstIter, secondIter, opcodeEq && typeEq);
        } else {
            result = value;
        }
        return result;
    }
}
