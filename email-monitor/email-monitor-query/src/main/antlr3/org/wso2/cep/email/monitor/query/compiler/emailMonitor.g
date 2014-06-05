grammar emailMonitor ;

options {
  language = Java;
  backtrack=true;
  output    = AST;
  ASTLabelType=CommonTree;

}

tokens {
  EMAIL_PRO;
  TIME_EXP;
  FREQ_COND;
  YEAR;
   MONTH;
  WEEK;
  DAY;
  HOUR;
  MIN;
  SEC;
  MILLI_SEC;
  
}

@header {
package test;
import java.util.HashMap;
}

@lexer::header {package test;}

@members {
/** Map variable name to Integer object holding value */
HashMap memory = new HashMap();
}

prog:   'if'    conditions   'then' action  -> ^(EMAIL_PRO conditions action);
                
conditions:   toCondition
    | toCondition 'and'  ^labelFromFrequencyCondtion
    | toCondition 'or' ^labelFromFrequencyCondtion
    | labelFromFrequencyCondtion
    ;

labelFromFrequencyCondtion
    : labelCondition
    | labelCondition 'and' ^ fromFrequencyCondtion
    | labelCondition 'or' ^fromFrequencyCondtion
    | fromFrequencyCondtion	
    ;
    
    
fromFrequencyCondtion
    : fromCondition
    | fromCondition 'and' ^ frequencyCondtion
    | fromCondition 'or' ^ frequencyCondtion
    | frequencyCondtion	
    ;
    
action 
    :   'add' 'label' stringVal
    | 'send' 'mail' '(' 'to' ':' emailAddr 'subject' ':' stringVal 'body' ':' stringVal  ('$frequency')?    stringVal')'
    ;


    
toCondition 
    :   'to' '='	'"'emailAddrSet '"';
    
labelCondition
 	: 'label' '=' '"' labelSet '"'
 	;
 	
 fromCondition
      :	'from' '=' '"'emailAddrSet '"'
      	;
      	
 frequencyCondtion 
 	:	('thread')? 'frequency' 'per' timeExpr compareOperation intVal  -> ^(FREQ_COND  timeExpr compareOperation intVal);	
 	
 timeExpr 
 	:(yearValue)? (monthValue)? (weekValue)? (dayValue)? (hourValue)? (minuteValue)? (secondValue)?  (milliSecondValue)?	
 	-> ^(TIME_EXP yearValue? monthValue? weekValue? dayValue? hourValue? minuteValue? secondValue? milliSecondValue?  )
 	;
    

yearValue
	: intVal ( 'years' | 'year')  ->  ^(YEAR intVal)
	;
	

monthValue
	: intVal ( 'months' | 'month')  -> ^(MONTH  intVal)
	;

weekValue
	: intVal ( 'weeks' | 'week')    -> ^(WEEK  intVal)
	;

dayValue
	: intVal ( 'days' | 'day')    -> ^(DAY  intVal)
	;

hourValue
	: intVal ( 'hours' |   'hour' )   -> ^(HOUR  intVal)
	;

minuteValue
	: intVal ( 'minutes' |  'min' | 'minute'  )    -> ^(MIN  intVal)
	;

secondValue
	: intVal ('seconds' | 'second' | 'sec'  )   -> ^(SEC  intVal)
	;

milliSecondValue
	: intVal ( 'milliseconds' |  'millisecond'  )    -> ^(MILLI_SEC  intVal)
	;

labelSet
	: label
	| label ('and' ^labelSet)
    	| label ('or' ^labelSet)	
	;
	
  emailAddrSet
    	:  emailAddr
    	| emailAddr ('and' ^emailAddrSet)
    	| emailAddr ('or' ^emailAddrSet)	;

compareOperation
	:'=' |'!=' |'<='|'>=' |'<' |'>'  
	;

label 	
   : stringVal
   ;


emailAddr 
   :  stringVal
   ;

stringVal: STRING_VAL ;


intVal 	:	INT_VAL;

ID  :   ('a'..'z'|'A'..'Z')+ ;

STRING_VAL
	:'\'' ( ~('\u0000'..'\u001f' | '\\' | '\''| '\"' ) )* '\'' 
	|'"' ( ~('\u0000'..'\u001f' | '\\'  |'\"') )* '"'          
	;	

INT_VAL :   '0'..'9'+ ;

WS  : (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;};

