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

/**
 *
 * @author Konrad Renner
 */
public interface EntryUIDFactory {

    /**
     * Creates a new EntryUID
     *
     * @param namespace
     * @param id
     * @param sortigKey
     * @return EntryUID
     */
    EntryUID createUID(Namespace namespace, String id, String sortigKey);

    /**
     * Creates a new EntryUID for the current namespace
     *
     * @param namespace
     * @param id
     * @param sortigKey
     * @return EntryUID
     */
    EntryUID createUID(String id, String sortigKey);

    /**
     * Creates a new EntryUID for the current namespace and with a default
     * sorting key. This method should be used for searching an UID with the
     * given ID
     *
     * @param namespace
     * @param id
     * @param sortigKey
     * @return EntryUID
     */
    EntryUID createUID(String id);
}
