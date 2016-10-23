# Civolution
A strategy game will be developed here.

Better documentation will soon be provided.

For starters:

1. Clone repo.
2. Make the project using maven.
3. Run the created jar.

Now you can try out what happens when you enter commands using the following abstract grammar:
(Id describes any String consisting of letters and digits, starting with a letter;
Num describes any natural Number give in decimal representation.)

  Stmt       ::= ( BlockStmt | SingleStmt ) Stmt
  
  SingleStmt ::= "!" Act ";" | Id "=" Exp
  
  BlockStmt  ::= ( "if" | "while" ) Exp "{" Stmt "}"


  Exp       ::= ( SingleExp | BinaryExp )
  
  SingleExp ::= ( Id | Num ) 
  
  BinaryExp ::= SingleExp Operator SingleExp
  

  Act       ::= move Direction | command "{" Stmt "}"
  
  Direction ::= "N" | "NE" | "E" | "SE"
              | "S" | "SW" | "W" | "NW"
   
