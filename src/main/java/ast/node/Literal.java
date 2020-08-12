package ast.node;

import org.antlr.v4.runtime.ParserRuleContext;

public class Literal extends Expression {
    public Literal(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public String toString() {
        return "lit";
    }
}
