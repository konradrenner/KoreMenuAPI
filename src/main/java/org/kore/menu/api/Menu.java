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
 * Navigation
 *
 * Entry-Point for the navigation/menus. This interface has to be
 * implemented/loaded in the client application of this API
 *
 * @author koni
 */
public interface Menu {

    /**
     * Loads the toplevel EntryGroup for the given namespace. Return an
     * EmptyEntryGroup if no matching group is found
     *
     * @param namespace
     * @return EntryGroup
     */
    EntryGroup getMainGroup(Namespace namespace);

    /**
     * Loads an EntryGroup
     *
     * Searches rekursive threw all EntryGroups of the given namespace. Return
     * an EmptyEntryGroup if no matching group is found
     *
     * @see EmptyEntryGroup
     * @param namespace
     * @param uid
     * @return EntryGroup
     */
    EntryGroup getGroup(Namespace namespace, EntryUID uid);

    /**
     * Loads an entry, searches rekursive threw all EntryGroups of the given
     * namespace. Return an NullEntry if nothing is found
     *
     * @param namespace
     * @param uid
     * @return Entry
     */
    Entry getEntry(Namespace namespace, EntryUID uid);

    /**
     * Loads the toplevel EntryGroup for the current namespace. Return an
     * EmptyEntryGroup if no matching group is found
     *
     * @param namespace
     * @return EntryGroup
     */
    EntryGroup getMainGroup();

    /**
     * Loads an EntryGroup
     *
     * Searches rekursive threw all EntryGroups of the current namespace. Return
     * an EmptyEntryGroup if no matching group is found
     *
     * @see EmptyEntryGroup
     * @param namespace
     * @param uid
     * @return EntryGroup
     */
    EntryGroup getGroup(EntryUID uid);

    /**
     * Loads an entry, searches rekursive threw all EntryGroups of the current
     * namespace. Return an NullEntry if nothing is found
     *
     * @param namespace
     * @param uid
     * @return Entry
     */
    Entry getEntry(EntryUID uid);

    /**
     * Returns the namespace of the current application
     *
     * @return Namespace
     */
    Namespace getCurrentNamespace();
}
