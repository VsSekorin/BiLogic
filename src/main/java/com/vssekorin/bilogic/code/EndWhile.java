/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.method.MethodInfo;
import com.vssekorin.bilogic.util.ChainInsnList;
import jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import lombok.AllArgsConstructor;
import lombok.val;

import static jdk.internal.org.objectweb.asm.Opcodes.GOTO;

/**
 * End of while.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class EndWhile implements Code {

    /**
     * The information about method.
     */
    private final MethodInfo info;

    @Override
    public ChainInsnList asBytecode() {
        val pair = this.info.labels().pop();
        return new ChainInsnList()
            .add(new JumpInsnNode(GOTO, pair.first()))
            .add(pair.second());
    }
}
