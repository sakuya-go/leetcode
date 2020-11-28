package com.sakuya.leetcode.compiler.lexer;

import com.sakuya.leetcode.week05.Token;
import com.sakuya.leetcode.week05.TokenReader;
import com.sakuya.leetcode.week05.TokenType;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleLexer {

    public static void main(String[] args) {
        SimpleLexer lexer = new SimpleLexer();
        SimpleTokenReader reader = lexer.tokenize("int a = 321");
        lexer.dump(reader);
    }

    private List<Token> tokens;
    private StringBuilder tokenText;
    private SimpleToken token;

    //是否是字母
    private boolean isAlpha(int ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    //是否是数字
    private boolean isDigit(int ch) {
        return ch >= '0' && ch <= '9';
    }

    //是否是空白字符
    private boolean isBlank(int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

    public SimpleTokenReader tokenize(String script) {
        tokens = new ArrayList<>();
        tokenText = new StringBuilder();
        token = new SimpleToken();
        CharArrayReader reader = new CharArrayReader(script.toCharArray());
        int ich;
        char ch = 0;
        DfaState dfaState = DfaState.Initial;
        try {
            while ((ich = reader.read()) != -1) {
                ch = (char) ich;
                switch (dfaState) {
                    case Initial:
                    case Assignment:
                        dfaState = initToken(ch);
                        break;
                    case Id_int1:
                        if (ch == 'n') {
                            dfaState = DfaState.Id_int2;
                            tokenText.append(ch);
                        } else if (isAlpha(ch) || isDigit(ch)) {
                            dfaState = DfaState.Id;
                            tokenText.append(ch);
                        } else {
                            dfaState = initToken(ch);
                        }
                        break;
                    case Id_int2:
                        if (ch == 't') {
                            dfaState = DfaState.Id_int3;
                            tokenText.append(ch);
                        } else if (isAlpha(ch) || isDigit(ch)) {
                            dfaState = DfaState.Id;
                            tokenText.append(ch);
                        } else {
                            dfaState = initToken(ch);
                        }
                        break;
                    case Id_int3:
                        if (isBlank(ch)) {
                            token.type = TokenType.Int;
                            dfaState = initToken(ch);
                        } else {
                            dfaState = DfaState.Id;
                            tokenText.append(ch);
                        }
                        break;
                    case Id:
                        if (isAlpha(ch) || isDigit(ch)) {
                            tokenText.append(ch);
                        } else {
                            token.type = TokenType.Identifier;
                            dfaState = initToken(ch);
                        }
                        break;
                    case IntLiteral:
                        if (isDigit(ch)) {
                            tokenText.append(ch);
                        } else {
                            dfaState = initToken(ch);
                        }
                        break;
                }
            }
            // 把最后一个token送进去
            if (tokenText.length() > 0) {
                initToken(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleTokenReader(tokens);
    }

    private DfaState initToken(char ch) {
        if (tokenText.length() > 0) {
            token.text = tokenText.toString();
            tokens.add(token);
            token = new SimpleToken();
            tokenText = new StringBuilder();
        }
        DfaState newState = DfaState.Initial;
        if (ch == '=') {
            newState = DfaState.Assignment;
            token.type = TokenType.Assignment;
            tokenText.append(ch);
        } else if (isDigit(ch)) {
            newState = DfaState.IntLiteral;
            token.type = TokenType.IntLiteral;
            tokenText.append(ch);
        } else if (isAlpha(ch)) {
            if (ch == 'i') {
                newState = DfaState.Id_int1;
            } else {
                newState = DfaState.Id;
                token.type = TokenType.Identifier;
            }
            tokenText.append(ch);
        }
        return newState;
    }

    private void dump(SimpleTokenReader reader) {
        Token token;
        while ((token = reader.read()) != null) {
            System.out.println(token.getText() + "\t\t" + token.getType());
        }
    }

    /**
     * 有限状态机的各种状态。
     */
    private enum DfaState {
        Initial,

        Id_int1, Id_int2, Id_int3, Id,

        Assignment,

        IntLiteral
    }

    private static class SimpleTokenReader implements TokenReader {

        private List<Token> tokens;
        private int pos;

        public SimpleTokenReader(List<Token> tokens) {
            this.tokens = tokens;
            this.pos = 0;
        }

        @Override
        public Token read() {
            if (pos < tokens.size()) {
                return tokens.get(pos++);
            }
            return null;
        }

        @Override
        public Token peek() {
            if (pos < tokens.size()) {
                return tokens.get(pos);
            }
            return null;
        }

        @Override
        public void unread() {
            if (pos > 0) pos--;
        }

        @Override
        public int getPosition() {
            return pos;
        }

        @Override
        public void setPosition(int position) {
            if (pos >= 0 && pos < tokens.size()) {
                pos = position;
            }
        }
    }

    private static final class SimpleToken implements Token {
        private TokenType type;
        private String text;

        @Override
        public TokenType getType() {
            return type;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
