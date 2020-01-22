package org.jmqtt.group.remoting.codec;

import java.io.FileReader;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.ZRule;
import org.jmqtt.rule.processor.impl.DefaultRuleDespatcher;


public class test1 {

	public static void main(String[] args) {

		ZRule rule1 = new ZRule();
		
		
		

		// create a person instance (fact)
		Message msg = new Message();
		
		Facts facts = new Facts();
		facts.put("msg", msg);
		
		
		
		
	

	
		//DefaultRuleTransform ruleTrans=new DefaultRuleTransform();
		//Rule rule= ruleTrans.TransFormToEasyRule(rule1);
		
		

		// create a rule set
		//Rules rules = new Rules();
		//rules.register(ageRule);
		//rules.register(alcoholRule);

		// create a default rules engine and fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();

		System.out.println("Tom: Hi! can I have some Vodka please?");
		//rulesEngine.fire(rules, facts);

	}

}
