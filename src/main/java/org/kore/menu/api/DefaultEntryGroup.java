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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.kore.menu.api.security.Authorization;
import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;
import org.kore.menu.api.security.SecurityUID;

/**
 * Serializable default implementation of the interface EntryGroup
 *
 * @author Konrad Renner
 */
public class DefaultEntryGroup implements EntryGroup{

    private final EntryUID uid;
    private final Map<EntryUID, Entry> entries;
    private final Map<SecurityUID, Authorization> auths;
    private final EntryUID parent;
    private final Map<EntryUID, EntryTask> tasks;
    private final NavigationPath path;
    private final String displayKey;

    //Construct with builder
    private DefaultEntryGroup(EntryUID uid, Map<EntryUID, Entry> entries, Map<SecurityUID, Authorization> auths, EntryUID parent, Map<EntryUID, EntryTask> tasks, NavigationPath pat, String dkey) {
        this.uid = uid;
        this.entries = entries;
        this.auths = auths;
        this.parent = parent;
        this.tasks = tasks;
        this.path = pat;
        this.displayKey = dkey;
    }

    @Override
    public Type getType() {
        return Type.GROUP;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.uid != null ? this.uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultEntryGroup other = (DefaultEntryGroup) obj;
        if (this.uid != other.uid && (this.uid == null || !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }


    @Override
    public int compareTo(Entry o) {
        if (this.equals(o)) {
            return 0;
        }
        return this.uid.compareTo(o.getUID());
    }


    @Override
    public boolean isMainGroup() {
        return this.parent.equals(new NullEntry().getUID());
    }

    @Override
    public Set<Entry> getEntries() {
        return new TreeSet<Entry>(this.entries.values());
    }

    @Override
    public Entry getEntry(EntryUID uid) {
        if (this.entries.get(uid) == null) {
            return new NullEntry();
        }

        return this.entries.get(uid);
    }

    @Override
    public Entry searchForEntryDeeply(EntryUID uid) {
        return searchForEntryDeeply(uid, this);
    }

    Entry searchForEntryDeeply(EntryUID uid, EntryGroup group) {
        Entry entry = group.getEntry(uid);

        //If there is no entry found in this level for the UID, we have to search the child entries
        if (Type.NULL.equals(entry.getType())) {
            Set<Entry> entriesGroup = group.getEntries();

            for (Entry etr : entriesGroup) {
                //if the entry is a group, we have to search
                if (Type.GROUP.equals(etr.getType())) {
                    entry = ((EntryGroup) etr).searchForEntryDeeply(uid);

                    //if the type is not a NULL Type we have the one we have searched for
                    if (!Type.NULL.equals(entry.getType())) {
                        break;
                    }
                }
            }
        }

        return entry;
    }


    @Override
    public Collection<Entry> getAllofType(Type typ) {
        return searchGroupForTypes(typ, this);
    }

    Collection<Entry> searchGroupForTypes(Type typ, EntryGroup group) {
        if (Type.NULL.equals(typ)) {
            throw new IllegalArgumentException("It is not possible to search for NULL entires");
        }

        ArrayList<Entry> ret = new ArrayList<Entry>();

        for (Entry entry : getEntries()) {
            if (typ.equals(entry.getType())) {
                ret.add(entry);
            }

            if (Type.GROUP.equals(entry.getType())) {
                ret.addAll(searchGroupForTypes(typ, (EntryGroup) entry));
            }
        }

        return ret;
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
            inspector.inspect(ctx, this);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    @Override
    public EntryUID getParent() {
        return this.parent;
    }

    @Override
    public EntryGroup getChildren() {
        DefaultEntryGroup copy = new DefaultEntryGroup(uid, entries, auths, parent, tasks, path, displayKey);
        return copy;
    }

    @Override
    public Set<EntryTask> getMappedTasks() {
        return new HashSet<EntryTask>(this.tasks.values());
    }

    @Override
    public boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector) {
        EntryTask task = this.tasks.get(taskid);
        if (task == null) {
            return false;
        }
        
        if (task.controlAuthorizations(inspector, context)) {
            task.execute(context, inspector);
        }
        return false;
    }

    @Override
    public String getDisplayKey() {
        return this.displayKey;
    }

    @Override
    public NavigationPath getNavigationPath() {
        return this.path;
    }


    public static class Builder {
        //Required properties

        private final EntryUID uid;
        private final Map<EntryUID, Entry> entries;
        //optional properties
        private Map<SecurityUID, Authorization> auths;
        private EntryUID parent;
        private Map<EntryUID, EntryTask> tasks;
        private NavigationPath path;
        private String displayKey;

        public Builder(final EntryUID uid, final Map<EntryUID, Entry> entries) {
            this.uid = Objects.requireNonNull(uid);
            this.entries = Objects.requireNonNull(entries);

            this.auths = Collections.emptyMap();
            this.parent = new NullEntry().getUID();
            this.tasks = Collections.emptyMap();
        }
        
        public Builder addRequiredAuthorizations(final Map<SecurityUID, Authorization> auth) {
            this.auths = Objects.requireNonNull(auth);
            return this;
        }
        
        public Builder addParent(final EntryUID parent) {
            this.parent = Objects.requireNonNull(parent);
            return this;
        }
        
        public Builder addTasks(final Map<EntryUID, EntryTask> task) {
            this.tasks = Objects.requireNonNull(task);
            return this;
        }
        
        public Builder addDisplayKey(final String key) {
            this.displayKey = Objects.requireNonNull(key);
            return this;
        }
        
        public Builder addNavigationPath(final NavigationPath pat) {
            this.path = Objects.requireNonNull(pat);
            return this;
        }

        public DefaultEntryGroup build() {

            return new DefaultEntryGroup(uid, entries, auths, parent, tasks, path, displayKey);
        }
    }
}
