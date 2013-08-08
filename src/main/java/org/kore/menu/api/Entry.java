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
import org.kore.menu.api.security.Authorization;
import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;

/**
 *
 * @author koni
 */
public interface Entry {

    EntryUID getUID();

    Set<Authorization> getRequiredAuthorization();

    /**
     * Returns true if the Entry is in the current security context displayable
     *
     * @param context
     * @return boolean
     */
    boolean isDisplayable(SecurityContext context);

    /**
     * The given SecurityInspector checks if a user has all required
     * authorizations for this entry. This method returns true if everthing is
     * OK
     *
     * @param inspector
     * @return boolean
     */
    boolean controlAuthorizations(SecurityInspector inspector);

    /**
     * Gets the parent entry. This is an entry which is a level higher than this
     * Entry. Returns an NullEntry if this Entry has no Parent
     *
     * @return Entry
     */
    Entry getParent();

    /**
     * Gets the children entries of this entry. Returns an EmptyEntryGroup, if
     * this entry has no children.
     *
     *
     * @return EntryGroup
     */
    EntryGroup getChildren();

    Set<EntryTask> getMappedTasks();

    /**
     * Executes a mapped task, return true if everthing is OK (task started and
     * if the task runs not asynchron true if the task was completed)
     *
     * @param taskid
     * @param context
     * @param inspector
     * @return boolean
     */
    boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector);
}
