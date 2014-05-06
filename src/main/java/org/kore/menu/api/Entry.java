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
import java.util.Set;
import org.kore.menu.api.security.Authorization;
import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;

/**
 * Represents an entry of the menu. Each Entry must be comparable (comparison
 * should be done with the EntryUID).
 *
 * @author koni
 */
public interface Entry extends Comparable<Entry>, Serializable {

    enum Type {

        ENTRY, GROUP, TASK, NULL;
    }

    /**
     * Returns the type of the entry. If the Entry is a NullEntry or a
     * NullEntryGroup Type.NULL will be returned
     *
     * @return Type
     */
    Type getType();

    EntryUID getUID();

    Set<Authorization> getRequiredAuthorization();


    /**
     * The given SecurityInspector checks if a user has all required
     * authorizations for this entry. The SecurityInspector also checks if the
     * SecurityContext is OK. This method returns true if everthing is OK
     *
     * @param context
     * @param inspector
     * @return boolean
     */
    boolean controlAuthorizations(SecurityInspector inspector, SecurityContext context);

    /**
     * Gets the parent entry. This is an entry which is a level higher than this
     * Entry. Returns an EntryUID of an NullEntry if it has no parent
     *
     * @return EntryUID
     */
    EntryUID getParent();

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
     * if the task runs not asynchron, true if the task was completed)
     *
     * @param taskid
     * @param context
     * @param inspector
     * @return boolean
     */
    boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector);

    /**
     * Returns a String which can be used for presentation on a GUI
     *
     * @return String
     */
    String getDisplayKey();

    /**
     * Returns the path of the Entry
     *
     * @return NavigationPath
     */
    NavigationPath getNavigationPath();
}
