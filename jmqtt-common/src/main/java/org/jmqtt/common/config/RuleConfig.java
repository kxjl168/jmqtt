package org.jmqtt.common.config;

public class RuleConfig {

	private String ruleweburl="";
	private boolean ruleenable=true;

		public String getRuleweburl() {
			return ruleweburl;
		}

		public void setRuleweburl(String ruleweburl) {
			this.ruleweburl = ruleweburl;
		}


		public boolean isRuleenable() {
			return ruleenable;
		}

		public void setRuleenable(boolean ruleenable) {
			this.ruleenable = ruleenable;
		}
}
