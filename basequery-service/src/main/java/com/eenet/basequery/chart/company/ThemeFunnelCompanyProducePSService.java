package com.eenet.basequery.chart.company;

import com.eenet.base.SimpleResultSet;
import com.eenet.base.query.QueryCondition;

public interface ThemeFunnelCompanyProducePSService {

	/**
	 * 获取测试情况
	 * 
	 * @param condition
	 * @return
	 */
	public SimpleResultSet<ThemeFunnelCompanyProducePS> getTest(QueryCondition condition);	

}
