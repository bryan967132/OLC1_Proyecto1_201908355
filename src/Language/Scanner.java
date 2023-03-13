/* The following code was generated by JFlex 1.7.0 */

/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Colors.Type;
import Components.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>src/Language/Scanner.jflex</tt>
 */
public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  7,  9,  9,  8,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     1, 13,  5, 10, 10, 32, 10, 30, 10, 10, 26, 25, 23, 33, 28, 11, 
     3,  3,  3,  3,  3,  3,  3,  3,  3,  3, 22, 21, 12, 10, 14, 27, 
    10,  2,  2, 15,  2,  2,  2,  2,  2,  2, 18,  2,  2,  2, 17, 16, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2, 10,  6, 10, 10,  4, 
    10,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2, 31,  2, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2, 19, 24, 20, 29,  0, 
     0,  0,  0,  0,  0,  9,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    34,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\3\3\1\5\3\3"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\2\3\1\21\1\0\1\22\1\0"+
    "\1\23\1\24\1\25\1\2\1\0\1\21\1\26\1\0"+
    "\1\27\1\0\1\21\1\2\1\30";

  private static int [] zzUnpackAction() {
    int [] result = new int[42];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\43\0\106\0\151\0\43\0\43\0\214\0\257"+
    "\0\43\0\322\0\365\0\u0118\0\43\0\43\0\43\0\43"+
    "\0\43\0\43\0\43\0\43\0\43\0\43\0\43\0\u013b"+
    "\0\u015e\0\151\0\214\0\43\0\u0181\0\43\0\43\0\43"+
    "\0\u01a4\0\u01c7\0\u01ea\0\43\0\u015e\0\43\0\u020d\0\u0230"+
    "\0\43\0\151";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[42];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\3\1\0\1\6\1\12\1\13\2\6\1\14\3\4"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\6\1\4\1\30\1\31\1\2"+
    "\44\0\1\3\6\0\1\3\34\0\3\32\12\0\4\32"+
    "\14\0\1\32\3\0\5\33\1\34\1\35\34\33\5\0"+
    "\1\36\30\0\1\37\1\40\16\0\1\41\44\0\1\42"+
    "\27\0\3\32\12\0\1\32\1\43\2\32\14\0\1\32"+
    "\43\0\1\44\3\0\1\45\5\0\3\45\4\0\1\46"+
    "\23\0\1\45\7\33\3\0\31\33\7\41\2\0\32\41"+
    "\14\42\1\0\1\47\1\0\24\42\2\0\3\32\12\0"+
    "\2\32\1\50\1\32\14\0\1\32\21\0\1\51\26\0"+
    "\3\32\12\0\3\32\1\52\14\0\1\32\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[595];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\2\11\2\1\1\11\3\1\13\11"+
    "\3\1\1\0\1\11\1\0\3\11\1\1\1\0\1\1"+
    "\1\11\1\0\1\11\1\0\1\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[42];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    ArrayList<Token> tokens = new ArrayList<>();
    ArrayList<ErrorL> errors = new ArrayList<>();
    ArrayList<String> operations = new ArrayList<>();
    String operation = "";
    public void printTokens() {
        System.out.println("TOKENS");
        System.out.printf("%-25s%-6s%-8s%-10s\n",
            "Lexeme",
            "Line",
            "Column",
            "Type"
        );
        tokens.forEach(
            token -> {
                token.print();
            }
        );
    }
    public void printErrors() {
        System.out.println("ERRORS");
        System.out.printf("%-6s%-8s%-10s\n",
            "Line",
            "Column",
            "Description"
        );
        errors.forEach(
            error -> {
                error.print();
            }
        );
    }
    public boolean thereAreTokens() {
        if(tokens.size() > 0) {
            return true;
        }
        return false;
    }
    public String getStrTokens() {
        String tokensTab = "TOKENS\n";
        tokensTab += "Lexeme                   Line  Column  Type\n";
        if(tokens.size() > 0) {
            for(int i = 0; i < tokens.size(); i ++) {
                tokensTab += tokens.get(i) + "\n";
            }
        }
        else tokensTab += "No Tokens\n";
        return tokensTab + "\n";
    }
    public String getStrErrors() {
        String errorsTab = "ERRORS\n";
        errorsTab += "Line  Column  Description\n";
        if(errors.size() > 0) {
            for(int i = 0; i < errors.size(); i ++) {
                errorsTab += errors.get(i) + "\n";
            }
        }
        else errorsTab += "No Lexical Errors\n";
        return errorsTab + "\n";
    }
    void concat(String token) {
        operation += token;
    }
    void addOperation() {
        if(!operation.equals("")) {
            if(operation.charAt(0) == ' ') {
                operation = new StringBuilder(operation).deleteCharAt(0).toString();
            }
            operations.add(operation);
            operation = "";
        }
    }
    void addToken(String lexeme,int line,int column,Type type) {
        tokens.add(new Token(lexeme,line,column,type));
    }
    void addError(int line,int column,String character) {
        errors.add(new ErrorL(line,column,character));
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
      yyline = 1;
    yychar = 1;
    this.zzReader = in;
  }



  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(Sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { addError(yyline,yychar,yytext());
            } 
            // fall through
          case 25: break;
          case 2: 
            { 
            } 
            // fall through
          case 26: break;
          case 3: 
            { addToken(yytext(),yyline,yychar,Type.CHAR);            return new Symbol(Sym.CHAR,yyline,yychar,yytext());
            } 
            // fall through
          case 27: break;
          case 4: 
            { addToken(yytext(),yyline,yychar,Type.CHARNUM);         return new Symbol(Sym.CHARNUM,yyline,yychar,yytext());
            } 
            // fall through
          case 28: break;
          case 5: 
            { yychar = 1;
            } 
            // fall through
          case 29: break;
          case 6: 
            { addToken(yytext(),yyline,yychar,Type.LBRACKET);        return new Symbol(Sym.LBRACKET,yyline,yychar,yytext());
            } 
            // fall through
          case 30: break;
          case 7: 
            { addToken(yytext(),yyline,yychar,Type.RBRACKET);        return new Symbol(Sym.RBRACKET,yyline,yychar,yytext());
            } 
            // fall through
          case 31: break;
          case 8: 
            { addToken(yytext(),yyline,yychar,Type.SEMICOLON);       return new Symbol(Sym.SEMICOLON,yyline,yychar,yytext());
            } 
            // fall through
          case 32: break;
          case 9: 
            { addToken(yytext(),yyline,yychar,Type.COLON);           return new Symbol(Sym.COLON,yyline,yychar,yytext());
            } 
            // fall through
          case 33: break;
          case 10: 
            { addToken(yytext(),yyline,yychar,Type.COMMA);           return new Symbol(Sym.COMMA,yyline,yychar,yytext());
            } 
            // fall through
          case 34: break;
          case 11: 
            { addToken(yytext(),yyline,yychar,Type.OR);              return new Symbol(Sym.OR,yyline,yychar,yytext());
            } 
            // fall through
          case 35: break;
          case 12: 
            { addToken(yytext(),yyline,yychar,Type.POSITIVE);        return new Symbol(Sym.POSITIVE,yyline,yychar,yytext());
            } 
            // fall through
          case 36: break;
          case 13: 
            { addToken(yytext(),yyline,yychar,Type.KLEENE);          return new Symbol(Sym.KLEENE,yyline,yychar,yytext());
            } 
            // fall through
          case 37: break;
          case 14: 
            { addToken(yytext(),yyline,yychar,Type.OPTIONAL);        return new Symbol(Sym.OPTIONAL,yyline,yychar,yytext());
            } 
            // fall through
          case 38: break;
          case 15: 
            { addToken(yytext(),yyline,yychar,Type.CONCAT);          return new Symbol(Sym.CONCAT,yyline,yychar,yytext());
            } 
            // fall through
          case 39: break;
          case 16: 
            { addToken(yytext(),yyline,yychar,Type.TILDE);           return new Symbol(Sym.TILDE,yyline,yychar,yytext());
            } 
            // fall through
          case 40: break;
          case 17: 
            { addToken(yytext(),yyline,yychar,Type.ID);              return new Symbol(Sym.ID,yyline,yychar,yytext());
            } 
            // fall through
          case 41: break;
          case 18: 
            { addToken(yytext(),yyline,yychar,Type.STRING);          return new Symbol(Sym.STRING,yyline,yychar,yytext());
            } 
            // fall through
          case 42: break;
          case 19: 
            { addToken(yytext(),yyline,yychar,Type.DOUBLEQUOTE);     return new Symbol(Sym.DOUBLEQUOTE,yyline,yychar,yytext());
            } 
            // fall through
          case 43: break;
          case 20: 
            { addToken(yytext(),yyline,yychar,Type.SINGLEQUOTE);     return new Symbol(Sym.SINGLEQUOTE,yyline,yychar,yytext());
            } 
            // fall through
          case 44: break;
          case 21: 
            { addToken(yytext(),yyline,yychar,Type.ENTER);           return new Symbol(Sym.ENTER,yyline,yychar,yytext());
            } 
            // fall through
          case 45: break;
          case 22: 
            { addToken(yytext(),yyline,yychar,Type.LIMIT);           return new Symbol(Sym.LIMIT,yyline,yychar,yytext());
            } 
            // fall through
          case 46: break;
          case 23: 
            { addToken(yytext(),yyline,yychar,Type.PROMPT);          return new Symbol(Sym.PROMPT,yyline,yychar,yytext());
            } 
            // fall through
          case 47: break;
          case 24: 
            { addToken(yytext(),yyline,yychar,Type.RW_CONJ);         return new Symbol(Sym.RW_CONJ,yyline,yychar,yytext());
            } 
            // fall through
          case 48: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
