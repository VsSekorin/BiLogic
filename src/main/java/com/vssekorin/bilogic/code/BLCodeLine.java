/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vseslav Sekorin
 */
package com.vssekorin.bilogic.code;

import com.vssekorin.bilogic.error.IncorrectLine;
import com.vssekorin.bilogic.util.ChainedInsnList;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Arrays;

/**
 * BiLogic code line.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@AllArgsConstructor
public final class BLCodeLine implements Code {

    /**
     * Code line.
     */
    private final String line;

    @Override
    public ChainedInsnList asBytecode() {
        val words = Arrays.asList(this.line.split("\\s+"));
        final Code code;
        if (words.contains("is")) {
            code = new Assignment(line);
        } else {
            switch (words.get(0)) {
                case "in": code = new In(line); break;
                case "out": code = new Out(line); break;
                case "panic": code = new Panic(line); break;
                case "if": code = new If(line); break;
                case "else": code = new Else(); break;
                case "while": code = new While(line); break;
                case "end": code = new End(line); break;
                default: throw new IncorrectLine(line);
            }
        }
        return code.asBytecode();
    }
}
