package AnalizadorL;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sintactico2
{
	Stack<String> pila, pilaaux;
	int fila=0,col=0;
	String Accion;
	String noterminales[] = {"prog","dec","sigid","modulos","proc","fun","tiporetorno","list-arg","siglist","list-param","sig-param","sentencias","sentencia","sigif","L","L’","R","R’","E","E’","T","T’","F","$"};
	String terminales[] = 
			{"id",						"num",			"(",			")",		"litcad",		"litcar",		"+",			"-",			"*",			"/",			"=",			"<",			">",			"meniq",		"mayiq",		"dif",			"igual",		"!",			"&",			"|",			"true",			"false",		"if",								"while",							"repeat",						"else",				"then",			"do",			"endif",		"endwhile",		"program",										"int",					"float",					"char",					"string",					"boolean",					"read",						"write",					",",				"method",										"funtion",														"endm",		"endf",		"endp",		"idm",					"idf",					"return",				"$"};
	String tabla[][]= {
			//0		 					1				2				3			4				5				6				7				8				9				10				11				12				13				14				15				16				17				18				19				20				21				22									23									24								25					26				27				28				29				30												31						32							33						34							35							36							37							38					39												40																41			42			43			44						45						46						47
			{"saltar ",     			"saltar ",  	"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"program id ; modulos dec sentencias endp ",	"saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",										"int id sigid ; dec ",	"float id sigid ; dec ",	"char id sigid ; dec ",	"string id sigid ; dec ",	"boolean id sigid ; dec ",	"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"ç ",		"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"= E sigid ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",										"saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					", id sigid ",		"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"ç ",						"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"ç ",								"ç ",								"ç ",							"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",										"ç ",					"ç ",						"ç ",					"ç ",						"ç ",						"saltar ",					"saltar ",					"saltar ",			"proc modulos ",								"fun modulos ",													"ç ",		"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"method idp ( list-arg ) dec sentencias endp ",	"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"funtion idf ( list-arg ) : tiporetorno dec sentencias endf ",	"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "int ",					"float ",					"char ",				"string ",					"boolean ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "int id list ",			"float id siglist ",		"char id siglist ",		"string id siglist ",		"boolean id siglist ",		"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					", list-arg ",		"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"L sig-param ",			"L sig-param ",	"L sig-param ",	"ç ",		"L sig-param ",	"L sig-param ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"L sig-param ",	"L sig-param ",	"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"L sig-param ",			"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					", L sig-param ",	"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"sacar "},
			{"sentencia sentencias ",	"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"sentencia sentencias ",			"sentencia sentencias ",			"sentencia sentencias ",		"ç ",				"saltar ",		"saltar ",		"ç ",			"ç ",			"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"ç ",		"ç ",		"ç ",		"sentencia sentencias ","saltar ",				"sentencia sentencias ","ç "},
			{"id = L ; ",				"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"if L then sentencias sigif endif ","while L do sentencias endwhile ",	"repeat sentencias until L ; ",	"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"write ( list-param ) ; ",	"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"idp ( list-param ) ; ","saltar ",				"return L ; ",			"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"else sentencias ",	"saltar ",		"saltar ",		"ç ",			"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"ç ",		"saltar ",				"saltar ",				"saltar ",				"ç "},
			{"R L’ ",					"R L’ ",		"R L’ ",		"saltar ",	"R L’ ",		"R L’ ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"! L ",			"saltar ",		"saltar ",		"R L’ ",		"R L’ ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"R L’ ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",		"saltar ",		"saltar ",		"ç ",			"ç ",			"ç ",			"ç ",			"saltar ",		"ç ",			"ç ",			"ç ",			"ç ",			"ç ",			"ç ",			"saltar ",		"& R L’ ",		"| R L’ ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"ç "},
			{"E R’ ",					"E R’ ",		"E R’ ",		"saltar ",	"E R’ ",		"E R’ ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar  ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"ç ",			"ç ",			"E R’ ",		"E R’ ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"E R’ ",				"saltar ",				"ç "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",	 	"saltar ",		"saltar ",		"ç ",			"ç ",			"ç ",			"ç ",			"saltar ",		"< E ",			"> E ",			"meniq E ",		"mayiq E ",		"dif E ",		"igual E ",		"saltar ",		"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"ç "},
			{"T E’ ",					"T E’ ",		"T E’ ",		"saltar ",	"T	E’ ",		"T E’ ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"T E’ ",		"T E’ ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"T E’ ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",		"saltar ",		"saltar ",		"+ T E’ ",		"- T E’ ",		"ç ",			"ç ",			"saltar ",		"ç ",			"ç ",			"ç ",			"ç ",			"ç ",			"ç",			"saltar ",		"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"ç "},
			{"F T’ ",					"F T’ ",		"F T’ ",		"saltar ",	"F	T’ ",		"F T’ ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"F T’ ",		"F T’ ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"F T’ ",				"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"ç ",		"saltar ",		"saltar ",		"ç ",			"ç ",			"* F T’ ",		"/ F T’ ",		"saltar ",		"ç ",			"ç ",			"ç ",			"ç ",			"ç ",			"ç ",			"saltar ",		"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"ç ",			"ç ",			"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"ç "},
			{"id ",						"num ",			"( L ) ",		"saltar ",	"litcad ",		"litcar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"true ",		"false ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"read ( list-param ) ; ",	"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"idf ( list-param ) ",	"saltar ",				"sacar "},
			{"saltar ",					"saltar ",		"saltar ",		"saltar ",	"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",							"saltar ",							"saltar ",						"saltar ",			"saltar ",		"saltar ",		"saltar ",		"saltar ",		"saltar ",									    "saltar ",				"saltar ",					"saltar ",				"saltar ",					"saltar ",					"saltar ",					"saltar ",					"saltar ",			"saltar ",										"saltar ",														"saltar ",	"saltar ",	"saltar ",	"saltar ",				"saltar ",				"saltar ",				"aceptar "}
	};
	
	public Sintactico2() {
		pila = new Stack<String>();
		pila.push("$");
		pila.push("prog");
	}
	public void ASintactico(String comp) {
		if(pila.peek().equals(comp))
		{
			pila.pop();
			
		}
		else
		{
			for(int f=0;f<noterminales.length;f++)
				if(noterminales[f].equals("pila.peek"))
					fila=f;
			System.out.println(fila);
			for(int c=0;c<terminales.length;c++)
				if(terminales[c].equals(comp))
					col=c;
			System.out.println(col);
			Accion=tabla[fila][col];
			System.out.println(Accion);
			/*StringTokenizer st = new StringTokenizer(Accion);
			while(st.hasMoreTokens())
				System.out.println(st.nextToken());*/
		}
			
	}

	/*public void Pila(String token)
	{
		try
		{			
			System.out.println("\nToken "+token);
			if (token.equals(pila.peek()))
				if (token.equals("$"))
				{
					msj = "Acepta";
					System.out.println("Acepta " + pila.peek());
					pila.pop();
					System.out.println(pila + "");
					return;
				}
				else
				this.Versima();
			else
			{
				this.y(token);
				do
				{
					this.x();					
					if (tabla[x][y].equals("saltar "))
					{
						err="";
						System.out.println("Saltar " + pila.peek());
						System.out.println(pila + "");
						
						msj = "Error saltar";
						this.Errores(token);
						
						break;
					}
					else
						if (tabla[x][y].equals("sacar "))
						{
							err="";
							msj = "Error sacar" + pila.peek();
							System.out.println("Error sacar " + pila.peek());
							System.out.println(pila + "");
							this.Errores(token);
							pila.pop();
							System.out.println(pila + "");
							this.Pila(token);
							
						}
						else
							if (tabla[x][y].equals("ç "))
							{
								msj = "ç";
								pila.pop();
								System.out.println(pila + "");
								this.Pila(token);
								break;
							}
							else
								if (tabla[x][y].equals("aceptar "))
								{
									msj = "Aceptar";
									System.out.println("Acepta " + pila.peek());
									System.out.println(pila + "");
									break;
								}
								else
								{
									java.util.Stack<String> pilaaux = new java.util.Stack<String>();
									String pal = "";
									pila.pop();
									for (int j = 0; j < tabla[x][y].length(); j++)
										if (tabla[x][y].charAt(j) != ' ')
											pal += tabla[x][y].charAt(j) + "";
										else
										{
											pilaaux.push(pal);
											pal = "";
										}
									do
										pila.push(pilaaux.pop());
									while (!pilaaux.isEmpty());
									System.out.println(pila + "");
									if (token.equals(pila.peek()))
									{
										this.Versima();
										break;
									}
								}
				}
				while (!token.equals(pila.peek()));
			}
		}
		catch (EmptyStackException e)
		{
			// TODO: handle exception
		}
	}*/
//para los errores se deben ver la sima de la pila y hacer comparaciones con la tabla
	
	
	public String Pila() {
		return pila+"";
	}
	public String Accion() {
		return Accion;
	}
}
