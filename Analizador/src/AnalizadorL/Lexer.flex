package AnalizadorL;
import static AnalizadorL.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]
D=[0-9]
espacio=[ ,\t,\r]+
espacios=[ ,\t,\r]?
comillas=[\"]+
comillasS=[']+
opAg=[\(,\),\[,\],{,}]
opAr=[*,\+,\-,/,%]
opRe=[<,>]
opLo=[&,|,!]
com=[\,]
SMB=[\!,#,$,%,&,\(,\),*,\+,\,,\-,\.,/,:,;,<,=,>,?,@,\[,\],\^,_,`,{,|,},¿]+
%{
    public String lexeme;
%}
%%
{opAr} {lexeme=yytext(); return aritmetico;}
-?([1-9][0-9]*|\-[1-9][0-9]*|0)\.([0-9]*[1-9]|[0-9]*[1-9]|((e|E)(\+|\-)[1-9][0-9]*)) {lexeme=yytext(); return numf;}
-?("(-"{D}+")")|-?{D}+ {lexeme=yytext(); return nume;}
{com} {lexeme=yytext();return coma;}
("/*")({comillas}|{L}|{D}|{espacio}|{SMB}|{comillasS}|{opAg}|{opAr}|{opLo}|{opRe}|"\n")*("*/") {/*Ignore*/}
{comillas}({L}|{D}|{espacio}|{SMB})*{comillas} {lexeme=yytext(); return litcad;}
{comillas}({L}|{D}|{espacio}|{SMB})* {return ERRcad;}
{comillasS}({L}|{D}){comillasS} {lexeme=yytext(); return litcar;}
{comillasS}({L}|{D})* {return ERRchar;}
{comillasS}({L}|{D})+{comillasS} {return ERRMDig;}
if|for|while|do|main|switch|case|program|void|int|char|write|method|funtion|endm|endf|endp|
float|string|boolean|final|new|true|false|null|this|try|catch|else|repeat|pi|e|read|return|
then|endif|endwhile|endcase|endswitch|until|
write {lexeme=yytext(); return reservada;}
(" "|"\t"|\r)+ {/*Ignore*/}
"<=" {lexeme=yytext(); return meniq;}
">=" {lexeme=yytext(); return mayiq;}
"==" {lexeme=yytext(); return igual;}
"!=" {lexeme=yytext(); return dif;}
{opAg} {lexeme=yytext(); return agrupacion;}

{opLo} {lexeme=yytext(); return logico;}
{opRe} {lexeme=yytext(); return relacional;}
"//".* {/*Ignore*/}
"\n" {lexeme=yytext(); return salto;}
"=" {lexeme=yytext(); return asignacion;}
";"	{lexeme=yytext();return finSentencia;}
":"	{lexeme=yytext();return puntos;}
{L}({L}|{D})* {lexeme=yytext(); return id;}
 . {return ERROR;}