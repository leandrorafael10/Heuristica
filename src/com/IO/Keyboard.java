package com.IO;

/* As seguintes linhas permitem o uso das classes de entrada e saída e de processamento
de tokens */
import java.io.*;
import java.util.*;

/**
* A classe <tt>Keyboard</tt> permite a leitura de dados de tipos nativos e de
* instâncias da classe <tt>String</tt> a partir do teclado. Basicamente esta classe 
* encapsula o funcionamento do Stream <tt>System.in</tt>, que pode ser usado como 
* entrada padrão de dados.
* <p>                         
* Esta classe é baseada na classe <tt>EasyIn</tt>, de Peter van der Linden. As
* principais modificações foram:
* <ul>
* <li>Os métodos são estáticos para facilitar o uso da classe
* <li>Criação de métodos sobrecarregados para que valores <i>default</i> possam ser 
*     usados
* <li>Melhor tratamento das exceções
* <li>Reconhecimento de vários valores como booleanos e capacidade de leitura de 
*     <tt>NaNs</tt> e infinitos para valores dos tipos <tt>float</tt> e 
*     <tt>double</tt>
* <li>Descrição dos detalhes de implementação com comentários
* </ul>                        
* A classe permite o uso de arquivos de respostas (<i>"answer files"</i>), que são 
* arquivos contendo os dados que deveriam ser entrados pelo teclado. A entrada destes 
* dados pode ser simulada através de redirecionamento de arquivos. Se, por exemplo, 
* tivermos uma aplicação (classe) <tt>DemoDataKeyboard</tt> e quisermos usar um arquivo 
* de respostas chamado <tt>dados.dat</tt> que já contém os dados que devem ser 
* digitados, podemos executar o comando <tt>java DemoDataKeyboard &lt; dados.dat </tt>
* para ler os dados do arquivo em vez do teclado. O comando será o mesmo para 
* os sistemas operacionais Windows e Linux.
* <p>
* O suporte a arquivos de respostas é limitado, somente funcionará se todos os dados
* a serem digitados forem entrados no arquivo de respostas, um em cada linha. 
* Exceções ocorrerão caso estas regras não sejam seguidas.
* <p>
* A mensagem original de copyright de Peter van de Linden é mostrada ao final desta
* listagem. Vale a pena notar que a sintaxe proposta e mostrada na mensagem é 
* diferente da usada pela classe <tt>Keyboard</tt>.
* <p>                   
* O livro <b>"An Introduction to Computer Science Using Java"</b>, de Samuel N. Kamin,
* M. Dennis Mickunas e Edward M. Reingold (editora McGraw-Hill, ISBN 0-07-034224-5)
* também apresenta uma classe <tt>Keyboard</tt>, cujo funcionamento e código são 
* diferentes desta.
*   
* @author Rafael Santos
* @version 1.2
*/
public class Keyboard
{
/**
* Declaração dos campos privados desta classe.
*/
// O campo is é uma instância da classe InputStreamReader que será construída a 
// partir do stream System.in. Note que já que esta classe só tem métodos estáticos,
// não terá construtor, mas podemos inicializar instâncias de classes da forma mostrada 
// abaixo.
private static InputStreamReader is = new InputStreamReader( System.in );
// O campo br é uma instância da classe BufferedReader que usa 
// is como argumento para seu construtor.
private static BufferedReader br = new BufferedReader( is );
// O campo st é uma instância da classe StringTokenizer que será 
// usada em vários métodos nesta classe.
private static StringTokenizer st;
// O campo nt é uma instância da classe String que será usada em 
// vários métodos nesta classe.
private static String nt;
// O campo debug estabelece se mensagens de erro de conversão e leitura 
// devem ser mostradas ou não. O campo não pode ser modificado diretamente, 
// somente através de métodos específicos descritos abaixo.
private static boolean debug = false;

/**
* O método <tt>getToken</tt> lê uma string do <tt>BufferedReader</tt> associado com 
* a entrada padrão e retorna uma instância de <tt>StringTokenizer</tt> contendo os 
* <i>tokens</i> criados a partir da linha lida. O método é declarado como 
* <tt>private</tt> pois só deverá ser chamado a partir de outros métodos da classe.
* @return uma instância de <tt>StringTokenizer</tt> contendo os <i>tokens</i> 
*         obtidos da linha lida
* @exception IOException se um erro de entrada e saída ocorrer.
* @exception NullPointerException se uma string vazia for lida e tentarmos construir
*                                 uma instância de <tt>StringTokenizer</tt> com ela
* @see java.util.StringTokenizer <tt>StringTokenizer</tt>
*/
private static StringTokenizer getToken() throws IOException, NullPointerException 
 {
 String s = br.readLine();
 return new StringTokenizer(s);
 } // fim do método getToken

/**
* O método <tt>readBoolean</tt> lê e retorna um valor do tipo <tt>boolean</tt>. 
* Este método simplesmente chama o método <tt>readBoolean</tt> com argumentos, 
* descrito abaixo, considerando o valor <i>default</i> como sendo <tt>true</tt>.
* @return o valor do tipo boolean lido (ou, em caso de erro de leitura, 
*         <tt>true</tt>)
*/
public static boolean readBoolean() 
 {
 return readBoolean(true);
 }

/**
* O método <tt>readBoolean</tt> lê e retorna um valor do tipo <tt>boolean</tt>. Este 
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e o compara
*  com algumas strings constantes para verificar a igualdade, retornando <tt>true</tt> 
* ou <tt>false</tt> dependendo do resultado da comparação.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e decodificar
*                     um valor booleano
* @return o valor do tipo <tt>boolean</tt> lido (ou, em caso de erro de leitura, igual ao 
*         argumento passado para o método)
*/
public static boolean readBoolean(boolean defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // Retorna true se a string for igual a...
   if (nt.equalsIgnoreCase("true") ||        // true
       nt.equalsIgnoreCase("t") ||           // t
       nt.equalsIgnoreCase("yes") ||         // yes
       nt.equalsIgnoreCase("y") ||           // y
       nt.equalsIgnoreCase("on") ||          // on
       nt.equalsIgnoreCase("v") ||           // v
       nt.equalsIgnoreCase("s") ||           // s
       nt.equalsIgnoreCase("sim") ||         // sim
       nt.equalsIgnoreCase("verdadeiro"))    // verdadeiro
       return true; 
   // Retorna false se a string for igual a...
   else if (nt.equalsIgnoreCase("false") ||  // false
            nt.equalsIgnoreCase("f") ||      // f
            nt.equalsIgnoreCase("no") ||     // no
            nt.equalsIgnoreCase("n") ||      // n
            nt.equalsIgnoreCase("off") ||    // off
            nt.equalsIgnoreCase("nao") ||    // nao
            nt.equalsIgnoreCase("não") ||    // não
            nt.equalsIgnoreCase("falso"))    // falso
       return false; 
   // Se não for nenhum dos valores reconhecidos, retorna o default
   else return defaultvalue;
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um boolean. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada n�o contem um boolean. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readBoolean

/**
* O método <tt>readByte</tt> lê e retorna um valor do tipo <tt>byte</tt>. Este 
* método simplesmente chama o método <tt>readByte</tt> com argumentos, descrito 
* abaixo, considerando o valor <i>default</i> como sendo <tt>(byte)0</tt>.
* @return o valor do tipo <tt>byte</tt> lido (ou, em caso de erro de leitura, 
*         <tt>(byte)0</tt>)
*/
public static byte readByte() 
 {
 return readByte((byte)0);
 } // fim do método readByte

/**
* O método <tt>readByte</tt> lê e retorna um valor do tipo <tt>byte</tt>. Este 
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e o 
* passa como argumento para o método <tt>parseByte</tt> da classe <tt>Byte</tt>, 
* que tenta convertê-lo. Se a leitura e conversão puderem ser feitas, um valor
* do tipo <tt>byte</tt> é retornado, caso contrário o valor <i>default</i> 
* (passado como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e 
*                     decodificar um valor do tipo <tt>byte</tt>
* @return o valor do tipo <tt>byte</tt> lido (ou, em caso de erro de leitura, 
*         igual ao argumento passado para o método)
*/
public static byte readByte(byte defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // retorna o valor processado pela classe Byte
   return Byte.parseByte(nt);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um byte. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NumberFormatException nfe) // se houver algum erro de conversão
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de convers�o de "+nt+" para um byte. "+
                        "Retorna "+defaultvalue);
   return defaultvalue;
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada n�o cont�m um byte. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readByte

/**
* O método <tt>readShort</tt> lê e retorna um valor do tipo <tt>short</tt>. 
* Este método simplesmente chama o método <tt>readShort</tt> com argumentos, 
* descrito abaixo, considerando o valor <i>default</i> como sendo <tt>(short)0</tt>.
* @return o valor do tipo <tt>short</tt> lido (ou, em caso de erro de leitura, 
*         <tt>(short)0</tt>)
*/
public static short readShort() 
 {
 return readShort((short)0);
 } // fim do método readShort

/**
* O método <tt>readShort</tt> lê e retorna um valor do tipo <tt>short</tt>. Este 
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e o passa 
* como argumento para o método <tt>parseShort</tt> da classe <tt>Short</tt>, que 
* tenta convertê-lo. Se a leitura e conversão puderem ser feitas, um valor do tipo 
* <tt>short</tt> é retornado, caso contrário o valor <i>default</i> (passado 
* como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e 
*                     decodificar um valor do tipo short
* @return o valor do tipo <tt>short</tt> lido (ou, em caso de erro de leitura, 
*         igual ao argumento passado para o método)
*/
public static short readShort(short defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // retorna o valor processado pela classe Short
   return Short.parseShort(nt);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo um short. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NumberFormatException nfe) // se houver algum erro de conversão
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de conversão de "+nt+" para um short. "+
                        "Retorna "+defaultvalue);
   return defaultvalue;
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada não contém um short. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readShort

/**
* O método <tt>readInt</tt> lê e retorna um valor do tipo <tt>int</tt>. Este
* método simplesmente chama o método <tt>readInt</tt> com argumentos, descrito 
* abaixo, considerando o valor <i>default</i> como sendo <tt>0</tt>.
* @return o valor do tipo <tt>int</tt> lido (ou, em caso de erro de leitura,
*         </tt>0</tt>)
*/
public static int readInt() 
 {
 return readInt(0);
 } // fim do método readInt

/**
* O método <tt>readInt</tt> lê e retorna um valor do tipo <tt>int</tt>. Este
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e o 
* passa como argumento para o método <tt>parseInt<tt> da classe <tt>Integer</tt>,
* que tenta convertê-lo. Se a leitura e conversão puderem ser feitas, um valor 
* do tipo <tt>int</tt> é retornado, caso contrário o valor <i>default</i> 
* (passado como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e 
*                     decodificar um valor do tipo <tt>int</tt>
* @return o valor do tipo <tt>int</tt> lido (ou, em caso de erro de leitura, 
*         igual ao argumento passado para o método)
*/
public static int readInt(int defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // retorna o valor processado pela classe Integer
   return Integer.parseInt(nt);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo um int. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NumberFormatException nfe) // se houver algum erro de conversão
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de conversão de "+nt+" para um int. "+
                        "Retorna "+defaultvalue);
   return defaultvalue;
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada não contém um int. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readInt

/**
* O método <tt>readLong</tt> lê e retorna um valor do tipo <tt>long</tt>. Este
* método simplesmente chama o método <tt>readLong</tt> com argumentos, descrito
* abaixo, considerando o valor <i>default</i> como sendo <tt>0l</tt>.
* @return o valor do tipo <tt>long</tt> lido (ou, em caso de erro de leitura, 
*         <tt>0l</tt>)
*/
public static long readLong() 
 {
 return readLong(0l);
 } // fim do método readLong

/**
* O método <tt>readLong</tt> lê e retorna um valor do tipo <tt>long</tt>. Este 
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e o 
* passa como argumento para o método <tt>parseLong</tt> da classe <tt>Long</tt>,
* que tenta convertê-lo. Se a leitura e conversão puderem ser feitas, um valor 
* do tipo <tt>long</tt> é retornado, caso contrário o valor <i>default</i> 
* (passado como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e 
*                     decodificar um valor do tipo <tt>long</tt>
* @return o valor do tipo <tt>long</tt> lido (ou, em caso de erro de leitura, 
*         igual ao argumento passado para o método)
*/
public static long readLong(long defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // retorna o valor processado pela classe Long
   return Long.parseLong(nt);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo um long. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NumberFormatException nfe) // se houver algum erro de conversão
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de conversão de "+nt+" para um long. "+
                        "Retorna "+defaultvalue);
   return defaultvalue;
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada não contém um long. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readLong

/**
* O método <tt>readFloat</tt> lê e retorna um valor do tipo <tt>float</tt>. Este 
* método simplesmente chama o método <tt>readFloat</tt> com argumentos, descrito 
* abaixo, considerando o valor <i>default</i> como sendo <tt>0f</tt>.
* @return o valor do tipo <tt>float</tt> lido (ou, em caso de erro de leitura, 
*         <tt>0f</tt>)
*/
public static float readFloat() 
 {
 return readFloat(0f);
 } // fim do método readFloat

/**
* O método <tt>readFloat</tt> lê e retorna um valor do tipo <tt>float</tt>. Este 
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e o 
* analisa, verificando se aparentemente é <tt>NaN</tt> ou infinito. Se não for, 
* passa o valor lido como argumento para o método <tt>parseFloat</tt> da classe 
* <tt>Float</tt>, que tenta convertê-lo. Se a leitura e conversão puderem ser 
* feitas, um valor do tipo <tt>float</tt> é retornado, caso contrário o valor
* <i>default</i> (passado como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e decodificar
*                     um valor do tipo <tt>float</tt>
* @return o valor do tipo <tt>float</tt> lido (ou, em caso de erro de leitura, 
*         igual ao argumento passado para o método)
*/
public static float readFloat(float defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // Verifica se o valor é aparentemente NaN ou infinito
   if (nt.toLowerCase().startsWith("nan")) return Float.NaN;
   else if (nt.toLowerCase().startsWith("inf")) return Float.POSITIVE_INFINITY;
   else if (nt.toLowerCase().startsWith("+inf")) return Float.POSITIVE_INFINITY;
   else if (nt.toLowerCase().startsWith("-inf")) return Float.NEGATIVE_INFINITY;
   // Retorna o valor processado pela classe Float
   else return Float.parseFloat(nt);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo um float. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NumberFormatException nfe) // se houver algum erro de conversão
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de conversão de "+nt+" para um float. "+
                        "Retorna "+defaultvalue);
   return defaultvalue;
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada não contém um float. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readFloat

/**
* O método <tt>readDouble</tt> lê e retorna um valor do tipo <tt>double</tt>. 
* Este método simplesmente chama o método <tt>readDouble</tt> com argumentos, 
* descrito abaixo, considerando o valor <i>default</i> como sendo <tt>0.0</tt>.
* @return o valor do tipo <tt>double</tt> lido (ou, em caso de erro de leitura, 
*         <tt>0.0</tt>)
*/
public static double readDouble() 
 {
 return readDouble(0.0);
 } // fim do método readDouble

/**
* O método <tt>readDouble</tt> lê e retorna um valor do tipo <tt>double</tt>. Este
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão, 
* analisando o valor para ver se é <tt>NaN</tt> ou infinito, e se não for, usa o 
* valor como argumento para o método <tt>parseDouble</tt> da classe <tt>Double</tt>,
* que tenta convertê-lo. Se a leitura e conversão puderem ser feitas, um valor 
* do tipo <tt>double</tt> é retornado, caso contrário o valor <i>default</i> 
* (passado como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler e 
*                     decodificar um valor do tipo <tt>double</tt>
* @return o valor do tipo <tt>double</tt> lido (ou, em caso de erro de leitura, 
*         igual ao argumento passado para o método)
*/
public static double readDouble(double defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // Verifica se o valor é aparentemente NaN ou infinito
   if (nt.toLowerCase().startsWith("nan")) return Double.NaN;
   else if (nt.toLowerCase().startsWith("inf")) return Double.POSITIVE_INFINITY;
   else if (nt.toLowerCase().startsWith("+inf")) return Double.POSITIVE_INFINITY;
   else if (nt.toLowerCase().startsWith("-inf")) return Double.NEGATIVE_INFINITY;
   // Retorna o valor processado pela classe Double
   return Double.parseDouble(nt);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo um double. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NumberFormatException nfe) // se houver algum erro de conversão
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de conversão de "+nt+" para um double. "+
                        "Retorna "+defaultvalue);
   return defaultvalue;
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada não contém um double. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readDouble

/**
* O método <tt>readChar</tt> lê e retorna um valor do tipo <tt>char</tt>. Este
* método simplesmente chama o método <tt>readChar</tt> com argumentos, descrito
* abaixo, considerando o valor <i>default</i> como sendo <tt>' '</tt> (espaço).
* @return o valor do tipo <tt>char</tt> lido (ou, em caso de erro de leitura, 
*         espaço)
*/
public static char readChar() 
 {
 return readChar(' ');
 } // fim do método readChar

/**
* O método <tt>readChar</tt> lê e retorna um valor do tipo <tt>char</tt>. Este 
* método tenta pegar o próximo <i>token</i> a ser lido da entrada padrão e retorna
* o primeiro caractere deste <i>token</i>, ignorando os outros. Se a leitura puder ser 
* feita, um valor do tipo <tt>char</tt> é retornado, caso contrário o valor
* <i>default</i> (passado como argumento) é retornado.
* @param defaultvalue o valor <i>default</i> caso não seja possível ler um valor do tipo
*                     <tt>char</tt>
* @return o valor do tipo <tt>char</tt> lido (ou, em caso de erro de leitura, igual ao 
*         argumento passado para o método)
*/
public static char readChar(char defaultvalue) 
 {
 try 
   {
   st = getToken(); // pega os tokens a partir da linha lida
   nt = st.nextToken(); // e a primeira string do token.
   // retorna o primeiro caractere da string acima
   return nt.charAt(0);
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo um char. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 catch (NoSuchElementException nsee) // se não houver tokens
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Entrada não contém um char. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do metodo readChar

/**
* O método <tt>readString</tt> lê e retorna uma instância da classe <tt>String</tt>.
* Este método simplesmente chama o método <tt>readString</tt> com argumentos, descrito
* abaixo, considerando o valor <i>default</i> como sendo <tt>""</tt> (string vazia).
* @return o valor da string lida (ou, em caso de erro de leitura, uma string
*         vazia)
*/
public static String readString() 
 {
 return readString("");
 } // fim do metodo readString

/**
* O método <tt>readString</tt> lê e retorna uma instância da classe <tt>String</tt>. 
* Este método retorna uma linha inteira lida da entrada padrão, sem processá-la como
* <i>tokens</i>. Se a leitura puder ser feita, a string lida é retornada, caso 
* contrário o valor <i>default</i> (passado como argumento) é retornado. O valor
* <i>default</i> também é retornado no caso de uma string vazia. 
* @param defaultvalue o valor <i>default</i> caso não seja possível ler uma instância
*                     da classe <tt>String</tt>
* @return o valor da string lida (ou, em caso de erro de leitura, igual ao 
*         argumento passado para o método)
*/
public static String readString(String defaultvalue) 
 {
 try 
   {
   nt = br.readLine(); // uma string lida diretamente 
   if (nt.trim().length() == 0) // nada foi entrado, então
     return defaultvalue; // retorna o valor default
   else return nt; // retorna o que foi lido
   }
 catch (IOException ioe) // se houver algum erro de leitura
   {
   if (debug) // se for pedida a impressão de mensagens de erro
     System.err.println("KEYBOARD:: Erro de entrada e saída lendo uma string. "+
                        "Retorna "+defaultvalue);
   return defaultvalue; // retorna o valor default
   }
 } // fim do método readString

/**
* O método <tt>debugOn</tt> modifica o campo que indica que mensagens de erro 
* deverão ser mostradas, fazendo com que sejam mostradas até que o método 
* <tt>debugOff</tt> seja chamado.
*/
public static void debugOn() 
 {
 debug = true;
 System.err.println("KEYBOARD:: Mostrando mensagens de erro e avisos...");
 } // fim do método debugOn

/**
* O método <tt>debugOff</tt> modifica o campo que indica que mensagens de erro 
* deverão ser mostradas, fazendo com que não sejam mostradas até que o método 
* <tt>debugOn</tt> seja chamado.
*/
public static void debugOff() 
 {
 debug = false;
 } // fim do método debugOff

} // fim da classe Keyboard

//Segue a mensagem original da classe EasyIn de Peter van der Linden

//Simple input from the keyboard for all primitive types. ver 1.0
//Copyright (c) Peter van der Linden,  May 5 1997.
//  corrected error message 11/21/97
//
//The creator of this software hereby gives you permission to:
//1. copy the work without changing it
//2. modify the work providing you send me a copy which I can
//  use in any way I want, including incorporating into this work.
//3. distribute copies of the work to the public by sale, lease, 
//  rental, or lending
//4. perform the work
//5. display the work
//6. fold the work into a funny hat and wear it on your head.
//
//This is not thread safe, not high performance, and doesn't tell EOF.
//It's intended for low-volume easy keyboard input.
//An example of use is:
//  EasyIn easy = new EasyIn();
//  int i = easy.readInt();   // reads an int from System.in
//  float f = easy.readFloat();   // reads a float from System.in