package com.eenet.basequery.chart.company;

import com.eenet.base.SimpleResultSet;
import com.eenet.base.query.QueryCondition;

public interface ThemeFunnelCompanyAgreeCityService {

	/**
	 * 获取测试情况
	 * 
	 * @param condition
	 * @return
	 */
	public SimpleResultSet<ThemeFunnelCompanyAgreeCity> getTest(QueryCondition condition);	

}
