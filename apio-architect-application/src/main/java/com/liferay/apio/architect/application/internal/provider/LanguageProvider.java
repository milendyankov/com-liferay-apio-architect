/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.apio.architect.application.internal.provider;

import com.liferay.apio.architect.language.Language;
import com.liferay.apio.architect.provider.Provider;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * Lets resources provide the requested {@link Language} as a parameter in the
 * methods of {@link Routes.Builder}.
 *
 * @author Alejandro Hernández
 */
@Component(immediate = true)
public class LanguageProvider implements Provider<Language> {

	@Override
	public Language createContext(HttpServletRequest httpServletRequest) {
		return new Language() {

			@Override
			public Stream<Locale> getLocales() {
				Enumeration<Locale> locales = httpServletRequest.getLocales();

				return Collections.list(locales).stream();
			}

			@Override
			public Locale getPreferredLocale() {
				return httpServletRequest.getLocale();
			}

		};
	}

}