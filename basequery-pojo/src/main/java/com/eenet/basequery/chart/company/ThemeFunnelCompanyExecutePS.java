package com.eenet.basequery.chart.company;

import java.util.Date;

import com.eenet.base.BaseEntity;

public class ThemeFunnelCompanyExecutePS extends BaseEntity{

	
	private static final long serialVersionUID = 5866891569541028742L;
	private String DIM_PERSONS_SCALE;//人数规模
	private Date DIM_CREATED_DT;//时间
	private String AFFIRM_COUNT;//覆盖客户数
	private String EXECUTE_COUNT;//激活客户数
	private String EXECUTE_PER;//激活转化率
	private String CREATE_DT_FROM;//统计开始时间
	private String CREATE_DT_TO;//统计结束时间
	
	public String getCREATE_DT_FROM() {
		return CREATE_DT_FROM;
	}
	public void setCREATE_DT_FROM(String cREATE_DT_FROM) {
		CREATE_DT_FROM = cREATE_DT_FROM;
	}
	public String getCREATE_DT_TO() {
		return CREATE_DT_TO;
	}
	public void setCREATE_DT_TO(String cREATE_DT_TO) {
		CREATE_DT_TO = cREATE_DT_TO;
	}
	public String getEXECUTE_COUNT() {
		return EXECUTE_COUNT;
	}
	public void setEXECUTE_COUNT(String eXECUTE_COUNT) {
		EXECUTE_COUNT = eXECUTE_COUNT;
	}
	private Integer currentCount;
	private Integer pageSize;

	
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Date getDIM_CREATED_DT() {
		return DIM_CREATED_DT;
	}
	public void setDIM_CREATED_DT(Date dIM_CREATED_DT) {
		DIM_CREATED_DT = dIM_CREATED_DT;
	}
	public String getDIM_PERSONS_SCALE() {
		return DIM_PERSONS_SCALE;
	}
	public void setDIM_PERSONS_SCALE(String dIM_PERSONS_SCALE) {
		DIM_PERSONS_SCALE = dIM_PERSONS_SCALE;
	}
	public String getAFFIRM_COUNT() {
		return AFFIRM_COUNT;
	}
	public void setAFFIRM_COUNT(String aFFIRM_COUNT) {
		AFFIRM_COUNT = aFFIRM_COUNT;
	}
	public String getEXECUTE_PER() {
		return EXECUTE_PER;
	}
	public void setEXECUTE_PER(String eXECUTE_PER) {
		EXECUTE_PER = eXECUTE_PER;
	}

	
	
	
}
