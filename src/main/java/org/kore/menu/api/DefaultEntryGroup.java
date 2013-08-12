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
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.kore.menu.api.security.Authorization;
import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;
import org.kore.menu.api.security.SecurityUID;

/**
 * Serializable default implementation of the interface EntryGroup
 *
 * @author Konrad Renner
 */
public class DefaultEntryGroup implements EntryGroup, Serializable {

    private final EntryUID uid;
    private final Map<EntryUID, Entry> entries;
    private final Map<SecurityUID, Authorization> auths;
    private final Entry parent;
    private final Map<EntryUID, EntryTask> tasks;

    //Construct with builder
    private DefaultEntryGroup(EntryUID uid, Map<EntryUID, Entry> entries, Map<SecurityUID, Authorization> auths, Entry parent, Map<EntryUID, EntryTask> tasks) {
        this.uid = uid;
        this.entries = entries;
        this.auths = auths;
        this.parent = parent;
        this.tasks = tasks;
    }

    @Override
    public int compareTo(Entry o) {
        return this.uid.compareTo(o.getUID());
    }


    @Override
    public boolean isMainGroup() {
        return this.parent.equals(new NullEntry());
    }

    @Override
    public Set<Entry> getEntries() {
        return new HashSet<Entry>(this.entries.values());
    }

    @Override
    public Entry getEntry(EntryUID uid) {
        if (this.entries.get(uid) == null) {
            return new NullEntry();
        }

        return this.entries.get(uid);
    }

    @Override
    public EntryUID getUID() {
        return this.uid;
    }

    @Override
    public Set<Authorization> getRequiredAuthorization() {
        return new HashSet<Authorization>(this.auths.values());
    }

    @Override
    public boolean controlAuthorizations(SecurityInspector inspector, SecurityContext ctx) {
        try {
            inspector.inspect(ctx, parent);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    @Override
    public Entry getParent() {
        return this.parent;
    }

    @Override
    public EntryGroup getChildren() {
        DefaultEntryGroup copy = new DefaultEntryGroup(uid, entries, auths, parent, tasks);
        return copy;
    }

    @Override
    public Set<EntryTask> getMappedTasks() {
        return new HashSet<EntryTask>(this.tasks.values());
    }

    @Override
    public boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Builder {
        //Required properties

        private final EntryUID uid;
        private final Map<EntryUID, Entry> entries;
        //optional properties
        private Map<SecurityUID, Authorization> auths;
        private Entry parent;
        private Map<EntryUID, EntryTask> tasks;

        public Builder(final EntryUID uid, final Map<EntryUID, Entry> entries) {
            this.uid = Objects.requireNonNull(uid);
            this.entries = Objects.requireNonNull(entries);

            this.auths = Collections.emptyMap();
            this.parent = new NullEntry();
            this.tasks = Collections.emptyMap();
        }
        
        public Builder addRequiredAuthorizations(final Map<SecurityUID, Authorization> auth) {
            this.auths = Objects.requireNonNull(auth);
            return this;
        }
        
        public Builder addParent(final Entry parent) {
            this.parent = Objects.requireNonNull(parent);
            return this;
        }
        
        public Builder addTasks(final Map<EntryUID, EntryTask> task) {
            this.tasks = Objects.requireNonNull(task);
            return this;
        }

        public DefaultEntryGroup build() {

            return new DefaultEntryGroup(uid, entries, auths, parent, tasks);
        }
    }
}
