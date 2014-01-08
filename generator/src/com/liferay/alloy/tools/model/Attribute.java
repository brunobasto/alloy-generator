/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.alloy.tools.model;

import com.liferay.alloy.util.ReservedAttributeUtil;
import com.liferay.alloy.util.StringUtil;
import com.liferay.alloy.util.TypeUtil;

import java.util.List;

import jodd.util.StringPool;

import org.apache.commons.lang.StringUtils;
public class Attribute extends BaseModel {

	public String getCapitalizedName() {
		return StringUtils.capitalize(getSafeName());
	}

	public Component getComponent() {
		return _component;
	}

	public String getConstantName() {
		return StringUtil.fromCamelCase(getSafeName(), '_').toUpperCase();
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public String getInputType() {
		return TypeUtil.getInputJavaType(_inputType, true);
	}

	public String getInputTypeSimpleClassName() {
		return getTypeSimpleClassName(getRawInputType());
	}

	public String getJSFInputType() {
		String inputJavaType = TypeUtil.getInputJavaType(_inputType, true);

		if (TypeUtil.isPrimitiveType(inputJavaType)) {
			//possible check if we have enough of a string
			inputJavaType =
				inputJavaType.substring(0, 1).toUpperCase() +
				inputJavaType.substring(1);

			return "java.lang." + inputJavaType;
		}
		else {
			return inputJavaType;
		}
	}

	public String getOutputType() {
		return TypeUtil.getOutputJavaType(_outputType, true);
	}

	public String getOutputTypeSimpleClassName() {
		return getTypeSimpleClassName(getRawOutputType());
	}

	public String getRawInputType() {
		return TypeUtil.getInputJavaType(_inputType, false);
	}

	public String getRawOutputType() {
		return TypeUtil.getOutputJavaType(_outputType, false);
	}

	public String getSafeName() {
		String safeName = getName();

		if ((getComponent() != null) && getComponent().isAlloyComponent()) {
			safeName = ReservedAttributeUtil.getSafeName(this);
		}

		if (safeName.indexOf(StringPool.COLON) > -1) {
			safeName = StringUtils.substringAfterLast(
				safeName, StringPool.COLON);
		}

		return safeName;
	}

	public String getTypeSimpleClassName(String type) {
		if (TypeUtil.isPrimitiveType(type)) {
			return type;
		}
		else {
			try {
				return Class.forName(type).getSimpleName();
			}
			catch (ClassNotFoundException e) {
			}
		}

		return StringPool.EMPTY;
	}

	public String getUncapitalizedName() {
		return StringUtil.uncapitalize(getSafeName());
	}

	public boolean isEvent() {
		List<Attribute> events = _component.getEvents();

		return events.contains(this);
	}

	public boolean isGettable() {
		return _gettable;
	}

	public boolean isRequired() {
		return _required;
	}

	public boolean isSettable() {
		return _settable;
	}

	public void setComponent(Component component) {
		_component = component;
	}

	public void setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;
	}

	public void setGettable(boolean gettable) {
		_gettable = gettable;
	}

	public void setInputType(String inputType) {
		_inputType = inputType;
	}

	public void setOutputType(String outputType) {
		_outputType = outputType;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public void setSettable(boolean settable) {
		_settable = settable;
	}

	private Component _component;
	private String _defaultValue;
	private boolean _gettable = true;
	private String _inputType;
	private String _outputType;
	private boolean _required;
	private boolean _settable = true;

}