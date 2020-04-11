import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class SymbolCheckTester {
    public static void main(String[] args) throws Exception{
//    String inputFile = null;
//    if (args.length>0) inputFile = args[0];
//    InputStream is = System.in;
//    if(inputFile!=null) is = new FileInputStream(inputFile);

        InputStream is = new FileInputStream("examples/nim.cl");

        ANTLRInputStream input = new ANTLRInputStream(is);
        CLParserLexer lexer = new CLParserLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CLParserParser parser = new CLParserParser(tokens);
        ParseTree tree = parser.program();

        ASTParser trans = new ASTParser();
        Program p = (Program) trans.visit(tree);

        SymbolCheck symbolChecker = new SymbolCheck();

        ASTWalker walker = new ASTWalker();
        walker.walk(symbolChecker, p);
    }
}