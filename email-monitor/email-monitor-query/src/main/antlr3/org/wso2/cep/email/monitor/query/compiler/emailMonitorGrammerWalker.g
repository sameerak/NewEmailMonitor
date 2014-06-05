tree grammar emailMonitorGrammerWalker;

options {
    backtrack=true;
    tokenVocab=emailMonitor;
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
package org.wso2.cep.email.monitor.query.compiler;

    import org.wso2.cep.email.monitor.query.api.Query;
  import org.wso2.cep.email.monitor.query.api.actions.Action;
  import org.wso2.cep.email.monitor.query.api.actions.AddLabel;
   import org.wso2.cep.email.monitor.query.api.actions.SendMail;

  import org.wso2.cep.email.monitor.query.api.attribute.EmailAddress;
  import org.wso2.cep.email.monitor.query.api.attribute.Label;

  import org.wso2.cep.email.monitor.query.api.attribute.set.EmailAddressSet;
  import org.wso2.cep.email.monitor.query.api.attribute.set.LabelSet;

  import org.wso2.cep.email.monitor.query.api.expressions.CompareVal;
  import org.wso2.cep.email.monitor.query.api.expressions.TimeExpr;

  import org.wso2.cep.email.monitor.query.api.utils.Constants;

import org.wso2.cep.email.monitor.query.api.operators.Operator;
import org.wso2.cep.email.monitor.query.api.operators.EqualOP;
import org.wso2.cep.email.monitor.query.api.operators.GreaterThanOP;
import org.wso2.cep.email.monitor.query.api.operators.GreaterThanOREqualOP;
import org.wso2.cep.email.monitor.query.api.operators.LessThanOP;
import org.wso2.cep.email.monitor.query.api.operators.LessThanOREqualOP;
import org.wso2.cep.email.monitor.query.api.operators.NOTEqualOP;


  import org.wso2.cep.email.monitor.query.api.conditions.AndCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.Condition;
  import org.wso2.cep.email.monitor.query.api.conditions.ConditionAttribute;
  import org.wso2.cep.email.monitor.query.api.conditions.FrequencyCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.FromCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.FromFrequencyCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.LabelCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.LabelFromFrequencyCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.ORCondition;
  import org.wso2.cep.email.monitor.query.api.conditions.ToCondition;
}


prog returns [Query query]
@init{
             $query = new Query();
}
:^(EMAIL_PRO conditions{
$query.setConditionAttribute($conditions.condition);} action{
$query.setAction($action.act);})
;



conditions returns [ConditionAttribute condition]
@init{
$condition=null;
}
:toCondition{$condition = $toCondition.to;}
    | toCondition{$condition=new AndCondition();
    $condition.setLeft($toCondition.to);

} 'and'  ^labelFromFrequencyCondition{
      $condition.setRight($labelFromFrequencyCondition.labelFromFreq);

}
    | toCondition{$condition=new ORCondition();
      $condition.setLeft($toCondition.to);

    } 'or' ^labelFromFrequencyCondition{

      $condition.setRight($labelFromFrequencyCondition.labelFromFreq);
    }
    | labelFromFrequencyCondition{$condition= $labelFromFrequencyCondition.labelFromFreq;}
;



labelFromFrequencyCondition returns [ConditionAttribute labelFromFreq]
  @init{
      $labelFromFreq=null;
  }
    : labelCondition {$labelFromFreq=$labelCondition.lbCondition;}
    | labelCondition{$labelFromFreq= new AndCondition();
         $labelFromFreq.setLeft($labelCondition.lbCondition);
     } 'and' ^ fromFrequencyCondition{
          $labelFromFreq.setRight($fromFrequencyCondition.fromFreqCond);

     }
    | labelCondition{$labelFromFreq= new ORCondition();
                             $labelFromFreq.setLeft($labelCondition.lbCondition);
                         } 'or' ^fromFrequencyCondition{
                                                                $labelFromFreq.setRight($fromFrequencyCondition.fromFreqCond);

                                                           }
    | fromFrequencyCondition{$labelFromFreq= $fromFrequencyCondition.fromFreqCond;}
    ;




    
 fromFrequencyCondition returns [ConditionAttribute fromFreqCond]
   @init{
      $fromFreqCond=null;
   }
    : fromCondition{$fromFreqCond=$fromCondition.fromCond;}
    | fromCondition{$fromFreqCond= new AndCondition();
        $fromFreqCond.setLeft($fromCondition.fromCond);} 'and' ^ frequencyCondition{ $fromFreqCond.setRight($frequencyCondition.freqCond);}
    | fromCondition {$fromFreqCond= new ORCondition();
                            $fromFreqCond.setLeft($fromCondition.fromCond);}'or' ^ frequencyCondition{ $fromFreqCond.setRight($frequencyCondition.freqCond);}
    | frequencyCondition{$fromFreqCond=$frequencyCondition.freqCond;}
    ;
    
action returns [Action act]
    :   'add' 'label' stringVal{$act = new AddLabel($stringVal.val);}
    | 'send' 'mail' '(' 'to' ':' emailAddr 'subject' ':' stringVal 'body' ':' stringVal ('$frequency')? stringVal ')'
    ;





toCondition returns [ConditionAttribute to]
@init{
$to= new ToCondition();
}
    :   'to' '='	'"'emailAddrSet{$to.setEmailAddressSet($emailAddrSet.emailSet);} '"';




labelCondition returns [ConditionAttribute lbCondition]
@init{
   $lbCondition=new LabelCondition();
}
 	: 'label' '=' '"' labelSet{$lbCondition.setLabelSet($labelSet.lbSet);} '"'
 	;




 fromCondition returns [ConditionAttribute fromCond]
 @init{
    $fromCond= new FromCondition();
 }
      :	'from' '=' '"'emailAddrSet{$fromCond.setEmailAddressSet($emailAddrSet.emailSet);} '"'
      	;




 frequencyCondition returns [ConditionAttribute freqCond]
 @init{
     $freqCond= new FrequencyCondition();
     Operator operator=null;
 }
 	:	 ^(FREQ_COND 'thread'{$freqCond.setType(Constants.THREAD)}? timeExpr compareOperation{
 	   operator= $compareOperation.oper;
 	   operator.setLeft($timeExpr.timeEx);
 	$freqCond.setOperator();
 	} intVal{operator.setRight(new CompareVal(Integer.parseInt($intVal.val)));});




 timeExpr returns [TimeExpr timeEx]
 @init{
     $timeEx= new TimeExpr();
 }
 	: ^(TIME_EXP yearValue{$timeEx.setYear(Integer.parseInt($yearValue.year))}? monthValue{$timeEx.setMonth(Integer.parseInt($monthValue.month))}? weekValue{$timeEx.setWeek(Integer.parseInt($weekValue.week))}? dayValue{$timeEx.setDay(Integer.parseInt($dayValue.day))}? hourValue{$timeEx.setHour(Integer.parseInt($hourValue.hour))}? minuteValue{$timeEx.setMinute(Integer.parseInt($minuteValue.minute))}? secondValue{$timeEx.setSecond(Integer.parseInt($secondValue.second))}? milliSecondValue{$timeEx.setMilisecond(Integer.parseInt($milliSecondValue.milisecond))}?  )
 	;
    



yearValue returns [String year]
	:  ^(YEAR intVal{$year=$intVal.val;})
	;




monthValue returns [String month]
	:  ^(MONTH  intVal{$month=$intVal.val;})
	;



weekValue returns [String week]
	: ^(WEEK  intVal{$week=$intVal.val;})
	;



dayValue returns [String day]
	:  ^(DAY  intVal{$day=$intVal.val;})
	;



hourValue returns [String hour]
	: ^(HOUR  intVal{$hour=$intVal.val;})
	;

minuteValue returns [String minute]
	: ^(MIN  intVal{$minute=$intVal.val;})
	;



secondValue returns[String second]
	:  ^(SEC  intVal{$second=$intVal.val;})
	;



milliSecondValue returns [String milisecond]
	:  ^(MILLI_SEC  intVal{$milisecond=$intval.val;})
	;



labelSet returns [ConditionAttribute lbSet]
@init{
     $lbSet = null;
}
	: label{
	$lbSet= new LabelSet();
	$lbSet.setConditionAttribute($label.label);}
	| label{$lbSet= new AndCondition();
	$lbSet.setLeft($label.label);
	} ('and' labelSetRec{
	  $lbSet.setRight($labelSetRec.lbSetRec);
	})
    | label{$lbSet= new ORCondition();
           	$lbSet.setLeft($label.label);
           	} ('or' labelSetRec{
           	$lbSet.setRight($labelSetRec.lbSetRec);
           	})
	;

	labelSetRec returns[LabelSet lbSetRec]

	:labelSet{$lbSetRec=$labelSet.lbSet;};










  emailAddrSet returns [ConditionAttribute emailSet]
  @init{
    $emailSet= null;
  }
    	:  emailAddr {$emailSet = new EmailAddressSet();
    	    $emailSet.setConditionAttribute($emailAddr.email);
    	}
    	| emailAddr {
    	$emailSet= new AndCondition();
    	$emailSet.setLeft($emailAddr.email);



    	}('and' emailAddrSetRec{

    	$emailSet.setRight($emailAddrSetRec.emailSetRec);


    	})
    	| emailAddr{
                       	$emailSet= new ORCondition();
                       	$emailSet.setLeft($emailAddr.email);



                       	}('or' emailAddrSetRec{

                       	$emailSet.setRight($emailAddrSetRec.emailSetRec);


                       	})
    	;


    emailAddrSetRec returns[EmailAddressSet emailSetRec]


    :emailAddrSet{$emailSetRec=$emailAddrSet.emailSet;};



compareOperation returns [Operator oper]
	:'='{$oper= new EqualOP();} |'!=' {$oper= new NOTEqualOP();}|'<='{$oper=new LessThanOREqualOP();}|'>='{$oper= new GreaterThanOREqualOP();} |'<' {$oper= new LessThanOP();} |'>' {$oper= new GreaterThanOP();}
	;



label 	returns [Label label]
   : stringVal {$label = new Label($stringVal.val);}
   ;




emailAddr returns [EmailAddress email]
   :  stringVal{$email= new EmailAddress($stringVal.val);}
   ;



stringVal returns [String val]

: STRING_VAL{$val = $STRING_VAL.text;} ;




intVal returns [String val]

	:	INT_VAL{$val = $INT_VAL.text;};