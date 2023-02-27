package Colors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class Parser {
    ArrayList<Token> code;
    Stack<Token> stack;
    Stack<Token> op;
    Queue<Token> queue;
    Token token;
    enum characters{CHAR,LBRACKET,RBRACKET,SEMICOLON,COLON,COMMA,OR,POSITIVE,KLEENE,CONCAT}
    WordPainter painter;
    public Parser(ArrayList<Token> code,WordPainter painter) {
        this.code = code;
        this.painter = painter;
    }
    /*start with ini*/
    public void parse() {
        ini();
    }
    /*ini::=LBRACKET instructions LIMIT analysis RBRACKET*/
    void ini() {
        token = popToken(0);
        if(token != null && token.type == Tokens.LBRACKET) {
            instructions();
            token = popToken(0);
            if(token != null && token.type == Tokens.LIMIT) {
                painter.LIMIT(token.yychar,token.yylength);
                analysis();
                token = popToken(0);
                if(token != null && token.type == Tokens.RBRACKET) {
                    return;
                }
            }
        }
    }
    /*instructions::=instruction instructions | instruction*/
    void instructions() {
        instruction();
        try {
            token = getToken(0);
            if(token != null && token.type != Tokens.LIMIT) {
                instructions();
            }
        }
        catch(StackOverflowError e) {}
    }
    /*instruction::=set | regex*/
    void instruction() {
        token = getToken(0);
        if(token != null) {
            if(token.type == Tokens.RW_CONJ) {
                set();
                return;
            }
            if(token.type == Tokens.ID) {
                regex();
                return;
            }
        }
    }
    /*RW_CONJ COLON ID PROMPT elements SEMICOLON*/
    void set() {
        queue = new LinkedList<Token>();
        toQueue();
        token = popTokenQueue();
        if(token != null && token.type == Tokens.RW_CONJ) {
            painter.RW(token.yychar,token.yylength);
            token = popTokenQueue();
            if(token != null && token.type == Tokens.COLON) {
                token = popTokenQueue();
                if(token != null && token.type == Tokens.ID) {
                    painter.VARIABLE(token.yychar,token.yylength);
                    token = popTokenQueue();
                    if(token != null && token.type == Tokens.PROMPT) {
                        token = getTokenQueue();
                        if(token != null && (token.type == Tokens.CHAR || characters(token.type))) {
                            elements();
                            token = popToken(0);
                            if(token != null && token.type == Tokens.SEMICOLON) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        emptyQueue();
        token = getToken(0);
        if(token != null && token.type == Tokens.SEMICOLON) {
            popToken(0);
            return;
        }
    }
    /*elements::=CHAR TILDE CHAR | specific*/
    void elements() {
        token = getTokenQueue();
        if(token != null && (token.type == Tokens.CHAR || characters(token.type))) {
            token = getTokenQueue(1);
            if(token != null) {
                if(token.type == Tokens.TILDE) {
                    token = popTokenQueue();
                    painter.CHARACTER(token.yychar,token.yylength);
                    popTokenQueue();
                    token = popTokenQueue();
                    if(token != null && (token.type == Tokens.CHAR || characters(token.type))) {
                        painter.CHARACTER(token.yychar,token.yylength);
                        return;
                    }
                    return;
                }
                if(token.type == Tokens.COMMA) {
                    specific();
                    return;
                }
            }
            token = popTokenQueue();
            painter.CHARACTER(token.yychar,token.yylength);
            return;
        }
    }
    /*specific::=CHAR COMMA specific | CHAR*/
    void specific() {
        token = popTokenQueue();
        if(token != null && (token.type == Tokens.CHAR || characters(token.type))) {
            painter.CHARACTER(token.yychar,token.yylength);
            token = popTokenQueue();
            if(token != null && token.type == Tokens.COMMA) {
                specific();
                return;
            }
            return;
        }
    }
    /*character::=CHAR | LBRACKET | RBRACKET | SEMICOLON | COLON | COMMA | OR | POSITIVE | KLEENE | CONCAT*/
    boolean characters(Tokens type) {
        for(characters token : characters.values()) {
            if(token == characters.valueOf(String.valueOf(type))) return true;
        }
        return false;
    }
    /*regex::=ID PROMPT operations SEMICOLON*/
    void regex() {
        queue = new LinkedList<Token>();
        toQueue();
        token = popTokenQueue();
        if(token != null && token.type == Tokens.ID) {
            painter.REGEX(token.yychar,token.yylength);
            token = popTokenQueue();
            if(token != null && token.type == Tokens.PROMPT) {
                stack = new Stack<>();
                op = new Stack<>();
                toStack();
                operations();
                token = popToken(0);
                if(token != null && token.type == Tokens.SEMICOLON) {
                    return;
                }
            }
        }
        emptyQueue();
        token = getToken(0);
        if(token != null && token.type == Tokens.SEMICOLON) {
            popToken(0);
            return;
        }
    }
    /*operations::=operations operation | operation*/
    void operations() {
        operation();
    }
    /*operation::=CONCAT operation operation | OR operation operation | KLEENE operation | POSITIVE operation | node*/
    void operation() {
        token = getTokenStack();
        if(token != null) {
            if(token.type == Tokens.RBRACKET || token.type == Tokens.STRING) {
                node();
                operation();
                return;
            }
            if(token.type == Tokens.OR && op.size() == 2) {
                painter.OPERATOR(token.yychar,token.yylength);
                op.pop();
                op.pop();
                op.push(token);
                popTokenStack();
                operation();
                return;
            }
            if(token.type == Tokens.CONCAT && op.size() == 2) {
                painter.OPERATOR(token.yychar,token.yylength);
                op.pop();
                op.pop();
                op.push(token);
                popTokenStack();
                operation();
                return;
            }
            if(token.type == Tokens.KLEENE && op.size() == 1) {
                painter.OPERATOR(token.yychar,token.yylength);
                op.pop();
                op.push(token);
                popTokenStack();
                operation();
                return;
            }
            if(token.type == Tokens.POSITIVE && op.size() == 1) {
                painter.OPERATOR(token.yychar,token.yylength);
                op.pop();
                op.push(token);
                popTokenStack();
                operation();
                return;
            }
        }
    }
    /*node::=LBRACKET ID RBRACKET | STRING*/
    void node() {
        token = popTokenStack();
        if(token != null) {
            if(token.type == Tokens.RBRACKET) {
                token = popTokenStack();
                if(token != null && token.type == Tokens.ID) {
                    painter.VARIABLEUSE(token.yychar,token.yylength);
                    op.push(token);
                    token = popTokenStack();
                    if(token != null && token.type == Tokens.LBRACKET) {
                        return;
                    }
                    return;
                }
            }
            if(token.type == Tokens.STRING) {
                painter.STRING(token.yychar,token.yylength);
                op.push(token);
                return;
            }
        }
    }
    /*analysis::=expresion analysis | expresion*/
    void analysis() {
        expresion();
        try {
            token = getToken(0);
            if(token != null && token.type != Tokens.RBRACKET) {
                analysis();
            }
        }
        catch(StackOverflowError e) {}
    }
    /*expresion::=ID COLON STRING SEMICOLON*/
    void expresion() {
        queue = new LinkedList<Token>();
        toQueue();
        token = popTokenQueue();
        if(token != null && token.type == Tokens.ID) {
            painter.REGEXUSE(token.yychar,token.yylength);
            token = popTokenQueue();
            if(token != null && token.type == Tokens.COLON) {
                token = popTokenQueue();
                if(token != null && token.type == Tokens.STRING) {
                    painter.STRING(token.yychar,token.yylength);
                    token = popToken(0);
                    if(token != null && token.type == Tokens.SEMICOLON) {
                        return;
                    }
                }
            }
        }
        emptyQueue();
        token = getToken(0);
        if(token != null && token.type == Tokens.SEMICOLON) {
            popToken(0);
            return;
        }
    }
    void toStack() {
        token = getTokenQueue();
        if(queue.size() > 0) {
            stack.push(token);
            popTokenQueue();
            toStack();
        }
    }
    void emptyQueue() {
        while(!queue.isEmpty()) {
            popTokenQueue();
        }
    }
    void toQueue() {
        token = getToken(0);
        if(token.type != Tokens.SEMICOLON) {
            queue.add(token);
            popToken(0);
            toQueue();
        }
    }
    Token getToken(int index) {
        try{return code.get(index);}
        catch(Exception e) {return null;}
    }
    Token popToken(int index) {
        try{return code.remove(index);}
        catch(Exception e) {return null;}
    }
    Token getTokenStack() {
        try {return stack.get(stack.size() - 1);}
        catch(Exception e) {return null;}
    }
    Token popTokenStack() {
        try {return stack.pop();}
        catch(Exception e) {return null;}
    }
    Token getTokenQueue() {
        return queue.peek();
    }
    Token getTokenQueue(int index) {
        try {
            Iterator<Token> iterator = queue.iterator();
            for(int i = 0; i < index; i ++) {
                iterator.next();
            }
            return iterator.next();
        }
        catch(Exception e) {return null;}
    }
    Token popTokenQueue() {
        return queue.poll();
    }
}