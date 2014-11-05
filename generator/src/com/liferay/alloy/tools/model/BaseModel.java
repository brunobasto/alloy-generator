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

import jodd.util.StringPool;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 */
public abstract class BaseModel implements Comparable<Object> {

	public int compareTo(Object o) {
		BaseModel model = (BaseModel)o;

		return _name.compareTo(model.getName());
	}

	public boolean equals(Object o) {
		BaseModel model = (BaseModel)o;

		return _name.equals(model.getName());
	}

	public String getCleanDescription() {

		if (_cleanDescription == null) {
			_cleanDescription = _description;
			_cleanDescription = _cleanDescription.replace(StringPool.NEWLINE,
				StringPool.SPACE);
			_cleanDescription = _cleanDescription.replaceAll("<!--.*-->",
				StringPool.EMPTY);
			_cleanDescription = _cleanDescription.replace(PLACEHOLDER_DESCRIPTION, StringPool.EMPTY);
			_cleanDescription = _cleanDescription.trim();
		}

		return _cleanDescription;
	}

	public String getDescription() {
		return _description;
	}

	public String getName() {
		return _name;
	}

	public int hashCode() {
		return _name.hashCode();
	}

	public boolean isGenerateJava() {
		return _generateJava;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setGenerateJava(boolean _generateJava) {
		this._generateJava = _generateJava;
	}
	
	private static final String PLACEHOLDER_DESCRIPTION = "TODO. Wanna help? Please send a Pull Request.";

	public void setName(String name) {
		_name = name;
	}

	private String _cleanDescription;
	private String _description;
	private boolean _generateJava;
	private String _name;

}