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

import java.util.Collections;
import java.util.Set;
import org.kore.menu.api.security.Authorization;
import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;

/**
 *
 * @author Konrad Renner
 */
public class NullEntry implements Entry {

    @Override
    public EntryUID getUID() {
        return new EntryUID() {
            @Override
            public String getIdentifierString() {
                return "";
            }

            @Override
            public int compareTo(EntryUID o) {
                if (o.equals(this)) {
                    return 0;
                }
                return getSortingKey().compareTo(o.getSortingKey());
            }

            @Override
            public String getSortingKey() {
                return "";
            }
        };
    }

    @Override
    public int compareTo(Entry o) {
        return getUID().compareTo(o.getUID());
    }


    @Override
    public Set<Authorization> getRequiredAuthorization() {
        return Collections.emptySet();
    }


    @Override
    public boolean controlAuthorizations(SecurityInspector inspector, SecurityContext context) {
        return false;
    }

    @Override
    public Entry getParent() {
        return new NullEntry();
    }

    @Override
    public EntryGroup getChildren() {
        return new EmptyEntryGroup();
    
    }

    @Override
    public Set<EntryTask> getMappedTasks() {
        return Collections.emptySet();
    }

    @Override
    public boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector) {
        return true;
    }
}
