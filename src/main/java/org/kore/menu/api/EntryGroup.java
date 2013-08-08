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

import java.util.Set;

/**
 *
 * @author Konrad Renner
 */
public interface EntryGroup extends Entry {

    boolean isMainGroup();

    /**
     * Returns all Entries form this group
     *
     * @return Set<Entry>
     */
    Set<Entry> getEntries();

    /**
     * Returns the entries of this group as group (creates a new copy of this
     * group)
     *
     *
     * @return EntryGroup
     */
    @Override
    EntryGroup getChildren();


    /**
     * Searches for an entry in this group an all children from the groups
     * entries. Returns a NullEntry if no matching Entry is found
     *
     * @param uid
     * @return Entry
     */
    Entry getEntry(EntryUID uid);
}
