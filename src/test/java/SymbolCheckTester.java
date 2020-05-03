import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SymbolCheckTester {
    public static void main(String[] args) throws Exception {
        // 单独测试某个文件
        InputStream is = new FileInputStream("examples/biorhythms.cl");

        ANTLRInputStream input = new ANTLRInputStream(is);
        CLParserLexer lexer = new CLParserLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CLParserParser parser = new CLParserParser(tokens);
        ParseTree tree = parser.program();

        ASTParser trans = new ASTParser();
        Program p = (Program) trans.visit(tree);
        ASTWalker walker = new ASTWalker();

        SymbolCheck symbolChecker = new SymbolCheck();
        walker.walk(symbolChecker, p);
    }

    @Test
    public void iterTestSymbolCheck() throws IOException {
        File dir = new File("examples");
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                testSymbolCheck(file.getAbsolutePath());
            }
        }
    }

    public void testSymbolCheck(String inputFile) throws IOException {
        System.out.println("testing file: " + inputFile);
        InputStream is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);
        CLParserLexer lexer = new CLParserLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CLParserParser parser = new CLParserParser(tokens);
        ParseTree tree = parser.program();

        ASTParser trans = new ASTParser();
        Program p = (Program) trans.visit(tree);
        ASTWalker walker = new ASTWalker();

        SymbolCheck symbolChecker = new SymbolCheck();
        walker.walk(symbolChecker, p);
    }
}