/*
 * Copyright (C) 2013 Konrad Renner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kore.menu.api;

import java.io.Serializable;

/**
 * Entry Unique Identifier. Each EntryUID must be compareable.
 *
 * @author Konrad Renner
 */
public interface EntryUID extends Comparable<EntryUID>, Serializable {

    public static String DEFAULT_SORTING = "";

    /**
     * Returns the domain specific namespace
     *
     * @return Namespace
     */
    Namespace getNamespace();

    /**
     * Returns a domain specific String-representation of the UID
     *
     * @return String
     */
    String getIdentifierString();

    /**
     * Returns a String with which help UIDs are sorted.
     *
     * @return String
     */
    String getSortingKey();
}
