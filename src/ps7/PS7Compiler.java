/* PS7Compiler.java */
/* Generated By:JavaCC: Do not edit this line. PS7Compiler.java */
package ps7;
import java.util.HashMap;

public class PS7Compiler implements PS7CompilerConstants {
  // Maps programming language variables to DC registers  private HashMap < String, Character > variables =
        new HashMap < String, Character > ();

  // Maps programming language variables to types.
  private HashMap<String, String > types =
        new HashMap<String, String >();

  // If the t is not "int", throws a TypeException
  public void checkInt (String type)
  {
    if (!(type.equals("int")))
    {
      throw new TypeException("Expected int, received " + type);
    }
  }
  //if(!(type.equals("int") && !(type.equals("boolean"))))
  public void checkBoolInt(String type)
  {
        if(type.equals("string"))
        {
                throw new TypeException("Expected int or boolean, received " + type);
        }
  }


  public static void main(String args [])
  {
    PS7Compiler parser = new PS7Compiler(System.in);
    try
    {
      parser.program();
    }
    catch (TypeException e)
    {
      System.err.println(e.getMessage());
    }
    catch (ParseException e)
    {
      System.err.println(e.getMessage());
    }
    catch (TokenMgrError e)
    {
      System.err.println(e.getMessage());
    }
    catch (RuntimeException e)
    {
      System.err.println(e.getMessage());
    }
  }

/***************************** Productions that define grammar *******************/
  final public void program() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTTYPE:
      case STRINGTYPE:
      case BOOLEANTYPE:
      case PRINT:
      case NEWLINE:
      case VAR:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      statement();
    }
  }

  final public void statement() throws ParseException {Token t;
  Token type;
  String etype;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VAR:{
      t = jj_consume_token(VAR);
      jj_consume_token(EQUALS);
      etype = Exp();
      jj_consume_token(24);
if (variables.get(t.image) == null)
      {
          {if (true) throw new TypeException("Undeclared variable: " + t.image);}
      }

      if (!types.get(t.image).equals(etype))
          {
                  {if (true) throw new TypeException("Type mismatch");}
      }

      char reg = variables.get(t.image);
      System.out.println("s" + reg + " ");
      break;
      }
    case INTTYPE:
    case STRINGTYPE:
    case BOOLEANTYPE:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTTYPE:{
        type = jj_consume_token(INTTYPE);
        break;
        }
      case STRINGTYPE:{
        type = jj_consume_token(STRINGTYPE);
        break;
        }
      case BOOLEANTYPE:{
        type = jj_consume_token(BOOLEANTYPE);
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t = jj_consume_token(VAR);
      jj_consume_token(EQUALS);
      etype = Exp();
      jj_consume_token(24);
if (variables.get(t.image) == null)
      {
        if (variables.size() >= 26)
        {
          {if (true) throw new TypeException("Too many variables; limit is 26");}
        }
        variables.put(t.image, (char) ('A' + variables.size()));
                types.put(t.image, type.image);

                if (!type.image.equals(etype))
                {
                  {if (true) throw new TypeException("Type mismatch: Expected " + type + " instead of "+ etype);}
                }

                char reg2 = variables.get(t.image);
        System.out.println("s" + reg2 + " ");
      }
      else
      {
        {if (true) throw new TypeException("Variable already declared: " + t.image);}
      }
      break;
      }
    case PRINT:{
      jj_consume_token(PRINT);
      Exp();
      jj_consume_token(24);
System.out.println("n ");
      break;
      }
    case NEWLINE:{
      jj_consume_token(NEWLINE);
      jj_consume_token(24);
System.out.print("[] n");
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public String Exp() throws ParseException {Token t;
  String type7, type8;
    type7 = exp();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case GTHEN:
      case LTHEN:
      case CMPEQUALS:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LTHEN:{
        t = jj_consume_token(LTHEN);
        type8 = exp();
System.out.print("sa sb [false] la lb [sa[true]] sa <a ");
                checkInt(type7);
                checkInt(type8);
                {if ("" != null) return "boolean";}
        break;
        }
      case GTHEN:{
        t = jj_consume_token(GTHEN);
        type8 = exp();
System.out.print("sa sb [false] la lb [sa[true]] sa >a ");
                checkInt(type7);
                checkInt(type8);
                {if ("" != null) return "boolean";}
        break;
        }
      case CMPEQUALS:{
        t = jj_consume_token(CMPEQUALS);
        type8 = exp();
System.out.print("sa sb [false] la lb [sa[true]] sa =a ");
                checkBoolInt(type7);
                checkBoolInt(type8);
                {if ("" != null) return "boolean";}
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return type7;}
    throw new Error("Missing return statement in function");
  }

  final public String exp() throws ParseException {Token t;
  String type5, type6;
    type5 = term();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        t = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        t = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      type6 = term();
System.out.print(t.image + " ");
      checkInt(type5);
          checkInt(type6);
    }
{if ("" != null) return type5;}
    throw new Error("Missing return statement in function");
  }

  final public String term() throws ParseException {Token t;
  String type3, type4;
    type3 = factor();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MULTIPLY:
      case DIVIDE:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MULTIPLY:{
        t = jj_consume_token(MULTIPLY);
        break;
        }
      case DIVIDE:{
        t = jj_consume_token(DIVIDE);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      type4 = factor();
System.out.print(t.image + " ");
      checkInt(type3);
      checkInt(type4);
    }
{if ("" != null) return type3;}
    throw new Error("Missing return statement in function");
  }

  final public String factor() throws ParseException {String type2;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MINUS:{
      jj_consume_token(MINUS);
System.out.print("_1 * ");
      type2 = element();
checkInt(type2);
    {if ("" != null) return "int";}
      break;
      }
    case BOOLEAN:
    case CONSTANT:
    case VAR:
    case STRING:
    case 25:{
      type2 = element();
{if ("" != null) return type2;}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String element() throws ParseException {Token t;
  String type1;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case CONSTANT:{
      t = jj_consume_token(CONSTANT);
System.out.print(t.image + " ");
    {if ("" != null) return "int";}
      break;
      }
    case STRING:{
      t = jj_consume_token(STRING);
String dcs = t.image.replaceFirst("\u005c"", "[");
    dcs = dcs.replaceFirst("\u005c"", "]");
        System.out.println(dcs);
        {if ("" != null) return "string";}
      break;
      }
    case BOOLEAN:{
      t = jj_consume_token(BOOLEAN);
int bool = t.image.equals("true") ? 1 : 0;

          System.out.print(bool + " sa [true] la 0 [sa [false]] sa =a ");
          {if ("" != null) return "boolean";}
      break;
      }
    case VAR:{
      t = jj_consume_token(VAR);
Character reg = variables.get(t.image);
    if (reg == null)
    {
      {if (true) throw new RuntimeException("Undefined variable " + t.image);}
    }
    else
    {
      System.out.print("l" + reg + " ");
      {if ("" != null) return types.get(t.image);}
    }
      break;
      }
    case 25:{
      jj_consume_token(25);
      type1 = exp();
      jj_consume_token(26);
{if ("" != null) return type1;}
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public PS7CompilerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x26e000,0xe000,0x26e000,0x1c00,0x1c00,0xc0,0xc0,0x300,0x300,0x2a90080,0x2a90000,};
   }

  /** Constructor with InputStream. */
  public PS7Compiler(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public PS7Compiler(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new PS7CompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public PS7Compiler(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new PS7CompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public PS7Compiler(PS7CompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(PS7CompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[27];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 11; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 27; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}

/**
 * Exception used to report type-checking errors.
 */
 class TypeException extends RuntimeException
 {
   public TypeException (String message)
   {
     super(message);
   }
 }
