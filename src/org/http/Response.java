//$Id$
package org.http;

import java.util.List;
import java.util.Map;

public class Response
{

	private int httpStatus;
	private String entity;

	public int getHttpStatus()
	{
		return httpStatus;
	}
	public String getEntity()
	{
		return entity;
	}

	public void setHttpStatus(int httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	public void setEntity(String entity)
	{
		this.entity = entity;
	}

}